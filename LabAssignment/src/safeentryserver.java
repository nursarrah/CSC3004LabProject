import java.rmi.Naming;	//Import naming classes to bind to rmiregistry

public class safeentryserver {
	static int port = 1099;
	   //safeentryserver constructor
	   public safeentryserver() {

			 try {
			 	//Construct a new safeentryimpl object and bind it to the local rmiregistry
				 safeentry safeEntry = new safeentryimpl();
				 Naming.rebind("rmi://localhost/SafeEntryService", safeEntry);

	     }
	     catch (Exception e) {
	       System.out.println("Server Error: " + e);
	     }
	   }

	   public static void main(String args[]) {
	     	//Create the new SafeEntry server
				if (args.length == 1)
					port = Integer.parseInt(args[0]);
					new safeentryserver();
	   		}
}
