import java.util.*;

    /**
    * Esta es la clase Descripcion. Se utiliza tanto para la parte de declaración
    * 	de los datos como para los datos mismos.  
    */

class Descripcion {

		private String nombreTabla;
		private int numDeCampos;
		private Stack campos;
		
		public Descripcion (String nombreTabla, int numDeCampos, Stack campos) {
			this.nombreTabla = nombreTabla;
			this.numDeCampos = numDeCampos;
			this.campos = campos;
		}

		public int getNumDeCampos() {
			return numDeCampos;
		}
		public void setTipo(int numDeCampos) {
			this.numDeCampos = numDeCampos;
		}

		public String getNombreTabla() {
			return nombreTabla;
		}
		public void setNombreTabla(String nombreTabla) {
			this.nombreTabla = nombreTabla;
		}

		public Stack getCampos() {
			return campos;
		}
		public void setCampos(Stack campos) {
			this.campos = campos;
		}
		
		public void comprobarNumeroDeCampos(){
			if(!(this.numDeCampos == this.campos.size())) {
				System.out.print("Error semántico: El valor \"number of fields\" ");
				System.out.println("no coincide con los campos declarados. ");
			}
		}

		public Boolean hayCamposRepetidos(Stack campos){
			if(campos.empty()) return false;
				else if (buscarNombre(((Campo)campos.pop()).getNombre(), (Stack)campos.clone())) return true;
					else return hayCamposRepetidos(campos);
		}

		public static Boolean buscarNombre(String nombre, Stack campos){
			if(campos.empty()) return false;
			else if(((Campo)campos.pop()).getNombre().equals(nombre)) {return true;}
				else return buscarNombre(nombre,campos);
		}

		public Boolean hayNecesarios(Stack campos){
			if(campos.empty()) return false;
			else if (((Campo)campos.pop()).getNecesario()) return true;
				else return hayNecesarios(campos);
		}

		public void comprobarNombreTabla(String nombre, Stack registros) {
			Registro r;
			while (!registros.empty()) {
				r = (Registro) registros.pop();
				if(!r.getNombreTabla().equals(nombre)){
					System.out.print("Error semántico: el registro de la línea ");
					System.out.print(r.getLinea() + " no tiene el mismo ");
					System.out.println("nombre que la tabla.");
				}
			}
		}

		public static Boolean valorDuplicado(Campo campo, Stack resto){
			if(resto.empty()) return false;
			else {
				Campo cabeza = (Campo)resto.pop();
				if(cabeza.getNombre().equals(campo.getNombre()))
				{
					if(cabeza.getValor().equals(campo.getValor()))
					{
						return valorDuplicado(campo,resto);
					}else{
						return true;
					}	
				}else{
					return valorDuplicado(campo,resto);
				}
			}
		}


		public void comprobarCampos(Stack descripcion, Stack registros, String opcion){
			Registro r;
			if(opcion.equals("nombre"))
			{
				while (!registros.empty()) {
					r = (Registro)registros.pop();
					if(!nombresIguales((Stack)descripcion.clone(),r.getCampos())){
						System.out.print("Error semántico: los campos especificados ");
						System.out.print("en el registro de la línea "+ r.getLinea());
						System.out.println(" no coinciden con los descritos.");

					}
				}
			}else if(opcion.equals("tipo")){
				while (!registros.empty()) {
					r = (Registro)registros.pop();
					if(!tiposIguales((Stack)descripcion.clone(),r.getCampos())){
						System.out.print("Error semántico: los campos especificados ");
						System.out.print("en el registro de la línea "+ r.getLinea());
						System.out.println(" no coinciden con los descritos.");

					}
				}
			}
		}


		public Boolean tiposIguales(Stack descripcion, Stack campos) {
			if(descripcion.empty() && campos.empty()) return true;
			else if(!(descripcion.size() == campos.size())) return false;
			else if(((Campo)descripcion.pop()).getTipo().equals(((Campo)campos.pop()).getTipo())) 
					return nombresIguales(descripcion,campos);
			else return false;
		}


		public Boolean nombresIguales(Stack descripcion, Stack campos) {
			if(descripcion.empty() && campos.empty()) return true;
			else if(!(descripcion.size() == campos.size())) return false;
			else if(((Campo)descripcion.pop()).getNombre().equals(((Campo)campos.pop()).getNombre())) 
					return nombresIguales(descripcion,campos);
			else return false;
		}
}