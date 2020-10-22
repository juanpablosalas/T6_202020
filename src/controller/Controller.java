package controller;

import java.util.Scanner;

import model.logic.ManejadorAccidente;
import view.View;

public class Controller {

	/* Instancia de la Vista*/
	private View view;

	/* Instancia del Model*/
	private ManejadorAccidente modelo;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new ManejadorAccidente();

	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		boolean datosCargados = false;
		String dato = "";
		String respuesta = "";
		String respuesta2 = "";


		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 0: // Realiza la carga de datos
				view.printMessage("------------------- \n CARGA DE DATOS \n-------------------");
				view.printMessage("------------------- \n Ingrese el año del que quiere cargar los datos: \n-------------------");
				dato = lector.next();
				int ano = Integer.parseInt(dato);
				try {
					respuesta = modelo.leerArchivo(ano);
					datosCargados = true;
					view.printMessage(respuesta);
				} catch (Exception e) {
					view.printMessage("------------------- \n Error en la carga de datos: \n-------------------");
					e.printStackTrace();
				}

				break;
			case 1: //Requerimiento 1
				if(datosCargados) {
					view.printMessage("------------------- \n Ingrese la fecha: \n-------------------");
					dato = lector.next();
					try {
						respuesta = modelo.buscarAccidenteBST(dato) + modelo.buscarAccidenteRBT(dato);
					} catch (Exception e) {
						respuesta = e.getMessage();			
						e.printStackTrace();
					}
					view.printMessage(respuesta);
				}else {
					view.printMessage("------------------- \n Debe cargar los datos primero \n-------------------");
				}
				break;
			case 2:
				view.printMessage("------------------- \n ¡Hasta pronto! \n-------------------");
				fin = true;
				lector.close();
				break;
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
