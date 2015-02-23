package com.academiabreak.principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilidades {

	public static boolean esOpcionValida(String opc, int min, int max) {
		boolean esValida = false;

		if (esEntero(opc) 
				&& Integer.parseInt(opc) >= min
				&& Integer.parseInt(opc) <= max) {
			esValida = true;
		}

		return esValida;
	}

	public static int pedirOpcion(int min, int max) throws IOException {
		String opt = "";
		int option = 0;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (option < min || option > max) {
			System.out.println("\tElige opcion: ");
			opt = in.readLine();

			if (esEntero(opt))
				option = Integer.parseInt(opt);
		}

		return option;
	}

	public static boolean esEntero(String cad) {
		boolean esEntero = !cad.isEmpty();
		int i = 0;

		while (i < cad.length() && esEntero) {
			if (Character.isDigit(cad.charAt(i))) {
				i++;
			} else {
				esEntero = false;
			}
		}

		return esEntero;
	}

	public static boolean esDecimal(String cad) {
		boolean esDecimal = !cad.isEmpty();
		boolean hayPuntos = false;
		int i = 0;

		while (i < cad.length() && esDecimal) {
			if (Character.isDigit(cad.charAt(i))) {
				i++;
			} else if(i>0 && i<cad.length()-1 && cad.charAt(i)=='.') {
				if (hayPuntos) {
					esDecimal = false;
				} else {
					i++;
					hayPuntos = true;
				}
			} else {
				esDecimal = false;
			}
		}

		return esDecimal;
	}

	public static boolean esDni(String cad) {
		String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
		boolean esDni = true;
		String cadDni = "";
		int i;

		if (cad.length() != 9 || !Character.isLetter(cad.charAt(cad.length() - 1))) {
			esDni = false;
		} else {
			i = 0;
			while (i < cad.length() - 1 && esDni) {
				if (Character.isDigit(cad.charAt(i))) {
					cadDni += cad.charAt(i);
					i++;
				} else {
					esDni = false;
				}
			}

			if (!cadDni.isEmpty() && letrasDni.charAt(Integer.parseInt(cadDni) % 23) != Character.toUpperCase(cad.charAt(cad.length() - 1))) {
				esDni = false;
			}
		}

		return esDni;
	}

	public static void limpiarPantalla() {
		for (int i = 0; i < 30; i++) {
			System.out.println("");
		}
	}

	public static void pulsaIntro() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Pulsa intro para continuar...");
		in.readLine();
	}
}
