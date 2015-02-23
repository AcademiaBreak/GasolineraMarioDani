package com.academiabreak.principal;

public enum Combustible {
	DIESEL,
	GASOLINA,
	ELECTRICO;
	
	public static Combustible getCombustibleByNum(int num) {
		Combustible com = null; 
		
		switch(num) {
		case 1: 
			com = DIESEL;
			break;
		case 2: 
			com = GASOLINA;
			break;
		case 3: 
			com = ELECTRICO; 
		}
		
		return com; 
	}
}
