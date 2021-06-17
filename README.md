# CSC3004 : Lab Assessment

A lab assessment for Cloud and Distributing Computing Module.\
For a better readibility, do visit the following repository:\
https://github.com/nursarrah/CSC3004LabProject.git 


# Source code listings:
SafeEntry System
1. Safeentry.java – SafeEntry interface
2. Safeentryimpl.java – Implementation of the SafeEntry Remote Service
3. Safeentryserver.java – SafeEntry Host Server
4. Safeentryclient.java – SafeEntry Client program

Remote Access System
1. Remoteaccess.java – Remote Access Interface
2. Remoteaccessimpl.java – Implementation of the Remote Access Service
3. Remoteaccessserver.java – Remote Access Host Server
4. Remoteaccessclient.java – Remote Access Client program 

# Getting Started
1. Prerequisites : Java Version: 16
2. File > Import project > Import Existing Projects
3. Right click project folder on project explorer > Maven > Update Project
4. Right click project folder > Run as.. > Maven Build (to compile, ensure that build is successful)
5. Start RMI naming service (RMIRegistry) at command prompt

# To Run SafeEntry System
1. Right click safeentryserver.java > Run as.. > Java Application (to start server)
2. Right click safeentryclient.java > Run as.. > Java Application (to run as client)
3. Enter the following input to log in:
    1.  NRIC: S999991D as Jennie  
          OR 
    2. NRIC:  S999999D as Lalisa

4. Check-In & Check-Out
    1. Enter ‘1’ to check-in
    2. Enter ‘Y’ to check-in Individually and ‘N’ to check-in as a group
    3. Enter Location of check-in :\
         Location: Gym
    4. For group check-in,\
        Number of people in a group: 2 \
        NRIC: ‘S999999D’ and ‘S999991D’
    5. Enter ‘O’ to check-out
    6. Enter ‘B’ to back to Main Menu

5. View SafeEntry History
    1. Enter ‘2’ to View SafeEntry History
    2. Enter ‘B’ to back to Main Menu

6. View Notification
    1. Enter ‘3’ to View Notification for possible exposure.
    2. Enter ‘B’ to back to Main Menu
 
7. Enter ‘Q’ to logout the SafeEntry program

# To Run Remote Access System
1. Right click remoteaccessserver.java > Run as.. > Java Application (to run as server)
2. Right click remoteaccessclient.java > Run as.. > Java Application (to run as client)
3. Enter the following input to put information in remoteaccessdb.json and send notification to affected clients :
    1. Location: suntec
    2. Date: 2021-03-03
    3. Check In Time: 13:25
    4. Check Out Time: 14:45

