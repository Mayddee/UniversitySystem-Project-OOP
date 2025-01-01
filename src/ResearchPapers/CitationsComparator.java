package ResearchPapers;

import java.util.Comparator;

public class CitationsComparator implements Comparator<ResearchPaper>{
	public int compare(ResearchPaper rp1, ResearchPaper rp2) { 
		Integer citation1 = rp1.getCitations();
		Integer citation2 = rp2.getCitations();
		return citation1.compareTo(citation2);
	}
}
