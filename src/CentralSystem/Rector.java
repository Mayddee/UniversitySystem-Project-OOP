package CentralSystem ;

import java.util.ArrayList;
import java.util.List;

public class Rector {
	private static Rector instance;
    private List<String> acceptedRequests;

    private Rector() {
        acceptedRequests = new ArrayList<>();
    }

    public static synchronized Rector getInstance() {
        if (instance == null) {
            instance = new Rector();
        }
        return instance;
    }
    

    public void confirmRequest(String request) {
        System.out.println("Request '" + request + "' has been confirmed by the rector.");
        acceptedRequests.add(request); // Добавление запроса в список принятых запросов
    }

    public List<String> getAcceptedRequests() {
        return acceptedRequests; // Метод для получения списка принятых запросов
    }
}