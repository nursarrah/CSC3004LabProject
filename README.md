# CSC3004 : Lab Assessment

A lab assessment for Cloud and Distributing Computing Module.

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
    3. NRIC:  S999999D as Lalisa

# To Run Remote Access System
1. Right click remoteaccessserver.java > Run as.. > Java Application (to run as server)
2. Right click remoteaccessclient.java > Run as.. > Java Application (to run as client)
3. Enter the following input to put information in remoteaccessdb.json and send notification to affected clients :
    1. Location: suntec
    2. Date: 2021-03-03
    3. Check In Time: 13:25
    4. Check Out Time: 14:45

