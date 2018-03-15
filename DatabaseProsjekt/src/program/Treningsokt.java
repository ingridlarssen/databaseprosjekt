package program;

import java.sql.SQLException;

public class Treningsokt {
	
	private int dato;
	private int tidspunkt;
	private int varighetMin;
	private int form;
	private int prestasjon;
	private String beskrivelse;
	
	public Treningsokt(int dato, int tidspunkt, int varighetMin, int form, int prestasjon, String beskrivelse) {
		this.dato = dato;
		this.tidspunkt = tidspunkt;
		this.varighetMin = varighetMin;
		this.form = form;
		this.prestasjon = prestasjon;
		this.beskrivelse = beskrivelse;
	}
	
	public void leggTilTreningsokt(ConnectionEstablisher connection, Treningsokt okt) {
		try {
			java.sql.PreparedStatement prep = connection.myConnection.prepareStatement(""
					+ "INSERT INTO Treningsokt(okt.dato, okt.tidspunk, okt.varighetMin, bla)");
			prep.setString(1, okt.getDato());
			prep.setString(2, okt.getTidspunkt());
			prep.setLong(3, dep.getTlf());
			prep.setLong(4, dep.getBrukerTlf());
			prep.setLong(5, dep.isPrimary());
			prep.setLong(6, dep.isActive());
			prep.setString(7, dep.getRelationType());
			prep.setLong(8, dep.getNotificationType());
			prep.executeUpdate();
		} catch (SQLException e){
			System.out.println("Kunne ikke legge til treningsøkt");
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
		return varighetMinutter;
	}

	public void setVarighetMinutter(int varighetMinutter) {
		this.varighetMinutter = varighetMinutter;
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
