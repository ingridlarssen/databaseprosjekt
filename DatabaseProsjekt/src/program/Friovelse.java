package program;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Friovelse extends Ovelse {
	
	private String beskrivelse;
	
	public Friovelse(String navn, String type, String beskrivelse) {
		super(navn, type);
		this.beskrivelse = beskrivelse;
	}
	
	public static void leggTilFriovelse(ConnectionEstablisher connection, Friovelse friovelse) throws SQLException {
		int maxID = getMaxOvelseID(connection);
		try {
			java.sql.PreparedStatement prep = connection.myConnection.prepareStatement("INSERT INTO Ovelse (Ovelse.OvelsesID, Ovelse.Navn, Ovelse.Type) VALUES(?,?,?)");
			prep.setLong(1, maxID);
			prep.setString(2, friovelse.getNavn());
			prep.setString(3, friovelse.getType());
			prep.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Kunne ikke legge til øvelse pga " + e);
		}
		try {
			java.sql.PreparedStatement prep = connection.myConnection.prepareStatement("INSERT INTO Friovelser (Friovelser.OvelsesID, Friovelser.Beskrivelse) VALUES(?,?)");
			prep.setLong(1, maxID);
			prep.setString(2, friovelse.getBeskrivelse());
			prep.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Kunne ikke legge til friøvelse pga " + e);
		}
	}
	
	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

}
