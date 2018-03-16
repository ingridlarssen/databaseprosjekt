package program;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Treningsokt {
	
	private int dato;
	private int tidspunkt;
	private int varighetMin;
	private int form;
	private int prestasjon;
	private String beskrivelse;
	
	public static void main(String[] args) throws ClassNotFoundException {
		Treningsokt treningsokt = new Treningsokt(0304, 1305, 30, 7, 8, "Knall økt");
		ConnectionEstablisher connection = new ConnectionEstablisher();
		Treningsokt.leggTilTreningsokt(connection, treningsokt);
	}

	
	public Treningsokt(int dato, int tidspunkt, int varighetMin, int form, int prestasjon, String beskrivelse) {
		this.dato = dato;
		this.tidspunkt = tidspunkt;
		this.varighetMin = varighetMin;
		this.form = form;
		this.prestasjon = prestasjon;
		this.beskrivelse = beskrivelse;
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
			prep.setLong(2, okt.getDato());
			prep.setLong(3, okt.getTidspunkt());
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

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public int getTidspunkt() {
		return tidspunkt;
	}

	public void setTidspunkt(int tidspunkt) {
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
