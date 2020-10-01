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
			System.out.println("0. Realizar la carga de las fuentes de datos de las películas.");
			System.out.println("1. Descubrir películas de una compañía de producción en cierto año");
			System.out.println("2. Conocer a un director");
			System.out.println("3. Conocer a un actor");
			System.out.println("4. Entender un género cinematográfico");
			System.out.println("5. Encontrar películas por país");
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}
		


		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		

}
