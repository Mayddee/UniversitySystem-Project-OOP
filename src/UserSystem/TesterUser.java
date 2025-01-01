package UserSystem;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import GradingSystem.Student;

public class TesterUser {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		
//		UserData.users.clear();

		Admin a = new Admin();
//		System.out.println(a.createUsername("Madina", "Amangeldi"));
		User user = new Student("Aizhan", "Saduakas");
		
		a.registerUser(user);
//		System.out.println(user);
		System.out.println(UserData.users);
//		String ps = a.generatePassword();
//		System.out.println(ps);
//		System.out.println(PasswordHash.hash(ps));
//		UserData.hash(a.generateUser());
	}

}
