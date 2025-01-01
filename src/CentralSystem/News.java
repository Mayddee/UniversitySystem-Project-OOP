package CentralSystem;

import java.util.List;

import ResearchPapers.ResearchPaper;

import java.util.ArrayList;
import java.util.List;



public class News {
	private static List<String> newsList;
	static {
		newsList = new ArrayList<>();
	}

    public News() {
        
    }

    public static void addNews(String newsText) {
    	 boolean important = isPriority(newsText);
    	    if (important) {
    	        newsList.add(0, newsText);
    	    } else {
    	        newsList.add(newsText);
    	    }
    }

    public static void viewAllNews() {
        for (String news : newsList) {
            System.out.println(news);
        }
    }

    public static void showPriorityFirst() {
        for (String news : newsList) {
            System.out.println(news);
            if (isPriority(news)) {
                break;
            }
        }
    }

    private static boolean isPriority(String news) {
        return news.contains("!");
    }

    public static void addNewsFromResearchPaper(ResearchPaper paper) {
    	 boolean important = paper.getTitle().contains("!");
    	    String importancePrefix = "";
    	    if (important) {
    	        importancePrefix = "Important News - ";
    	    }
    	    // Создаем текст для добавления в новости
    	    String newsText = importancePrefix + "Title: " + paper.getTitle() +
    	            " Authors: " + paper.getAuthors() +
    	            " Journals: " + paper.getJournals() +
    	            " Pages: " + paper.getPages() +
    	            " Date: " + paper.getPublicationDate() +
    	            " DOI: " + paper.getDoi();

    	    // Добавляем новость, указывая важность
    	    addNews(newsText);
    }

    public static void updateNews(int index, String updatedNewsText) {
        if (index >= 0 && index < newsList.size()) {
            newsList.set(index, updatedNewsText);
            System.out.println("News at index " + index + " updated successfully.");
        } else {
            System.out.println("Invalid index provided. News update failed.");
        }
    }
}
