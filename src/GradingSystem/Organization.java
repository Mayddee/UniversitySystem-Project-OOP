package GradingSystem ;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Organization{
	
	private String name;
	private Department department;
	private boolean castingAnnounced = false;
	private List<Student> students = new ArrayList<>();
	private Map<Student, Department> candidateDepartmentMap;
	private Map<Student, Department> memberDepartmentMap;


	public Organization() {
		  
	 }
	 
	 public Organization(String name, Department department) {
	  this.name = name;
	  this.department = department;
	  this.candidateDepartmentMap = new HashMap<>();
	  this.memberDepartmentMap = new HashMap<>();
	  this.students = new ArrayList<>();
	 }
	 
	 public String getName() {
	  return name;
	 }
	 
	 public void setname(String name) {
	  this.name = name;
	 }
	 
	 public Department getDepartment() {
	  return department;
	 }
	 
	 public void setDepartment(Department department) {
	  this.department = department; 
	 }
	 
	 public Map<Student, Department> getCandidateDepartmentMap() {
	        return candidateDepartmentMap;
	    }

	    public void setCandidateDepartmentMap(Map<Student, Department> candidateDepartmentMap) {
	        this.candidateDepartmentMap = candidateDepartmentMap;
	    }
	    
	    public Map<Student, Department> getMemberDepartmentMap() {
	        return memberDepartmentMap;
	    }

	    public void setMemberDepartmentMap(Map<Student, Department> memberDepartmentMap) {
	        this.memberDepartmentMap = memberDepartmentMap;
	    }
	    
	    public List<Student> getStudents() {
	        return students;
	    }

	    public void setStudents(List<Student> students) {
	        this.students = students;
	    }
	 
	 public String toString() {
	  return "Organization details: " + name + department + candidateDepartmentMap + memberDepartmentMap + students;
	 }
	 
	 public void announceCasting(String name, Time time) {
	  castingAnnounced = true;
	     String title = "Dear KBTU students, get ready to showcase you incredible talents and join " + name + "." + " We are thrilled to announce OPEN CASTING for our upcoming projects, and we want YOU to be a part of the magic!";
	     String content = "The casting will be held at " + time + " in Kazybek Bi 1st floor";
	     Manager.manageNews(title, content);
	 }
	 
	 
	 public void createSelection() {
	     Department department = null;  

	     if (memberDepartmentMap.containsKey(students)) {
	         department = memberDepartmentMap.get(students);
	     }

	     if (department == null) {
	         System.out.println("Casting announcement for other department.");
	     } else {
	         if (department == Department.HEAD) {
	             System.out.println("Criteria: Must have leadership skills and experience.");
	         } else if (department == Department.HR) {
	             System.out.println("Criteria: Must have strong communication skills.");
	         } else if (department == Department.MARKETING) {
	             System.out.println("Criteria: Must have creativity and marketing experience.");
	         } else if (department == Department.FINANCE) {
	             System.out.println("Criteria: Must have financial expertise and analytical skills.");
	         } else {
	             System.out.println("Casting announcement for other department.");
	         }
	     }
	 }

	 
	 public void cancelSelection() {
	  castingAnnounced = false;
	  candidateDepartmentMap.clear();
	 }
	 

	}
	
}