
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
    public static void main(String[] args) {
    	
		ArrayList<String> nrics = new ArrayList<String>();
	    final String INPUT_PROMPT = "Please key in value:"
                + "\n1 To Check In"
                + "\n2 To View History"
                + "\nb Back To Main Menu"
                + "\nq To Quit"
                + "\n";
	    String name, nric, location, checkin, checkout;
	    LocalDate  date;
	    
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
		
		login login = (login) Naming.lookup("rmi://localhost/LoginService");

		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter NRIC to login: ");
	    String userNRIC = input.nextLine();
	    login.setNRIC(userNRIC);
	    System.out.println("Welcome " + login.getNRIC() + ",");
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
        	    	
        	    	System.out.println("Enter location: ");
        	    	String inputLocation = input.nextLine();
        	    	String formattedCheckInTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        	    	// set location, date & check in time
        	    	login.setLocation(inputLocation); login.setDate(LocalDate.now()); login.setCheckInTime(formattedCheckInTime);
        	    	
        	    	nric = login.getNRIC();
        	    	location = login.getLocation();
        	    	date = login.getDate();
        	    	checkin = login.getCheckInTime();
        	    	
        	    	if(login.checkIn(nric, location, date.toString(), checkin)==true) {
        				// display check in details of user
        	    		System.out.println("Successfully Checked in");
        		    	System.out.println("Name:" + "bob" + "\nNRIC: "+ nric + "\nLocation: " + location + "\nDate: " + date + "\nCheck in Time : " + checkin);
            	    	System.out.println("[O] to Check Out");
            	    	userInput = input.nextLine();
            	    	// 'O' to check out 
            	    	if(userInput.equalsIgnoreCase("O")) {
            	    		String formattedCheckOutTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            	    		login.setCheckOutTime(formattedCheckOutTime);
            	    		checkout = login.getCheckOutTime();
            	    		if(login.checkOut(nric, location, date.toString(), checkin, checkout)==true) {
            	    			System.out.println("Successfully Checked Out");
            	    			System.out.println("Location: " + location + "\nDate: " + date + "\nCheck Out Time : " + checkout);
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
        	    	
        	    	System.out.println("Enter location: ");
        	    	String inputLocation = input.nextLine();
        	    	String formattedCheckInTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        	    	// set location, date & check in time
        	    	login.setLocation(inputLocation); login.setDate(LocalDate.now()); login.setCheckInTime(formattedCheckInTime);
        	    	// get location, date & check in time
        	    	location = login.getLocation(); date = login.getDate(); checkin = login.getCheckInTime();
        	    	
        	    	// Input Number of Group
        	    	System.out.println("Enter Number of Group: ");
        	    	String inputNoOfPeople = input.nextLine();
        	    	System.out.println("Enter NRIC(s): ");
        	    	// Input NRIC of people in the group
        	    	for(int i=0; i<Integer.parseInt(inputNoOfPeople); i++) {
        	    		String groupNric = input.nextLine();
        	    		login.setNRIC(groupNric);
        	    		nrics.add(login.getNRIC());
        	    	}
        	    	for (int n=0; n < nrics.size(); n++) {
         	    		if(login.checkIn(nrics.get(n), location, date.toString(), checkin)==true) {
                		    System.out.println("Name:" + "bob" + "\nNRIC: "+ nrics.get(n));                		    	              		    	
                	    }
         	    		else {
         	    			System.out.println("Please try again");
         	    		}
        	    	}
        	    	System.out.println("\nSuccessfully Checked in \nLocation: " + location + "\nDate: " + date + "\nCheck in Time : " + checkin);
        	    	System.out.println("[O] to Check Out");
        	    	userInput = input.nextLine();
        	    	// 'O' to Check Out 
        	    	if(userInput.equalsIgnoreCase("O")) {
        	    		String formattedCheckOutTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        	    		login.setCheckOutTime(formattedCheckOutTime);
        	    		checkout = login.getCheckOutTime();
        	    		for (int count=0; count < nrics.size(); count++) {
	        	    		if(login.checkOut(nrics.get(count), location, date.toString(), checkin, checkout)==true){
		        	    			System.out.println("Successfully Checked Out");
		        	    	}
        	    		}
        	    		System.out.println("Location: " + location + "\nDate: " + date +  "\nCheck Out Time : " + checkout);
        	    		//'B' Back to Main Menu
            	    	System.out.println("[B] to back to Main Menu");
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
            	System.out.println(login.viewHistory(login.getNRIC()));
            	//'B' to Back to Main Menu
    	    	System.out.println("[B] to back to Main Menu");
    	    	userInput = input.nextLine();
            }
            else {
            	 System.out.println("Please enter a valid key");
                 userInput = "b";
            }
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
}
