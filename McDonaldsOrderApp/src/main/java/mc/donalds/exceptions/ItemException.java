package mc.donalds.exceptions;

public class ItemException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message = "";
	
	public ItemException() {
	}
	
	public ItemException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
