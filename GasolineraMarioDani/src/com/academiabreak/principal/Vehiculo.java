package com.academiabreak.principal;

public class Vehiculo {
	private String matricula;
	private String marca;
	
	public Vehiculo() {
		matricula = "";
		marca = "";
	}
	
	public Vehiculo(String matricula, String marca) {
		super();
		this.matricula = matricula;
		this.marca = marca;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	
	
	
	
}
