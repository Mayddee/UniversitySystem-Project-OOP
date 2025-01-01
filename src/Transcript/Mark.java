package Transcript;

import java.util.HashMap;

public class Mark {
	public double att1;
	public double att2;
	public double finalExam = 0;
	private double total = 0;
	private double totalAtts = 0;
	
	{
	}
	public Mark() {
		
	}

	
	public String toString() {
		String res = "";
		res += "Attestation 1: " + att1 + "/n";
		res += "Attestation 2: " + att2 + "/n";
		res += "Final: " + finalExam + "/n";
	
		return res;
	}
	

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
