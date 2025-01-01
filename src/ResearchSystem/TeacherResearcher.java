package ResearchSystem ;

import java.util.Comparator;
import java.util.Vector;

import Data.School;
import GradingSystem.Teacher;
import GradingSystem.TeacherTitle;
import ResearchPapers.ResearchPaper;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class TeacherResearcher extends Teacher implements Researcher
{
	ResearcherCore researcher;
	
	public Vector<ResearchPaper> papers;
	public Vector<ResearchProject> projects;
	{
		papers = new Vector<ResearchPaper>();
		projects = new Vector<ResearchProject>();
	}
	
	
	public TeacherResearcher(){
		super();
	}
	public TeacherResearcher(String firstname, String lastname) {
		super(firstname, lastname);
	}

	public TeacherResearcher(String firstname, String lastname, School school) {
		super(firstname, lastname, school);
	}
	
	public TeacherResearcher(String firstname, String lastname, TeacherTitle title, School school) {
		super(firstname, lastname, title, school);
	}
	
	public void addProject(ResearchProject project) {
		ResearcherCore researcher = new ResearcherCore();

		researcher.addProject(project);
	}
	public void removeProject(ResearchProject project) {
		ResearcherCore researcher = new ResearcherCore();

		researcher.removeProject(project);
	}
	
	public void addPaper(ResearchPaper paper) {
		ResearcherCore researcher = new ResearcherCore();

		researcher.addPaper(paper);
	}
	public void removePaper(ResearchPaper paper) {
		ResearcherCore researcher = new ResearcherCore();

		researcher.removePaper(paper);
	}
	

	
	public Vector<ResearchPaper> getResearchPapers() {
		// TODO implement me
		return papers;
	}
	
	
	public void writeResearchPaper(String title, Vector<Researcher> otherAuthors, int pages, String publicationDate, double doi, int citations) {
		ResearcherCore researcher = new ResearcherCore();

		researcher.writeResearchPaper(title, otherAuthors, pages, publicationDate, doi, citations);	
	}

	

	public void createResearchProject(String title, String definition) {
		ResearcherCore researcher = new ResearcherCore();

		// TODO Auto-generated method stub
		researcher.createResearchProject(title, definition);
		
	}

	public void printPapers(Comparator<ResearchPaper> comparator) {
		ResearcherCore researcher = new ResearcherCore();

		researcher.printPapers(comparator);
	}
	
	public void publishNewPaper() {
		ResearcherCore researcher = new ResearcherCore();
		researcher.publishNewPaper();
	}

	@Override
	public void calculateHIndex() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Vector<ResearchProject> getResearchProjects() {
		// TODO Auto-generated method stub
		return projects;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

