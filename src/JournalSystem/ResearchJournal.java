package JournalSystem ;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ResearchPapers.ResearchPaper;


public class ResearchJournal {
	String name;
	private ResearchPaper latestPaper;
    private Vector<Observer> subscribers;
    {
        subscribers = new Vector<>();

    }

    public ResearchJournal() {
    }
    public ResearchJournal(String name) {
    	this.name = name;
    }
	
    public void attach(Observer observer) {
    	subscribers.add(observer);
    }

    public void detach(Observer observer) {
        subscribers.remove(observer);
    }
	
//    public void publishNewPaper(ResearchPaper paper) {
//        this.latestPaper = paper;
//        notifySubscribers();
//    }
    
  
   
    
	public void notifySubscribers() {
        for (Observer subscriber : subscribers) {
            subscriber.update("Dear students, new research paper published: " + getLatestPaper());
        }
    }
	public ResearchPaper getLatestPaper() {
		return latestPaper;
	}
	public void setLatestPaper(ResearchPaper latestPaper) {
		this.latestPaper = latestPaper;
	}
	
}
