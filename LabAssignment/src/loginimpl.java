import java.rmi.RemoteException;

public class loginimpl  extends java.rmi.server.UnicastRemoteObject implements login {

	protected loginimpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	 @SuppressWarnings("unchecked")
	public String userLogin(String a) throws java.rmi.RemoteException {
	      String nric = a;
	      switch (nric) {
	      case "S999991D":
	    	  return "Jennie";
	      case "S999999D":
	    	  return "Lalisa";
	      default:
	    	  return "No NRIC in database.";  
	      }

	 }		 
}
