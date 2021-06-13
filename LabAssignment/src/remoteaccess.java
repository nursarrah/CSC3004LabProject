public interface remoteaccess extends java.rmi.Remote {
	
    public void setLocation(String location)
			throws java.rmi.RemoteException;
	    
    public String getLocation()
        throws java.rmi.RemoteException;
    
    public void setDate(String date)
			throws java.rmi.RemoteException;
	    
    public String getDate()
        throws java.rmi.RemoteException;
	    
    public void setCheckOutTime(String checkedOut)
			throws java.rmi.RemoteException;
    
    public String getCheckOutTime()
            throws java.rmi.RemoteException;
	    
    public void setCheckInTime(String checkedIn)
			throws java.rmi.RemoteException;
	    
    public String getCheckInTime()
        throws java.rmi.RemoteException;	
    
    
    public void addDeclarationToDB(String location, String date, String checkedInTime, String CheckedOutTime)
    		throws java.rmi.RemoteException;


}

