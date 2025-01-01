package GradingSystem ;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Vector;

import CentralSystem.CanViewInfoAboutTeachers;
import CourseSystem.Course;
import CourseSystem.CourseType;
import Data.Data;
import Data.School;
import Data.Semester;
import Transcript.Mark;
import Transcript.Transcript;
import UserSystem.Admin;
import UserSystem.User;



public class Student extends User implements CanViewInfoAboutTeachers{

	
	public School school;
	private Admin admin;
	private boolean confirmReg = false;
	int numOfClasses = 0;
	
	private Transcript transcript = new Transcript();

	
	public HashMap<Course, Mark> marks;
	private Vector<Organization> orgs; 
	// changed courses to HasMap
	private HashMap<CourseType,HashMap<Course, Teacher>> courses; //for addCourse()
	
	private Vector<Course> regedCourses;
	private int numOfCredits = 0;
	private int currNumOfCredits = 0;
	private int id;
	
	{
		marks = new HashMap<Course, Mark>();
		orgs = new Vector<Organization>();
		setCourses(new HashMap<CourseType,HashMap<Course, Teacher>>());
		for(CourseType type: CourseType.values()) {
			getCourses().put(type, new HashMap<Course, Teacher>());
		}
	}
	private boolean registered = false;
	
	public int yearOfStudy = 1;
	
	
	
	public Student(){
		super();
	}

//	public Student(String username, String password, String firstname, String lastname, School school){
//		super(username, password, firstname, lastname);
//		this.school = school;
//	}
//	public Student(String username, String password, String firstname, String lastname, School school, int yearOfStudy) throws Exception{
//		super(username, password, firstname, lastname);
//		this.school = school;
//		if(yearOfStudy > 2 || yearOfStudy < 0) throw new Exception("A student of more than 2nd year of study can not be accepted.");
//	}
	public Student(String firstname, String lastname) {
		super(firstname, lastname);
	}

	public Student(String firstname, String lastname, int id) {
		super(firstname, lastname);
		this.id = id;
	}
	public Student(String firstname, String lastname, int id, School school) {
		super(firstname, lastname);
		this.id = id;
		this.school = school;
		
	}
	
	public void viewCourses() {
		System.out.println("Your courses: ");
		for(CourseType type: courses.keySet()) {
			System.out.print(type + ": ");

			for(Course c: courses.get(type).keySet()) {
				System.out.println(c.getName());
			}
		}
	}
	

	
	public void viewMarks() {
		for(Course course: marks.keySet()) {
			System.out.println("Course: " + course.getCipher() + " : " + course.getName() + " : " + marks.get(course).getTotal());
		}
	}
	
	
	
	public void viewTranscript() {
		if(marks.size() < 0) {
			for(Course course: marks.keySet()) {
				if(marks.get(course).finalExam == 0) return;
				String tr = transcript.calculateTranscript(marks.get(course));
				double gpa = transcript.getCorrespGPA().get(tr);
				System.out.println("Course: " + course.getName() + ", credits: " + 
				course.getNumOfCredits() + ", grade: " + marks.get(course).getTotal() + " : " + tr + ", GPA: " + gpa);
			}
		}

	}
	
	public double viewGPA() {
		if(marks.size() > 0) return -1;
		double a = 0;
		for(Course course: marks.keySet()) {
			if(marks.get(course).finalExam == 0) return -1;
			String tr = transcript.calculateTranscript(marks.get(course));
			double cgpa = transcript.getCorrespGPA().get(tr);
			a += cgpa * course.getNumOfCredits();
		}
		double gpa = a / currNumOfCredits;
		return gpa;
	}
	

	
	public void rateTeachers() {
		// TODO implement me	
	}
	

	
	public void registerForCourses(Semester sem, Vector<Course> courses) throws Exception {
		regedCourses = new Vector<Course>();

		int cntMajors = 0;
		int cntElectives = 0;
		int numOfCredits = 0;
		int limitCredits = 21;

		
		for(Course cs: courses) {
			if(Data.courses.containsKey(cs)) {
				if(numOfCredits > limitCredits) {
					regedCourses.clear();
					numOfCredits = 0;
					throw new Exception("You can not able to register for this course since limit of credits is exceeded.");
				}
				if(cntMajors > 4 || cntElectives > 1) {
					regedCourses.clear();
					numOfCredits = 0;

					throw new Exception("You can pick maximum 4 major courses and 1 elective course! Please try again!");
				}	
				for(Course course: Data.courses.keySet()) {
					if(course.equals(cs)) {
						if(course.getSchool().equals(this.school)) {
							if(course.getCourseType().equals(CourseType.MAJOR) && !this.getCourses().get(CourseType.MAJOR).containsKey(course)) cntMajors += 1;
							else if(course.getCourseType().equals(CourseType.MINOR) && !this.getCourses().get(CourseType.MINOR).containsKey(course)) cntElectives += 1;
							else {
								throw new Exception("You can choose course " + course + " only as minor!");
							}
							
						}else {
							if(!this.getCourses().get(CourseType.FREE).containsKey(course)) cntElectives += 1;
							
						}
						regedCourses.add(course);
						numOfCredits += course.getNumOfCredits();
					}
					
					
				}
			}else {
				throw new Exception("You can not choose these courses! Try again!");
			}
				
	
		}
		this.setCurNumOfCredits(numOfCredits);
		registered = true;				
		
	}
	
	public void addCourse(Course course) {
		if(course.getSchool().equals(this.school)) {
			if(course.getCourseType().equals(CourseType.MAJOR)) this.getCourses().get(CourseType.MAJOR).put(course, null);
			else this.getCourses().get(CourseType.MINOR).put(course, null);
		}
		else this.getCourses().get(CourseType.FREE).put(course, null);
	}
	
	public void registerForTeachers(Course course, Teacher teacher, LessonType type) throws Exception {
		if(confirmReg == true && regedCourses.contains(course)) {
			if(numOfClasses > 5) {
				throw new Exception("Classes are exceeded!");
			}

			if(teacher.getTitle().equals(TeacherTitle.tutor) && type.equals(LessonType.lecture)) throw new Exception("Choose another teacher, who conducts lectures!");

			else if(registered) {
				if(teacher.students.get(type) != null && teacher.students.get(type).containsKey(this)) throw new Exception(this + ": You have already choosen " + course + " : " + type + "!" + teacher);
				if(!teacher.students.containsKey(type)) throw new Exception(this + ": Please choose another teacher who conducts " + type + "!");
				
				if(teacher.getCourse().equals(course)) {
					teacher.students.get(type).put(this, new Vector<Integer>());
					if(courses.containsKey(course.getCourseType()))
					courses.get(course.getCourseType()).put(course, teacher);
					if(!teacher.allStudents.contains(this)) {
						teacher.allStudents.add(this);

					}
					numOfClasses += 1;
					regedCourses.remove(course);
				}else throw new Exception(this + ": No matches between the teacher and the course!");
			}
		}
//		
		
		
	}
	
	public void enterOrganization() {
		// TODO implement me	
	}
	

	
	public Vector<Organization> viewOrganizations() {
		// TODO implement me
		return null;	
	}
	

	
	public void signForAttendance(Course c, LessonType ltype) throws Exception {
		if(Data.getCurrWeek() < 16) {
			for(CourseType ctype: courses.keySet()) {
				Teacher teacher = courses.get(ctype).get(c);
				 if(teacher != null && teacher.students != null && teacher.students.containsKey(ltype)) {
			            if (Data.getCurrWeek() > 0) {
			                if (teacher.students.get(ltype).containsKey(this)) {
			                    teacher.students.get(ltype).get(this).add(Data.getCurrWeek());
			                } else {
			                    throw new Exception("You are not the student of this lesson!");
			                }
			            } else {
			                throw new Exception("Invalid week number for signing attendance!");
			            }
			        }
			}
		}
	}

	@Override
	public void viewInfoAboutTeachers() {
		// TODO Auto-generated method stub
		
	}
	

	public Vector<Course> getRegedCourses(){
		return regedCourses;
	}
	
	
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	public boolean getRegistered() {
		return registered;
	}
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = super.hashCode();
//		result = prime * result + Objects.hash(admin, confirmed, courses, getMarks(), numOfClasses, numOfCredits, orgs,
//				regedCourses, registered, school, transcript, yearOfStudy);
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (!super.equals(obj))
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Student other = (Student) obj;
//		return Objects.equals(admin, other.admin) && confirmed == other.confirmed
//				&& Objects.equals(courses, other.courses) && Objects.equals(getMarks(), other.getMarks())
//				&& numOfClasses == other.numOfClasses && numOfCredits == other.numOfCredits
//				&& Objects.equals(orgs, other.orgs) && Objects.equals(regedCourses, other.regedCourses)
//				&& registered == other.registered && school == other.school
//				&& Objects.equals(transcript, other.transcript) && yearOfStudy == other.yearOfStudy;
//	}
//	public void setTranscript(Transcript transcript) {
//		// TODO Auto-generated method stub
//		this.transcript = transcript;
//		
//	}
//	public Transcript getTranscript() {
//		// TODO implement me	
//		return transcript;
//	}
//	@Override
//	public void nextSemester() {
//		// TODO Auto-generated method stub
//		
//	}
	public HashMap<Course, Mark> getMarks() {
		return marks;
	}
	public void setMarks(HashMap<Course, Mark> marks) {
		this.marks = marks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
//	public String toString() {
//		return id + " " + super.lastname + " " + super.firstname;
//	}

	public boolean isConfirmReg() {
		return confirmReg;
	}

	public void setConfirmReg(boolean confirmReg) {
		this.confirmReg = confirmReg;
	}

	public HashMap<CourseType, HashMap<Course, Teacher>> getCourses() {
		return courses;
	}

	public void setCourses(HashMap<CourseType,HashMap<Course, Teacher>> courses) {
		this.courses = courses;
	}

	public int getNumOfCredits() {
		return numOfCredits;
	}

	public void setNumOfCredits(int numOfCredits) {
		this.numOfCredits = numOfCredits;
	}

	public int getCurNumOfCredits() {
		return currNumOfCredits;
	}

	public void setCurNumOfCredits(int curNumOfCredits) {
		this.currNumOfCredits = curNumOfCredits;
	}

	
	
}
