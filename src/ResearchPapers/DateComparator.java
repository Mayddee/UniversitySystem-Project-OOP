package ResearchPapers;

import java.util.Comparator;

public class DateComparator implements Comparator<ResearchPaper>{
	public int compare(ResearchPaper rp1, ResearchPaper rp2) {
		return rp1.getPublicationDate().compareTo(rp2.getPublicationDate());
	}
}
