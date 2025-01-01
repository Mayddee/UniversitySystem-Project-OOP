package Transcript ;


import java.util.HashMap;

public class Transcript {
    public String[] transcript = {"A", "A-", "B+", "B", "B-", "B", "C+", "C", "C-", "D+", "D", "D-"};

    
    static {
    	
    }
    
    public Transcript() {
    	
    }

//    public Mark getMarks() {
//        return marks;
//    }
//
//    public void setMarks(Mark marks) {
//        this.marks = marks;
//    }

    public String calculateTranscript(Mark marks) {
        double totalMarks = marks.getTotal();
        int index = (int) ((totalMarks / 100) * transcript.length);

        // Ensure the index is within bounds
        index = Math.max(0, Math.min(index, transcript.length - 1));

        return transcript[index];
    }
    public HashMap<String, Double> getCorrespGPA(){
    	HashMap<String, Double> byGpa = new HashMap<String, Double>();
    	byGpa.put("A", 4.0);
    	byGpa.put("A-", 3.67);
    	byGpa.put("B+", 3.33);
    	byGpa.put("B", 3.0);
    	byGpa.put("B-", 2.67);
    	byGpa.put("C+", 2.33);
    	byGpa.put("C", 2.0);
    	byGpa.put("C-", 1.67);
    	byGpa.put("D+", 1.33);
    	byGpa.put("D", 1.0);
    	byGpa.put("D-", 0.67);
    	return byGpa;
    }

//    private double calculateTotalMarks(Student student) {
//        double att1Weight = 0.3;
//        double att2Weight = 0.3;
//        double finalExamWeight = 0.4;
//
//        Mark studentMark = student.getMarks().get(student.getEnrolledCourse()); // Assuming Student has a method to get the enrolled course
//
//        double totalMarks = att1Weight * studentMark.att1 +
//                            att2Weight * studentMark.att2 +
//                            finalExamWeight * studentMark.finalExam;
//
//        return totalMarks;
//    }
    public String toString() {
    	String res = "";
    	for(String transcript: getCorrespGPA().keySet()) {
    		res += transcript + " : " + getCorrespGPA().get(transcript) + "\n";
    	}
    	return res;
    }
}