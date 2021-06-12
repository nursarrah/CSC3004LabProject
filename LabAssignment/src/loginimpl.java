import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
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
	public String userLogin(String nric) throws java.rmi.RemoteException {
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
					name = (String) getUser.get("name");
		        }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
			} }
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return historyList;
			
		}
		
		public String notificationFeature(String nric) throws java.rmi.RemoteException{
			//read remoteaccessdb
//	        FileReader reader;
			String alertMessage = null;
			try {
				FileReader reader = new FileReader("remoteaccessdb.json");
				JSONParser jsonParser = new JSONParser();
		        Object remoteAccessObj;
				try {
					remoteAccessObj = jsonParser.parse(reader);
					 JSONArray remoteAccessDetails = (JSONArray) remoteAccessObj;
				     JSONObject detailsObject = (JSONObject)remoteAccessDetails.get(0);
				    JSONObject details = (JSONObject) detailsObject.get("details");
				    String declaredLocation = (String) details.get("place");
				    String declaredDate = (String) details.get("date");
				    String declaredCheckInTime = (String) details.get("checkInTime");
				    String declaredCheckOutTime = (String) details.get("checkOutTime");
				    
				  //calculate declared date and find 14 days ago date to have range
			        LocalDate endDateRange = LocalDate.parse(declaredDate);
			        LocalDate startDateRange = endDateRange.minusDays(14);
				    
				    FileReader safeEntryReader = new FileReader("safeentrydb.json");
			        JSONParser safeEntryJsonParser = new JSONParser();
			        JSONObject data = (JSONObject)safeEntryJsonParser.parse(safeEntryReader);
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
								String place = (String) getVisitedLocation.get("place");
								if (place.equals(declaredLocation)) {
									String date = (String) getVisitedLocation.get("date");
									LocalDate clientDate = LocalDate.parse(date);
									if(clientDate.isAfter(startDateRange) || clientDate.isEqual(endDateRange)){
						        			alertMessage = "You were at a place visited by a covid 19 case." 
						        							+ "\nCovid 19 patient's case information:"
						        							+ "\nPlace: " + declaredLocation.toString()
						        							+ "\nDate: "  + declaredDate.toString()
						        							+ "\nCheck In Time: " + declaredCheckInTime.toString()
						        							+ "\nCheck Out Time: " + declaredCheckOutTime.toString();
						        		}else {
											alertMessage = "No exposure alerts.";
										}
								}else {
									alertMessage = "No exposure alerts.";
								}
						}}}}
				    
						 catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	        
			return alertMessage;
	}
		}
