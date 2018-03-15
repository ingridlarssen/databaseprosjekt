package program;

public class OvelsePaaApparat extends Ovelse {
	
	private int kg;
	private int sett;
	private Apparat apparat;

	public OvelsePaaApparat(String navn, int kg, int sett, Apparat apparat) {
		super(navn);
		this.kg = kg;
		this.sett = sett;
		this.apparat = apparat;
	}
	
	public void leggTilOvelsePaaApparat() {
		//legg til �velse p� apparat i databasen v�r.
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
