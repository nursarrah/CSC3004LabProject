import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class remoteaccessclient {
	  public static void main(String[] args) {
		  String location, date, inTime, outTime;
	       String reg_host = "localhost";
	       int reg_port = 1099;

	       if (args.length == 1) {
	       	   reg_port = Integer.parseInt(args[0]);
	       } else if (args.length == 2) {
	      	   reg_host = args[0];
	      	   reg_port = Integer.parseInt(args[1]);
	      }

		try {

			remoteaccess remoteaccess = (remoteaccess) Naming.lookup("rmi://localhost/RemoteAccessService");
			Scanner input = new Scanner(System.in);
				System.out.println("Please provide necessary input for declaration. ");
		    	System.out.println("Enter location: ");
		    	String inputLocation = input.nextLine();
		    	remoteaccess.setLocation(inputLocation);
		    	System.out.println("Enter Date(yyyy-mm-dd): ");
		    	String inputDate = input.nextLine();
		    	remoteaccess.setDate(inputDate);
		    	System.out.println("Enter Check In Time: ");
		    	String inputCheckInTime = input.nextLine();
		    	remoteaccess.setCheckInTime(inputCheckInTime);
		    	System.out.println("Enter Check Out Time: ");
		    	String inputCheckOutTime = input.nextLine();
		    	remoteaccess.setCheckOutTime(inputCheckOutTime);
		    			    	
		    	location = remoteaccess.getLocation();
		    	date = remoteaccess.getDate();
		    	inTime = remoteaccess.getCheckInTime();
		    	outTime = remoteaccess.getCheckOutTime();
		    	System.out.println("The following information has been added to the database: " + location + " " + date + " " + inTime + " " + outTime);
		    	remoteaccess.addDeclarationToDB(location, date, inTime, outTime);

		    
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
