package JournalSystem ;

import UserSystem.User;

import java.util.Vector;

public class UserSubscriber extends User implements Observer{
	public Vector<ResearchJournal> subscribedJournals;
	
	public UserSubscriber() {
        this.subscribedJournals = new Vector<>();
    }
	
	public void update(String message) {
        System.out.println("Attention! User received notification: " + message);
    }
	
	
	public void subscribeToJournal(ResearchJournal journal) {
        journal.attach(this);
        subscribedJournals.add(journal);
    }

    public void unsubscribeFromJournal(ResearchJournal journal) {
        journal.detach(this);
        subscribedJournals.remove(journal);
    }

}
