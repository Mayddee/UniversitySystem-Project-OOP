package UserSystem;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JPasswordField;

public class PasswordHash {
	
	
//	public PasswordHash() {
		
//	}
//	public PasswordHash() {
//		String ps = new String(password.getPassword());
//	}
//	public PasswordHash(JPasswordField passwordField) {
//        this.password = passwordField;
//        String ps = new String(password.getPassword());
//        // Rest of your logic
//    }
	private static byte[] salt;
	static {
		
			try {
				salt = getSalt();
			} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException      
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");

        // Create array for salt
        byte[] salt = new byte[16];

        // Get a random salt
        sr.nextBytes(salt);

        // return salt
        return salt;
    }
	
	public static byte[] hash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {

		 char[] passwordChars = password.toCharArray();

		    // Use the stored salt
		    KeySpec spec = new PBEKeySpec(passwordChars, salt, 65536, 128);

		    // Use the same algorithm and provider
//		    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1", "SUN");
		    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1", "SunJCE");


		    // Return the hash
		    return factory.generateSecret(spec).getEncoded();

	}

	
}
