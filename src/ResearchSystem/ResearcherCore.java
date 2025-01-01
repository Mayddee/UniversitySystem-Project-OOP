package ResearchSystem ;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Comparator;
import java.util.Vector;

import CentralSystem.Employee;
import Data.Data;
import Data.School;
import JournalSystem.ResearchJournal;
import Manager.JournalManager;
import Manager.Manager;
import ResearchPapers.ResearchPaper;
import UserSystem.Admin;
import UserSystem.User;



public class ResearcherCore extends Employee implements Researcher{
	School school;
	ResearchPaper paper;
	ResearchProject project;
	private double hIndex;
	private Vector<ResearchPaper> papers;
	private Vector<ResearchProject> projects;
	{
		papers = new Vector<>();
        projects = new Vector<>();
	}
	
	
	public ResearcherCore(){
		super();
	}
	public ResearcherCore(String firstname, String lastname) {
		super(firstname, lastname);
	}

	
	public ResearcherCore(String firstname, String lastname, School school) {
		super(firstname, lastname);
		this.school = school;
		
	}
	public ResearcherCore(String firstname, String lastname, School school, double hIndex) {
		super(firstname, lastname);
		this.school = school;
		this.hIndex = hIndex;
	}

	//complete with journal
	public void writeResearchPaper(String title, Vector<Researcher> otherAuthors, int pages, String publicationDate, double doi, int citations) {
		ResearchPaper paper = new ResearchPaper(title, pages, publicationDate, doi, citations);
		paper.setWhichSchool(school);
		paper.setAuthors(otherAuthors);
		paper.addAuthor(this);
	
	}
	public void addProject(ResearchProject project) {
		projects.add(project);
	}
	public void removeProject(ResearchProject project) {
		projects.remove(project);
	}
	
	public void addPaper(ResearchPaper paper) {
		getPapers().add(paper);
	}
	public void removePaper(ResearchPaper paper) {
		getPapers().remove(paper);
	}
	
	public void printPapers(Comparator<ResearchPaper> comparator) {
		// TODO Auto-generated method stub
		if (paper != null) {
            paper.printPapers(getPapers(), comparator);

        } else {
            System.out.println("No research paper available for printing.");
        }
		
	}
	
	public void publishNewPaper() {
		
		
		boolean jm = false;

	    try {
	        if (Data.managers.isEmpty() || Data.managers == null) {
	            Admin a = new Admin();
	            Manager m = new JournalManager("Aidana", "Yuldasheva");
	            a.registerUser((User) m);
	        }

	        for (Manager m : Data.managers) {
	            if (m instanceof JournalManager) {
	                JournalManager journalManager = (JournalManager) m;

	                // Create a new journal if it doesn't exist
	                if (journalManager.getJournal() == null) {
	                    ResearchJournal rj = new ResearchJournal("Research Journal");
	                    journalManager.setJournal(rj);
	                }

	                // Check if the papers vector is not empty
	                Vector<ResearchPaper> papers = getPapers();
	                if (!papers.isEmpty()) {
	                    int lastIndex = papers.size() - 1;

	                    // Check if the index is within the valid range
	                    if (lastIndex >= 0) {
	                        journalManager.publishNewPaper(papers.get(lastIndex), papers.get(lastIndex).getAuthors());
	                        jm = true;
	                        break;
	                    }
	                }
	            }
	        }

	        // If no JournalManager is found or the papers vector is empty, create a new one
	        if (!jm) {
	            Admin a = new Admin();
	            Manager m = new JournalManager("Aidana", "Yuldasheva");
	            a.registerUser((User) m);
	            if (((JournalManager) m).getJournal() == null) {
	                ResearchJournal rj = new ResearchJournal("Research Journal");
	            }

	            // Check if the papers vector is not empty
	            Vector<ResearchPaper> papers = getPapers();
	            if (!papers.isEmpty()) {
	                int lastIndex = papers.size() - 1;

	                // Check if the index is within the valid range
	                if (lastIndex >= 0) {
	                    ((JournalManager) m).publishNewPaper(papers.get(lastIndex), papers.get(lastIndex).getAuthors());
	                }
	            }
	        }
	    } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
	        e.printStackTrace();
	    }
		
//		boolean jm = false;
//
//		try {
//    		if(Data.managers.isEmpty() || Data.managers == null){
//            	Admin a = new Admin();
//            	Manager m = new JournalManager("Aidana", "Yuldasheva");
//            	a.registerUser((User) m);
//    		}
//			for(Manager m: Data.managers) {
//        		if(m instanceof JournalManager) {
//        			if(((JournalManager) m).getJournal() == null) {
//        				ResearchJournal rj = new ResearchJournal("Research Journal");
//        			}
//        			((JournalManager) m).publishNewPaper(getPapers().get(getPapers().size() - 1), getPapers().get(getPapers().size() - 1).getAuthors());
//        			jm = true;
//        			break;
//        		}
//        	}
//			if(!jm) {
//				Admin a = new Admin();
//            	Manager m = new JournalManager("Aidana", "Yuldasheva");
//            	a.registerUser((User) m);
//            	if(((JournalManager) m).getJournal() == null) {
//    				ResearchJournal rj = new ResearchJournal("Research Journal");
//    			}
//    			((JournalManager) m).publishNewPaper(getPapers().get(getPapers().size() - 1), getPapers().get(getPapers().size() - 1).getAuthors());
//    			
//			}
//		} catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	

	public void createResearchProject(String title, String definition) {
		// TODO implement me
		ResearchProject project = ResearchProject.createProject(title, definition);
        projects.add(project);
        project.addParticipant(this);
	}
	
	

	public Vector<ResearchProject> getResearchProjects() {
		// TODO Auto-generated method stub
		return projects;
		
	}


	public Vector<ResearchPaper> getResearchPapers() {
		return getPapers();
	}


	public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
		this.setPapers(researchPapers);
	}


	@Override
	public void calculateHIndex() {
		// TODO Auto-generated method stub
		
	}
	public double gethIndex() {
		return hIndex;
	}
	public void sethIndex(double hIndex) {
		this.hIndex = hIndex;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public Vector<ResearchPaper> getPapers() {
		return papers;
	}
	public void setPapers(Vector<ResearchPaper> papers) {
		this.papers = papers;
	}

	
}
