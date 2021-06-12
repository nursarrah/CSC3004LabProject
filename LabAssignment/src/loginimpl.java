import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class loginimpl  extends java.rmi.server.UnicastRemoteObject implements login {
	
	private String name, nric, location, checkedIn, checkedOut;
	private boolean success = false;
	LocalDate date;
	JSONParser parser = new JSONParser();
	Reader reader;
	
	protected loginimpl() throws RemoteException {
		super();
	}
	@SuppressWarnings("unchecked")
	public String userLogin(String a) throws java.rmi.RemoteException {
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
	public void setDate(LocalDate date) throws java.rmi.RemoteException{
		this.date=date;
	}
	public LocalDate getDate() throws java.rmi.RemoteException{
		return date;
	}
	
	/**
	 * Check In user and add Check In details (place, date, check in time) to database (safeentrydb.json)
	 * 
	 *  @param	nric			NRIC of logged in user
	 *  @param	location		current location 
	 *  @param	date			current date
	 *  @param	checkedInTime	current time
	 *  @return	success			the check in state true(success) or false(failure)
	 */
	@SuppressWarnings("unchecked")
	public boolean checkIn(String nric, String location, String date, String checkedInTime) throws java.rmi.RemoteException {
		
		try {
			reader = new FileReader("safeentrydb.json");
			JSONObject data = (JSONObject) parser.parse(reader);
			JSONArray client = (JSONArray) data.get("client");
			
			// loop through array of client
			 for(int i = 0; i < client.size(); i++) {
				 JSONObject getUser  = (JSONObject) client.get(i);
				 String userNRIC = (String) getUser.get("nric");
				 // if nric in database matches user account, add check in details to db
				 if(userNRIC.equals(nric)) {
					 JSONArray visitedLocation = (JSONArray) getUser.get("visitedlocation");
					 JSONObject newVisitedLocation = new JSONObject();
					 newVisitedLocation.put("place", location);
					 newVisitedLocation.put("date", date);
					 newVisitedLocation.put("checkInTime", checkedInTime);  
					 visitedLocation.add(newVisitedLocation);
					 try {
						 Writer file = new FileWriter("safeentrydb.json");
						 data.writeJSONString(file);
						 file.flush();
						 file.close();
						 success = true;
					 } catch (IOException e) {
						 System.out.println("An error occurred.Please try again.");
						 success = false;
					}
				 }
			 }     
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.Please try again.");
		} catch (IOException e) {
			System.out.println("An error occurred.Please try again.");
		} catch (ParseException e) {
			System.out.println("An error occurred.Please try again.");
		}
		return success;
	}
	/**
	 * Check Out user and add Check Out details (check out time) to database (safeentrydb.json)
	 * 
	 *  @param	nric			NRIC of logged in user
	 *  @param	location		checked in location 
	 *  @param	date			checked in date
	 *  @param	checkedInTime	checked in time
	 *  @param	checkedOutTime	current time
	 *  @return	success			the check out state true(success) or false(failure)
	 */
	@SuppressWarnings("unchecked")
	public boolean checkOut(String nric, String location, String date, String checkedInTime, String checkedOutTime) throws java.rmi.RemoteException {
		
		try {
			reader = new FileReader("safeentrydb.json");
			JSONObject data = (JSONObject) parser.parse(reader);
			JSONArray client = (JSONArray) data.get("client");
			// loop through array of client
			 for(int i = 0; i < client.size(); i++) {
				 JSONObject getUser  = (JSONObject) client.get(i);
				 String userNRIC = (String) getUser.get("nric");
				 // if nric in database matches user account, add check in details to db
				 if(userNRIC.equals(nric)) {
					 JSONArray visitedLocation = (JSONArray) getUser.get("visitedlocation");
					 for (int c = 0; c < visitedLocation.size();c++) {
						 JSONObject getVisitedLocation = (JSONObject) visitedLocation.get(c);
						 String getLocation = (String) getVisitedLocation.get("place");
						 String getCheckInTime = (String) getVisitedLocation.get("checkInTime");
						 String getDate = (String) getVisitedLocation.get("date");
						 if(getLocation.equals(location) && getCheckInTime.equals(checkedInTime) && getDate.equals(date)) {
							 getVisitedLocation.put("checkOutTime", checkedOutTime); 
						 }
					 }					 
					 try {
						 Writer file = new FileWriter("safeentrydb.json");
						 data.writeJSONString(file);
						 file.flush();
						 file.close();
						 success = true;
					 } catch (IOException e) {
						 System.out.println("An error occurred. Please try again.");
						 success = false;
					}
				 }
			 }     
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.Please try again.");
		} catch (IOException e) {
			System.out.println("An error occurred.Please try again.");
		} catch (ParseException e) {
			System.out.println("An error occurred.Please try again.");
		}
		return success;
	}
	/**
	 * Check Out user and add Check Out details (check out time) to database (safeentrydb.json)
	 * 
	 *  @param	nric			NRIC of logged in user
	 *  @return	historyList		list of past visited location of logged in user
	 */
	public ArrayList<String> viewHistory(String nric) throws java.rmi.RemoteException {
		ArrayList<String> historyList = new ArrayList<>();
		try {
			reader = new FileReader("safeentrydb.json");
			JSONObject data = (JSONObject) parser.parse(reader);
			JSONArray client = (JSONArray) data.get("client");
			// loop through array of client
			for(int c = 0; c < client.size(); c++) {
				JSONObject getUser  = (JSONObject) client.get(c);
				String userNRIC = (String) getUser.get("nric");
				// if nric in database matches user account, add check in details to db
				if(userNRIC.equals(nric)) {
					JSONArray visitedLocation = (JSONArray) getUser.get("visitedlocation");
			        for(int i = 0; i < visitedLocation.size(); i++) {
				        JSONObject getVisitedLocation = (JSONObject) visitedLocation.get(i);				
						String displayPlace = (String) getVisitedLocation.get("place");
						String displayDate = (String) getVisitedLocation.get("date");
						String displayCheckInTime = (String) getVisitedLocation.get("checkInTime");
						String displayCheckOutTime = (String) getVisitedLocation.get("checkOutTime");
						String visitedPlace = "\n" + displayDate + " " + displayPlace + " " + displayCheckInTime + "-" + displayCheckOutTime;
						historyList.add(visitedPlace);
			        }
		        }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return historyList;
	}
}
