package program;

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
