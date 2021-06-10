import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.rmi.RemoteException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class remoteaccessimpl extends java.rmi.server.UnicastRemoteObject implements remoteaccess{
	String location, date, checkedIn, checkedOut;
	
	protected remoteaccessimpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setLocation(String location) throws java.rmi.RemoteException{
    	this.location=location;
    }
	    
    public String getLocation() throws java.rmi.RemoteException{
    	return location;
    }
	    
    public void setCheckInTime(String checkedIn) throws java.rmi.RemoteException{
    	this.checkedIn=checkedIn;
    }
	    
    public String getCheckInTime() throws java.rmi.RemoteException{
    	return checkedIn;
    }
	    
    public void setCheckOutTime(String checkedOut) throws java.rmi.RemoteException{
    	this.checkedOut=checkedOut;
    }
	    
    public String getCheckOutTime() throws java.rmi.RemoteException{
    	return checkedOut;
    }

	public void setDate(String date) throws java.rmi.RemoteException{
		this.date=date;
	}
	public String getDate() throws java.rmi.RemoteException{
		return date;
	}
	/**
	 * addLocationToDB method 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void addLocationToDB(String location, String date, String checkedInTime, String checkedOutTime) throws java.rmi.RemoteException {
		
		try {
			Reader reader = new FileReader("affectedclientsdb.json");
			
    		JSONArray users = (JSONArray) JSONValue.parse(reader);
    		JSONObject firstUser = (JSONObject) users.get(0);
			
    		JSONArray getVisitedlocation = (JSONArray) firstUser.get("visitedlocation");
    		JSONObject visitedLocation = new JSONObject();
    		
    		visitedLocation.put("place", location);
    		visitedLocation.put("date", date);
	        visitedLocation.put("checkInTime", checkedInTime); 
	        visitedLocation.put("checkOutTime", checkedOutTime);
	        getVisitedlocation.add(visitedLocation);
			try {
				Writer file = new FileWriter("affectedclientsdb.json");
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
