import java.time.LocalDate;
import java.util.ArrayList;

public interface login extends java.rmi.Remote{
	
	public String userLogin(String nric)
			throws java.rmi.RemoteException;

    public void setLocation(String location)
			throws java.rmi.RemoteException;
	    
    public String getLocation()
        throws java.rmi.RemoteException;
    
    public void setDate(LocalDate date)
			throws java.rmi.RemoteException;
	    
    public LocalDate getDate()
        throws java.rmi.RemoteException;
	    
    public void setCheckOutTime(String checkedOut)
			throws java.rmi.RemoteException;
    
    public String getCheckOutTime()
            throws java.rmi.RemoteException;
	    
    public void setCheckInTime(String checkedIn)
			throws java.rmi.RemoteException;
	    
    public String getCheckInTime()
        throws java.rmi.RemoteException;	
    
    public boolean checkIn(String nric, String location, String date, String checkedInTime)
    		throws java.rmi.RemoteException;
    
    public ArrayList<String> viewHistory(String nric)
    		throws java.rmi.RemoteException;
    
    public boolean checkOut(String nric, String location, String date, String checkedInTime, String checkedOutTime)
    		throws java.rmi.RemoteException;
    
    public String notificationFeature(String nric) throws java.rmi.RemoteException;
}
