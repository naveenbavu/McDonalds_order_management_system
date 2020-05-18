package mc.donalds.exceptions;

public class OrderExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message = "";
	
	public OrderExistsException() {
	}
	
	public OrderExistsException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return getStackTrace().toString();
	}
}
