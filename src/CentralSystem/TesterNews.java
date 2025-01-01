package CentralSystem;

import Data.Data;
import Manager.Manager;
import ResearchPapers.ResearchPaper;

public class TesterNews {

    public static void main(String[] args) {

        System.out.println("News");

        News news = new News();

        Manager manager = new Manager("John", "Doe");

        Data.managers.add(manager);

        news.addNews("Regular News: Welcome back, students!");
        news.addNews("Important News: Don't forget to submit your assignments!\n");

        // Managing news
        System.out.println("Manage News:");
        manager.manageNews();
        news.showPriorityFirst();
        news.viewAllNews();


        
        try {
            for (int i = 0; i < 19; i++) {  
                Data.nextWeek();  // Call nextWeek to increment currWeek
                  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nAll News after Management:");
        

    }
}

