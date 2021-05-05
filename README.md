<!-- INTRODUCTION -->
<br />
<p align="center">
    <a href="https://drive.google.com/file/d/1ibmIm21KvMW_6VOI1cBgHkeRLEUMgqc1/view?usp=sharing">
    <img src="https://user-images.githubusercontent.com/42817904/117049270-38461400-ad14-11eb-97e8-ddc5c280ed77.png"/>
    </a>
  <h2 align="center">WAREHOUSE MANAGEMENT SYSTEM</h2>
  <p align="center">
    JAVA Desktop platform, which allows retailers, warehouses and headquarters better track inventory, employees and communicate between each entity.
    <br />
    <a href="https://drive.google.com/file/d/1ibmIm21KvMW_6VOI1cBgHkeRLEUMgqc1/view?usp=sharing"><strong>Explore the project report »</strong></a>
    <br />
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

JAVA made application using two tier architecture, where the presentation layer runs on a client, and data is stored on a server using PostgreSQL database.  

The application uses sockets between tiers to communicate.

<strong>Features:</strong>
* Headquarter's platform - Can supervise other entities and communicate using chat system to control nonproblematic workflow between Retailer and Warehouse
* Retailer's platform - Inventory management, Employee management, Send request to warehouse for items, See deliveries from warehouse, Chat system with other entities
* Warehouse platform - Inventory management, Employee management, Send delivery items to retailer, See requests from retailer, Chat system with other entities

### Built With

* [Java JDK 1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* jdbc - Java Database Connectivity
* [JavaFX Scene Builder](https://gluonhq.com/products/scene-builder/) - GUI

<!-- GETTING STARTED -->
## Getting Started

Code is located in `Code/src` folder. 

Documentation is located in `documentation` folder. SCRUM and UP framework related things are in `Analysis and Design` folder.

### Prerequisites

* [Java JDK 1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* Java IDE

### Installation

1. Clone the repository:
  ```
 git clone https://github.com/davele-itsme/Semester-project-2.git
  ```
2. Open the project in Java IDE
3. Run: RunApplicationHQ, RunApplicationWH, RunApplicationRT

If you want to create tables with already existing data to test the system, please follow these guidelines

#### Set up tables:
1. Open PgAdmin
2. Create a new database
3. Go into src/jdbc/ and open DataBaseModel
4. Find setConnection() and change the Strings to match the database
5. Run src/jdbc/Setup.java

Now you have the required tables and dummy data


Now you have to run the server before running the applications
Run application:
1. Run src/network/Server/Server.java
2. Open src/Run.java, src/RunHQ.java and src/RunRT.java and change the HOST String to "localhost"

Now you can use RunApplicationHQ, RunApplicationWH and RunApplicationRT to open the different clients.
Beware of closing clients, our Server still thinks you're connected and will keep throwing exceptions, slowing down your system. 


