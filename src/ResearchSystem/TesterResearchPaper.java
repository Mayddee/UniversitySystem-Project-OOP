package ResearchSystem;

import java.util.Vector;

import Data.School;
import ResearchPapers.CitationsComparator;
import ResearchPapers.DateComparator;
import ResearchPapers.PageComparator;
import ResearchPapers.ResearchPaper;

public class TesterResearchPaper {
public static void main(String[] args) {
//	ResearcherCore researcher = new ResearcherCore("John", "Doe", School.SITE);
//
//    // Test writeResearchPaper method
//    researcher.writeResearchPaper("Paper1", new Vector<>(), 15, "2023-01-15", 123.456, 8);
//    researcher.writeResearchPaper("Paper2", new Vector<>(), 12, "2023-02-10", 789.012, 10);
//
//    // Test getPapers method
//    Vector<ResearchPaper> papers = researcher.getResearchPapers();
//    System.out.println("Research Papers:");
//    for (ResearchPaper paper : papers) {
//        System.out.println(paper.getTitle() + " - " + paper.getPages() + " pages");
//    }
//
//  
// 
//	
	
	 ResearcherCore researcher = new ResearcherCore("John", "Doe", School.SITE);

     // Test writeResearchPaper method
     researcher.writeResearchPaper("Paper1", new Vector<>(), 15, "2023-01-15", 123.456, 8);
     researcher.writeResearchPaper("Paper2", new Vector<>(), 12, "2023-02-10", 789.012, 10);

     // Test getPapers method before publishing
     System.out.println("Research Papers (Before Publishing):");
     Vector<ResearchPaper> papersBeforePublishing = researcher.getResearchPapers();
     for (ResearchPaper paper : papersBeforePublishing) {
         System.out.println(paper.getTitle() + " - " + paper.getPages() + " pages");
     }

     // Test publishNewPaper method
     researcher.publishNewPaper();

     // Test getPapers method after publishing
     System.out.println("\nResearch Papers (After Publishing):");
     Vector<ResearchPaper> papersAfterPublishing = researcher.getResearchPapers();
     for (ResearchPaper paper : papersAfterPublishing) {
         System.out.println(paper.getTitle() + " - " + paper.getPages() + " pages");
     }
//        
	}
}
