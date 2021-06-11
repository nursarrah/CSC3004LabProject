
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class safeentryclient {
    public static void main(String[] args) {
    	
		ArrayList<String> nrics = new ArrayList<String>();
	    final String INPUT_PROMPT = "Please key in a number:"
                + "\n1 To Check In"
                + "\n2 To View History"
                + "\nb Back To Main Menu"
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
            if(userInput.equalsIgnoreCase("Q")) {
                break;
            }
            else if(userInput.equalsIgnoreCase("B")) {
                System.out.print(INPUT_PROMPT);
                userInput = input.nextLine();
            }
            else if(userInput.equals("1")) {
        	    System.out.println("Individual Check In? [Y/N]");
        	    String choice = input.nextLine();
        	    
        	    if(choice.equalsIgnoreCase("Y")) {
        	    	
        	    	System.out.println("Enter location: ");
        	    	String inputLocation = input.nextLine();
        	    	login.setLocation(inputLocation); login.setDate(LocalDate.now()); login.setCheckInTime(LocalTime.now());
        	    	
        	    	String formattedTime = login.getCheckInTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        	    	
        	    	if(login.checkIn(login.getNRIC(), login.getLocation(), login.getDate().toString(), formattedTime)==true) {
        				
        	    		System.out.println("Successfully Checked in");
        		    	System.out.println("Name:" + "bob" + "\nNRIC: "+ login.getNRIC() + "\nLocation: " + login.getLocation() +
        		    			"\nDate: " + login.getDate() + "\nCheck in Time : " + formattedTime);
        		    	
            	    	// Check Out 
            	    	System.out.println("[O] to Check Out");
            	    	userInput = input.nextLine();
            	    	
            	    	if(userInput.equalsIgnoreCase("O")) {
            	    		login.setCheckOutTime(LocalTime.now());
            	    		String formattedCheckOutTime = login.getCheckOutTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            	    		
            	    		if(login.checkOut(login.getNRIC(), login.getLocation(), login.getDate().toString(), formattedTime, formattedCheckOutTime)==true) {
            	    			System.out.println("Successfully Checked Out");
            	    			System.out.println("Location: " + login.getLocation() + "\nDate: " + login.getDate() + "\nCheck in Time : " + formattedTime + "\nCheck Out Time : " + formattedCheckOutTime);
            	    			// Back to Main Menu
                    	    	System.out.println("[B] to back to Main Menu");
                    	    	userInput = input.nextLine();
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
        	    	login.setLocation(inputLocation); login.setDate(LocalDate.now()); login.setCheckInTime(LocalTime.now());
        	    	
        	    	String formattedTime = login.getCheckInTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        	    	System.out.println("Enter Number of Group: ");
        	    	String inputNoOfPeople = input.nextLine();
        	    	int noOfPeople = Integer.parseInt(inputNoOfPeople);
        	    	System.out.println("Enter NRIC(s): ");
        	    	
        	    	for(int i=0; i<noOfPeople; i++) {
        	    		String groupNric = input.nextLine();
        	    		login.setNRIC(groupNric);
        	    		nrics.add(login.getNRIC());
        	    	}
        	    	
        	    	for (int n=0; n < nrics.size(); n++) {
         	    		if(login.checkIn(nrics.get(n), login.getLocation(), login.getDate().toString(), formattedTime)==true) {
         	    			System.out.println("Successfully Checked in");
                		    System.out.println("Name:" + "bob" + "\nNRIC: "+ nrics.get(n));                		    	              		    	
                	    }
         	    		else {
         	    			System.out.println("Please try again");
         	    		}
        	    	}
        	    	System.out.println("Location: " + login.getLocation() + "\nDate: " + login.getDate() + "\nCheck in Time : " + formattedTime);
        	    	
        	    	// Check Out 
        	    	System.out.println("[O] to Check Out");
        	    	userInput = input.nextLine();
        	    	
        	    	if(userInput.equalsIgnoreCase("O")) {
        	    		login.setCheckOutTime(LocalTime.now());
        	    		String formattedCheckOutTime = login.getCheckOutTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        	    		for (int count=0; count < nrics.size(); count++) {
	        	    		if(login.checkOut(nrics.get(count), login.getLocation(), login.getDate().toString(), formattedTime, formattedCheckOutTime)==true){
		        	    			System.out.println("Successfully Checked Out");
		        	    		}
	        	    	}
        	    		System.out.println("Location: " + login.getLocation() + "\nDate: " + login.getDate() + "\nCheck in Time : " + formattedTime + "\nCheck Out Time : " + formattedCheckOutTime);
        	    		// Back to Main Menu
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
            else if(userInput.equals("2")) {
            	System.out.println(login.viewHistory(login.getNRIC()));
            	// Back to Main Menu
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
