package Manager ;



import java.util.List;

import CentralSystem.Employee;
import CentralSystem.News;
import CentralSystem.Rector;



public class Manager extends Employee{
	public Manager() {
		super();
	}
	
	public Manager(String firstname, String lastname) {
		super(firstname, lastname);
	}
	public void manageNews() {
       
        System.out.println("All News:");
        News.viewAllNews();

        // Обновляем новость по индексу
        News.updateNews(1, "Updated important news!");

        // Отображаем обновленные новости
        System.out.println("\nUpdated News:");
        News.viewAllNews();
    }
	
	
	 public void createReports() {
	        System.out.println("Creating reports");
	 }
	
	 
	public void viewAcceptedRequests() {
        Rector rector = Rector.getInstance();
        List<String> acceptedRequests = rector.getAcceptedRequests();

        System.out.println("Accepted Requests:");
        for (String request : acceptedRequests) {
            System.out.println(request);
        }
    }
}
