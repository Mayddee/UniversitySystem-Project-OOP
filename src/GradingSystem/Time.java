package GradingSystem;

public class Time {
	
		 public int hour;
		 public int min;
		 public int sec;
		 public Time() {
		  
		 }
		 public Time(int hour, int min, int sec) {
		  this.hour = hour;
		  this.min = min;
		  this.sec = sec;
		  
		 }
		 public String toUniversal() {
		  
		  //23:08:09
		  String res = "";
		  if(this.hour < 10) res += "0" + this.hour + ":";
		  else res += this.hour + ":";
		  if(this.min < 10) res += "0" + this.min + ":";
		  else res += this.min + ":";
		  if(this.sec < 10) res += "0" + this.sec;
		  else res += this.sec;
		  
		  return res;
		 }
		 public String toStandard() {
		  
		  //11:08:09 PM
		  String res = "";
		  if(this.hour < 10) res += "0" + this.hour + ":";
		  else if(this.hour >= 10 && this.hour < 12) res += this.hour + ":";
		  else if(this.hour >= 12 && this.hour < 20) res += "0" + this.hour % 12 + ":";
		  else res += this.hour % 12 + ":";
		  if(this.min < 10) res += "0" + this.min + ":";
		  else res += this.min + ":";
		  if(this.sec < 10) res += "0" + this.sec;
		  else res += this.sec;
		  if(this.hour < 10) res += " AM";
		  else res += " PM";
		  
		  return res;
		 }
		 
		 
		 public void add(Time t2) {
		        int newHour = this.hour + t2.hour;
		        int newMin = this.min + t2.min;
		        int newSec = this.sec + t2.sec;
		        
		        if (newHour >= 24) {
		            newHour %= 24;
		        }
		        if (newMin >= 60) {
		            newMin %= 60;
		            newHour++;
		        }
		        if (newSec >= 60) {
		            newSec %= 60;
		            newMin++;
		        }


		        this.hour = newHour;
		        this.min = newMin;
		        this.sec = newSec;
		    }
		 

		
}
