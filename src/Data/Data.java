package Data ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;
import java.util.Vector;

import CentralSystem.Dean;
import CentralSystem.News;
import CourseSystem.Course;
import CourseSystem.CourseType;
import GradingSystem.LessonType;
import GradingSystem.Student;
import GradingSystem.Teacher;
import Manager.Manager;
import ResearchPapers.ResearchPaper;



public class Data implements Serializable{
	public static Data INSTANCE;
	public static int cntYear = 0;
	private static int currWeek = 0;
	

	
	public static Vector<Student> students;
	public static HashMap<School, Vector<ResearchPaper>> papers;
	public static Vector<Manager> managers;
	public static HashMap<Course, HashMap<LessonType, Vector<Teacher>>> courses;

	public static HashMap<School, Vector<Teacher>> teachers;
	public static HashMap<School, Dean> deans;
	
	public static final Vector<CourseType> types;
	public static Semester semester = Semester.FALL;
	static {
		if(new File("data").exists()) {
			try {
				INSTANCE = read();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else INSTANCE = new Data();
		
		managers = new Vector<Manager>();
		courses = new HashMap<Course, HashMap<LessonType, Vector<Teacher>>>();
//		allCourses = new HashMap<School, HashMap<CourseType, HashSet<Course>>>();
		students = new Vector<Student>();
		teachers = new HashMap<School, Vector<Teacher>>();
		types = new Vector<CourseType>();
		
		papers = new HashMap<School, Vector<ResearchPaper>>();
		deans = new HashMap<School, Dean>();

//		}
		for(School school: School.values()) {
			teachers.put(school, new Vector<>());
		}

	}
	
	public Data(){
		super();
	}
	
	public static Data read() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("data");
		ObjectInputStream oin = new ObjectInputStream(fis);
		return (Data) oin.readObject();
	}
	public static void write()throws IOException{
		FileOutputStream fos = new FileOutputStream("data");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(INSTANCE);
		oos.close();
	}
	
	public static void addCourse(Course c) {

		courses.put(c, new HashMap<LessonType, Vector<Teacher>>());
		for(LessonType type: LessonType.values()) {
			courses.get(c).put(type, new Vector<Teacher>());
		}
	
	}
	public static void nextWeek() throws Exception {
		String newsText;
		if (currWeek < 17) {
	        currWeek = currWeek + 1;
	        newsText = "This is " + getCurrWeek() + " week!";
	        if(getCurrWeek() == 16 || getCurrWeek() == 17) {
	        	newsText += "This is the final week! Congratulations on reaching the end of the semester!";
	        }
	    } else {
	        currWeek = 0;
	        newsText = "Semester has not started yet!";
	        nextSemester();
	    }

	

	    News.addNews(newsText);
	}
	
	public static void nextSemester() {// calls next semester in user
		if(semester.equals(Semester.FALL)) {
			semester = Semester.SPRING;
		}
		else {
			semester = Semester.FALL;
			cntYear += 1;
		}

	}

	public static int getCurrWeek() {
		return currWeek;
	}

	public static void setCurrWeek(int currWeek) {
		Data.currWeek = currWeek;
	}
	


}

