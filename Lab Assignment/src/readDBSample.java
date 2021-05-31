import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class readDBSample {
	
	 @SuppressWarnings("unchecked")
	    public static void main(String[] args) 
	    {
	        //JSON parser object to parse read file
	        JSONParser jsonParser = new JSONParser();
	         
	        try (FileReader reader = new FileReader("safeentrydb.json"))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONArray clientList = (JSONArray) obj;
	            
	            //prints out the first array of the object
	            //System.out.println(clientList.get(0));
	             
	            //Iterate over employee array
	            clientList.forEach( emp -> parseClientObject( (JSONObject) emp ) );
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    private static void parseClientObject(JSONObject client) 
	    {
	    	//Get client object within list
	        JSONObject clientObject = (JSONObject) client.get("client");
	    
	        
	        //get location object within list
	        JSONObject location = (JSONObject) clientObject.get("visitedlocation");
	        
	        
	        //if else to print only if have "Jennie"
//	        String name = (String) clientObject.get("name");    
//	        if( name.equals("Jennie")) {
//		        System.out.println(name);
//	        }else {
//		        System.out.println("No Jennie");
//	        }
	         
	        //Get client name
	        String name = (String) clientObject.get("name");    
	        System.out.println(name);
	         
	        //Get client nric
	        String nric = (String) clientObject.get("nric");  
	        System.out.println(nric);
	         
	        //Get client visited places
	        String place = (String) location.get("place");    
	        System.out.println(place);
	        
	        //get client check in date
	        String date = (String) location.get("date");    
	        System.out.println(date);
	        
	        
	        //get client check in time
	        String timeIn = (String) location.get("checkInTime");    
	        System.out.println(timeIn);
	        
	      //get client check out time
	        String timeOut = (String) location.get("checkOutTime");    
	        System.out.println(timeOut);
	        
	        System.out.println();
	        
	   }

}
