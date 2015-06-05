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

}