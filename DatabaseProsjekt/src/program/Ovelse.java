package program;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ovelse {
	
	private String navn;
	
	public Ovelse (String navn) {
		this.navn = navn;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public static int getMaxOvelseID(ConnectionEstablisher connection) throws SQLException {
		String sql = "SELECT OvelseID, MAX(OvelseID) FROM Ovelse GROUP BY OvelseID";
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
