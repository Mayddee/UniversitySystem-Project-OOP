package ResearchSystem ;

import Data.School;



public class GraduateStudent extends StudentResearcher implements Researcher
{
	public GraduateStudentType studentType;
    public TeacherResearcher supervisor;
    private String diplomaWorkTopic;

    public GraduateStudent() {
    }
    
    public GraduateStudent(String firstname, String lastname) {
		super(firstname, lastname);
	}


	public GraduateStudent(String firstname, String lastname, int id) {
		super(firstname, lastname, id);
	}
	public GraduateStudent(String firstname, String lastname, int id, School school) {
		super(firstname, lastname, id, school);
		
	}
    public GraduateStudent(String firstname, String lastname, int id, School school, GraduateStudentType studentType, String diplomaWorkTopic) {
        super(firstname, lastname, id, school);
        this.studentType = studentType;
        this.studentType = studentType; 
        this.diplomaWorkTopic = diplomaWorkTopic;
    }

    public GraduateStudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(GraduateStudentType studentType) {
        this.studentType = studentType;
    }

    public TeacherResearcher getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(TeacherResearcher supervisor) {
        if (supervisor == null) {
        } else {
            this.supervisor = supervisor;
        }
    }

    public String getDiplomaWorkTopic() {
        return diplomaWorkTopic;
    }

    private void writeDiplomaWork() {
        System.out.println("Diploma Work Title Page:");
        System.out.println("Author: " + firstname + " " + lastname);
        System.out.println("School: " + school);
        
        if (supervisor != null) {
            System.out.println("Supervisor: " + supervisor.getName());
        } else {
            System.out.println("Supervisor: Not assigned");
        }
        
        System.out.println("Diploma Work Topic: " + diplomaWorkTopic);
        System.out.println();
    }
    
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraduateStudent that = (GraduateStudent) o;

        if (studentType != that.studentType) return false;
        if (supervisor != null ? !supervisor.equals(that.supervisor) : that.supervisor != null) return false;
        return diplomaWorkTopic != null ? diplomaWorkTopic.equals(that.diplomaWorkTopic) : that.diplomaWorkTopic == null;
    }
    
    public String toString() {
        return "GraduateStudent{" +
                "studentType=" + studentType +
                ", supervisor=" + (supervisor != null ? supervisor.getName() : "Not assigned") +
                ", diplomaWorkTopic='" + diplomaWorkTopic + '\'' + '}';
    }
	
}
