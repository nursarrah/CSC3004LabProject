import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class notification {

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
        
        
        //check date range against affected client, this is where the notification will be implemented
        
        
        
        //erase remoteaccessdb and clear clientArray Number
        //actually update value to nothing lolol
        jo.put("location", " ");
        jo.put("date", " ");
        jo.put("time", " ");
       
        //write into db to remove value
        try (FileWriter file = new FileWriter("remoteaccessdb.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jo.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientArrayNumber.clear();		 

	}

}
