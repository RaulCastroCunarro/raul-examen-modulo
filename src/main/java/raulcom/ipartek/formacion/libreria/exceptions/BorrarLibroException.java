package raulcom.ipartek.formacion.libreria.exceptions;

public class BorrarLibroException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private final String mensaje;
    public BorrarLibroException() {
        super();
        this.mensaje = "El libro que se intenta eliminar no existe.";
    }
    
    public String getMensaje() {
        return this.mensaje;
    }
}
