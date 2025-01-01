package ResearchSystem ;

import java.util.Comparator;
import java.util.Vector;

import ResearchPapers.ResearchPaper;

public  interface Researcher{
	
		
	public Vector<ResearchPaper> getResearchPapers() ;
	
	public Vector<ResearchProject> getResearchProjects() ;
	public void createResearchProject(String name, String definition);
	
	public void writeResearchPaper(String title, Vector<Researcher> otherAuthors, int pages, String publicationDate, double doi, int citations) ;

	public void printPapers(Comparator<ResearchPaper> comparator) ;
	
	public void calculateHIndex();
	
	public void addProject(ResearchProject project);
	public void removeProject(ResearchProject project);

	public void addPaper(ResearchPaper paper);
	public void removePaper(ResearchPaper paper);
	public void publishNewPaper();

	public String getName();
}
