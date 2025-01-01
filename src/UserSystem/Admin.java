package UserSystem ;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import CentralSystem.Dean;
import Data.Data;
import GradingSystem.LessonType;
import GradingSystem.Student;
import GradingSystem.Teacher;
import Manager.Manager;

//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;



public class Admin {

	
	public Admin(){
		super();
	}
	
	
	
	public void registerUser(User u) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		if (u.getUsername() == null) {
	        String password = generatePassword();
	        String username = createUsername(u.firstname, u.lastname);
	        if(!UserData.users.containsKey(u)) {
				 u.setUsername(username);
			        u.setPassword(password);
					UserData.addUser(u, password);
			}
	        if(u instanceof Teacher) {
				Teacher teacher = (Teacher) u;
				Data.teachers.get(teacher.getSchool()).add(teacher);
			}else if(u instanceof Student) {
				Student student = (Student) u;
				Data.students.add(student);
			}else if(u instanceof Dean){
				Dean dean = (Dean) u;
				Data.deans.put(dean.getSchool(), dean);
			}
			else if(u instanceof Manager){
				Manager manager = (Manager) u;
				Data.managers.add(manager);
			}

	    }
	}
	
	public void removeStudent(Student student) {
		Data.students.remove(student);
	}
	
	  public String generatePassword() {
	      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "!@#$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      Random random = new Random();
	      char[] ps = new char[8];

	      ps[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      ps[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      ps[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      ps[3] = numbers.charAt(random.nextInt(numbers.length()));
	   
	      for(int i = 4; i< 8 ; i++) {
	         ps[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      String password = new String(ps);
	      return password;
	   }
	  
	 public String createUsername(String firstname, String lastname) {
		 String username = "";
		 username += Character.toLowerCase(firstname.charAt(0)) + "_" + Character.toLowerCase(lastname.charAt(0)) + lastname.substring(1) + "@kbtu.kz";
		 return username;
		 
	 }
	 
	  
	 public void removeUser(User user) throws NoSuchUserException {
		 UserData.removeUser(user);
		 if(user instanceof Teacher) {
				Teacher teacher = (Teacher) user;
				Data.teachers.get(teacher.getSchool()).remove(teacher);
//				for(Course course: teacher.getCourses()) {
				for (HashMap<LessonType, Vector<Teacher>> lessonTypeMap : Data.courses.values()) {
		            for (Vector<Teacher> teachers : lessonTypeMap.values()) {
//		                teachers.remove(teacher);
		            	//removes the teacher from vector
		            	teachers.removeAll(Collections.singletonList(teacher));

		            }
		        }
//				}
				teacher = null;
			
			}else if(user instanceof Student) {
				Student student = (Student) user;
				removeStudent(student);
				student = null;
			}else if(user instanceof Manager){
				Manager manager = (Manager) user;
				Data.managers.remove(manager);
				manager = null;
			}
		 
		 user = null;
	 }
	 
	 public void seeLogFiles() throws ClassNotFoundException, IOException {
			System.out.println(read());
	}
	 
	 public User read() throws IOException, ClassNotFoundException{
			FileInputStream fis = new FileInputStream("users");
			ObjectInputStream oin = new ObjectInputStream(fis);
			return (User) oin.readObject();
	}
	
	public void manageUser() {
		// TODO implement me	
	}
	
}
