package program;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ovelse {
	
	private String navn;
	private String type;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConnectionEstablisher connection = new ConnectionEstablisher();
		hentTop5ovelser(connection);
	}
	
	public Ovelse (String navn, String type) {
		this.navn = navn;
		this.type = type;
	}
	
	public static void hentTop5ovelser(ConnectionEstablisher connection) throws SQLException {
		try {
			String sql = "SELECT Navn FROM Ovelse GROUP BY Navn ORDER BY COUNT(*) DESC LIMIT 5";
			java.sql.Statement st = connection.myConnection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			System.out.println("Her er de mest gjennomførte øvelsene i synkende rekkefølge: \n");
			while(rs.next()) {
				String navn = rs.getString(1);
				System.out.println(navn);	
			}
		}
		catch(SQLException m){
			m.printStackTrace();
			throw new SQLException("Kunne ikke hente Top 5");
		}
	}

	public static void getOvelseResultat(ConnectionEstablisher connection, String navn, Date date1, Date date2) {
		int OvelsesID = 0;
		String sql1 = "SELECT MIN(OvelsesID) FROM Ovelse WHERE Navn = '" + navn + "'";
		try {
			java.sql.Statement st = connection.myConnection.createStatement();
			ResultSet rs = st.executeQuery(sql1);
			while(rs.next()) {
				OvelsesID = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Fant ikke ovelse med navn: " + navn + " pga. "+ e);
			e.printStackTrace();
		}
		boolean isFriovelse = true;
		String sql2 = "SELECT Count(1) as record_count FROM Friovelser WHERE OvelsesID = " + OvelsesID;
		try {
			java.sql.Statement st = connection.myConnection.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while(rs.next()) {
				if (rs.getInt(1) == 0) {
					isFriovelse = false;
				}
			}
		} catch (SQLException e) {
			System.out.println("Fant ikke ovelse med navn: " + navn + " pga. "+ e);
			e.printStackTrace();
		}
		if(isFriovelse) {
			String sql3 = "SELECT Friovelser.Beskrivelse, Treningsokt.Dato FROM "
					+ "(((Treningsokt INNER JOIN OvelsePaaTreningsokt ON Treningsokt.TreningsoktID = OvelsePaaTreningsokt.TreningsoktID) "
					+ "INNER JOIN Friovelser ON OvelsePaaTreningsokt.OvelsesID = Friovelser.OvelsesID) "
					+ "INNER JOIN Ovelse ON Ovelse.OvelsesID = Friovelser.OvelsesID) "
					+ "WHERE (Treningsokt.Dato BETWEEN '" + date1 + "' AND '" + date2 + "') AND Ovelse.Navn = '" + navn + "';";
			try {
				java.sql.Statement st = connection.myConnection.createStatement();
				ResultSet rs = st.executeQuery(sql3);
				System.out.println("Resultatlogg for ovelse: " + navn);
				while(rs.next()) {
					System.out.println("Dato: " + rs.getString(2) + " Beskrivelse: " + rs.getString(1));
				}
			} catch (SQLException e) {
				System.out.println("Fant ikke ovelse med navn: " + navn + " pga. "+ e);
				e.printStackTrace();
			}
		} else {
			String sql4 = "SELECT OvelserPaaApparat.AntallKg, OvelserPaaApparat.AntallSett, Treningsokt.Dato FROM "
					+ "(((Treningsokt INNER JOIN OvelsePaaTreningsokt ON Treningsokt.TreningsoktID = OvelsePaaTreningsokt.TreningsoktID) "
					+ "INNER JOIN Ovelse ON Ovelse.OvelsesID = OvelserPaaApparat.OvelsesID) "
					+ "INNER JOIN OvelserPaaApparat ON OvelsePaaTreningsokt.OvelsesID = OvelserPaaApparat.OvelsesID) "
					+ "WHERE (Treningsokt.Dato BETWEEN '" + date1 + "' AND '" + date2 + "') AND Ovelse.Navn = '" + navn + "';";
			try {
				java.sql.Statement st = connection.myConnection.createStatement();
				ResultSet rs = st.executeQuery(sql4);
				System.out.println("Resultatlogg for ovelse: " + navn);
				while(rs.next()) {
					System.out.println("Dato: " + rs.getString(3) + " Antall Kg: " + rs.getString(1) + " Antall Sett: " + rs.getString(2));
				}
			} catch (SQLException e) {
				System.out.println("Fant ikke ovelse med navn: " + navn + " pga. "+ e);
				e.printStackTrace();
			}
		}
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
