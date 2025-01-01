package CourseSystem;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;

import Data.Data;
import Data.School;
import Data.Semester;
import GradingSystem.LessonType;
import GradingSystem.Student;
import GradingSystem.Teacher;
import GradingSystem.TeacherTitle;
import Manager.ORManager;
import UserSystem.Admin;
import UserSystem.NoSuchUserException;
import UserSystem.User;
import UserSystem.UserData;

public class TesterCourseRegistration {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException {
		Admin a = new Admin();
		User m = new ORManager("Aidana", "Aikyn");
		User t1 = new Teacher("Melisa", "Assanova", TeacherTitle.professor , School.SITE);
		User t2 = new Teacher("Adina", "Zhans", TeacherTitle.tutor, School.SITE);

		User s1 = new Student("Madina", "Zhurgen", 228767, School.SITE);
		User s2 = new Student("Aisulu", "Akylkyzy", 223457, School.BS);
		Vector<User> users = new Vector<User>();
		users.add(m);
		users.add(t1);
		users.add(s1);
		users.add(s2);
		users.add(t2);
		
		for(User u: users) {
			a.registerUser(u);
			
		}
		
		System.out.println("Users: " + UserData.users);
		System.out.println("Teachers: " + Data.teachers);
		
		Vector<Student> students = new Vector<Student>();

		for(User u: users) {
			try {
				System.out.println(u.login());
			} catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException
					| NoSuchUserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}

		Course oop = new Course("OOP", "345d3e", School.SITE, 4);
		Course db = new Course("DB", "5gYhe", School.SITE, 2);
		if(m instanceof ORManager) {
			ORManager m1 = (ORManager) m;
			try {
				m1.addCourse(oop, CourseType.MAJOR);
				m1.addCourse(db, CourseType.MINOR);
				m1.assignCourses();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if(t1 instanceof Teacher && t2 instanceof Teacher) {
			
			System.out.println(t1 + " : "  + ((Teacher) t1).getTitle() + " : " + ((Teacher) t1).getCourse());
			System.out.println(t2 + " : " + ((Teacher) t2).getTitle() + " : " + ((Teacher) t2).getCourse());
			System.out.println("\n");
			
			
			System.out.println(((Teacher) t1).students.keySet());
			System.out.println(((Teacher) t2).students.keySet());
			System.out.println("\n");

		}
		System.out.println(Data.students);
		
		
		
		if(s1 instanceof Student && s2 instanceof Student) {
			Student s01 = (Student) s1;

			Vector<Course> allCourses = new Vector<Course>();
			for(Course c: Data.courses.keySet()) {
				allCourses.add(c);
				System.out.println(c);
			}
			try {
				s01.registerForCourses(Semester.FALL, allCourses);
				((Student) s2).registerForCourses(Semester.FALL, allCourses);

				for(Student s: students) {
					(s).registerForTeachers(db, (Teacher) t1, LessonType.practice);
					((Student) s2).registerForTeachers(db, (Teacher) t1, LessonType.practice);


				}
				((Student) s2).registerForTeachers(db, (Teacher) t1, LessonType.practice);
				((Student) s2).registerForTeachers(db, (Teacher) t1, LessonType.lecture);
				((Student) s2).registerForTeachers(oop, (Teacher) t2, LessonType.practice);
				
				s01.registerForTeachers(db, (Teacher) t2, LessonType.lecture);
				s01.registerForTeachers(oop, (Teacher) t1, LessonType.lecture);
				s01.registerForTeachers(db, (Teacher) t2, LessonType.practice);
				System.out.println("\n");

				System.out.println(s01.getCourses());
				System.out.println(((Student) s2).getCourses());

				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(t1 + " " + ((Teacher) t1).students);
			System.out.println(t2 + " " + ((Teacher) t2).students);

			
		}

		
	}
	

}
