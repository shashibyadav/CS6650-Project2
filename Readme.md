### Author :- Shashi Bhushan Yadav

### Course :- CS6650 - Project 2


This zip contains all the files for the project including Executable jars in folder named "Executables". There are two executables Server.jar and Client.jar for the project.

### **How to run Server Executable** :-

	1.	For TCP protocol only :- 
		-> java -jar Server.jar TCP <server-host> <TCP-port>

	2.	For UDP protocol only :-
		-> java -jar Server.jar UDP <server-host> <UDP-port>

	3.	For RPC protocol only :-
		-> java -jar Server.jar RPC <server-host> <RPC-port> <RPC-Service-Key>

	4.	For TCP and UDP protocols concurrently :- 
		-> java -jar Server.jar All <server-host> <TCP-port> <UDP-port> <RPC-port> <RPC-Service-Key>
		
		
Ex. `java -jar Server.jar ALL localhost 40000 40001 40002 StoreService` 
	or for RPC only
		
Ex. `java -jar Server.jar RPC localhost 40002 StoreService`

### **How to run Client Executable** :- 

	1.	For TCP protocol :- 
		-> java -jar Client.jar TCP <server-host> <TCP-server-port>

	2.	For UDP protocol :-
		-> java -jar Client.jar UDP <server-host> <UDP-server-port>

	3.	For RPC protocol :-
		-> java -jar Client.jar RPC <server-host> <RPC-server-port> <RPC-Service-Key>

Ex. `java -jar Client.jar RPC localhost 40002 StoreService`

--------------------------------------------------------------------
Entry point Java files are located in src/main/java/com/project/two

How to run Server using ServerDebug.java :-
	->	`javac ServerDebug.java`

	1.	For TCP protocol only :- 
		-> java ServerDebug TCP <server-host> <TCP-port>
	2.	For UDP protocol only :-
		-> java ServerDebug UDP <server-host> <UDP-port>
	3.	For RPC protocol only :-
		-> java ServerDebug RPC <server-host> <RPC-port> <RPC-Service-Key>
	4.	For TCP and UDP protocols concurrently :- 
		-> java ServerDebug All <server-host> <TCP-port> <UDP-port> <RPC-port> <RPC-Service-Key>

How to run Client using ClientDebug.java :- 
	->	`javac ClientDebug.java`

	1.	For TCP protocol :- 
		-> java ClientDebug TCP <server-host> <TCP-server-port>
	2.	For UDP protocol :-
		-> java ClientDebug UDP <server-host> <UDP-server-port>
	3.	For RPC protocol :-
		-> java ClientDebug RPC <server-host> <RPC-server-port> <RPC-Service-Key>
--------------------------------------------------------------------

Screenshots of the terminal Output are present in folder named Screenshots

--------------------------------------------------------------------

# **Summary of working** 

After starting the server first using any of the start command start the client corresponding to the protocol. 
Client is written to test dummy data when it starts it will put 5 key-value pairs then fetch them. 
After printing those five fetched values client will ask for deletion of these five keys and fetch them from server which will result in values shown as null as client didn't receive any data for corresponding keys from Server.
After this client becomes interactive and user can perform PUT, GET, DELETE operations.
User can also exit the client or can ask server and client both to exit depending upon the selection.
Also, Server creates a log file starting with the name "Server_log_*" that can be used to asses server health or status.

---------------------------------------------------------------------

# **Executive Summary** 

### _Assignment Overview_:-

As an increment over Project 1, this project clarifies how Remote Procedure Calls (RPC) work in the background. 
I have used Java RMI for RPC communication implemented over TCP/IP communication utilizing serialization, networking, and class facilities already available in Java. 
It also handles all the concurrent and multithreading operations on the server, however, I have already utilized multi-threading and thread pools with traditional TCP/IP and UDP communications in the first project implementation. 
The purpose of this project is to have a decent understanding of working of Remote Procedure Call (RPC) and how concurrency as well as Debugging/ Exceptional Handling works in a distributed systems.


### _Technical Impression_:-

Completing this homework has been a thought-provoking experience. 
A more structured method of communication was made possible by implementing RPC using Java RMI, which also enabled smooth communication between the client and server. 
RPC brought a new degree of efficiency and organization to communication that had previously relied on sockets.

Concurrent client requests were handled by thread pools, and data consistency required careful synchronization. 
A primary goal in preventing data conflicts was managing mutual exclusion.

This project has improved my knowledge of multi-threading and RPC while highlighting the value of concurrent programming in practical situations. 
Regarding enhancements, offering more detailed instructions on selecting an RPC framework and multi-threading techniques would be beneficial. 
Overall, this project has expanded my knowledge of network communication and server-side programming.

By working of this project I understood about the workings of TCP,UDP and RPC protocols and how to build a server in multi-threaded environment. 
My implementation of Server spawns at least 6 threads in ALL protocol mode one for cleanup and one main thread, other than that 2 threads are assigned for each TCP and UDP, multiple for RPC protocols for request processing. 
I understood how client and server communicate and what kind errors or issues can arise in multiple clients (Ex. two different clients accessing common store concurrently). 
There is a lot of improvement that can be performed in my implementation but I guess for now it satisfies the requirements of this project. 