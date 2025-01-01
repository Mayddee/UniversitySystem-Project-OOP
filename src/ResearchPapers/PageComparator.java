package ResearchPapers;

import java.util.Comparator;

public class PageComparator implements Comparator<ResearchPaper>{
	public int compare(ResearchPaper rp1, ResearchPaper rp2) {
		Integer pages1 = rp1.getPages();
		Integer pages2 = rp2.getPages();
		return pages1.compareTo(pages2);
	}
}
