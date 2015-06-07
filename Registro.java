import java.util.*;

    /**
    * Esta es la clase Registro. Se utiliza tanto para la parte de declaración
    * 	de los datos como para los datos mismos.  
    */

 class Registro {

 		private String nombreTabla;
 		private Stack campos;
 		private int linea;
		
		public Registro(String nombreTabla, Stack campos, int linea){
			this.nombreTabla = nombreTabla;
			this.campos = campos;
			this.linea = linea;
		}

		public Registro(){

		}




		public int getLinea() {
			return linea;
		}

		public void setLinea(int linea) {
			this.linea = linea;
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

	}
