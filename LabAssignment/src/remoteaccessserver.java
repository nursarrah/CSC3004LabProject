

import java.rmi.Naming;

public class remoteaccessserver {
	static int port = 1099;
	   public remoteaccessserver() {

			 try {			 
				 remoteaccess remoteaccess = new remoteaccessimpl();
				 Naming.rebind("rmi://localhost/RemoteAccessService", remoteaccess);

	     }
	     catch (Exception e) {
	       System.out.println("Server Error: " + e);
	     }
	   }

	   public static void main(String args[]) {
				if (args.length == 1)
					port = Integer.parseInt(args[0]);
					new remoteaccessserver();
	   		}

}
