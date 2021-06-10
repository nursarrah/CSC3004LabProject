import java.time.LocalDate;
import java.time.LocalTime;

public interface login extends java.rmi.Remote{
	 	
	public String userLogin(String userName)
 			throws java.rmi.RemoteException;
	 
	public void setName(String name)
			throws java.rmi.RemoteException;
		
    public String getName()
        throws java.rmi.RemoteException;

    public void setNRIC(String nric)
			throws java.rmi.RemoteException;
	    
    public String getNRIC()
        throws java.rmi.RemoteException;

    public void setLocation(String location)
			throws java.rmi.RemoteException;
	    
    public String getLocation()
        throws java.rmi.RemoteException;
    
    public void setDate(LocalDate date)
			throws java.rmi.RemoteException;
	    
    public LocalDate getDate()
        throws java.rmi.RemoteException;
	    
    public void setCheckOutTime(LocalTime checkedOut)
			throws java.rmi.RemoteException;
    
    public LocalTime getCheckOutTime()
            throws java.rmi.RemoteException;
	    
    public void setCheckInTime(LocalTime checkedIn)
			throws java.rmi.RemoteException;
	    
    public LocalTime getCheckInTime()
        throws java.rmi.RemoteException;	
    
    public void enterLocation()
    		throws java.rmi.RemoteException;
    
    public void addLocationToDB(String nric, String location, String date, String checkedInTime)
    		throws java.rmi.RemoteException;
}
