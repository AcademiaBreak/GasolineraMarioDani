package com.academiabreak.principal;

import java.util.ArrayList;

public class Surtidor {
	private int id;
	private ArrayList<Vehiculo> lista; 
	
	public Surtidor() {
		id = -1;
		lista = new ArrayList<Vehiculo>(); 
	}
	
	public Surtidor(int id) {
		this.id = id; 
		this.lista = new ArrayList<Vehiculo>();
	}
	
	public void insertar(Vehiculo v) {
		lista.add(v); 
	}
	
	public Vehiculo atender(){
		return lista.remove(0);
	}
	
	//TODO: Esto no debe hacerse
	public Vehiculo getVehiculo(int pos){
		return lista.get(pos);  
	}
	
	public int getTamanio() {
		return lista.size(); 
	}
	
	public void vaciar() {
		lista.clear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
