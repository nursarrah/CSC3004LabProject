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
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class loginimpl  extends java.rmi.server.UnicastRemoteObject implements login {
	
	private String name, nric, location;
	LocalTime checkedIn, checkedOut;
	LocalDate date;
	JSONParser parser = new JSONParser();
	Reader reader;
	protected loginimpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
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
			reader = new FileReader("safeentrydb.json");
			
			JSONObject data = (JSONObject) parser.parse(reader);
			
			JSONArray client = (JSONArray) data.get("client");
			
			JSONObject getUser  = (JSONObject) client.get(0);
			String userName = (String) getUser.get("name");
			String userNRIC = (String) getUser.get("nric");
			
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
				System.out.println("Successfully Checked in");
		    	System.out.println("Name:" + userName + "\n" +
    			" , NRIC: "+ userNRIC + "\n" +
    			" , Location: " + location + "\n" +
    			" , Date: " + date + "\n" +
    			" , Check in Time : " + checkedInTime);
			} catch (IOException e) {
				System.out.println("An error occurred.Please try again.");
				e.printStackTrace();
			}  
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.Please try again.");
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}	
	}
	
	public void viewHistory(String nric) throws java.rmi.RemoteException {
		
		try {
			reader = new FileReader("safeentrydb.json");
			JSONObject data = (JSONObject) parser.parse(reader);
			
			JSONArray client = (JSONArray) data.get("client");
			
			JSONObject getUser  = (JSONObject) client.get(0);
	        
	        JSONArray visitedLocation = (JSONArray) getUser.get("visitedlocation");
	       
	        for(int i = 0; i < visitedLocation.size(); i++) {
		        JSONObject getVisitedLocation = (JSONObject) visitedLocation.get(i);
				
				String displayPlace = (String) getVisitedLocation.get("place");
				String displayDate = (String) getVisitedLocation.get("date");
				String displayCheckInTime = (String) getVisitedLocation.get("checkInTime");
				String displayCheckOutTime = (String) getVisitedLocation.get("checkOutTime");
				System.out.println("Date: " + displayDate + "\n" + displayPlace + " " + displayCheckInTime + "-" + displayCheckOutTime);
				System.out.println("=====================================================================");
	        }
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
	}
}
