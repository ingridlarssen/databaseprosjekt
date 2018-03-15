package program;

public class Friovelse extends Ovelse {
	
	private String beskrivelse;

	public Friovelse(String navn, String beskrivelse) {
		super(navn);
		this.beskrivelse = beskrivelse;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

}
