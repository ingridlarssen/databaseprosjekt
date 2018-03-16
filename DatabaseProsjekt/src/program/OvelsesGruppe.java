package program;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OvelsesGruppe {

	private String type;
	
	public OvelsesGruppe(String type) {
		this.type = type;
	}
	
	public static void leggTilOvelsesGruppe(ConnectionEstablisher connection, OvelsesGruppe ovelsesgruppe) {
		String sql = "INSERT INTO Ovelsesgruppe (Type) VALUES ('" + ovelsesgruppe.getType() + "')";
		try {
			java.sql.Statement st = connection.myConnection.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("nooo");
			e.printStackTrace();
		}
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
