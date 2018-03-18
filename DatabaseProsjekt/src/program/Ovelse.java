package program;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ovelse {
	
	private String navn;
	private String type;
	
	public static void main(String[] args) throws ClassNotFoundException {
		ConnectionEstablisher connection = new ConnectionEstablisher();
		finnOvelserISammeGruppe(connection, "Trene");
	}
	
	public Ovelse (String navn, String type) {
		this.navn = navn;
		this.type = type;
	}
	
	public static void finnOvelserISammeGruppe(ConnectionEstablisher connection, String ovelsesType) {
		String ovelserISammeGruppe = "";
		String sql = "SELECT Ovelse.Navn FROM Ovelse WHERE Ovelse.Type='" + ovelsesType + "'";
		try {
			java.sql.Statement st = connection.myConnection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			System.out.println("Øvelser av type '" + ovelsesType + "': ");
			while (rs.next()) {
				String navn = rs.getString(1);
				System.out.println(navn);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//Legger til økt + øvelse i OvelsePaaTreningsokt-tabellen i db
	public static void leggTilOvelseOgTreningsokt(ConnectionEstablisher connection) {
		int ovelsesID = OvelsePaaApparat.hentOvelsesID(connection);
		int treningsoktID = Treningsokt.hentTreningsoktID(connection);
		String sql = "INSERT INTO OvelsePaaTreningsokt (OvelsePaaTreningsokt.TreningsoktID, OvelsePaaTreningsokt.OvelsesID) VALUES (" + treningsoktID + ", " + ovelsesID + ")";
		try {
			java.sql.Statement st = connection.myConnection.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("fikk ikke lagt til økt og øvelse ass");
		}
	}
	
	//henter ID på siste økt som ble lagt inn
	public static int hentOvelsesID(ConnectionEstablisher connection) {
		String sql = "SELECT OvelsesID, MAX(OvelsesID) FROM Ovelse GROUP BY OvelsesID";
		try {
			int ID = 0;
			java.sql.Statement st = connection.myConnection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				ID = rs.getInt(1);
			} return ID;
		} catch (SQLException e) {
			System.out.println("fikk ikke til å hente siste ID");
			e.printStackTrace();
		} return 1;
	}
	
	//finner ID på økt som skal bli lagt til
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
	
	
	

}
