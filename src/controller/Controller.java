package controller;

import java.util.Scanner;

import view.View;

public class Controller {

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();

	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		boolean datosCargados = false;
		String dato = "";
		int dato2 = 0;
		String respuesta = "";


		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 0: // Realizar la carga de las fuentes de datos de las películas
				
			case 6:
				view.printMessage("------------------- \n ¡Hasta pronto! \n-------------------");
				fin = true;
				break;
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
