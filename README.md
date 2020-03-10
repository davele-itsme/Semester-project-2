**********************************************************************************************************************************************
If you want to create tables with already existing data to test the system, please follow these guidelines

Set up tables:
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
**********************************************************************************************************************************************