package program;

import java.util.ArrayList;

public class Apparat {
	
	private String navn;
	private String beskrivelse;
	private Apparat[] apparater;
	
	
	
	public Apparat (String navn, String beskrivelse) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		this.addToList();
	}
	
	public void addToList() {
		apparater.append(this);
	}
	
	public void leggTilApparat() {
		//legg til apparat i databasen
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
