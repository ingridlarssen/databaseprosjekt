package program;

import java.beans.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;

public class Treningsokt {
	
	private Date dato;
	private Time tidspunkt;
	private int varighetMin;
	private int form;
	private int prestasjon;
	private String beskrivelse;
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		LocalDate testDate = LocalDate.of(1996, Month.MARCH, 29);
		java.sql.Date d = java.sql.Date.valueOf(testDate);
		Time time = new Time(16,00,15);
		
		Treningsokt treningsokt = new Treningsokt(d, time, 30, 7, 8, "Denne vil jeg finne ID på");
		ConnectionEstablisher connection = new ConnectionEstablisher();
		Treningsokt.leggTilTreningsokt(connection, treningsokt);
		
	}
	
	public String toString() {
		return "Dato: " + dato + "Tidspunkt: " + tidspunkt;
	}

	
	public Treningsokt(Date d, Time tidspunkt, int varighetMin, int form, int prestasjon, String beskrivelse) {
		this.dato = d;
		this.tidspunkt = tidspunkt;
		this.varighetMin = varighetMin;
		this.form = form;
		this.prestasjon = prestasjon;
		this.beskrivelse = beskrivelse;
	}
	
	
	public static int hentTreningsoktID(ConnectionEstablisher connection) {
		String sql = "SELECT TreningsoktID, MAX(TreningsoktID) FROM Treningsokt GROUP BY TreningsoktID";
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
	
	public static int getMaxTreningsoktID(ConnectionEstablisher connection) throws SQLException {
		String sql = "SELECT TreningsoktID, MAX(TreningsoktID) FROM Treningsokt GROUP BY TreningsoktID";
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
	
	public static void leggTilTreningsokt(ConnectionEstablisher connection, Treningsokt okt) {
		try {
			java.sql.PreparedStatement prep = connection.myConnection.prepareStatement("INSERT INTO Treningsokt (Treningsokt.TreningsoktID, Treningsokt.Dato, Treningsokt.Tidspunkt, Treningsokt.Varighet, Treningsokt.PersonligForm, Treningsokt.Prestasjon, Treningsokt.Notat) VALUES(?,?,?,?,?,?,?)");
			prep.setLong(1, getMaxTreningsoktID(connection));
			prep.setDate(2, okt.getDato());
			prep.setTime(3, okt.getTidspunkt());
			prep.setLong(4, okt.getVarighetMinutter());
			prep.setLong(5, okt.getForm());
			prep.setLong(6, okt.getPrestasjon());
			prep.setString(7, okt.getBeskrivelse());
			prep.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Kunne ikke legge til treningsøkt pga " + e);
		}
	}
	
	public static void hentNSistetreningsokter(ConnectionEstablisher connection, int n) {
		String sql = "SELECT * FROM Treningsokt ORDER BY TreningsoktID desc LIMIT " + n;
		try {
			java.sql.Statement st = connection.myConnection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Date dato = rs.getDate(2);
				Time tidspunkt = rs.getTime(3);
				int varighet = rs.getInt(4);
				int form = rs.getInt(5);
				int prestasjon = rs.getInt(6);
				String beskrivelse = rs.getString(7);
				System.out.println("Treningsøkt \nDato: " + dato + ", Tidspunkt: " + tidspunkt + ", Varighet (min): " + varighet
						+ ", Form: " + form + ", Prestasjon: " + prestasjon + ", Beskrivelse: " + beskrivelse);
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public Date getDato() {
		return dato;
	}

	public void setDato(Date dato) {
		this.dato = dato;
	}

	public Time getTidspunkt() {
		return tidspunkt;
	}

	public void setTidspunkt(Time tidspunkt) {
		this.tidspunkt = tidspunkt;
	}

	public int getVarighetMinutter() {
		return varighetMin;
	}

	public void setVarighetMinutter(int varighetMin) {
		this.varighetMin = varighetMin;
	}

	public int getForm() {
		return form;
	}

	public void setForm(int form) {
		this.form = form;
	}

	public int getPrestasjon() {
		return prestasjon;
	}

	public void setPrestasjon(int prestasjon) {
		this.prestasjon = prestasjon;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	
	

}
