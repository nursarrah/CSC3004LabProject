

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class safeentryclient {
    public static void main(String[] args) {
    	
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH:mm:ss");
		String name, nric, location, date, inTime;

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
	    // Input user NRIC upon login
		System.out.println("Enter NRIC to login: ");
	    String userNRIC = input.nextLine();
	    login.setNRIC(userNRIC);
	    System.out.println("Welcome " + login.getNRIC() + ",");
        
	    System.out.println("Individual Check In? [Y/N]");
	    String choice = input.nextLine();
	    if(choice.equals("Y"))
	    {
	    	System.out.println("Enter location: ");
	    	String inputLocation = input.nextLine();
	    	login.setLocation(inputLocation);
	    	login.setDate(LocalDate.now());
	    	login.setCheckInTime(LocalTime.now());
//	    	try {
	    	
	    	nric = login.getNRIC();
	    	location = login.getLocation();
	    	date = login.getDate().toString();
	    	inTime = login.getCheckInTime().toString();
	    	System.out.println(nric + " " + location + " " + date + " " + inTime);
	    	login.addLocationToDB(nric, location, date, inTime);
	            		            
//	    		Reader reader = new FileReader("safeentrydb.json");
//	    		JSONParser parser = new JSONParser();
//	    		
//	    		JSONArray users = (JSONArray) JSONValue.parse(reader);
//	    		JSONObject firstUser = (JSONObject) users.get(0);
//
//	    		JSONArray getVisitedlocation = (JSONArray) firstUser.get("visitedlocation");
//	    		JSONObject visitedLocation = new JSONObject();
//	    		visitedLocation.put("place", login.getLocation());
//	    		visitedLocation.put("date", login.getDate().toString());
//		        visitedLocation.put("checkInTime", login.getCheckInTime().toString());
//		        
//		        getVisitedlocation.add(visitedLocation);
//		        
//		        Writer file = new FileWriter("safeentrydb.json");
//		        users.writeJSONString(file);
//				file.flush();
//				file.close();
//	    	    System.out.println("Successfully Checked in");
	    	    System.out.println("Name: Bob" + "\n" +
	    	    					" , NRIC: "+ login.getNRIC() + "\n" +
	    	    					" , Location: " + login.getLocation() + "\n" +
	    	    					" , Date: " + login.getDate().toString() + "\n" +
	    	    					" , Check in Time : " + login.getCheckInTime().toString());
	    		
//	    	} 
//	    	catch (IOException e) {
//	    	      System.out.println("An error occurred.");
//	    	      e.printStackTrace();
//	    	 }
	    }
	    else if(choice.equals("N"))
	    {
	    	System.out.println("Enter location: ");
	    	String inputLocation = input.nextLine();
	    	login.setLocation(inputLocation);
	    	login.setCheckInTime(LocalTime.now());
	    	// enter NRIC(s)
	    	System.out.println("Enter Number of Group: ");
	    	String inputNoOfPeople = input.nextLine();
	    	int noOfPeople = Integer.parseInt(inputNoOfPeople);
	    	System.out.println("Enter NRIC(s): ");
	    	for(int i=0;i<noOfPeople;i++) {
	    		String groupNric = input.nextLine();
	    		// add nric to array(?)
	    		login.setNRIC(groupNric);
	    		try {
	    			// add location, date, check in time detail to file
		    		FileWriter myWriter = new FileWriter("C:\\Users\\Amirah\\eclipse-workspace\\CS3004\\data.txt", true);
		            String formatDateTime = login.getCheckInTime().format(format);  
		    	    myWriter.write(login.getNRIC() + "," + login.getLocation() + "," + formatDateTime+"\n");
		    	    myWriter.close();
			    	// display names, nric, location and check in time
			    	//display name and nric
			    	//display location, date and time
	    		}
		    	catch (IOException e) {
		    	      System.out.println("An error occurred.");
		    	      e.printStackTrace();
		    	 }
	    	}
	    }
	    else {
	    	System.out.println("Invalid Input");
	    }
	    
  }
  // Catch the exceptions that may occur - rubbish URL, Remote exception
	// Not bound exception or the arithmetic exception that may occur in
	// one of the methods creates an arithmetic error (e.g. divide by zero)
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
