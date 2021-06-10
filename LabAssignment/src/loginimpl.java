import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class loginimpl  extends java.rmi.server.UnicastRemoteObject implements login {
	
	private String name, nric, location;
	LocalTime checkedIn, checkedOut;
	LocalDate date;
	
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
	public void setName(String name) throws java.rmi.RemoteException{
		this.name=name;
	}
	
    public String getName() throws java.rmi.RemoteException{
    	return name;
    }
	    
    public void setNRIC(String nric) throws java.rmi.RemoteException{
    	this.nric=nric;
    }
	    
    public String getNRIC() throws java.rmi.RemoteException{
    	return nric;
    }

    public void setLocation(String location) throws java.rmi.RemoteException{
    	this.location=location;
    }
	    
    public String getLocation() throws java.rmi.RemoteException{
    	return location;
    }
	    
    public void setCheckInTime(LocalTime checkedIn) throws java.rmi.RemoteException{
    	this.checkedIn=checkedIn;
    }
	    
    public LocalTime getCheckInTime() throws java.rmi.RemoteException{
    	return checkedIn;
    }
	    
    public void setCheckOutTime(LocalTime checkedOut) throws java.rmi.RemoteException{
    	this.checkedOut=checkedOut;
    }
	    
    public LocalTime getCheckOutTime() throws java.rmi.RemoteException{
    	return checkedOut;
    }
	public void enterLocation() throws java.rmi.RemoteException{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter location: ");
    	String location = input.nextLine();
	}
	public void setDate(LocalDate date) throws java.rmi.RemoteException{
		this.date=date;
	}
	public LocalDate getDate() throws java.rmi.RemoteException{
		return date;
	}
	/**
	 * addLocationToDB method 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void addLocationToDB(String nric, String location, String date, String checkedInTime) throws java.rmi.RemoteException {
		
		try {
			Reader reader = new FileReader("safeentrydb.json");
			
    		JSONArray users = (JSONArray) JSONValue.parse(reader);
    		JSONObject firstUser = (JSONObject) users.get(0);
			
    		JSONArray getVisitedlocation = (JSONArray) firstUser.get("visitedlocation");
    		JSONObject visitedLocation = new JSONObject();
    		visitedLocation.put("place", location);
    		visitedLocation.put("date", date);
	        visitedLocation.put("checkInTime", checkedInTime);  
	        getVisitedlocation.add(visitedLocation);
			try {
				Writer file = new FileWriter("safeentrydb.json");
				users.writeJSONString(file);
				file.flush();
				file.close();
			} catch (IOException e) {
				System.out.println("An error occurred.Please try again.");
				e.printStackTrace();
			}  
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.Please try again.");
			e.printStackTrace();
		}	
	}
}
