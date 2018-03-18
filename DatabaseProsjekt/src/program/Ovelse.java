package program;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ovelse {
	
	private String navn;
	private String type;
	
	public Ovelse (String navn, String type) {
		this.navn = navn;
		this.type = type;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public static int getMaxOvelseID(ConnectionEstablisher connection) throws SQLException {
		String sql = "SELECT OvelsesID, MAX(OvelsesID) FROM Ovelse GROUP BY OvelsesID";
		try {
			int maxID = 0;
			java.sql.Statement st = connection.myConnection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				maxID = rs.getInt(1);	
			}		
			return maxID + 1;
		} catch(SQLException m) {
			m.printStackTrace();
			throw new SQLException("Fillern!");
		} 
	}
	

}
