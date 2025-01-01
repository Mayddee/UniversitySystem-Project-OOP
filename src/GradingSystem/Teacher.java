package GradingSystem ;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import CentralSystem.Employee;
import CentralSystem.Dean;

import CourseSystem.Course;
import Data.Data;
import Data.School;


public class Teacher extends Employee{

	
	public TeacherTitle title;
	private Course course;
	public HashMap<LessonType, HashMap<Student, Vector<Integer>>> students;
	public Vector<Student> allStudents;
	private School school;
	boolean closedAtt = false;
	{
		students = new HashMap<LessonType, HashMap<Student, Vector<Integer>>>();
		
		allStudents = new Vector<Student>();			
	}
	

	public Teacher(){
		super();
	}
	public Teacher(String firstname, String lastname) {
		super(firstname, lastname);
	}
	public Teacher(String firstname, String lastname, School school) {
		super(firstname, lastname);
		this.school = school;
	}
	public Teacher(String firstname, String lastname, TeacherTitle title, School school) {
		super(firstname, lastname);
		this.title = title;
		this.school = school;
	}


	
	public void viewCourses() {
//		System.out.println("List of assigned courses: ");
//		for(Course course: courses) {
//			System.out.println(course);
//		}
	}
	

	
	public void manageCourse() {
		// TODO implement me	
	}
	

	
	public Vector<Student> viewStudents() {
		// TODO implement me
		return null;	
	}
	
	public void sendComplaint(Student s, String complaint, UrgencyLevel level) {
		if(allStudents.contains(s)) {
			if(Data.deans.containsKey(school)) {
				Data.deans.get(school).addComplaint(s, complaint, level);
			}else System.out.println("Wait for a new dean be assigned!");
		}else System.out.println("You have no such student!");
	}
	

	
	public boolean studentSignedForAttendance(Student s, int week, LessonType ltype) throws Exception {
		if(!students.containsKey(ltype)) throw new Exception("You do not conduct " + ltype + "!"); 
		if(!students.get(ltype).containsKey(s)) throw new Exception("You do not have student " + s.firstname + " " + s.lastname + "!"); 
		if(students.get(ltype).get(s).contains(week)) return true;	
		return false;
	}
	
	
	public void putMarksForWeek(Student s, double points) throws Exception {
		if(students.containsKey(LessonType.practice)) {
			if(students.get(LessonType.practice).containsKey(s)) {
				if(Data.getCurrWeek() <= 15 && studentSignedForAttendance(s, Data.getCurrWeek(), LessonType.practice)) {
					s.marks.get(course).setTotal(s.marks.get(course).getTotal() + points);
					if(Data.getCurrWeek() < 9) {
						s.marks.get(course).att1 += points;
						if(s.marks.get(course).att1 > 30) {
							double bonus = s.marks.get(course).att1 - 30;
							s.marks.get(course).att1 = 30;
							s.marks.get(course).att2 += bonus;
						}
					}else{
						s.marks.get(course).att2 += points;
						if(s.marks.get(course).att1 + s.marks.get(course).att2 > 60) {
							s.marks.get(course).att2 = 60 - s.marks.get(course).att1;
						}
					}
				}else throw new Exception("You can not put mark!");
			}
		}
		
		

	}
	
	public void putMarksForTerm(Student s, double mark, boolean putNewAtt) throws Exception {
		if(students.containsKey(LessonType.practice)) {
			if(students.get(LessonType.practice).containsKey(s)) {
				if(Data.getCurrWeek() < 9) {
					if(s.marks.get(course).getTotal() > 0 && !putNewAtt) {
						s.marks.get(course).att1 = s.marks.get(course).getTotal();
//						s.marks.get(course).setTotal(0);
						if(s.marks.get(course).att1 > 30) {
							double bonus = s.marks.get(course).att1 - 30;
							s.marks.get(course).att1 = 30;
							s.marks.get(course).setTotal(s.marks.get(course).getTotal() + bonus);
						}
					}else if(putNewAtt && mark <= 30) {
						s.marks.get(course).setTotal(mark);

						s.marks.get(course).att1 = mark;
					}else throw new Exception("No appropriate mark for 1 attestation!");
				}else if(Data.getCurrWeek() < 16){
					if(s.marks.get(course).att1 > 0) {
						if(s.marks.get(course).getTotal() > 0 && !putNewAtt){
							s.marks.get(course).att2 = s.marks.get(course).getTotal() - s.marks.get(course).att1;
//							s.marks.get(course).setTotal(0);
							if(s.marks.get(course).att1 + s.marks.get(course).att2 > 60) {
								s.marks.get(course).att2 = 60 - s.marks.get(course).att1;
								s.marks.get(course).setTotal(60);

							}
						}else if(putNewAtt && s.marks.get(course).att1 + mark < 60) {
							s.marks.get(course).att2 = mark;
						}else throw new Exception("No appropriate mark for 2 attestation!");
						
					}else throw new Exception("You did not put 1 attestation!");
				}else {
					if(s.marks.get(course).finalExam == 0 && putNewAtt && mark <= 40) {
						s.marks.get(course).finalExam = mark;
						s.marks.get(course).setTotal(s.marks.get(course).getTotal() + mark);

					}else throw new Exception("You can not putt mark for final exam!");
				}
			}
		}
	}
	

	
	public void setTitle(TeacherTitle title) {
		this.title = title;
	}
	public TeacherTitle getTitle() {
		return title;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public School getSchool() {
		return school;
	}

    public Course getCourse() {
    	return course;
    }
    public void setCourse(Course course) {
    	this.course = course;
    }
    

	
}
	



