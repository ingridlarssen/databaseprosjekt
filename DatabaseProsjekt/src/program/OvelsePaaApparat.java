package program;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OvelsePaaApparat extends Ovelse {
	
	private int kg;
	private int sett;
	private String apparatNavn;

	public OvelsePaaApparat(String navn, String type, int kg, int sett, String apparatNavn) {
		super(navn, type);
		this.kg = kg;
		this.sett = sett;
		this.apparatNavn = apparatNavn;
	}
	
	public static void leggTilOvelsePaaApparat(ConnectionEstablisher connection, OvelsePaaApparat ovelse) throws SQLException {
		int maxID = getMaxOvelseID(connection);
		try {
			java.sql.PreparedStatement prep = connection.myConnection.prepareStatement("INSERT INTO Ovelse (Ovelse.OvelsesID, Ovelse.Navn, Ovelse.Type) VALUES(?,?,?)");
			prep.setLong(1, maxID);
			prep.setString(2, ovelse.getNavn());
			prep.setString(3, ovelse.getType());
			prep.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Kunne ikke legge til øvelse pga " + e);
		}
		try {
			java.sql.PreparedStatement prep = connection.myConnection.prepareStatement("INSERT INTO OvelserPaaApparat (OvelserPaaApparat.OvelsesID, OvelserPaaApparat.AntallKg, OvelserPaaApparat.AntallSett, OvelserPaaApparat.ApparatNavn) VALUES(?,?,?,?)");
			prep.setLong(1, maxID);
			prep.setLong(2, ovelse.getKg());
			prep.setLong(3, ovelse.getSett());
			prep.setString(4, ovelse.apparatNavn);
			prep.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Kunne ikke legge til øvelse på apparat pga " + e);
		}
	}
	
	public int getKg() {
		return kg;
	}

	public void setKg(int kg) {
		this.kg = kg;
	}

	public int getSett() {
		return sett;
	}

	public void setSett(int sett) {
		this.sett = sett;
	}

	public String getApparat() {
		return apparatNavn;
	}

	public void setApparat(String apparatNavn) {
		this.apparatNavn = apparatNavn;
	}

}
