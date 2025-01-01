package UserSystem;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;

public class UserTester {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, NoSuchUserException, IOException, ClassNotFoundException {
		
		Admin a = new Admin();
//		User u1 = new User("madina", "87dghj");
//		User u2 = new User("madtftina", "oijuy");
//		User u3 = new User("rftgyhuj", "9876");
//		a.registerUser(u1);
//		a.registerUser(u2);
//		a.registerUser(u3);
//		
//		u1.login();
		a.seeLogFiles();
		
		
//		FileInputStream fis;
//		Vector<User> users = new Vector<User>();
//		try {
//			fis = new FileInputStream("users");
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			while (true) {
//                try {
//                    User u = (User) ois.readObject();
//                    users.add(u);
//                } catch (EOFException e) {
//                    break; // End of file reached
//                }
//            }
//			System.out.println(users);
//			ois.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
}
