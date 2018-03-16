package program;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Friovelse extends Ovelse {
	
	private String beskrivelse;
	
	public static void main(String[] args) throws ClassNotFoundException {
		ConnectionEstablisher connection = new ConnectionEstablisher();
		Friovelse friovelse = new Friovelse("Planke", "Hardt!!!!!");
		leggTilFriovelse(connection,friovelse);
	}
	

	public Friovelse(String navn, String beskrivelse) {
		super(navn);
		this.beskrivelse = beskrivelse;
	}
	
	public static void leggTilFriovelse(ConnectionEstablisher connection, Friovelse friovelse) {
		try {
			int maxID = getMaxOvelseID(connection);
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
