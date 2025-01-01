package Manager ;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import CentralSystem.CanViewInfoAboutStudents;
import CentralSystem.CanViewInfoAboutTeachers;
import CourseSystem.Course;
import CourseSystem.CourseType;
import Data.Data;
import Data.School;
import GradingSystem.LessonType;
import GradingSystem.Student;
import GradingSystem.Teacher;
import GradingSystem.TeacherTitle;



public class ORManager extends Manager implements CanViewInfoAboutStudents, CanViewInfoAboutTeachers
{

	public ORManager(){
		super();
	}
	public ORManager(String firstname, String lastname) {
		super(firstname, lastname);
	}
	
	public void addCourse(Course course, CourseType type) throws Exception {
		if(type.equals(CourseType.FREE)) throw new Exception("You can add only major or minor courses!");
//		if(!course.getCourseType().equals(type)) course.setCourseType(type);
		if(type.equals(CourseType.MAJOR) && course.getNumOfCredits() < 3) course.setNumOfCredits(3);
		else if(type.equals(CourseType.MINOR)) course.setNumOfCredits(1);
		course.setCourseType(type);
		Data.addCourse(course);
	}
	
	public void removeCourse(Course course) {
		/// remove from all the data
		
	}
	
	public void assignCourses() { // teachers except for senior lecturers have 1 course, seniors 2, tutors don't have lectures
		for(HashMap.Entry<School, Vector<Teacher>> schoolEntry: Data.teachers.entrySet()) {

			for(Teacher teacher: schoolEntry.getValue()) {
				Course c = getRandomCourse(teacher.getSchool());
				teacher.setCourse(c);
				teacher.students.put(LessonType.practice, new HashMap<Student, Vector<Integer>>(chooseRandomNumber(200, 350)));
				
				if(!teacher.getTitle().equals(TeacherTitle.tutor)) {
					teacher.students.put(LessonType.lecture, new HashMap<Student, Vector<Integer>>(chooseRandomNumber(300, 400)));
					
						assignCourseToTeacher(c, teacher, LessonType.lecture);
						assignCourseToTeacher(c, teacher, LessonType.practice);

				}else {
					assignCourseToTeacher(c, teacher, LessonType.practice);
				}
			}
		}
	}
	
	public void assignCourseToTeacher(Course course, Teacher teacher, LessonType type) {
		if(course.getSchool().equals(teacher.getSchool())) {
			if(!teacher.getTitle().equals(TeacherTitle.tutor) ) {
				Data.courses.get(course).get(type).add(teacher);
				teacher.setCourse(course);
				if(type.equals(LessonType.lecture)) {
					teacher.students.put(type, new HashMap<Student, Vector<Integer>>(chooseRandomNumber(300, 400)));				
				}else teacher.students.put(type, new HashMap<Student, Vector<Integer>>(chooseRandomNumber(200, 250)));
				


			}else if(teacher.getTitle().equals(TeacherTitle.tutor) && !type.equals(LessonType.lecture)) {
				Data.courses.get(course).get(type).add(teacher);
				teacher.setCourse(course);
				teacher.students.put(type, new HashMap<Student, Vector<Integer>>(chooseRandomNumber(200, 250)));
				
//				if (teacher.students == null) {
//	                teacher.students = new HashMap<>();
//	            }
//
//	            teacher.students.put(type, new HashMap<>(chooseRandomNumber(200, 250)));
//	        
			}
		}
		
	}
	
	private Course getRandomCourse(School school) {
		 if (Data.courses.size() == 0) {
	            throw new IllegalStateException("No courses found for the specified school: " + school);
	        }
		Vector<Course> schoolCourses = new Vector<Course>();
		for(Course c: Data.courses.keySet()) {
			if(c.getSchool().equals(school)) {
				schoolCourses.add(c);
			}
		}
		Random random = new Random();
        int randomIndex = random.nextInt(schoolCourses.size());

        return schoolCourses.get(randomIndex);
	}

	public int chooseRandomNumber(int a, int b) {
        Random random = new Random();
        // Generate a random boolean to decide between a  and b
        boolean chooseA = random.nextBoolean();

        return chooseA ? a : b;
    }
	
	public void registerStudentToCourses(Student student) {
		if(student.getRegistered() == true && student.getRegedCourses().size() != 0) {
			for(Course rc : student.getRegedCourses()) {
				student.addCourse(rc);
				
			}
			student.setConfirmReg(true);
			student.setNumOfCredits(student.getNumOfCredits() + student.getCurNumOfCredits());
			System.out.println("Student " + student + " registered for courses successfully!");
		}else {
			student.setCurNumOfCredits(0);
			student.setRegistered(false); 
			System.out.println("Fail to register student " + student + " for courses!");
		}
	}

	@Override
	public void viewInfoAboutStudents() {
	
		
	}
	@Override
	public void viewInfoAboutTeachers() {
		// TODO Auto-generated method stub
		
	}
	
}
