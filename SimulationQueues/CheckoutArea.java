/* Dhruv Shah; 917274155
   CSC 220 Monday & Wednesday 
   Project 3 */

package PJ3;

import java.util.*;

//--------------------------------------------------------------------------
//
// Define simulation queues in a checkout area. Queues hold references to Customer 
// and Cashier objects
//
// Customer (FIFO) queue is used to hold waiting customers. If the queue is too long
// (i.e. >  customerQLimit), customer goes away without entering customer queue
//
// There are several cashiers in a checkout area. Use PriorityQueue to 
// hold BUSY cashiers and FIFO queue to hold FREE cashiers, 
// i.e. a cashier that is FREE for the longest time should start be used first.
//
// To handle cashier in PriorityQueue, we need to define comparator 
// for comparing 2 cashier objects. Here is a constructor from Java API:
//
// 	PriorityQueue(int initialCapacity, Comparator<? super E> comparator) 
//
// For priority queue, the default compare function is "natural ordering"
// i.e. for numbers, minimum value is returned first
//
// User can define own comparator class for PriorityQueue.
// For cashier objects, we like to have smallest end busy interval time first.
// i.e. use Cashier's getEndBusyTime() 
//
// The following class define compare() for two cashiers :

class CompareCashier implements Comparator<Cashier>{
	// overide compare() method
 	public int compare(Cashier o1, Cashier o2) {
		return o1.getEndBusyTime() - o2.getEndBusyTime(); 
	}
}

// DO NOT ADD NEW METHODS OR NEW DATA FIELDS

class CheckoutArea {

  
  // Private data fields:
  
  // define one priority queue 
  private PriorityQueue <Cashier> busyCashierQ;

  // define two FIFO queues
  private Queue<Customer> customerQ;
  private Queue<Cashier> freeCashierQ;

  // define customer queue limit
  private int customerQLimit;


  // Constructor 
  public CheckoutArea() 
  {
	  busyCashierQ = new PriorityQueue<>();
          customerQ = new ArrayDeque<>();
          freeCashierQ = new ArrayDeque<>();
          customerQLimit = 0;
          
  }

  // Constructor 
  public CheckoutArea(int numCashiers, int customerQlimit)
  {
	// use ArrayDeque to construct FIFO queue objects
      freeCashierQ = new ArrayDeque<>(numCashiers);
      customerQ =  new ArrayDeque<>(customerQlimit);

	// construct PriorityQueue object
 	// overide compare() in Comparator to compare Cashier objects
	busyCashierQ = new PriorityQueue<Cashier>(numCashiers,
              new CompareCashier());

	// initialize customerQlimit
        customerQLimit = customerQlimit;

        // Construct Cashier objects and insert into FreeCashierQ
	for (int i = 0; i < numCashiers; i++) {
          int cashierID = i + 1;
          Cashier newCashier = new Cashier (cashierID);
          freeCashierQ.add(newCashier);
        }
	
  }


  // -------------------------------------------------
  // freeCashierQ methods: remove, insert, empty, size 
  // -------------------------------------------------
  public Cashier removeFreeCashierQ()
  {
	// remove and return a free cashier
	// Add statetments
      
	return freeCashierQ.remove();
  }

  public void insertFreeCashierQ(Cashier cashier)
  {
	  // insert a free cashier
	  // Add statetments
      freeCashierQ.add(cashier);
  }

  public boolean emptyFreeCashierQ()
  {
	// is freeCashierQ empty?
	// Add statetments
	return  freeCashierQ.isEmpty();
  }

  public int sizeFreeCashierQ()
  {
	// get number of free cashiers
	// Add statetments
	return freeCashierQ.size();
  }

  // -------------------------------------------------------
  // busyCashierQ methods: remove, insert, empty, size, peek 
  // -------------------------------------------------------

  public Cashier removeBusyCashierQ() 
  {
	// remove and return a busy cashier
	// Add statetments
	return busyCashierQ.remove();
  }

  public void insertBusyCashierQ(Cashier cashier)
  {
	// insert a busy cashier
	// Add statetments
      busyCashierQ.add(cashier);
  }

  public boolean emptyBusyCashierQ()
  {
	// is busyCashierQ empty?
	// Add statetments
	return busyCashierQ.isEmpty();
  }

  public int sizeBusyCashierQ()
  {
	// get number of busy cashiers
	// Add statetments
	return busyCashierQ.size();
  }

  public Cashier peekBusyCashierQ() 
  {
	// get highest prioirty cashier
	// "retrieve" but not "remove"
	// Add statetments
	return busyCashierQ.peek();
  }

  // -------------------------------------------------------
  // customerQ methods: remove, insert, empty, size 
  //                    and check isCustomerQTooLong()
  // -------------------------------------------------------

  public Customer removeCustomerQ()
  {
	// remove and return a customer 
	// Add statetments
	return customerQ.remove();
  }

  public void insertCustomerQ(Customer customer)
  {
	// insert a customer 
	// Add statetments
      customerQ.add(customer);
  }

  public boolean emptyCustomerQ()
  {
	// is customerQ empty?
	// Add statetments
	return customerQ.isEmpty();
  }

  public int sizeCustomerQ()
  {
	// get number of customers 
	// Add statetments
	return customerQ.size();
  }

  public boolean isCustomerQTooLong()
  {
	// is customerQ too long?
	// Add statetments
	return customerQLimit < customerQ.size();
  }


  public void printStatistics()
  {
  	System.out.println("\t# waiting customers  : "+sizeCustomerQ());
  	System.out.println("\t# busy cashiers      : "+sizeBusyCashierQ());
  	System.out.println("\t# free cashiers      : "+sizeFreeCashierQ());
  }


  public static void main(String[] args) {

        // quick check

        // create a CheckoutArea and 4 customers
        CheckoutArea sc = new CheckoutArea(4, 5);
        Customer c1 = new Customer(1,18,10);
        Customer c2 = new Customer(2,33,11);
        Customer c3 = new Customer(3,21,12);
        Customer c4 = new Customer(4,37,13);

        // insert customers into customerQ
  	sc.insertCustomerQ(c1);
  	sc.insertCustomerQ(c2);
  	sc.insertCustomerQ(c3);
  	sc.insertCustomerQ(c4);
	System.out.println("customerQ:"+sc.customerQ);
	System.out.println("===============================================");
	System.out.println("Remove customer:"+sc.removeCustomerQ());
	System.out.println("Remove customer:"+sc.removeCustomerQ());
	System.out.println("Remove customer:"+sc.removeCustomerQ());
	System.out.println("Remove customer:"+sc.removeCustomerQ());
	System.out.println("===============================================");

        // remove cashiers from freeCashierQ
	System.out.println("freeCashierQ:"+sc.freeCashierQ);
	System.out.println("===============================================");
	Cashier p1=sc.removeFreeCashierQ();
	Cashier p2=sc.removeFreeCashierQ();
	Cashier p3=sc.removeFreeCashierQ();
	Cashier p4=sc.removeFreeCashierQ();
	System.out.println("Remove free cashier:"+p1);
	System.out.println("Remove free cashier:"+p2);
	System.out.println("Remove free cashier:"+p3);
	System.out.println("Remove free cashier:"+p4);
	System.out.println("===============================================");
	System.out.println("freeCashierQ:"+sc.freeCashierQ);
	System.out.println("===============================================");


        // insert customers to cashiers
        p1.startServeCustomer(c1, 13);
        p2.startServeCustomer(c2, 13);
        p3.startServeCustomer(c3, 13);
        p4.startServeCustomer(c4, 13);

        // insert cashiers to busyCashierQ
	System.out.println("busyCashierQ:"+sc.busyCashierQ);
	System.out.println("===============================================");
	sc.insertBusyCashierQ(p1);
	sc.insertBusyCashierQ(p4);
	sc.insertBusyCashierQ(p2);
	sc.insertBusyCashierQ(p3);
	System.out.println("busyCashierQ:"+sc.busyCashierQ);
	System.out.println("===============================================");

        // remove cashiers from busyCashierQ
	p1=sc.removeBusyCashierQ();
	p2=sc.removeBusyCashierQ();
	p3=sc.removeBusyCashierQ();
	p4=sc.removeBusyCashierQ();
        p1.endServeCustomer();
        p2.endServeCustomer();
        p3.endServeCustomer();
        p4.endServeCustomer();
	System.out.println("Remove busy cashier:"+p1);
	System.out.println("Remove busy cashier:"+p2);
	System.out.println("Remove busy cashier:"+p3);
	System.out.println("Remove busy cashier:"+p4);

   }


};