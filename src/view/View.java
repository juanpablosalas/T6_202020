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
			System.out.println("0. Realizar la carga de los accidentes.");
			System.out.println("1. Requerimiento 1");
			System.out.println("2. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}
		


		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		

}
