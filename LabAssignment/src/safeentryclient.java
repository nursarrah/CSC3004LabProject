
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class safeentryclient {
	
	static LocalDate setDate;
	static String setLocation, setCheckInTime, setCheckOutTime;
    
	public static void main(String[] args) {
    	
		ArrayList<String> nrics = new ArrayList<String>();
	    final String INPUT_PROMPT = "Please key in value:"
                + "\n1 To Check In"
                + "\n2 To View History"
                + "\n3 To View Notification"
                + "\nq To Quit"
                + "\n";
	    
//      //use localhost if running the server locally or use IP address of the server
       String reg_host = "localhost";
       int reg_port = 1099;

       if (args.length == 1) {
       	   reg_port = Integer.parseInt(args[0]);
       } else if (args.length == 2) {
      	   reg_host = args[0];
      	   reg_port = Integer.parseInt(args[1]);
      }

	try {
		
		safeentry safeEntry = (safeentry) Naming.lookup("rmi://localhost/SafeEntryService");
		
		Scanner input = new Scanner(System.in);
	    System.out.println("Enter NRIC to login");
	    String userNRIC = input.nextLine();
	    String userName = safeEntry.userLogin(userNRIC);
	    
	    // if user nric exist in database
	    if(userName!=null) {
		    System.out.println("Welcome " + userName + ",");
		    System.out.print(INPUT_PROMPT);	    
		    
		    String userInput = "";
		    userInput = input.nextLine();
		    
		    while(!userInput.equalsIgnoreCase("Q"))
	        {
		    	// 'Q' to quit program
	            if(userInput.equalsIgnoreCase("Q")) {
	                break;
	            }
	            // 'B' to back to main menu
	            else if(userInput.equalsIgnoreCase("B")) {
	                System.out.print(INPUT_PROMPT);
	                userInput = input.nextLine();
	            }
	            // '1' to check in
	            else if(userInput.equals("1")) {
	        	    System.out.println("Individual Check In? [Y/N]");
	        	    String choice = input.nextLine();
	        	    // 'Y' for individual check in
	        	    if(choice.equalsIgnoreCase("Y")) {
	        	    		        	    	
	        	    	// set location, date & check in time
	        	    	setCheckInDetails();
	        	    	
	        	    	if(safeEntry.checkIn(userNRIC, setLocation, setDate.toString(), setCheckInTime)==true) {
	        				// display check in details of user
	        		    	System.out.println("Successfully Checked in \nName:" + userName + 
	        		    			"\nNRIC: "+ userNRIC + "\nLocation: " + setLocation + 
	        		    			"\nDate: " + setDate + "\nCheck in Time : " + setCheckInTime);
	            	    	System.out.println("[O] to Check Out");
	            	    	userInput = input.nextLine();
	            	    	// 'O' to check out 
	            	    	if(userInput.equalsIgnoreCase("O")) {
	            	    		setCheckOutDetails();
	            	    		if(safeEntry.checkOut(userNRIC, setLocation, setDate.toString(), setCheckInTime, setCheckOutTime)==true) {
	            	    			System.out.println("Successfully Checked Out \nLocation: " + setLocation + 
	            	    					"\nDate: " + setDate + "\nCheck Out Time : " + setCheckOutTime);
	            	    			// Back to Main Menu
	                    	    	System.out.println("[B] to back to Main Menu");
	                    	    	userInput = input.nextLine();
	            	    		} 
	            	    		else {
	            	    			System.out.println("Invalid option. Please try again.");
	            	    		}
	            	    	}
	            	    	else {
	            	    		System.out.println("Invalid option. Please try again.");
	            	    	}
	        	    	}
	        	    	else {
	        	    		System.out.println("Please try again");
	        	    	}
	
	        	    } else if(choice.equalsIgnoreCase("N")) {
	        	    		        	    	
	        	    	// set location, date & check in time
	        	    	setCheckInDetails();
	        	    	// Input Number of Group
	        	    	System.out.println("Enter Number of Group: ");
	        	    	String inputNoOfPeople = input.nextLine();
	        	    	System.out.println("Enter NRIC(s): ");
	        	    	// Input NRIC of people in the group
	        	    	for(int i=0; i<Integer.parseInt(inputNoOfPeople); i++) {
	        	    		String groupNric = input.nextLine();
	        	    		nrics.add(groupNric);
	        	    	}
	        	    	for (int n=0; n < nrics.size(); n++) {
	         	    		if(safeEntry.checkIn(nrics.get(n), setLocation, setDate.toString(), setCheckInTime)==true) {
	                		    // display users nric that successfully checked-in
	         	    			System.out.println("NRIC: "+nrics.get(n));                		    	              		    	
	                	    }
	         	    		else {
	         	    			System.out.println("Please try again");
	         	    		}
	        	    	}
	        	    	System.out.println("Successfully Checked in \nLocation: " + setLocation + "\nDate: " + setDate + "\nCheck in Time : " + setCheckInTime);
	        	    	System.out.println("[O] to Check Out");
	        	    	userInput = input.nextLine();
	        	    	// 'O' to Check Out 
	        	    	if(userInput.equalsIgnoreCase("O")) {
	        	    		setCheckOutDetails();
	        	    		for (int count=0; count < nrics.size(); count++) {
		        	    		if(safeEntry.checkOut(nrics.get(count), setLocation, setDate.toString(), setCheckInTime, setCheckOutTime)==true) {
			        	    			System.out.println("NRIC: " +nrics.get(count));
			        	    	}
	        	    		}
	        	    		System.out.println("Successfully Checked Out \nLocation: " + setLocation + "\nDate: " + setDate +  "\nCheck Out Time : " + setCheckOutTime);
	        	    		//'B' Back to Main Menu
	            	    	System.out.println("[B] to back to Main Menu");
	            	    	// remove all nric in an array
	            	    	nrics.clear();
	            	    	userInput = input.nextLine();
	        	    	}
	        	    	else {
	        	    		System.out.println("Invalid option. Please try again.");
	        	    	}
	        	    }
	        	    else {
	        	    	System.out.println("Invalid Input");
	        	    }
	            }
	            // '2' to View History
	            else if(userInput.equals("2")) {
	            	System.out.println(safeEntry.viewHistory(userNRIC));
	            	//'B' to Back to Main Menu
	    	    	System.out.println("[B] to back to Main Menu");
	    	    	userInput = input.nextLine();
	            }
	            // '3' to View Notification
	            else if(userInput.equals("3")) {
	            	System.out.println(safeEntry.notificationFeature(userNRIC));
	            	// Back to Main Menu
	    	    	System.out.println("[B] to back to Main Menu");
	    	    	userInput = input.nextLine();
	            }
	            else {
	            	 System.out.println("Please enter a valid key");
	                 userInput = "b";
	            }
	        }
	    
	    } else {
	    	System.out.println("Invalid NRIC.");
	    }
	    	    
  }
  // Catch the exceptions that may occur - rubbish URL, Remote exception
	catch (MalformedURLException murle) {
            System.out.println();
            System.out.println("MalformedURLException");
            System.out.println(murle);
        }
        catch (RemoteException re) {
            System.out.println();
            System.out.println("RemoteException");
            System.out.println(re);
        }
        catch (NotBoundException nbe) {
            System.out.println();
            System.out.println("NotBoundException");
            System.out.println(nbe);
        }
        catch (java.lang.ArithmeticException ae) {
            System.out.println();
            System.out.println("java.lang.ArithmeticException");
            System.out.println(ae);
        }
    }
    public static void setCheckInDetails() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Enter location: ");
    	String inputLocation = input.nextLine();
    	setCheckInTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    	setLocation = inputLocation;
    	setDate = LocalDate.now();
    	
    }
    public static void setCheckOutDetails() {
    	setCheckOutTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
