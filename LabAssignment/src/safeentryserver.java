import java.rmi.Naming;	//Import naming classes to bind to rmiregistry

public class safeentryserver {
	static int port = 1099;
	   //calculatorserver constructor
	   public safeentryserver() {

			 try {
			 		//Construct a new CalculatorImpl object and bind it to the local rmiregistry
	     		//N.b. it is possible to host multiple objects on a server
//				 calculator c = new calculatorimpl();
//				 Naming.rebind("rmi://localhost/CalculatorService", c);
				 
				 login login = new loginimpl();
				 Naming.rebind("rmi://localhost/LoginService", login);

	     }
	     catch (Exception e) {
	       System.out.println("Server Error: " + e);
	     }
	   }

	   public static void main(String args[]) {
	     	//Create the new Calculator server
				if (args.length == 1)
					port = Integer.parseInt(args[0]);
					new safeentryserver();
	   		}
}
