package UserSystem;

public class NoSuchUserException extends Exception{
	public NoSuchUserException() {
        super();
    }

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String toString() {
    	return super.getMessage();
    }
}
