package CourseSystem ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import Data.School;
import Data.Semester;



public class Course
{
	
	private School school;

	private CourseType courseType;


	private int numOfCredits;
	private String name;
	private String cipher;
	public Semester sem;
	
	
	public Course(){
		super();
	}
	public Course(String name){
		this.setName(name);
	}
	public Course(String name, String cipher){
		this.setName(name);
		this.setCipher(cipher);
	}
	public Course(String name, String cipher, int numOfCredits){
		this(name, cipher);
		try {
            if (numOfCredits < 0) {
                throw new IllegalArgumentException("Number of Credits must be non-negative.");
            }
    		this.setNumOfCredits(numOfCredits);
        } catch (IllegalArgumentException e) {
            // Обработка исключения или возможно перевыброс исключения
            e.printStackTrace();
        }
	}
	public Course(String name, String cipher, School school, int numOfCredits){
		this(name, cipher, numOfCredits);
		this.school = school;
		
	}
	

	public String toString() {
		// TODO implement me
		return school + ", " + cipher + ", " + name + ", " + numOfCredits;	
	}
	

	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public CourseType getCourseType() {
		return courseType;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	public int getNumOfCredits() {
		return numOfCredits;
	}
	public void setNumOfCredits(int numOfCredits) {
		this.numOfCredits = numOfCredits;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCipher() {
		return cipher;
	}
	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

	
}