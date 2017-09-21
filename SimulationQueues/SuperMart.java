/* Dhruv Shah; 917274155
   CSC 220 Monday & Wednesday 
   Project 3 */
   
package PJ3;

import java.util.*;
import java.io.*;

// You may add new functions or data fields in this class 
// You may modify any functions or data members here
// You must use Customer, Cashier and CheckoutArea classes
// to implement SuperMart simulator

class SuperMart {

  // input parameters
  private int numCashiers, customerQLimit;
  private int chancesOfArrival, maxServiceTime;
  private int simulationTime, dataSource;

  // statistical data
  private int numGoaway, numServed, totalWaitingTime;
  private double avgWaitingTime;

  // internal data
  private int counter;	             // customer ID counter
  private CheckoutArea checkoutarea; // checkout area object
  private Cashier cashiers;          // cashiers object
  private Customer customers;        // customers object
  private Scanner dataFile;	     // get customer data from file
  private Random dataRandom;	     // get customer data using random function

  // most recent customer arrival info, see getCustomerData()
  private boolean anyNewArrival;  
  private int serviceTime;
 private int input;
  // initialize data fields
  private SuperMart()
  {
	// add statements
         numCashiers = 0;
	  customerQLimit = 0;
	  chancesOfArrival = 0;
          maxServiceTime = 0;
	  serviceTime = 0;
	  simulationTime = 0;
          dataSource = 0;
          numGoaway = 0;
          numServed = 0;
          totalWaitingTime = 0;
          counter = 0; 
  }

    private void setupParameters() {
// read input parameters from user
        dataFile = new Scanner(System.in);
        // setup dataFile or dataRandom
        System.out.println(" Enter simulation time (positive integer): ");
        simulationTime = dataFile.nextInt();
        System.out.println(" Enter the number of cashiers: ");
        numCashiers = dataFile.nextInt();
        System.out.println(" Enter chances (0% < & <= 100%) of new customer: ");
        chancesOfArrival = dataFile.nextInt();
        while (chancesOfArrival < 0 || chancesOfArrival > 100) {
            System.out.println("Please try again.");
            chancesOfArrival = dataFile.nextInt();
        }
        System.out.println(" Enter maximum service time of customers : ");
        maxServiceTime = dataFile.nextInt();
        System.out.println(" Enter customer queue limit: ");
        customerQLimit = dataFile.nextInt();
        System.out.println(" Enter 0/1 to get data from random/file: ");
        input = dataFile.nextInt();
        if (input == 0) {
            dataRandom = new Random();
            dataRandom.nextInt();
        } else if (input == 1) {
            //asks user for filename
            System.out.println("Enter filename: ");
            String fileName = dataFile.next();// reads filename
            // trouble opening file so I threw an exception instead of having it crash
            try {
                dataFile = new Scanner(new File(fileName));
            } catch (Exception e) {
                System.out.println("Error opening the file " + fileName);
                System.exit(0);
            }

        }
        // add statements
    }

  // Refer to step 1 in doSimulation()
  private void getCustomerData()
  {
	// get next customer data : from file or random number generator
      // set anyNewArrival and serviceTime
      if (input == 1){
          int data1;
          int data2;
          data1 = dataFile.nextInt();
          data2 = dataFile.nextInt();
           anyNewArrival = (((data1%100)+1)<= chancesOfArrival);
         serviceTime = (data2%maxServiceTime)+1;
      } 
     else if (input == 0) {
          anyNewArrival = ((dataRandom.nextInt(100)+1) <= chancesOfArrival);
        serviceTime = dataRandom.nextInt(maxServiceTime)+1;
      }
      // see Readme file for more info
      // add statements
      

  }

  private void doSimulation()
    {
	// add statements

        // Initialize CheckoutArea
        checkoutarea = new CheckoutArea(numCashiers, customerQLimit);
        // Time driver simulation loop
        for (int currentTime = 0; currentTime < simulationTime; currentTime++) {
            // Step 1: any new customer enters the checkout area?
            getCustomerData();
            System.out.println("---------------------------------------------");
    		System.out.println("Time: " + currentTime);
            if (anyNewArrival) {
                // Step 1.1: setup customer data
                Customer customer = new Customer(counter, serviceTime, currentTime);
                counter++;
                System.out.println("Customer #" + counter
                        + " arrives with checkout time " + serviceTime + " units.");
                // Step 1.2: check customer waiting queue too long?
                // if customer queue is too long, update numGoaway
                if (checkoutarea.isCustomerQTooLong()) {
                    numGoaway++;
                    System.out.println("The customer queue is too long. Customer # "
                            + counter + "has left.");
                    // else go to customer queue
                } else {
                    checkoutarea.insertCustomerQ(customer);
                    System.out.println("Customer #"
                            + counter + " wait in the customer queue.");
                }
            } else {
                System.out.println("\tNo new customer!");
            }
            // Step 2: free busy cashiers, add to free cashierQ
            while (!checkoutarea.emptyBusyCashierQ()) {
                // peeks cashier from priority que
                Cashier currentCashier = checkoutarea.peekBusyCashierQ();  
                // busy cashier becomes free
                if (currentCashier.getEndBusyTime() == currentTime) {  
                    Customer currentCustomer = currentCashier.getCurrentCustomer();
                    System.out.println("Customer #" + currentCustomer.getCustomerID()
                            + " is done.");
                    checkoutarea.removeBusyCashierQ();
                    currentCashier.endServeCustomer();
                    //cashier added back to free cashier q
                    checkoutarea.insertFreeCashierQ(currentCashier);
                    System.out.println("Cashier #" + currentCashier.getCashierID()
                            + " is free.");
                } else { // busy cashier is not free yet
                    break; // end while loop
                }
            }
            // Step 3: get free cashiers to serve waiting customers 
            while ((!checkoutarea.emptyFreeCashierQ()) && (!checkoutarea.emptyCustomerQ())) {
                Cashier waitingCashier = checkoutarea.removeFreeCashierQ();
                // next customer in line to be served
                Customer nextCustomer = checkoutarea.removeCustomerQ(); 
                waitingCashier.startServeCustomer(nextCustomer, currentTime);
                checkoutarea.insertBusyCashierQ(waitingCashier);
                System.out.println("Customer #" + counter + " gets a cashier.");
                System.out.println("Cashier  #" + waitingCashier.getCashierID() +
                        " starts serving customer # "
                        + nextCustomer.getCustomerID() + " for "
                        + nextCustomer.getServiceTime() + " units.");
                numServed++;
            }	 		
  	} // end simulation loop
  	avgWaitingTime = ((double)totalWaitingTime)/counter;
  	System.out.println("End of simulation report");
  	// clean-up
  }
    
  private void printStatistics()
  {
	// add statements into this method!
	// print out simulation results
	// see the given example in project statement
        // you need to display all free and busy gas pumps
  System.out.println("\n\n=======================================================\n\n");
    System.out.println("End of simulation report\n");

    
    System.out.println("\t# of arrival customers    : " + counter);
    System.out.println("\t# customer gone-away      : " + numGoaway);
    System.out.println("\t# customers served        : " + numServed);

    System.out.println("\n\t*** Current Cashiers Info ***\n");
    System.out.println("\t# WaitingCustomers        : " + checkoutarea.sizeCustomerQ());
    System.out.println("\t# busy cashiers           : " + checkoutarea.sizeBusyCashierQ());
    System.out.println("\t# free cashiers           : " + checkoutarea.sizeFreeCashierQ());

    System.out.println("\n\tTotal waiting time       :" + totalWaitingTime);
    System.out.println("\tAverage waiting time      :" + avgWaitingTime);
    
    // need to free up all customers in queue to get extra waiting time.
   while (!checkoutarea.emptyCustomerQ()) {
       Customer waitingCustomers = checkoutarea.removeCustomerQ();
       waitingCustomers.setWaitTime(simulationTime) ;
       totalWaitingTime += waitingCustomers.getWaitTime();
   }
    
    // need to free up all cashiers in queue to get extra free & busy time.
    System.out.println("Busy Cashiers Info: ");
    while(!checkoutarea.emptyBusyCashierQ()){
        Cashier busyCashier = checkoutarea.peekBusyCashierQ();
        checkoutarea.removeBusyCashierQ();
        busyCashier.setEndFreeTime(simulationTime);
        busyCashier.updateTotalBusyTime();
        busyCashier.printStatistics();  
    }
    
    while(!checkoutarea.emptyFreeCashierQ()){
    System.out.println("Free Cashiers Info: ");
        Cashier freeCashier = checkoutarea.removeFreeCashierQ();
        freeCashier.setEndBusyTime(simulationTime); 
        freeCashier.updateTotalFreeTime();
        freeCashier.printStatistics();
    }
  }

  // *** main method to run simulation ****

  public static void main(String[] args) {
   	SuperMart runSuperMart=new SuperMart();
   	runSuperMart.setupParameters();
   	runSuperMart.doSimulation();
   	runSuperMart.printStatistics();
  }

}
