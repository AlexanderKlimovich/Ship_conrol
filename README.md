# Ship_conrol
This README provides an overview of the Microservice Project based on a microservices architecture,  
utilizing Kafka for messaging and WebSockets for communication.   
The project consists of four main components: Common, Office, and Ship.  
  
Components  
Common  
The Common component contains shared libraries and classes required by other parts of the project.  
It centralizes common functionalities, models, and utility functions, ensuring consistency and reducing redundancy across the microservices.  
  
Office  
The Office component represents the station where ships are managed.  
It handles the following tasks:  
Registering and tracking ships in the system.  
Assigning routes to ships for their journeys between offices.  
Utilizing Kafka for inter-component messaging, such as notifying the Ship component about route assignments.  
  
Ship  
The Ship component embodies the ships that can travel between offices. It manages:  
  
Navigating between offices based on assigned routes.  
Updating the Office component about its current location.  
Utilizing WebSockets to provide real-time updates to the Office component and other connected clients.  
