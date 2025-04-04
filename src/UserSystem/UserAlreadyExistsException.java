package UserSystem;

public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String toString() {
    	return super.getMessage();
    }

}
