package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import model.logic.ManejadorViajes;
import view.View;

public class Controller {

	/* Instancia de la Vista*/
	private View view;

	/* Instancia del Model*/
	private ManejadorViajes modelo;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new ManejadorViajes();

	}

	public void run() throws ParseException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		boolean datosCargados = false;
		int dato = 0;
		String respuesta = "";


		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 0: // Realiza la carga de datos
				view.printMessage("------------------- \n CARGA DE DATOS \n-------------------");
				try {
					respuesta = modelo.leerArchivo();
					datosCargados = true;
					view.printMessage(respuesta);
				} catch (Exception e) {
					view.printMessage("------------------- \n Error en la carga de datos: \n-------------------");
					e.printStackTrace();
				}

				break;
			case 1: // Consultar el grado de entrada y de salida de una estación de bicicletas 
				if(datosCargados) {
					view.printMessage("------------------- \n Ingrese el ID de la estación: \n-------------------");
					dato = lector.nextInt();
					try {
						respuesta = modelo.grado(dato);
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
