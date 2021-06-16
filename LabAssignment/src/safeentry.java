import java.util.ArrayList;

public interface safeentry extends java.rmi.Remote{
	
	public String userLogin(String nric)
			throws java.rmi.RemoteException;
    
    public boolean checkIn(String nric, String location, String date, String checkedInTime)
    		throws java.rmi.RemoteException;
    
    public ArrayList<String> viewHistory(String nric)
    		throws java.rmi.RemoteException;
    
    public boolean checkOut(String nric, String location, String date, String checkedInTime, String checkedOutTime)
    		throws java.rmi.RemoteException;
    
    public String notificationFeature(String nric) 
    		throws java.rmi.RemoteException;
}
