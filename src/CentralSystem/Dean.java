package CentralSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import Data.School;
import GradingSystem.Student;
import GradingSystem.UrgencyLevel;
import UserSystem.Admin;



public class Dean extends Employee implements Serializable{
	private School school; 
	
	private HashMap<Student, HashMap<String, UrgencyLevel>> complaints;
	{
		complaints = new HashMap<Student, HashMap<String, UrgencyLevel>>();
	}
	public Dean() {
		
	}
	public Dean(String firstname, String lastname) {
		super(firstname, lastname);
	}
	public Dean(String firstname, String lastname, School school) {
		super(firstname, lastname);
		this.setSchool(school);
	}
	
	 public void checkComplaints() {
		 if(complaints == null) return;
		 for(Student s: complaints.keySet()) {
			 if(!s.school.equals(school)) {
				 complaints.remove(s);
			 }
			 if(complaints.get(s).size() >= 3) {
				 removeStudent(s);
	        	System.out.println("Student is kicked out from University!");
	  
			 }
			 for(String l: complaints.get(s).keySet()) {
				 if(complaints.get(s).get(l).equals(UrgencyLevel.HIGH)) {
					 removeStudent(s);
			         System.out.println("Student is kicked out from University!");
				 }
			 }
		 }
		
	     
	    }
	 public void addComplaint(Student s, String complaint, UrgencyLevel level) {
		 if(!complaints.containsKey(s)) complaints.put(s, new HashMap<>());
		 complaints.get(s).put(complaint, level);
	 }
	 
	 public void removeStudent(Student s) {
		 Admin a = new Admin();
		 a.removeStudent(s);
	 }
	
	
	
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	

}
