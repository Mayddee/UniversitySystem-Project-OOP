package CentralSystem ;

import UserSystem.User;

public class Employee extends User{ 
	 private String name; 
	    private String role; 
	    private Map<Integer, WorkMessage> receivedMessages; 
	    private List<WorkMessage> sentMessages; 
	    private int messageIdCounter; 
	    TechSupportSpecialist tech;
	    
	    
	    public Employee() {
	     
	    }
	 
	    public Employee(String name, String role) { 
	        this.name = name; 
	        this.role = role; 
	        this.receivedMessages = new HashMap<>(); 
	        this.sentMessages = new ArrayList<>(); 
	        this.messageIdCounter = 1; 
	    } 
	 
	    public boolean sendMessage(Employee receiver, String content) { 
	        WorkMessage message = new WorkMessage(this, receiver, "Message", content, messageIdCounter++); 
	        boolean sentSuccessfully = receiver.receiveMessage(message); 
	        if (sentSuccessfully) { 
	            sentMessages.add(message); 
	        } 
	        return sentSuccessfully; 
	    } 
	 
	    public boolean sendRequest(Employee receiver, String content) { 
	        WorkMessage request = new WorkMessage(this, receiver, "Request", content, messageIdCounter++); 
	        boolean sentSuccessfully = receiver.receiveMessage(request); 
	        if (sentSuccessfully) { 
	            sentMessages.add(request); 
	        } 
	        return sentSuccessfully; 
	    } 
	    
	    public boolean sendOrder(Order order) {
	        if (!order.description.equals(null) && order.getLevel() != null) {

	            boolean sentSuccessfully = tech.receiveOrder(order);

	            if (sentSuccessfully) {
	                tech.orders.add(order);
	            }

	            return sentSuccessfully;
	        }
	        return false;
	    }
	   
	    public boolean receiveMessage(WorkMessage message) { 
	        if (!receivedMessages.containsKey(message.getMessageId())) { 
	            receivedMessages.put(message.getMessageId(), message); 
	            return true; 
	        } 
	        return false; 
	    } 
	 
	    public Collection<WorkMessage> getReceivedMessages() { 
	        return receivedMessages.values(); 
	    } 
	 
	 public String getName() { 
	  return name; 
	 } 
	}

