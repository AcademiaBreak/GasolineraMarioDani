package com.academiabreak.principal;

public class Moto extends Vehiculo {
	private int cc;
	
	public Moto() {
		super();
		cc = 0; 
	}

	public Moto(String matricula, String marca, int cc) {
		super(matricula, marca);
		this.cc=cc;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

}
