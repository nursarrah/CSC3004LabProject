
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
	 * Add declaration information to database, remoteaccessdb,json
	 * 
	 *  @param	location			location of the declared covid19 case
	 *  @param	date				date of the declared covid19 case
	 *  @param	checkedInTime		check in time of the declared covid19 case
	 *  @param	checkedOutTime		check out time of the declared covid19 case

	 */
	@SuppressWarnings("unchecked")
	public void addDeclarationToDB(String location, String date, String checkedInTime, String checkedOutTime) throws java.rmi.RemoteException {
		
		JSONObject details = new JSONObject();
		
		details.put("place", location);
		details.put("date", date);
		details.put("checkInTime", checkedInTime); 
		details.put("checkOutTime", checkedOutTime);
		
		JSONObject detailsObject = new JSONObject(); 
		detailsObject.put("details", details);
		
		JSONArray detailsList = new JSONArray();
		detailsList.add(detailsObject);
      
		try {
			Writer file = new FileWriter("remoteaccessdb.json");
			file.write(detailsList.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println("An error occurred.Please try again.");
			e.printStackTrace();
		}	
	}



}
