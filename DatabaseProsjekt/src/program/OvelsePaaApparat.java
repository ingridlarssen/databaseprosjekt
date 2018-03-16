package program;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OvelsePaaApparat extends Ovelse {
	
	private int kg;
	private int sett;
	private Apparat apparat;

	public OvelsePaaApparat(String navn, int kg, int sett, Apparat apparat) {
		super(navn);
		this.kg = kg;
		this.sett = sett;
		this.apparat = apparat;
	}
	
	public static void leggTilOvelsePaaApparat(ConnectionEstablisher connection, OvelsePaaApparat ovelse) throws SQLException {
		int maxID = getMaxOvelseID(connection);
		try {
			java.sql.PreparedStatement prep = connection.myConnection.prepareStatement("INSERT INTO Ovelse (Ovelse.OvelseID, Ovelse.Navn, Ovelse.Type) VALUES(?,?,?)");
			prep.setLong(1, maxID);
			prep.setString(2, ovelse.getNavn());
			prep.setString(3, ovelse.getType());
			prep.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Kunne ikke legge til øvelse pga " + e);
		}
		try {
			java.sql.PreparedStatement prep = connection.myConnection.prepareStatement("INSERT INTO OvelsePaaApparat (OvelsePaaApparat.OvelsesID, OvelsePaaApparat.AntallKg, OvelsePaaApparat.AntallSett, OvelsePaaApparat.ApparatNavn) VALUES(?,?,?,?)");
			prep.setLong(1, maxID);
			prep.setLong(2, ovelse.getKg());
			prep.setLong(3, ovelse.getSett());
			prep.setString(4, ovelse.apparat.getNavn());
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

	public Apparat getApparat() {
		return apparat;
	}

	public void setApparat(Apparat apparat) {
		this.apparat = apparat;
	}
	
	
	
	

}
