package program;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

import program.Apparat;

public class MainProgram {
	
	public static void main (String [] args) throws ClassNotFoundException, SQLException {
		
		ConnectionEstablisher connection = new ConnectionEstablisher();
		java.sql.Date d = null;
		System.out.println("Registrer apperat: skriv 'reg a'. \n"
				+ "Registrer treningsokt med tilhørende øvelser: skriv 'reg t' \n"
				+ "Hente ut n siste treningsøkter: skriv tallet på hvor mange av de siste du vil hente \n"
				+ "Vil du se resultatlogg i gitt tidspunkt? Skriv 'resultatlogg' \n"
				+ "Vil du se øvelser som tilhører samme gruppe? Skriv 'gruppe' \n"
				+ "Vil du legge til en ny øvelsestype? Skriv 'ny gruppe'"
				+ "Vil du se resulater for en spesifikk øvelse i et gitt tidsintervall? Skriv 'resultatlogg'");
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
		//REGISTRERE APPARAT:
		if (input.equals("reg a")) {
			System.out.println("Skriv inn navn på apparat:");
			System.out.println("        ");
			String navn = scanner.nextLine();
			System.out.println("Skriv inn beskrivelse: \n");
			String beskrivelse = scanner.nextLine();
			Apparat apparat = new Apparat(navn,beskrivelse);
			Apparat.leggTilApparat(connection, apparat);
			System.out.println("Apparat lagt til i apparatlisten");
		}
		
		//REGISTRERE TRENINGSØKT (MED TILHØRENDE ØVELSER)
		if (input.equals("reg t")) {
			Time time = null;
			//Datosjekk av input
			System.out.println("Skriv inn dato for treningsøkt (yyyy-mm-dd): ");
			String treningsokt = scanner.nextLine();
			if (treningsokt.length() != 10) {
				System.out.println("Feil datoformat. Prøv igjen (yyyy-mm-dd): ");
				treningsokt = scanner.nextLine();
			}
			else {
				d = java.sql.Date.valueOf(treningsokt);
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
				int hour = Integer.parseInt(Integer.toString(IntTidspunkt).substring(0, 2));
				int minute = Integer.parseInt(Integer.toString(IntTidspunkt).substring(2, 4));
				time = new Time(hour, minute, 00);
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
				System.out.println("Oppgi prestasjon innenfor intervallet (1-10). PrÃ¸v igjen");
				prestasjon = scanner.nextLine();
			}
			Integer IntPrestasjon = StringToInt(prestasjon);
			if (!between(IntPrestasjon, 1, 10)) {
				System.out.println("Oppgi prestasjon innenfor intervallet (1-10). PrÃ¸v igjen");
				prestasjon = scanner.nextLine();
			}
			else {
				IntPrestasjon = StringToInt(prestasjon);
			} 
			//Beskrivelse
			System.out.println("Beskriv treningsøkten: ");
			String beskrivelse = scanner.nextLine();
			Treningsokt to = new Treningsokt(d, time, IntVarighet, IntForm, IntPrestasjon, beskrivelse);
			System.out.println(to);
			System.out.println("Treningsøkt registsrert: ");
		
			Treningsokt.leggTilTreningsokt(connection, to);
			
			//LEGG TIL ØVELSE I TRENINGSØKTEN
			String tekst = "";
			while (! tekst.equals("nei")) {
				System.out.println("Vil du legge til en øvelse i treningsøkten, skriv 'reg o'. Hvis du ikke vil legge til flere, skriv 'nei'");
				tekst = scanner.nextLine();
				if (tekst.equals("reg o")) {
					System.out.println("Er øvelsen på apparat? 'j' for ja, 'n' for nei ");
					String ovelsePaApparat = scanner.nextLine();
					
					if (ovelsePaApparat.equals("j")) {
						System.out.println("Skriv inn navn på øvelse: ");
						String navnPaOvelse = scanner.nextLine();
						
						System.out.println("Hvilken type øvelse er det?");
						String type = scanner.nextLine();
						
						System.out.println("Antall kg: ");
						String antallKg = scanner.nextLine();
						int kg = Integer.parseInt(antallKg);
						
						System.out.println("Antall sett: ");
						String antallSett = scanner.nextLine();
						int sett = Integer.parseInt(antallSett);
						
						System.out.println("Skriv inn navn på apparat: ");
						String navnPaApparat = scanner.nextLine();
			
						//sjekker om et apparat med navnPaApparat ligger i db
						Apparat apparat = Apparat.getApparat(connection, navnPaApparat);

						if (apparat.getNavn().equals(navnPaApparat)) {
							OvelsePaaApparat o = new OvelsePaaApparat(navnPaOvelse, type, kg, sett, apparat.getNavn());
							OvelsePaaApparat.leggTilOvelsePaaApparat(connection, o);
							System.out.println("Øvelse er lagt til");
							Ovelse.leggTilOvelseOgTreningsokt(connection);
						}
						else {
							System.out.println("Apparatet finnes ikke");
						}
					} if (ovelsePaApparat.equals("n")) {
						System.out.println("Skriv inn navn på øvelse: ");
						String navnPaOvelse = scanner.nextLine();
						
						System.out.println("Skriv inn hvilken type øvelsen hører til: ");
						String type = scanner.nextLine();
						
						System.out.println("Legg til beskrivelse: ");
						String beskrivelsePaFriovelse = scanner.nextLine();
						
						
						Friovelse friovelse = new Friovelse(navnPaOvelse, type, beskrivelsePaFriovelse);
						Friovelse.leggTilFriovelse(connection, friovelse);
						Ovelse.leggTilOvelseOgTreningsokt(connection);
					}
					
				}
			}	
		}
		
		//LEGGE TIL EN NY ØVELSESGRUPPE
		if(input.equals("ny gruppe")) {
			System.out.println("Skriv inn hvilken type det er: ");
			String type = scanner.nextLine();
			OvelsesGruppe ovelsesGruppe = new OvelsesGruppe(type);
			OvelsesGruppe.leggTilOvelsesGruppe(connection, ovelsesGruppe);
		}
			
		//HENTE N SISTE TRENINGSØKTER
		if (isInteger(input)) {
			int n = Integer.parseInt(input);
			Treningsokt.hentNSistetreningsokter(connection, n);
		}
		
		//HENTE ØVELSER SOM TILHØRER SAMME GRUPPE
		if (input.equals("gruppe")) {
			System.out.println("Skriv inn navn på gruppe: ");
			String gruppeNavn = scanner.nextLine();
			Ovelse.finnOvelserISammeGruppe(connection, gruppeNavn);
		}
		
		//TODO: SE RESULTATLOGG I GITT INTERVALL, må gjøres ferdig
		if (input.equals("resultatlogg")) {
			System.out.println("Skriv inn navnet på øvelsen du vil se resultatlogg over: ");
			String navn = scanner.nextLine();
			System.out.println("Skriv inn startdato på tidsintervallet (YYYY-MM-DD): ");
			String startDato = scanner.nextLine();
			System.out.println("Skriv inn sluttdato på tidsintervallet (YYYY-MM-DD): ");
			String sluttDato = scanner.nextLine();
			Date start = java.sql.Date.valueOf(startDato);
			Date slutt = java.sql.Date.valueOf(sluttDato);
			
			
		}
		
		
		//TODO: VALGFRI USERCASE
		
		
		
		scanner.close();
	}	
	
	
	
	
	
	
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
