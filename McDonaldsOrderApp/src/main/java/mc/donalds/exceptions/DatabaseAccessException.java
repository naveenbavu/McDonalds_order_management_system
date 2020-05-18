package mc.donalds.exceptions;

public class DatabaseAccessException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private final String message = "No permission for this action";
	
	public String getMessage() {
		return message;
	}
}
