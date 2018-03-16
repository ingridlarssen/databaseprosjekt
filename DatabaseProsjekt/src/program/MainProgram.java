package program;

import java.util.Scanner;

import program.Apparat;

public class MainProgram {
	
	public static void main (String [] args) {
		System.out.println("Registrer apperat: skriv 'reg a'. \n"
				+ "Registrer ovelse: skriv 'reg o' \n"
				+ "Registrer treningsokt: skriv 'reg t'");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if (input.equals("reg a")) {
			System.out.println("Skriv inn navn på apparat:");
			System.out.println("        ");
			String navn = scanner.nextLine();
			System.out.println("Skriv inn beskrivelse: \n");
			String beskrivelse = scanner.nextLine();
			Apparat apparat = new Apparat(navn,beskrivelse);
			apparat.leggTilApparat();
			System.out.println("Apparat lagt til i apparatlisten: \n" + apparat.getStringList());
		}
		if (input.equals("reg o")) {
			System.out.println("Skriv inn navn på øvelse: ");
			String navn = scanner.nextLine();
			System.out.println("Er ovelsen paa et apparat? 'j' for ja, 'n' for nei ");
			String ovelsePaApparat = scanner.nextLine();
			if (ovelsePaApparat.equals("j")) {
				System.out.println("Skriv inn navn paa ovelse: ");
				String navnPaaOvelse = scanner.nextLine();
				
				System.out.println("Skriv inn antall kg: ");
				String antallKg = scanner.nextLine();
				int kg = Integer.parseInt(antallKg);
				
				System.out.println("Antall sett: ");
				String antallSett = scanner.nextLine();
				int sett = Integer.parseInt(antallSett);
				
				System.out.println("Navn paa apparat: ");
				String navnPaaApparat = scanner.nextLine();
				//gjor om navnPaaApparat til et apparat-objekt
				for (Apparat app : Apparat.apparater) {
					if (navnPaaApparat.equals(app.getNavn())) {
						OvelsePaaApparat o = new OvelsePaaApparat(navn, kg, sett, app);
						System.out.println("Øvelse" + o.getNavn() + " er lagt til");
					}
					else {
						System.out.println("Apparatet finnes ikke");
					}
				}
		
			}
			else {
				System.out.println("Beskriv ovelsen: ");
				String beskrivelse = scanner.nextLine();
				Friovelse fo = new Friovelse(navn, beskrivelse);
				System.out.println("Øvelse " + fo.getNavn() + " er lagt til");
			}
		}
		if (input.equals("reg t")) {
			//Datosjekk av input
			System.out.println("Skriv inn dato for treningsøkt (yyyy-mm-dd): ");
			String treningsokt = scanner.nextLine();
			Integer IntDato = 000000;
			if (treningsokt.length() != 10) {
				System.out.println("Feil datoformat. Prøv igjen (yyyy-mm-dd): ");
				treningsokt = scanner.nextLine();
			}
			else if(!isInteger(treningsokt)) {
				System.out.println("Feil datoformat. Prøv igjen (yyyy-mm-dd): ");
				treningsokt = scanner.nextLine();
			}
			else {
				IntDato = StringToInt(treningsokt);
			}
			
			//Tidspunksjekk av input
			System.out.println("Skriv inn tidspunkt for treningsøkt (hhmm): ");
			String tidspunkt = scanner.nextLine();
			Integer IntTidspunkt = 0000;
			if (tidspunkt.length() != 4) {
				System.out.println("Feil tidsformat. Prøv igjen (hhmm): ");
				tidspunkt = scanner.nextLine();
			}
			else if(!isInteger(tidspunkt)) {
				System.out.println("Feil tidsformat. Prøv igjen (hhmm): ");
				tidspunkt = scanner.nextLine();
			}
			else {
				IntTidspunkt = StringToInt(tidspunkt);
			}
			
			//Varighet
			System.out.println("Skriv inn varigheten på treingsøkten (i minutter): ");
			String varighet = scanner.nextLine();
			Integer IntVarighet = 00;
			if (!isInteger(varighet))  {
				System.out.println("Oppgi varighet i tall (minutter). Prøv igjen");
				varighet = scanner.nextLine();
			}
			else {
				IntVarighet = StringToInt(varighet);
			}
			
			//Form
			System.out.println("Skriv inn hvordan du følte at formen var (fra 1-10): ");
			String form = scanner.nextLine();
			if (!isInteger(form))  {
				System.out.println("Oppgi form i tall (1-10). Prøv igjen");
				form = scanner.nextLine();
			}
			Integer IntForm = StringToInt(form);
			if (!between(IntForm, 1, 10)) {
				System.out.println("Oppgi form i tall (1-10). Prøv igjen");
				form = scanner.nextLine();
				
			}
			else {
				IntForm = StringToInt(form);
			}
			
			//Prestasjon
			System.out.println("RangerDinPrestasjon (fra 1-10): ");
			String prestasjon = scanner.nextLine();
			if (!isInteger(prestasjon))  {
				System.out.println("Oppgi prestasjon innenfor intervallet (1-10). Prøv igjen");
				prestasjon = scanner.nextLine();
			}
			Integer IntPrestasjon = StringToInt(prestasjon);
			if (!between(IntPrestasjon, 1, 10)) {
				System.out.println("Oppgi prestasjon innenfor intervallet (1-10). Prøv igjen");
				prestasjon = scanner.nextLine();
				
			}
			else {
				IntPrestasjon = StringToInt(prestasjon);
			}
			
			//Beskrivelse
			System.out.println("Beskriv treningsøkten: ");
			String beskrivelse = scanner.nextLine();
			Treningsokt to = new Treningsokt(IntDato, IntTidspunkt, IntVarighet, IntForm, IntPrestasjon, beskrivelse);
			System.out.println("Treningsøkt registsrert: ");
			}
		}

	//Treningsokt(int dato, int tidspunkt, int varighetMin, int form, int prestasjon, String beskrivelse)
	
	
	public static int StringToInt(String s) {
		String ny = s;
		int i = Integer.parseInt(ny);
		return i;
	}
	
	/*Hjelpefunksjon - Sjekker om input er int*/
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	/*Hjelpefunksjon - Sjekker om et tall er i et gitt intervall*/
	public static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
	    if (i >= minValueInclusive && i <= maxValueInclusive) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}


}
