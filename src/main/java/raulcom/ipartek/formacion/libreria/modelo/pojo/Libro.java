package raulcom.ipartek.formacion.libreria.modelo.pojo;

public class Libro {
	
		
		public static final int DESCUENTO_MIN = 0;
		public static final int DESCUENTO_MAX = 100;
		
		private int id;
		private String nombre;
		private String autor;
		private float precio;
		private String imagen;
		private int descuento;
		
		
		
		public Libro() {
			super();
			this.id = 0;
			this.nombre = "";
			this.autor = "Anónimo";
			this.precio = 0;
			this.imagen = "https://www.faq-mac.com/wp-content/uploads/2017/06/book-bookmark-icon-libro.jpg";
			this.descuento = DESCUENTO_MIN;
		}

		public Libro(int id, String nombre, String autor, float precio, String imagen, int descuento) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.autor = autor;
			this.precio = precio;
			this.imagen = imagen;
			this.descuento = descuento;
		}
		
		public Libro(String nombre, String autor,  float precio, String imagen, int descuento) {
			super();
			this.id = 0;
			this.nombre = nombre;
			this.autor = autor;
			this.precio = precio;
			this.imagen = imagen;
			this.descuento = descuento;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getAutor() {
			return autor;
		}

		public void setAutor(String nombre) {
			this.nombre = autor;
		}
		
		public float getPrecio() {
			return precio;
		}

		public void setPrecio(float precio) {
			this.precio = precio;
		}

		public String getImagen() {
			return imagen;
		}

		public void setImagen(String imagen) {
			this.imagen = imagen;
		}

		public int getDescuento() {
			return descuento;
		}

		public void setDescuento(int descuento) {
			this.descuento = descuento;
		}
		
		public float getPrecioDescuento() {
			/*DecimalFormatSymbols separador = new DecimalFormatSymbols();
			separador.setDecimalSeparator('.');
			DecimalFormat formato1 = new DecimalFormat("#.00",separador);*/
			
			float resultado = (this.precio*(100 - this.descuento))/100;
			
			return resultado;
		}

		@Override
		public String toString() {
			return "Producto [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", precio=" + precio + ", imagen=" + imagen
					+ ", descuento=" + descuento + "]";
		}
		
		
}
