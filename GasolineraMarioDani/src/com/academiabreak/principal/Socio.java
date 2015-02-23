package com.academiabreak.principal;

import java.util.Hashtable;

public class Socio {
	private String dni;
	private String nombre;
	private String apellidos;
	private String direccion;
	private double saldo;
	private Hashtable<String,Vehiculo> vehiculos; 
	
	public Socio() {
		dni = "";
		nombre = "";
		apellidos = "";
		direccion = "";
		saldo = 0;
		vehiculos = new Hashtable<String,Vehiculo>(); 
	}
	
	public Socio(String dni, String nombre, String apellidos,
			String direccion, double saldo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.saldo = saldo;
		vehiculos = new Hashtable<String,Vehiculo>(); 
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void insertarVehiculo(Vehiculo v) {
		vehiculos.put(v.getMatricula(),v); 
	}
	
	public boolean eliminarVehiculo(String matricula) {
		boolean eliminado = false; 
		Vehiculo v = vehiculos.remove(matricula);
		
		if(v != null) {
			eliminado = true; 
		}
		
		return eliminado; 
	}
	
	public boolean estaVehiculo(String matricula) {
		return vehiculos.containsKey(matricula); 
	}
	
	public boolean tieneVehiculos(){
		return vehiculos.isEmpty();
	}
	
	public void ingresarSaldo(double cant) {
		this.saldo += cant; 
	}
	
	public boolean retirarSaldo(double cant) {
		boolean retirado = false; 
		
		if(saldo > cant) {
			saldo = saldo - cant; 
			retirado = true; 
		}
		
		return retirado; 
	}
	
	@Override
	public String toString() {
		return ("DNI: "+dni+"\nNombre: "+nombre+" "+apellidos+"\nDireccion: "+direccion+"\nSaldo: "+saldo); 
	}
}
