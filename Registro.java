import java.util.*;

    /**
    * Esta es la clase Registro. Se utiliza tanto para la parte de declaración
    * 	de los datos como para los datos mismos.  
    */

 class Registro {

 		private String nombreTabla;
 		private Stack campos;
		
		public Registro(String nombreTabla, Stack campos){
			this.nombreTabla = nombreTabla;
			this.campos = campos;
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
