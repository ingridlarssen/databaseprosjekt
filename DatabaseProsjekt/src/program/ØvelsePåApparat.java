package program;

public class ØvelsePåApparat extends Øvelse{
	
	private int kg;
	private int sett;
	private String apparat;

	public ØvelsePåApparat(String navn, int kg, int sett, String apparat) {
		super(navn);
		this.kg = kg;
		this.sett = sett;
		this.apparat = apparat;
	}
	
	public void leggTilØvelsePåApparat() {
		//legg til øvelse på apparat i databasen vår.
	}
	

	public int getKg() {
		return kg;
	}

	public void setKg(int kg) {
		this.kg = kg;
	}

	public int getSett() {
		return sett;
	}

	public void setSett(int sett) {
		this.sett = sett;
	}

	public Apparat getApparat() {
		return apparat;
	}

	public void setApparat(Apparat apparat) {
		this.apparat = apparat;
	}
	
	
	
	

}
