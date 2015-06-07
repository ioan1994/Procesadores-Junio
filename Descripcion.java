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
			Registro r = new Registro();
			int linea = 0;
			if(opcion.equals("nombre"))
			{
				while (!registros.empty()) {
					r = (Registro)registros.pop();
					linea = nombresIguales((Stack)descripcion.clone(),r.getCampos());
				}
			}else if(opcion.equals("tipo")){
				while (!registros.empty()) {
					r = (Registro)registros.pop();
					linea = tiposIguales((Stack)descripcion.clone(),(Stack)r.getCampos().clone());
				}
			} else if(opcion.equals("necesario")){
				while (!registros.empty()) {
					r = (Registro)registros.pop();
					linea = comprobarNecesarios((Stack)descripcion.clone(),(Stack)r.getCampos().clone());

				}
			}

			if(linea == -1)
			{
				System.out.print("Error semántico: los campos especificados ");
				System.out.print("en el registro de la línea "+ r.getLinea());
				System.out.println(" no coinciden con los descritos.");
			}

		}

/*
		public Boolean tiposIguales(Stack descripcion, Stack campos) {
			if(descripcion.empty() && campos.empty()) return true;
			else if(!(descripcion.size() == campos.size())) return false;
			else if(((Campo)descripcion.pop()).getTipo().equals(((Campo)campos.pop()).getTipo())) 
					return tiposIguales(descripcion,campos);
			else return false;
		}
*/
		public int tiposIguales(Stack descripcion, Stack campos) {
			if(descripcion.empty() && campos.empty()) return 0;
			else if(!(descripcion.size() == campos.size())) {return -1;}
			else if(((Campo)campos.peek()).getTipo().equals("none")) {
				descripcion.pop();
				campos.pop();
				return tiposIguales(descripcion,campos);
			} else if(((Campo)descripcion.peek()).getTipo().equals(((Campo)campos.peek()).getTipo())){ 
					descripcion.pop();
					campos.pop();
					return tiposIguales(descripcion,campos);
				}
			else {
				System.out.print("Error semántico: el tipo del campo especificado ");
				System.out.print("en la línea "+ ((Campo)campos.peek()).getLinea());
				System.out.println(" no coincide con el descrito.");
				descripcion.pop();
				campos.pop();
				return tiposIguales(descripcion,campos);
			}
		}



		public int nombresIguales(Stack descripcion, Stack campos) {
			if(descripcion.empty() && campos.empty()) return 0;
			else if(!(descripcion.size() == campos.size())) return -1;
			else if(((Campo)descripcion.peek()).getNombre().equals(((Campo)campos.peek()).getNombre())) {
				descripcion.pop();
				campos.pop();
				return nombresIguales(descripcion,campos);
			}
			else {
				System.out.print("Error semántico: el nombre del campo especificado ");
				System.out.print("en la línea "+ ((Campo)campos.peek()).getLinea());
				System.out.println(" no coincide con el descrito.");
				descripcion.pop();
				campos.pop();
				return nombresIguales(descripcion,campos);
			}
		}


		public int comprobarNecesarios(Stack descripcion, Stack campos){
			if(descripcion.empty() && campos.empty()) return 0;
			else if(!(descripcion.size() == campos.size())) return -1;
			else if(((Campo)descripcion.peek()).getNecesario()) {
				if(((Campo)campos.peek()).getValor().equals("none")){
					System.out.print("Error semántico: el campo especificado ");
					System.out.print("en la línea "+ ((Campo)campos.peek()).getLinea());
					System.out.println(" es necesario inicializarlo.");
					descripcion.pop();
					campos.pop();
					return comprobarNecesarios(descripcion,campos);
				} else {
					descripcion.pop();
					campos.pop();
					return comprobarNecesarios(descripcion,campos);
				}
			}	
			else {
				descripcion.pop();
				campos.pop();
				return comprobarNecesarios(descripcion,campos);
			}
		}
}