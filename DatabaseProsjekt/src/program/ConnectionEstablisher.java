package program;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionEstablisher {
	
	private static String username = "eivinry_DB";
	private static String pw = "gruppe100";
	private static String dbName = "jdbc:mysql://mysql.stud.ntnu.no/eivinry_prosjekt_gr100";
	public java.sql.Connection myConnection;
	static int connectionCounter;
	
	public ConnectionEstablisher() throws ClassNotFoundException{
		this.establishConnection();
	}
		
	private void establishConnection() throws ClassNotFoundException {
		try {
			java.sql.Connection kobling = DriverManager.getConnection(dbName ,username, pw);
			this.myConnection = kobling;
			connectionCounter++;
			System.out.println("Established a new connection with connection number: " + connectionCounter);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Could not connect due to: " + "\n" + e.getStackTrace());
		}
	}
	
	public void closeConnection() {
		try {
			this.myConnection.close();
		} catch (SQLException e) {
			System.out.println("Failed to close connection due to: \n"+ e.getStackTrace());
		}
		connectionCounter--;
	}	
}