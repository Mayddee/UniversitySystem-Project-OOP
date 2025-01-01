package TechSupport ;


public class Order {
    
    public String description;
    public boolean isAccepted;
    public Status status = Status.NEW;
    private boolean done = false;
    private Level level; 
    
    public Order() {
    	
    }
    
    public Order(String description, Level level) {
    	this.description = description;
    	this.level = level;
    	
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void markAsDone() {
        this.done = true;
        System.out.println("Order marked as done: " + this.description);
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    
    public String toString() {
    	return "Order: " + level + " : " + description;
    }
}

