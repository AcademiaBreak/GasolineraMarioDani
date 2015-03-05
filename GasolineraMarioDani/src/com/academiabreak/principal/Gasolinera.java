package com.academiabreak.principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

import com.academiabreak.principal.Utilidades;

public class Gasolinera {
	private static Surtidor surtidores[];
	private static Hashtable<String, Socio> clientes;
	private static BufferedReader in;

	public static void main(String args[]) {
		clientes = new Hashtable<String, Socio>();
		in = new BufferedReader(new InputStreamReader(System.in));
		surtidores = new Surtidor[obtenerNumSurtidores()];
		
		//TODO: cambiar los textos por los del enunciado
		
		menuPrincipal();

	}

	private static void menuPrincipal() {
		String cad = "";
		int opc = 0;
		boolean salir = false;

		while(!salir) {
			Utilidades.limpiarPantalla();
			System.out.println("1. Gestion Clientes.");
			System.out.println("2. Atencion Clientes.");
			System.out.println("3. Salir.");
			System.out.print("\tOPCION: ");
			try {
				cad = in.readLine();
				if(Utilidades.esOpcionValida(cad, 1, 3)) {
					opc = Integer.parseInt(cad);
					if(opc == 3) {
						salir = true;
					} else {
						realizarAccionMenuPrincipal(opc);
					}
				} else {
					System.out.print("Opcion invalida, ");
					Utilidades.pulsaIntro();
				}
			} catch(IOException ioe) {
				System.out.println("Error al leer de teclado...");
			}
		}
	}

	private static void realizarAccionMenuPrincipal(int opc) {
		switch(opc) {
		case 1:
			gestionClientes();
			break;
		case 2:
			atencionClientes();
			break;
		}
	}

	// GESTION CLIENTES
	private static void gestionClientes() {
		int opc;
		String cad = "";
		boolean salir = false;

		while(!salir) {
			Utilidades.limpiarPantalla();
			System.out.println("1. Alta Cliente.");
			System.out.println("2. Baja Cliente.");
			System.out.println("3. Ingreso Saldo Cliente.");
			System.out.println("4. Alta Vehiculo Cliente.");
			System.out.println("5. Baja Vehiculo Cliente.");
			System.out.println("6. Salir.");
			System.out.print("\tOPCION: ");
			try {
				cad = in.readLine();
				if(Utilidades.esOpcionValida(cad, 1, 6)) {
					opc = Integer.parseInt(cad);
					if(opc == 6) {
						salir = true;
					} else {
						Utilidades.limpiarPantalla();
						realizarAccionGestionClientes(opc);
					}
				} else {
					System.out.print("Opcion invalida, ");
					Utilidades.pulsaIntro();
				}
			} catch(IOException ioe) {
				System.out.println("Error al leer de teclado...");
			}
		}
	}

	private static void realizarAccionGestionClientes(int opc) {
		switch(opc) {
		case 1:
			altaCliente();
			break;
		case 2:
			if(hayClientes()) {
				bajaCliente();
			}
			break;
		case 3:
			if(hayClientes()) {
				ingresarSaldo();
			}
			break;
		case 4:
			if(hayClientes()) {
				altaVehiculo();
			}
			break;
		case 5:
			if(hayClientes()) {
				bajaVehiculo();
			}
		}
	}

	private static boolean hayClientes() {
		boolean hayClientes = true;

		if(clientes.isEmpty()) {
			hayClientes = false;

			try {
				System.out.print("Actualmente no hay socios. ");
				Utilidades.pulsaIntro();
			} catch(IOException ioe) {
				System.out.println("Error al leer de teclado...");
			}
		}

		return hayClientes;
	}

	private static void bajaVehiculo() {
		String dni = elegirCliente();
		String matricula = "";

		try {
			if(Utilidades.esDni(dni) && clientes.containsKey(dni)) {
				if(clientes.get(dni).tieneVehiculos()) {
					Utilidades.limpiarPantalla();
					listarVehiculos(clientes.get(dni));
					System.out.print("\tIntroduzca la matricula del vehiculo: ");
					matricula = in.readLine();
					if(!estaEnSurtidor(matricula)) {
						if(clientes.get(dni).eliminarVehiculo(matricula)) {
							System.out.println("El vehiculo ha sido eliminado correctamente. ");
						} else {
							System.out.println("No se ha encontrado un vehiculo con esa matricula. ");
						}
					} else {
						System.out.println("Este vehiculo esta en cola. ");
					}
				} else {
					System.out.println("Este cliente no tiene ningun vehiculo. ");
				}
				Utilidades.pulsaIntro();
			}
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}
	}

	private static boolean estaEnSurtidor(String matricula) {
		boolean encontrado = false;
		int i = 0;
		int j;

		while(!encontrado && i < surtidores.length) {
			j = 0;
			while(!encontrado && j < surtidores[i].getTamanio()) {
				//TODO: esta comprobacion se deberia hacer en el surtidor
				if(surtidores[i].getVehiculo(j).getMatricula().equalsIgnoreCase(matricula)) {
					encontrado = true;
				} else {
					j++;
				}
			}
			i++;
		}

		return encontrado;
	}

	private static boolean estaEnSurtidor(Enumeration matriculas) {
		boolean encontrado = false;
		int i = 0;
		int j;
		String matricula;

		while(matriculas.hasMoreElements() && !encontrado) {
			matricula = (String)matriculas.nextElement();
			while(!encontrado && i < surtidores.length) {
				j = 0;
				while(!encontrado && j < surtidores[i].getTamanio()) {
					if(surtidores[i].getVehiculo(j).getMatricula().equalsIgnoreCase(matricula)) {
						encontrado = true;
					} else {
						j++;
					}
				}
				i++;
			}
		}

		return encontrado;
	}

	private static void listarVehiculos(Socio soc) {
		Hashtable<String, Vehiculo> vehiculos = soc.getVehiculos();
		Enumeration claves = vehiculos.keys();

		while(claves.hasMoreElements()) {
			System.out.println(vehiculos.get(claves.nextElement()).toString());
			System.out.println("");
		}
	}

	private static void altaCliente() {
		Socio cliente = new Socio();
		String cad = "";

		try {
			System.out.print("Introduce el DNI: ");
			cad = in.readLine();
			if(Utilidades.esDni(cad)) {
				cliente.setDni(cad);
				System.out.print("Introduce el Nombre: ");
				cad = in.readLine();
				cliente.setNombre(cad);
				System.out.print("Introduce el Apellidos: ");
				cad = in.readLine();
				cliente.setApellidos(cad);
				System.out.print("Introduce el Direccion: ");
				cad = in.readLine();
				cliente.setDireccion(cad);
				System.out.print("Introduce el Saldo: ");
				cad = in.readLine();
				if(Utilidades.esDecimal(cad)) {
					cliente.setSaldo(Double.parseDouble(cad));
					clientes.put(cliente.getDni(), cliente);
					System.out.print("Usted ha sido inscrito correctamente. ");
					Utilidades.pulsaIntro();
				} else {
					System.out.print("Saldo incorrecto, ");
					Utilidades.pulsaIntro();
				}
			} else {
				System.out.print("DNI incorrecto, ");
				Utilidades.pulsaIntro();
			}
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}
	}

	private static void bajaCliente() {
		String dni = elegirCliente();

		if(Utilidades.esDni(dni) && clientes.containsKey(dni)) {
			if(estaEnSurtidor(clientes.get(dni).getVehiculos().keys())) {
				System.out.println("Este cliente tiene alg�n veh�culo en cola. ");
			} else {
				clientes.remove(dni);
				System.out.print("se ha dado de baja correctamente");
			}
		} else {
			System.out.print("DNI no valido. ");
		}

		try {
			Utilidades.pulsaIntro();
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}
	}

	private static void ingresarSaldo() {
		double cantidad = 0;
		String dni = elegirCliente();
		String cad = "";

		if(Utilidades.esDni(dni) && clientes.containsKey(dni)) {
			System.out.println("�Cuanto saldo quiere ingresar?");
			try {
				cad = in.readLine();
				if(Utilidades.esDecimal(cad)) {
					cantidad = Double.parseDouble(cad);
					clientes.get(dni).ingresarSaldo(cantidad);
					System.out.println("Ha ingresado el saldo correctamente. ");
				} else {
					System.out.println("Saldo incorrecto. ");
				}
				Utilidades.pulsaIntro();
			} catch(IOException ioe) {
				System.out.println("Error al leer de teclado...");
			}
		} else {
			try {
				System.out.print("DNI no valido. ");
				Utilidades.pulsaIntro();
			} catch(IOException ioe) {
				System.out.println("Error al leer de teclado...");
			}
		}
	}

	private static void altaVehiculo() {
		String dni = elegirCliente();
		Vehiculo vehiculo;

		try {
			if(Utilidades.esDni(dni) && clientes.containsKey(dni)) {
				Utilidades.limpiarPantalla();
				vehiculo = crearVehiculo();
				if(vehiculo != null) {
					clientes.get(dni).insertarVehiculo(vehiculo);
					System.out.println("");
					System.out.println("*** Se procede a dar de alta el vehiculo del cliente: ");
					System.out.println(clientes.get(dni).getNombre() + " con DNI: " + dni
							+ " Veh�culo con matr�cula: " + vehiculo.getMatricula());
					Utilidades.pulsaIntro();

				}
			} else {
				System.out.print("DNI no valido. ");
				Utilidades.pulsaIntro();
			}
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}

	}

	private static Vehiculo crearVehiculo() {
		Vehiculo vehiculo = null;
		String matricula, marca, tipo;

		try {
			System.out.print("Introduzca la matricula del vehiculo: ");
			matricula = in.readLine();
			if(!existeVehiculo(matricula)) {
				System.out.print("Introduzca la marca del vehiculo: ");
				marca = in.readLine();
				System.out.println("1. Coche");
				System.out.println("2. Moto");
				System.out.print("\tElige tipo de vehiculo: ");
				tipo = in.readLine();
				if(Utilidades.esOpcionValida(tipo, 1, 2)) {
					System.out.println("");
					switch(Integer.parseInt(tipo)) {
					case 1:
						vehiculo = crearCoche(matricula, marca);
						break;
					case 2:
						vehiculo = crearMoto(matricula, marca);
					}
				} else {
					System.out.print("Opcion no valida. ");
					Utilidades.pulsaIntro();
				}
			} else {
				System.out.print("Esta matricula ya esta registrada. ");
				Utilidades.pulsaIntro();
			}
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}

		return vehiculo;
	}

	private static Coche crearCoche(String matricula, String marca) {
		Coche coche = null;
		String modelo, tipoCar;

		try {
			System.out.print("Introduzca modelo: ");
			modelo = in.readLine();
			System.out.println("1. " + Combustible.DIESEL);
			System.out.println("2. " + Combustible.GASOLINA);
			System.out.println("3. " + Combustible.ELECTRICO);
			System.out.print("\tElija tipo de carburante: ");
			tipoCar = in.readLine();
			if(Utilidades.esOpcionValida(tipoCar, 1, 3)) {
				coche = new Coche(matricula, marca, modelo, Combustible.getCombustibleByNum(Integer.parseInt(tipoCar)));
			} else {
				System.out.print("Tipo de carburante no valido. ");
				Utilidades.pulsaIntro();
			}
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}

		return coche;
	}

	private static Moto crearMoto(String matricula, String marca) {
		Moto moto = null;
		String cc;

		try {
			System.out.print("Introduce cilindrada");
			cc = in.readLine();
			if(Utilidades.esEntero(cc)) {
				moto = new Moto(matricula, marca, Integer.parseInt(cc));
			} else {
				System.out.print("Cilindrada no v�lida. ");
				Utilidades.pulsaIntro();
			}

		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}

		return moto;
	}

	private static boolean existeVehiculo(String matricula) {
		Enumeration claves = clientes.keys();
		boolean encontrado = false;

		while(claves.hasMoreElements() && !encontrado) {
			if(clientes.get(claves.nextElement()).estaVehiculo(matricula)) {
				encontrado = true;
			}
		}

		return encontrado;
	}

	private static String elegirCliente() {
		String dni = "";

		listarClientes();
		System.out.print("\tIntroduce un DNI: ");
		try {
			dni = in.readLine();
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}

		return dni;
	}

	private static void listarClientes() {
		Enumeration claves = clientes.keys();

		while(claves.hasMoreElements()) {
			System.out.println(clientes.get(claves.nextElement()).toString());
			System.out.println("");
		}
	}

	// FIN GESTION CLIENTES

	// TODO:ATENCION CLIENTES
	private static void atencionClientes() {
		String cad;
		int opc;

		Utilidades.limpiarPantalla();
		System.out.println("1. Recibir vehiculo. ");
		System.out.println("2. Atender vehiculo. ");
		System.out.println("3. Ver Ocupacion Surtidores. ");
		System.out.println("4. Salir");
		System.out.print("\t OPCION: ");
		try {
			cad = in.readLine();

			if(Utilidades.esOpcionValida(cad, 1, 4)) {
				opc = Integer.parseInt(cad);
				realizarAccionAtencionCliente(opc);
			} else {
				System.out.println();
				System.out.println("Opcion Invalida");
			}
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}
	}

	public static void realizarAccionAtencionCliente(int opc) {
		switch(opc) {
		case 1:
			recibirVehiculo();
			break;
		case 2:
			// Atender Vehiculo
			break;
		case 3:
			// Ver Ocupacion Surtidores
			break;
		}
	}

	public static void recibirVehiculo() {
		String matricula;
		Surtidor surt = obtenerMinSurtidor();
		Vehiculo vehiculo;

		Utilidades.limpiarPantalla();
		try {
			System.out.println("Introduzca la matricula del vehiculo: ");
			matricula = in.readLine();

			if(existeVehiculo(matricula)) {
				vehiculo = obtenerVehiculo(matricula);
				surt.insertar(vehiculo);
				System.out.println("***Se procede a introducir al vehículo en el surtidor:");
				System.out.println(matricula+" se introduce en la cola del surtidor:"+ surt.getId());
				System.out.println("");
				Utilidades.pulsaIntro();
			}
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}

	}

	public static void atenderVehiculo() {
		String cantidad;
		double cant;
		Surtidor surtidor = obtenerMaxSuridor();
		Vehiculo vehiculo = surtidor.atender();
		Socio soc = getDuenio(vehiculo.getMatricula());

		try {
			System.out.println("Introduza la cantidad de dinero a retirar: ");
			cantidad = in.readLine();
			if(Utilidades.esDecimal(cantidad)) {
				cant = Double.parseDouble(cantidad);
				if(cant < soc.getSaldo()) {
					soc.retirarSaldo(cant);
					System.out.println("Su vehiculo ha sido atendido correctamente. Buen viaje. ");
				} else {
					System.out.println("Saldo insuficiente. Se procede a sacar el vehiculo del surtidor. ");
				}
			}else{
				System.out.println("La cantidad introducida es erronea. ");
			}
			Utilidades.pulsaIntro();
		} catch(IOException ioe) {
			System.out.println("Error al leer de teclado...");
		}
	}

	private static Socio getDuenio(String matricula) {
		Enumeration claves = clientes.keys();
		boolean encontrado = false;
		Socio soc = null;

		while(claves.hasMoreElements() && !encontrado) {
			soc = clientes.get(claves.nextElement());
			if(soc.estaVehiculo(matricula)) {
				encontrado = true;
			}
		}

		return soc;
	}

	public static Surtidor obtenerMaxSuridor() {
		Surtidor surtidor = null;

		if(surtidores.length > 0) {
			surtidor = surtidores[0];
			for(int i = 1; i < surtidores.length; i++) {
				if(surtidor.getTamanio() < surtidores[i].getTamanio()) {
					surtidor = surtidores[i];
				}
			}
		}

		return surtidor;
	}
	// FIN ATENCION CLIENTE
	
	private static Vehiculo obtenerVehiculo(String matricula) {
		Vehiculo vehiculo = null;
		boolean encontrado = false;
		Enumeration claves = clientes.keys();
		Socio socio;

		while(!encontrado && claves.hasMoreElements()) {
			socio = clientes.get(claves.nextElement());

			if(socio.estaVehiculo(matricula)) {
				encontrado = true;
				vehiculo = socio.getVehiculo(matricula);
			}
		}

		return vehiculo;
	}

	private static Surtidor obtenerMinSurtidor() {
		Surtidor surtidor = surtidores[0];

		for(int i = 0; i < surtidores.length; i++) {
			if(surtidores[i].getTamanio() < surtidor.getTamanio()) {
				surtidor = surtidores[i];
			}
		}
		return surtidor;
	}

	private static int obtenerNumSurtidores() {
		String cad = "";
		int numSurtidores = -1;

		while(numSurtidores < 1) {
			System.out.print("Numero de surtidores a abrir: ");
			try {
				cad = in.readLine();
				if(!Utilidades.esEntero(cad)) {
					System.out.println("Introduce un numero...");
					Utilidades.pulsaIntro();
				} else {
					try {
						numSurtidores = Integer.parseInt(cad);
					} catch(NumberFormatException nfe) {
						System.out.println("Numero no valido...");
					}
				}
			} catch(IOException ioe) {
				System.out.println("Error al leer de teclado...");
			}
		}

		return numSurtidores;
	}
}
