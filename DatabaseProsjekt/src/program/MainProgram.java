package program;

public class MainProgram {
	
	public void program () {
		System.out.println("Registrer apperat: skriv 'reg a'. \n"
				+ "Registrer �velse: skriv 'reg �' \n"
				+ "Registrer trenings�kt: skriv 'reg t'");
		String input = System.console().readLine();
		if (input.equals("reg a")) {
			System.out.println("Skriv inn navn: ");
			String navn = System.console().readLine();
			System.out.println("Skriv inn beskrivelse:");
			System.out.println("hei");
			String beskrivelse = System.console().readLine();
			Apparat apparat = new Apparat(navn,beskrivelse);
			apparat.leggTilApparat();
		}
		if (input.equals("reg �")) {
			System.out.println("Skriv inn navn: ");
			String navn = System.console().readLine();
			System.out.println("Er �velsen p� et apparat? 'j' for ja, 'n' for nei ");
			String ovelsePaApparat = System.console().readLine();
			if (ovelsePaApparat.equals("j")) {
				System.out.println("Skriv inn navn p� �velse: ");
				String navnPaOvelse = System.console().readLine();
				
				System.out.println("Skriv inn antall kg: ");
				String antallKg = System.console().readLine();
				int kg = Integer.parseInt(antallKg);
				
				System.out.println("Antall sett: ");
				String antallSett = System.console().readLine();
				int sett = Integer.parseInt(antallSett);
				
				System.out.println("Navn p� apparat: ");
				String navnPaApparat = System.console().readLine();
				//gj�r om navnP�Apparat til et apparat-objekt
				
				�velseP�Apparat ovelse = new �velseP�Apparat(navn,kg,sett,navnPaApparat);
			}
			else {
				System.out.println("Beskriv �velsen: ");
				String beskrivelse = System.console().readLine();
			}
			
		}
	}

}
