package TechSupport ;

import CentralSystem.Employee;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class TechSupportSpecialist extends Employee
{
	static Vector<Order> orders = new Vector<>();

    public TechSupportSpecialist() {
        super();
    }

    public void acceptOrder() { //        Order currentOrder = getCurrentOrder();
     for(Order currentOrder: orders) 
     { 
         if (currentOrder != null) { Level orderLevel = currentOrder.getLevel(); 
     
            if (orderLevel == Level.LOW || orderLevel == Level.MEDIUM) { changeOrderStatus(currentOrder, Status.ACCEPTED); }
     
     else { 
      System.out.println("Cannot accept order with current level: " + currentOrder ); }
            } 
         } 
     }
    

    public void rejectOrder() {
     for(Order currentOrder: orders) {
         if (currentOrder != null) {
                Level orderLevel = currentOrder.getLevel();
                if (orderLevel == Level.HIGH ) {
                    changeOrderStatus(currentOrder, Status.REJECTED);
                } else {
                    System.out.println("Cannot accept order with current level: " + currentOrder );
                }
            }
        }
    }

    

    public void viewOrders() {
//        System.out.println("New Orders: " + getOrders());
        for(Order order: orders) {
         System.out.println(order);
        }
    }

    public void viewAcceptedOrders() {
        System.out.print("Accepted (not yet done) Orders: ");
        boolean found = false;

        for (Order order : orders) {
            if (order.getStatus() == Status.ACCEPTED && !order.isDone()) {
                if (found) {
                    System.out.print(", ");
                }
                System.out.print(order.getDescription());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No accepted (not yet done) orders.");
        } else {
            System.out.println();
        }
    }


    public void changeOrderStatus(Order order, Status newStatus) {
        if (getOrders().contains(order) && order.getStatus() == Status.NEW) {
            order.setStatus(newStatus);
            System.out.println("Order status changed to " + newStatus + ": " + order.getDescription());
            if (newStatus == Status.ACCEPTED) {
                order.markAsDone();
            }
         
        } else {
            System.out.println("Error: Order not found in Orders.");
        }
    }

    public Status setStatus() {
        return Status.ACCEPTED;
    }

 public static Vector<Order> getOrders() {
  return orders;
 }
 
 public static boolean receiveOrder(Order order) {
  if(orders.contains(order))
   return true;
  return false;
 }

 public static void setOrders(Vector<Order> orders) {
  TechSupportSpecialist.orders = orders;
 }

	
}
