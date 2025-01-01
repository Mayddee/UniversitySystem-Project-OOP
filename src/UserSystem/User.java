package UserSystem ;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;
import java.util.Vector;

import Data.Data;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class User implements Serializable{

	
	private String username;
	private  String password;
	
	public String firstname;
	public String lastname;
	
	
	
	private Language language = Language.ENG;
	public boolean isLoggedIn;
	public boolean changedPassword = false;
	

	public User(){
		
	}

	public User(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;	
	}
	public User(String firstname, String lastname, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}


	
	public boolean login() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, NoSuchUserException, IOException {
		// TODO implement me
		if(UserData.authenticateUser(this, password)) isLoggedIn = true;
//		write();
		return isLoggedIn;
	}
	
	public void write()throws IOException{
		FileOutputStream fos = new FileOutputStream("users");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
	}
	
	
	public boolean logout() {
		// TODO implement me
		isLoggedIn = false;
		return isLoggedIn;	
	}
	
	public void changePassword(String newPassword) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		UserData.update(this, newPassword);
		this.password = newPassword;
	}
	
	
//	public abstract void nextSemester();
	
	
	public int hashCode() {
		return Objects.hash(username, password);
	}
	
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;
		User u = (User) o;
		return password == u.password && username.equals(u.username);
		
	}
	
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public void setLanguage(Language lang) {
		// TODO implement me	
		language = lang;
	}
	
//	public String getPassword()	{
//		return password;
//	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	public String toString() {
		return "" + username;
	}
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}
}
