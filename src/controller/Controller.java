package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

	public void run() throws ParseException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		boolean datosCargados = false;
		String dato = "";
		double dato3 = 0;
		double dato4 = 0;
		double dato5 = 0;
		String dato6 = "";
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
					view.printMessage("------------------- \n Ingrese la fecha (formato: YYYY-MM-DD): \n-------------------");
					dato = lector.next();
					Date dato2 = new SimpleDateFormat("yyyy-MM-dd").parse(dato);
					try {
						respuesta = modelo.buscarAccidenteRBT(dato2);
					} catch (Exception e) {
						respuesta = e.getMessage();			
						e.printStackTrace();
					}
					view.printMessage(respuesta);
				}else {
					view.printMessage("------------------- \n Debe cargar los datos primero \n-------------------");
				}
				break;

			case 2: //Requerimiento 2
				if (datosCargados)
				{
					view.printMessage("------------------- \n Ingrese la fecha (formato: YYYY-MM-DD): \n-------------------");
					dato = lector.next();
					Date dato2 = new SimpleDateFormat("yyyy-MM-dd").parse(dato);
					try
					{
						respuesta = modelo.req2(dato2);
					}
					catch (Exception e)
					{
						respuesta = e.getMessage();
						e.printStackTrace();
					}
					view.printMessage(respuesta);
				}
				else
				{
					view.printMessage("------------------- \n Debe cargar los datos primero \n-------------------");
				}
				break;

			case 3: //Requerimiento 3
				if(datosCargados) {
					view.printMessage("------------------- \n Ingrese la fecha de inicio (YYYY-MM-DD): \n-------------------");
					dato = lector.next();
					Date datoIn = new SimpleDateFormat("yyyy-MM-dd").parse(dato);

					view.printMessage("------------------- \n Ingrese la fecha de fin (YYYY-MM-DD): \n-------------------");
					dato6 = lector.next();

					Date datoFin = new SimpleDateFormat("yyyy-MM-dd").parse(dato6);

					respuesta = modelo.req3(datoIn, datoFin);
					view.printMessage(respuesta);
				}
				else
				{
					view.printMessage("------------------- \n Debe cargar los datos primero \n-------------------");
				}
				break;
			case 4: //Requerimiento 4
				if(datosCargados) {
					view.printMessage("------------------- \n Ingrese la fecha de inicio (YYYY-MM-DD): \n-------------------");
					dato = lector.next();
					Date datoIn = new SimpleDateFormat("yyyy-MM-dd").parse(dato);

					view.printMessage("------------------- \n Ingrese la fecha de fin (YYYY-MM-DD): \n-------------------");
					dato6 = lector.next();

					Date datoFin = new SimpleDateFormat("yyyy-MM-dd").parse(dato6);

					respuesta = modelo.req4(datoIn, datoFin);
					view.printMessage(respuesta);
				}
				else
				{
					view.printMessage("------------------- \n Debe cargar los datos primero \n-------------------");
				}
				
				
				break;
			case 5: //Requerimiento 5
				if (datosCargados)
				{
					view.printMessage("------------------- \n Ingrese la hora inicial (formato: HH:mm): \n-------------------");
					dato = lector.next();
					view.printMessage("------------------- \n Ingrese la hora final (formato: HH:mm): \n-------------------");
					dato6 = lector.next();
					
					Date datoIn = new SimpleDateFormat("HH:mm").parse(dato);
					Date datoFin = new SimpleDateFormat("HH:mm").parse(dato6);

					try
					{
						respuesta = modelo.req5(datoIn,datoFin);
					}
					catch (Exception e)
					{
						respuesta = e.getMessage();
						e.printStackTrace();
					}
					view.printMessage(respuesta);
				}
				else
				{
					view.printMessage("------------------- \n Debe cargar los datos primero \n-------------------");
				}
				break;


			case 6: //Requerimiento 6
				if(datosCargados) {
					view.printMessage("------------------- \n Ingrese la longitud: \n-------------------");
					lector.nextLine();
					dato3 = lector.nextInt();

					view.printMessage("------------------- \n Ingrese la latitud: \n-------------------");
					dato4 = lector.nextInt();

					view.printMessage("------------------- \n Ingrese el radio: \n-------------------");
					dato5 = lector.nextInt();

					respuesta = modelo.req6(dato3, dato4, dato5);
					view.printMessage(respuesta);
				} else {
					view.printMessage("------------------- \n Debe cargar los datos primero \n-------------------");
				}
				break;

			case 7:

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
