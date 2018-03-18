package program;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Apparat {
	
	private String navn;
	private String beskrivelse;
	public static List<Apparat> apparater = new ArrayList<>();
	public static List<String> StringApparater = new ArrayList<>();
	
	 public static void main(String[] args) {
		Apparat app1 = new Apparat("Tredemølle", "til å løpe på");
		Apparat app2 = new Apparat("Stang", "til å løfte");
	 }
	 
	
	public Apparat (String navn, String beskrivelse) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		this.addToList();
	}
	
	public void addToList() {
		apparater.add(this);
	}
	
	public List<String> getStringList() {
		for (Apparat a: apparater) {
			StringApparater.add(a.getNavn());
		}
		return StringApparater;
	}
	
	public List<Apparat> getList() {
		return apparater;
	}
	
	public Apparat getApparat(ConnectionEstablisher connection, String apparatNavn) throws SQLException {
		Apparat apparat = null;
		try {
			String sql = "SELECT * FROM Apparat WHERE Apparat.Navn = " + apparatNavn;
			java.sql.Statement st = connection.myConnection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				apparat = new Apparat(rs.getString(1), rs.getString(2));
				return apparat;
			}
		}catch(SQLException m) {
			m.printStackTrace();
			throw new SQLException("Fillern!");
		}
		return apparat;
	}
	
	public static void leggTilApparat(ConnectionEstablisher connection, Apparat app) {
		try {
			java.sql.PreparedStatement prep = connection.myConnection.prepareStatement("INSERT INTO Apparat (Apparat.Beskrivelse, Apparat.Navn) VALUES(?,?)");
			prep.setString(1, app.getBeskrivelse());
			prep.setString(2, app.getNavn());
			prep.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Kunne ikke legge til apparat pga " + e);
		}
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	

}
