import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.annotation.JsonProperty;

 

public class notification {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
		
		
		//read remoteaccessdb
        FileReader reader = new FileReader("remoteaccessdb.json");
        JSONParser jsonParser = new JSONParser();
        Object remoteAccessObj = jsonParser.parse(reader);
        JSONArray remoteAccessDetails = (JSONArray) remoteAccessObj;
        
        JSONObject detailsObject = (JSONObject)remoteAccessDetails.get(0);
    	JSONObject details = (JSONObject) detailsObject.get("details");
        String declaredLocation = (String) details.get("place");
        String declaredDate = (String) details.get("date");
        String declaredCheckInTime = (String) details.get("checkInTime");
        String declaredCheckOutTime = (String) details.get("checkOutTime");


        //read safe entry db
        FileReader safeEntryReader = new FileReader("safeentrydb.json");
        JSONParser safeEntryJsonParser = new JSONParser();
        JSONObject data = (JSONObject)safeEntryJsonParser.parse(safeEntryReader);
        JSONArray client = (JSONArray) data.get("client");
		JSONObject getUser  = (JSONObject) client.get(0);
        JSONArray visitedLocation = (JSONArray) getUser.get("visitedlocation");


                
        //calculate declared date and find 14 days ago date to have range
        LocalDate endDateRange = LocalDate.parse(declaredDate);
        LocalDate startDateRange = endDateRange.minusDays(14);
        
        //check for visited location
//        for (int i=0; i < clientList.size(); i++) {
//        	JSONObject clientObject = (JSONObject) clientList.get(i);
//        	JSONObject client = (JSONObject) clientObject.get("client");
//        	JSONObject location = (JSONObject) client.get("visitedlocation");
//        	String place = (String) location.get("place");
//        	if (place.equals(declaredLocation)) {
//        		clientArrayNumber.add(i);
//        	}
//        }
               
//        //check date range to know affected client
//        for ( int j=0; j< clientArrayNumber.size(); j++) {
//        	JSONObject clientObject = (JSONObject) clientList.get(clientArrayNumber.get(j));
//        	JSONObject client = (JSONObject) clientObject.get("client");
//        	JSONObject location = (JSONObject) client.get("visitedlocation");
//        	String date = (String) location.get("date");
//        	LocalDate clientDate = LocalDate.parse(date);
//        	if(clientDate.isAfter(startDateRange) || clientDate.isBefore(endDateRange) || clientDate.isEqual(endDateRange)){
//        		
//        		}
//        	} 
//        		  	  
        }
	
}
 	
