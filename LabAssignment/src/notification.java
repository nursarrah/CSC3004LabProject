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
	
	class MyClass
	{
	@JsonProperty
	private String Name;
	@JsonProperty
	private String CreationDate;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
		Object remoteAccessObj = new JSONParser().parse(new FileReader("remoteaccessdb.json"));
		JSONObject jo = (JSONObject) remoteAccessObj;
		
		//get declaration info
		String declaredLocation = (String) jo.get("location");
        String declaredDate = (String) jo.get("date");
        String declaredTime = (String) jo.get("time");
        
        //read safe entry db
        FileReader reader = new FileReader("safeentrydb.json");
        JSONParser jsonParser = new JSONParser();
        Object safeEntryObj = jsonParser.parse(reader);
        JSONArray clientList = (JSONArray) safeEntryObj;
        
        
        //clients who visited the location
        List<Integer> clientArrayNumber = new ArrayList<>();
        
        //check for visited location
        for (int i=0; i < clientList.size(); i++) {
        	JSONObject clientObject = (JSONObject) clientList.get(i);
        	JSONObject client = (JSONObject) clientObject.get("client");
        	JSONObject location = (JSONObject) client.get("visitedlocation");
        	String place = (String) location.get("place");
        	if (place.equals(declaredLocation)) {
        		clientArrayNumber.add(i);
        	}
        }
        
        //calculate declared date and find 14 days ago date to have range
        LocalDate endDateRange = LocalDate.parse(declaredDate);
        LocalDate startDateRange = endDateRange.minusDays(14);
        
 

       
        //check date range to know affected client
        for ( int j=0; j< clientArrayNumber.size(); j++) {
        	JSONObject clientObject = (JSONObject) clientList.get(clientArrayNumber.get(j));
        	JSONObject client = (JSONObject) clientObject.get("client");
        	JSONObject location = (JSONObject) client.get("visitedlocation");
        	String date = (String) location.get("date");
        	LocalDate clientDate = LocalDate.parse(date);
        	if(clientDate.isAfter(startDateRange) || clientDate.isBefore(endDateRange) || clientDate.isEqual(endDateRange)){
        		
        		}
        	} 
        		  
		  //clear clientArray Number
		 // clientArrayNumber.clear();
		  
		  
        }
	
}
 	
