
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class remoteaccessclient {
	  public static void main(String[] args) {

//	      //use localhost if running the server locally or use IP address of the server
	       String reg_host = "localhost";
	       int reg_port = 1099;

	       if (args.length == 1) {
	       	   reg_port = Integer.parseInt(args[0]);
	       } else if (args.length == 2) {
	      	   reg_host = args[0];
	      	   reg_port = Integer.parseInt(args[1]);
	      }

		try {

		    // Create the reference to the remote object through the remiregistry
//			calculator c = (calculator)
//					Naming.lookup("rmi://localhost/CalculatorService");
			login login = (login) Naming.lookup("rmi://localhost/RemoteAccessService");

	      //Naming.lookup("rmi://localhost/CalculatorService");


		    
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		    
		    
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
