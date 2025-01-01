package GradingSystem;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
//import java.security.InvalidKeySpecException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import CourseSystem.Course;
import CourseSystem.CourseType;
import Data.Data;
import Data.School;
import Data.Semester;
import GradingSystem.Student;
import Manager.ORManager;
import UserSystem.Admin;
import UserSystem.NoSuchUserException;
import UserSystem.User;
import UserSystem.UserData;

public class MainTester {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException {
        Admin a = new Admin();
        User m = new ORManager("Aidana", "Aikyn");
        
        User t1 = new Teacher("Melisa", "Assanova", TeacherTitle.professor, School.SITE);
        User t2 = new Teacher("Adina", "Zhans", TeacherTitle.tutor, School.SITE);
        User t5 = new Teacher("Assel", "Saduakasova", TeacherTitle.seniorLecturer, School.SITE);
        User t6 = new Teacher("Dimash", "Kudaibergen", TeacherTitle.lecturer, School.BS);

        User s1 = new Student("Madina", "Zhurgen", 228767, School.SITE);
        User s2 = new Student("Aisulu", "Akylkyzy", 223457, School.SITE);
        User s5 = new Student("David", "Miller", 333333, School.SITE);
        User s6 = new Student("Kamila", "Sakenova", 444444, School.SITE);

        Vector<User> users = new Vector<>();
        users.add(m);
        users.add(t1);
        users.add(s1);
        users.add(s2);
        users.add(t2);
        users.add(t5);
        users.add(t6);
        users.add(s5);
        users.add(s6);

        for (User u : users) {
            a.registerUser(u);
        }

        System.out.println("Users: " + UserData.users);
        System.out.println("Teachers: " + Data.teachers);

    	Vector<Student> students = new Vector<Student>();

        
        for (User u : users) {
            try {
                System.out.println(u.login());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException | NoSuchUserException e) {
                e.printStackTrace();
            }
			if(u instanceof Student) {
				students.add((Student) u);
			}
        }

        Course oop = new Course("OOP", "345d3e", School.SITE, 4);
        Course db = new Course("DB", "5gYhe", School.SITE, 2);
        Course ai = new Course("AI", "9zXy5", School.SITE, 3);
        Course ibm = new Course("IBM", "2sNt8", School.BS, 4);

        if (m instanceof ORManager) {
            ORManager m1 = (ORManager) m;
            try {
                m1.addCourse(oop, CourseType.MAJOR);
                m1.addCourse(db, CourseType.MAJOR);
//                m1.assignCourses();
                m1.addCourse(ai, CourseType.MAJOR);
                m1.addCourse(ibm, CourseType.MINOR);
//                m1.assignCourses();

                m1.assignCourseToTeacher(oop, (Teacher) t1, LessonType.lecture);
                m1.assignCourseToTeacher(db, (Teacher) t5, LessonType.practice);
                m1.assignCourseToTeacher(oop, (Teacher) t1, LessonType.practice);
                m1.assignCourseToTeacher(db, (Teacher) t5, LessonType.lecture);
                m1.assignCourseToTeacher(ai, (Teacher) t2, LessonType.practice);
                m1.assignCourseToTeacher(ibm, (Teacher) t6, LessonType.practice);
                m1.assignCourseToTeacher(ibm, (Teacher) t6, LessonType.lecture);


                System.out.println("Courses assigned to teachers successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Teachers: " + Data.teachers);
        System.out.println("Students: " + Data.students);
        
        if(t1 instanceof Teacher && t2 instanceof Teacher) {
			
			System.out.println(t1 + " : "  + ((Teacher) t1).getTitle() + " : " + ((Teacher) t1).getCourse());
			System.out.println(t2 + " : " + ((Teacher) t2).getTitle() + " : " + ((Teacher) t2).getCourse());
			System.out.println(t5 + " : " + ((Teacher) t5).getTitle() + " : " + ((Teacher) t5).getCourse());
			System.out.println(t6 + " : " + ((Teacher) t6).getTitle() + " : " + ((Teacher) t6).getCourse());


			System.out.println("\n");
			
			System.out.println(t1 + " " + ((Teacher) t1).students);
			System.out.println(t2 + " " + ((Teacher) t2).students);
			System.out.println(t5 + " " + ((Teacher) t5).students);
			System.out.println(t6 + " " + ((Teacher) t6).students);
			
//			System.out.println(((Teacher) t1).students.keySet());
//			System.out.println(((Teacher) t2).students.keySet());
			System.out.println("\n");

		}
     

		System.out.println(Data.students);
		
		
		
		if(s1 instanceof Student && s2 instanceof Student && s5 instanceof Student && s6 instanceof Student) {
			Student s01 = (Student) s1;
			Student s05 = (Student) s5;
			Student s06 = (Student) s6;

			Vector<Course> allCourses = new Vector<Course>();
			for(Course c: Data.courses.keySet()) {
				allCourses.add(c);
				System.out.println(c);
			}
			try {


				s01.registerForCourses(Semester.FALL, allCourses);
				((Student) s2).registerForCourses(Semester.FALL, allCourses);
				s05.registerForCourses(Semester.FALL, allCourses);
				s06.registerForCourses(Semester.FALL, allCourses);
				System.out.println("Number Of credits: " + s01.getNumOfCredits());


				for(Student s: Data.students) {
					((ORManager) m).registerStudentToCourses(s);


				}
				
				for(Student s: students) {
					s.registerForTeachers(oop, (Teacher) t1, LessonType.lecture);
					s.registerForTeachers(db, (Teacher) t5, LessonType.practice);
					s.registerForTeachers(oop, (Teacher) t1, LessonType.practice);
					s.registerForTeachers(db, (Teacher) t5, LessonType.lecture);
					s.registerForTeachers(ai, (Teacher) t2, LessonType.practice);
					s.registerForTeachers(ibm, (Teacher) t6, LessonType.practice);
					s.registerForTeachers(ibm, (Teacher) t6, LessonType.lecture);


				}
				
				System.out.println("\n");

				System.out.println(s01.getCourses());
				System.out.println(((Student) s2).getCourses());

				System.out.println("\n");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(t1 + " " + ((Teacher) t1).students);
			System.out.println(t2 + " " + ((Teacher) t2).students);
			System.out.println(t5 + " " + ((Teacher) t5).students);
			System.out.println(t6 + " " + ((Teacher) t6).students);	
			System.out.println("\n");	

		
			try {
				for(int i = 0; i < 16; i++) {
					Data.nextWeek();
					System.out.println(Data.getCurrWeek());
					s01.signForAttendance(ibm, LessonType.practice);
					
					System.out.println(((Teacher) t6).students.get(LessonType.practice).get(s01));
					System.out.println(((Teacher) t6).studentSignedForAttendance(s01, Data.getCurrWeek(), LessonType.practice));


					if(Data.getCurrWeek() == 8 || Data.getCurrWeek() == 15) {
						((Teacher) t6).putMarksForWeek(s06, 13);
						((Teacher) t6).putMarksForTerm(s06, 0, false);
					}else if(Data.getCurrWeek() < 16) {
						((Teacher) t6).putMarksForWeek(s06, 2.5);
					}else if(Data.getCurrWeek() == 16) {
						((Teacher) t6).putMarksForTerm(s06, 39, true);
					}
					System.out.println(s01.marks.get(ibm).getTotal());
//					s01.signForAttendance(oop, LessonType.lecture);
				}
				
//				System.out.println(
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}


    }
}
