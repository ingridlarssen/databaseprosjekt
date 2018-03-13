package program;

public class Treningsokt {
	
	private int dato;
	private int tidspunkt;
	private int varighetMinutter;
	private int form;
	private int prestasjon;
	private String beskrivelse;
	
	public Treningsokt(int dato, int tidspunkt, int varighetMinutter, int form, int prestasjon, String beskrivelse) {
		this.dato = dato;
		this.tidspunkt = tidspunkt;
		this.varighetMinutter = varighetMinutter;
		this.form = form;
		this.prestasjon = prestasjon;
		this.beskrivelse = beskrivelse;
	}
	
	public void leggTilTreningsokt() {
		//legg til treningsøkt i databasen
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
