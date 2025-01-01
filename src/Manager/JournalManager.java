package Manager ;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import JournalSystem.ResearchJournal;
import ResearchPapers.ResearchPaper;
import ResearchSystem.Researcher;


public class JournalManager extends Manager{
	
	private ResearchJournal journal;
	
	
	public JournalManager() {
		super();
	}

	public JournalManager(String firstname, String lastname) {
		super(firstname, lastname);
	}
	public JournalManager(String firstname, String lastname, ResearchJournal journal) {
		super(firstname, lastname);
		this.journal = journal;
	}

	public void setJournal(ResearchJournal journal) {
		this.journal = journal;
	}
	
	public ResearchJournal getJournal() {
		return journal;
	}
	
	
	public void manageJournal(Vector<ResearchPaper> papers, Comparator<ResearchPaper> compareByDate) {
//	    for (ResearchJournal journal : journals) {
//	        List<String> sortedPapers = ResearchPaper.printPapers(papers, compareByDate);
//	        if (!sortedPapers.isEmpty()) {
//	            String latestPaperInfo = sortedPapers.get(0); 
//	            journal.getLatestResearchPaper().setTitle(latestPaperInfo);
//	        }
//	    }
		
	}
	
	 public void publishNewPaper(ResearchPaper paper, Vector<Researcher> authors) {
	        journal.setLatestPaper(paper);
	        journal.notifySubscribers();
	   }
	

}