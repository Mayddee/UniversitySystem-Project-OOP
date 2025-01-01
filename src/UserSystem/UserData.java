package UserSystem;



import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;



public  class UserData {
	public static UserData INSTANCE;
	public static HashMap<User, byte[]> users = new HashMap<User, byte[]>();

//	public static HashMap<String, byte[]> users = new HashMap<String, byte[]>();
	PasswordHash psh = new PasswordHash();
	
	static {
		INSTANCE = new UserData();
	}
//	
	public UserData() {
		
	}
	public static void addUser(User user, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		users.put(user, PasswordHash.hash(password));
	}
	
	public static void removeUser(User user) throws NoSuchUserException{
		if(users.containsKey(user)) users.remove(user);
		else throw new NoSuchUserException("No such user!");
	}
	
	public static boolean authenticateUser(User user, String password) throws NoSuchUserException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		if (users.containsKey(user)) {
            byte[] hashedPasswordStored = users.get(user);
            byte[] hashedPasswordInput = PasswordHash.hash(password);

            // Use Arrays.equals to compare the content of the byte arrays
            if (Arrays.equals(hashedPasswordStored, hashedPasswordInput)) {
                return true;
            }
        } else {
            throw new NoSuchUserException("No such user! Your username or password is not correct!");
        }
        return false;
	}
	
	public static void update(User user, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		users.put(user, PasswordHash.hash(password));
	}
	

	public static UserData getInstance() {
		return INSTANCE;
	}

}


	


