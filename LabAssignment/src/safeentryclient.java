

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class safeentryclient {
    public static void main(String[] args) {
    	
		String name, nric, location, date, inTime;
		ArrayList<String> nrics = new ArrayList<String>();
		
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
        
	    System.out.println("Individual Check In? [Y/N]");
	    String choice = input.nextLine();
	    if(choice.equals("Y")) {
	    	
	    	System.out.println("Enter location: ");
	    	String inputLocation = input.nextLine();
	    	login.setLocation(inputLocation);
	    	login.setDate(LocalDate.now());
	    	login.setCheckInTime(LocalTime.now());
	    	
	    	String formattedDate = login.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
	    	String formattedTime = login.getCheckInTime().format(DateTimeFormatter.ofPattern("HH:mm"));
	    	
	    	nric = login.getNRIC();
	    	location = login.getLocation();
	    	date = formattedDate;
	    	inTime = formattedTime;
	    		
	    	login.addLocationToDB(nric, location, date, inTime);
	    	login.viewHistory(nric);
	    		
	    }
	    else if(choice.equals("N"))
	    {
	    	System.out.println("Enter location: ");
	    	String inputLocation = input.nextLine();
	    	login.setLocation(inputLocation);
	    	login.setCheckInTime(LocalTime.now());

	    	System.out.println("Enter Number of Group: ");
	    	String inputNoOfPeople = input.nextLine();
	    	int noOfPeople = Integer.parseInt(inputNoOfPeople);
	    	System.out.println("Enter NRIC(s): ");
	    	for(int i=0;i<noOfPeople;i++) {
	    		String groupNric = input.nextLine();
	    		login.setNRIC(groupNric);
	    		nrics.add(groupNric);
	    		
	    		
	    	}
	    }
	    else {
	    	System.out.println("Invalid Input");
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
