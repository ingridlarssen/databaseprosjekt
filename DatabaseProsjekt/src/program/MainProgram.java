package program;

public class MainProgram {
	
	public void program () {
		System.out.println("Registrer apperat: skriv 'reg a'. \n"
				+ "Registrer øvelse: skriv 'reg ø' \n"
				+ "Registrer treningsøkt: skriv 'reg t'");
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
		if (input.equals("reg ø")) {
			System.out.println("Skriv inn navn: ");
			String navn = System.console().readLine();
			System.out.println("Er øvelsen på et apparat? 'j' for ja, 'n' for nei ");
			String øvelsePåApparat = System.console().readLine();
			if (øvelsePåApparat.equals("j")) {
				System.out.println("Skriv inn navn på øvelse: ");
				String navnPåØvelse = System.console().readLine();
				
				System.out.println("Skriv inn antall kg: ");
				String antallKg = System.console().readLine();
				int kg = Integer.parseInt(antallKg);
				
				System.out.println("Antall sett: ");
				String antallSett = System.console().readLine();
				int sett = Integer.parseInt(antallSett);
				
				System.out.println("Navn på apparat: ");
				String navnPåApparat = System.console().readLine();
				//gjør om navnPåApparat til et apparat-objekt
				
				ØvelsePåApparat øvelse = new ØvelsePåApparat(navn,kg,sett,navnPåApparat);
			}
			else {
				System.out.println("Beskriv øvelsen: ");
				String beskrivelse = System.console().readLine();
			}
			
		}
	}

}
