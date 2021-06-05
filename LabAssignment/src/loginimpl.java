import java.rmi.RemoteException;


public class loginimpl  extends java.rmi.server.UnicastRemoteObject implements login {

	protected loginimpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	 @SuppressWarnings("unchecked")
	public String userLogin(String a) {
	      String nric = a;
	      String name = " ";
	      switch (nric) {
	      case "S999991D":
	    	  return "Jennie";
	      case "S999999D":
	    	  return "Lalisa";
	      default:
	    	  return "No NRIC in database.";  
      }
	
//	      FileReader reader = new FileReader("safeentrydb.json");
//	        JSONParser jsonParser = new JSONParser();
//	        Object safeEntryObj = jsonParser.parse(reader);
//	        JSONArray clientList = (JSONArray) safeEntryObj;
//	        for (int i=0; i < clientList.size(); i++) {
//	        	JSONObject clientObject = (JSONObject) clientList.get(i);
//	        	JSONObject client = (JSONObject) clientObject.get("client");
//	        	String clientnric = (String) clientObject.get("nric");  
//	        	if (clientnric.equals(a)) {
//	        		String clientname = (String) clientObject.get("name"); 
//	        		name = clientname;
//	        	}
//	        }
//	        return name;
	 }
}
