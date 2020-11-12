package view;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("0. Realizar la carga de los viajes.");
			System.out.println("1. Consultar el grado de entrada y de salida de una estación de bicicletas ");
			System.out.println("2. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}
		


		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		

}
