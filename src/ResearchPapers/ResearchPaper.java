package ResearchPapers;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import CentralSystem.News;
import Data.Data;
import Data.School;
import JournalSystem.ResearchJournal;
import Manager.JournalManager;
import Manager.Manager;
import ResearchSystem.Researcher;
import ResearchSystem.ResearcherCore;
import UserSystem.Admin;
import UserSystem.User;

public class ResearchPaper {
	private String title;
	private Vector<Researcher> authors;
	private Vector<ResearchJournal> journals;
	private int pages;
	private Date publicationDate;
	private double doi;
	private int citations;
	private School whichSchool;
	{
		authors = new Vector<Researcher>();
		journals = new Vector<ResearchJournal>();
	}
	
	public ResearchPaper(String title, int pages, String publicationDate, double doi, int citations) {
	    this.title = title;
	    this.pages = pages;

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        this.publicationDate = dateFormat.parse(publicationDate);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    this.doi = doi;
	    this.citations = citations;
	}
	public ResearchPaper(String title, Vector<Researcher> authors, int pages, String publicationDate, double doi, int citations, School whichSchool) {
	   
	    this(title, pages, publicationDate, doi, citations);
	    this.authors = authors;
	    this.whichSchool = whichSchool;
	}
	public ResearchPaper(String title, Vector<Researcher> authors, Vector<ResearchJournal> journals, int pages, String publicationDate, double doi, int citations, School whichSchool) {
		   
	    this(title, authors, pages, publicationDate, doi, citations, whichSchool);
	    this.authors = authors;
	    
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Vector<Researcher> getAuthors() {
		return authors;
	}

	public void setAuthors(Vector<Researcher> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Researcher author) {
		authors.add(author);
	}

	public Vector<ResearchJournal> getJournals() {
		return journals;
	}

	public void setJournal(Vector<ResearchJournal> journals) {
		this.journals = journals;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public double getDoi() {
		return doi;
	}

	public void setDoi(double doi) {
		this.doi = doi;
	}

	public int getCitations() {
		return citations;
	}

	public void setCitations(int citations) {
		this.citations = citations;
	}
	
	public School getWhichSchool() {
		return whichSchool;
	}
	
	public void setWhichSchool(School whichSchool) {
		this.whichSchool = whichSchool;
	}
	

	public String getCitation(Format f) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		
		switch (f) {
		case BibTeX:
			return "@INPROCEEDINGS{" + citations + ",\n" +
            "  author={" + authors + "},\n" +
            "  booktitle={" + journals + "},\n" +
            "  title={" + title + "},\n" +
            "  year={" + dateFormat.format(publicationDate) + "},\n" +
            "  pages={" + pages + "},\n" +
            "  doi={" + doi + "}\n" +
            "}";
			
		case plainText:
            return authors + ", \"" + title + "\", " + journals + ", " + dateFormat.format(publicationDate) +
                    ", pp. " + pages + ", doi: " + doi + ".";
            
        default:
        	return "Invalid format";
		}
	}
	
	public static void printTopResearchPaperFromSchool(String school) {
        Data.papers.getOrDefault(school, new Vector<>()).stream()
                .filter(paper -> school.equals(paper.getWhichSchool()))
                .max(Comparator.comparingInt(ResearchPaper::getCitations))
                .ifPresent(topPaper -> {
                    System.out.println("Top Research Paper from " + school + ":");
                    System.out.println("Title: " + topPaper.getTitle() +
                            " Authors: " + topPaper.getAuthors() +
                            " Journals: " + topPaper.getJournals() +
                            " Pages: " + topPaper.getPages() +
                            " Date: " + topPaper.getPublicationDate() +
                            " DOI: " + topPaper.getDoi() +
                            " Citations: " + topPaper.getCitations() +
                            " Which School: " + topPaper.getWhichSchool());
                });
    }

	@Override
	public int hashCode() {
		return Objects.hash(authors, citations, doi, journals, pages, publicationDate, title, whichSchool);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResearchPaper other = (ResearchPaper) obj;
		return Objects.equals(authors, other.authors) && citations == other.citations
				&& Double.doubleToLongBits(doi) == Double.doubleToLongBits(other.doi)
				&& Objects.equals(journals, other.journals) && pages == other.pages
				&& Objects.equals(publicationDate, other.publicationDate) && Objects.equals(title, other.title)
				&& whichSchool == other.whichSchool;
	}

	@Override
	public String toString() {
		return "ResearchPaper [title=" + title + ", authors=" + authors + ", journals=" + journals + ", pages=" + pages
				+ ", publication date=" + publicationDate + ", doi=" + doi + ", citations=" + citations
				+ ", school=" + whichSchool + "]";
	}
	
	public void publishNewPaper(ResearcherCore r) throws Exception {
		
		boolean jm = false;

	    try {
	        if (Data.managers.isEmpty() || Data.managers == null) {
	            Admin a = new Admin();
	            Manager m = new JournalManager("Aidana", "Yuldasheva");
	            a.registerUser((User) m);
	        }

	        Vector<ResearchPaper> papers = r.getPapers();

	        // Check if papers vector is not empty
	        if (!papers.isEmpty()) {
	            for (Manager m : Data.managers) {
	                if (m instanceof JournalManager) {
	                    if (((JournalManager) m).getJournal() == null) {
	                        ResearchJournal rj = new ResearchJournal("Research Journal");
	                    }

	                    // Check if the index is within the valid range
	                    int lastIndex = papers.size() - 1;
	                    if (lastIndex >= 0) {
	                        ((JournalManager) m).publishNewPaper(papers.get(lastIndex), papers.get(lastIndex).getAuthors());
	                        jm = true;
	                        break;
	                    }
	                }
	            }
	        }

	        if (!jm) {
	            Admin a = new Admin();
	            Manager m = new JournalManager("Aidana", "Yuldasheva");
	            a.registerUser((User) m);
	            if (((JournalManager) m).getJournal() == null) {
	                ResearchJournal rj = new ResearchJournal("Research Journal");
	            }

	            // Check if the index is within the valid range
	            int lastIndex = papers.size() - 1;
	            if (lastIndex >= 0) {
	                ((JournalManager) m).publishNewPaper(papers.get(lastIndex), papers.get(lastIndex).getAuthors());
	            }
	        }
	    } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
	        e.printStackTrace();
	    }
		

	}
	
	public List<String> printPapers(Vector<ResearchPaper> papers, Comparator<ResearchPaper> comparator) {
		 
	    return papers.stream()
	            .sorted(comparator)
	            .map(ResearchPaper::toString)
	            .collect(Collectors.toList());
	}

	

}

