import java.util.*;

    /**
    * Esta es la clase Campo. Se utiliza tanto para la parte de declaración
    * 	de los datos como para los datos mismos.  
    */

 class Campo {

		private String nombre;
		private String tipo;
		private Boolean necesario;
		private Object valor;
		
		public Campo(){	}

		public Object getValor() {
			return valor;
		}

		public void setValor(Object valor) {
			this.valor = valor;
		}

		public Boolean getNecesario() {
			return necesario;
		}

		public void setNecesario(Boolean necesario) {
			this.necesario = necesario;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		@Override
		public String toString() {
			return "Campo [nombre=" + this.nombre + "]";
		}
	
	}
