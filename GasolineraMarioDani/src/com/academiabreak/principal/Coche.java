package com.academiabreak.principal;

public class Coche extends Vehiculo {
	private String modelo;
	private Combustible combustible; 
	
	public Coche() {
		super(); 
		modelo = ""; 
		combustible = null;
	}

	public Coche(String matricula, String marca, String modelo, Combustible com) {
		super(matricula, marca);
		this.modelo=modelo;
		this.combustible = com; 
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}

}
