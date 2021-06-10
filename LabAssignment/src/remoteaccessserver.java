import java.rmi.Naming;
public class remoteaccessserver {
	static int port = 1099;
	   //calculatorserver constructor
	   public remoteaccessserver() {

			 try {
			 		//Construct a new CalculatorImpl object and bind it to the local rmiregistry
	     		//N.b. it is possible to host multiple objects on a server
//				 calculator c = new calculatorimpl();
//				 Naming.rebind("rmi://localhost/CalculatorService", c);
				 
				 remoteaccess remoteaccess = new remoteaccessimpl();
				 Naming.rebind("rmi://localhost/RemoteAccessService", remoteaccess);

//				 login login = new loginimpl();
//				 Naming.rebind("rmi://localhost/LoginService", login);

	     }
	     catch (Exception e) {
	       System.out.println("Server Error: " + e);
	     }
	   }

	   public static void main(String args[]) {
	     	//Create the new Calculator server
				if (args.length == 1)
					port = Integer.parseInt(args[0]);
					new remoteaccessserver();
	   		}

}
