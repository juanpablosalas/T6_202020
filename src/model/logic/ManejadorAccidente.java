package model.logic;

import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.ArregloDinamico;
import model.data_structures.BST;
import model.data_structures.RBT;
import model.data_structures.ShellSort;

public class ManejadorAccidente 
{
	public static final String datosAccidentes = "data/us_accidents_small.csv";

	private BST<String, Accidente> arbolAccidentes;
	
	private RBT<Date, Accidente> RBTAccidentes;
	
	private RBT<Date, Accidente> RBTHorario;

	public ManejadorAccidente()
	{

		arbolAccidentes = new BST<String,Accidente>();
		RBTAccidentes = new RBT<Date, Accidente>();
		RBTHorario = new RBT<Date, Accidente>();
	}

	public String leerArchivo(int ano) throws Exception
	{
		String csv = datosAccidentes;
		CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
		try 
		{
			CSVReader reader = new CSVReaderBuilder(new FileReader(csv)).withSkipLines(1).withCSVParser(parser).build();
			String[] campos;
			while ((campos = reader.readNext()) != null)
			{
				String id = campos[0];
				int gravedad = Integer.parseInt(campos[3]);
				String fechaHoraInicio = campos[4];
				String fechaHoraFinal = campos[5];
				String county = campos[16];
				String fechaInicio = fechaHoraInicio.split(" ")[0];
				String horaInicio = fechaHoraInicio.split(" ")[1];
				String fechaFinal = fechaHoraFinal.split(" ")[0];
				String horaFinal = fechaHoraFinal.split(" ")[1];
				Double latitud = Double.parseDouble(campos[6]);
				Double longitud = Double.parseDouble(campos[7]);
				String estado = campos[17];

				Date fInicio = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicio);
				Date fFinal = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinal);
				Date hInicial = new SimpleDateFormat("hh:mm:ss").parse(horaInicio);
				Date hFinal = new SimpleDateFormat("hh:mm:ss").parse(horaFinal);
				int anoInicio = Integer.parseInt(fechaInicio.split("-")[0]);

				if(anoInicio==ano) 
				{
					Accidente nuevo = new Accidente(id, fInicio, fFinal, county, gravedad, hInicial, hFinal, latitud, longitud, estado);
					nuevo.cambiarMinutos();
					nuevo.cambiarSegundos();
					arbolAccidentes.put(fechaInicio, nuevo);
					RBTAccidentes.put(fInicio, nuevo);
					RBTHorario.put(hInicial, nuevo);
				}

			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	

		String info = "";
		info += "Número total de accidentes en "+ano+": "+arbolAccidentes.valuesSize()+"\n";
		info += "Número total de llaves: BST - "+arbolAccidentes.size()+"\t RBT - "+RBTAccidentes.size()+"\n";
		info += "Altura árbol: BST - "+arbolAccidentes.height()+"\t RBT - "+RBTAccidentes.height()+"\n";
		info += "Valor mínimo: BST - "+arbolAccidentes.min()+"\t RBT - "+RBTAccidentes.min()+"\n";
		info += "Valor máximo: BST - "+arbolAccidentes.max()+"\t RBT - "+RBTAccidentes.max()+"\n";
		//info += arbolAccidentes.toString();

		return info;
	}
	
	
	public String buscarAccidenteBST(String fecha) throws Exception{
	
		
		int total = 0;
		int grav0 = 0;
		int grav1 = 0;
		int grav2 = 0;
		int grav3 = 0;
		
		long TInicio = System.currentTimeMillis();
		ArregloDinamico<Accidente> accFecha = arbolAccidentes.get(fecha);
		long TFin = System.currentTimeMillis();
		total = accFecha.size();
		long tiempo = TFin - TInicio;
		
		if(total == 0) {
			throw new Exception("No se encontro la fecha");
		}
		Accidente[] ordenado = accFecha.toArray();
		ShellSort.sort(ordenado);
		
		
		for(int i = 0; i < ordenado.length; i++) {
			if(ordenado[i].darGravedad() == 0) {
				grav0++;
			} 
			else if (ordenado[i].darGravedad() == 1) {
				grav1++;
			}
			else if (ordenado[i].darGravedad() == 2) {
				grav2++;
			}
			else if (ordenado[i].darGravedad() == 3) {
				grav3++;
			}
		}
		return "Total de accidentes: " + total + "\n Accidentes con gravedad 0: " + grav0+ " \n Accidentes con gravedad 1: " + grav1 + "\n Accidentes con gravedad 2: " + grav2 + "\n Accidentes con gravedad 3: " + grav3 + "\n Tiempo de ejecuci�n: " + tiempo;
	}
	
	public String buscarAccidenteRBT(Date fecha) throws Exception{
	
		
		int total = 0;
		int grav0 = 0;
		int grav1 = 0;
		int grav2 = 0;
		int grav3 = 0;
		
		long TInicio = System.currentTimeMillis();
		ArregloDinamico<Accidente> accFecha = RBTAccidentes.get(fecha);
		long TFin = System.currentTimeMillis();
		total = accFecha.size();
		long tiempo = TFin - TInicio;
		
		if(total == 0) {
			throw new Exception("No se encontro la fecha");
		}
		
		Accidente[] ordenado = accFecha.toArray();
		ShellSort.sort(ordenado);
		
		
		for(int i = 0; i < ordenado.length; i++) {
			if(ordenado[i].darGravedad() == 0) {
				grav0++;
			} 
			else if (ordenado[i].darGravedad() == 1) {
				grav1++;
			}
			else if (ordenado[i].darGravedad() == 2) {
				grav2++;
			}
			else if (ordenado[i].darGravedad() == 3) {
				grav3++;
			}
		}
		return "Total de accidentes: " + total + "\n Accidentes con gravedad 0: " + grav0+ " \n Accidentes con gravedad 1: " + grav1 + "\n Accidentes con gravedad 2: " + grav2 + "\n Accidentes con gravedad 3: " + grav3 + "\n Tiempo de ejecuci�n: " + tiempo;
	}
	
	public String req2 (Date pFecha) throws Exception
	{
		String date = "2016-02-08";
		Date init = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		ArregloDinamico<Date> accidentesFecha = RBTAccidentes.keysInRange(init, pFecha);
		ArregloDinamico<Accidente> valoresFecha = RBTAccidentes.valuesInRange(init, pFecha);
		int totalAccidentes = valoresFecha.size();
		Date fechaMasAccidentes = null;
		if(totalAccidentes == 0) {
			throw new Exception("No se encontro la fecha");
		}
		
		int contador = 0;
		for (int i = 0; i < accidentesFecha.size(); i++)
		{
			Date fechaAccidentes = accidentesFecha.getElement(i);
			for (int j = 0; j < valoresFecha.size(); j++)
			{
				Date fechaComparacion = valoresFecha.getElement(j).darFechaInicio();
				int contadorComparacion = 0;
				if (fechaAccidentes.compareTo(fechaComparacion) == 0)
				{
					contadorComparacion++;
				}
				if (contadorComparacion > contador)
				{
					contador = contadorComparacion;
					fechaMasAccidentes = fechaAccidentes;
				}
			}
		}
		
		return "El total de accidentes ocurridos antes de la fecha dada es: " + totalAccidentes + " \n La fecha con más accidentes es: " + fechaMasAccidentes + "\n";
	}
	
	public String req3(Date dato, Date dato6) {
		ArregloDinamico<Accidente> accidentes = (ArregloDinamico<Accidente>) RBTAccidentes.valuesInRange(dato, dato6);

		int totalAccidentes = accidentes.size();
		
		return "En el rango de: " + dato + " - " + dato6 + " hubieron un total de " + totalAccidentes;
		}
	
	
	public String req6 (double pLongitud, double pLatitud, double pRadio) {
		ArregloDinamico<Accidente> accidentes = (ArregloDinamico<Accidente>) RBTAccidentes.valuesInRange(RBTAccidentes.min(), RBTAccidentes.max());
		int totalAccidentes = 0;
		int lunes = 0;
		int martes = 0;
		int miercoles = 0;
		int jueves = 0;
		int viernes = 0;
		int sabado = 0;
		int domingo = 0;
		
		for(int i = 0; i < accidentes.size(); i++) {
			Accidente actual = accidentes.getElement(i);
			
			Date fecha = actual.darFechaInicio();
			int dia = getDayNumberOld(fecha);
			
			double distancia = calcularDistancia(pLongitud, actual.darLongitud(), pLatitud, actual.darLatitud());
			if(distancia <= pRadio) {
				totalAccidentes++;
				if(dia == 1) {
					domingo++;
				} else if(dia == 2) {
					lunes++;
				} else if(dia == 3) {
					martes++;
				} else if(dia == 4) {
					miercoles++;
				} else if(dia == 5) {
					jueves++;
				} else if(dia == 6) {
					viernes++;
				} else if(dia == 7) {
					sabado++;
				}
			}
		}
		return "Hay un total de " + totalAccidentes + " accidentes dentro del radio dado. \n Accidentes que sucedieron en lunes: " + lunes + "\n Accidentes que sucedieron en martes " + martes + "\n Accidentes que sucedieron en miercoles " + miercoles + "\n Accidentes que sucedieron en jueves " + jueves + "\n Accidentes que sucedieron en viernes " + viernes + "\n Accidentes que sucedieron en sabado " + sabado + "\n Accidentes que sucedieron en domingo " + domingo;
	}
	
	private double calcularDistancia(double pLongitud1, double pLongitud2, double pLatitud1, double pLatitud2) {
		double distancia = Math.sqrt((pLongitud2 - pLongitud1) + (pLatitud2 - pLatitud1));
		return distancia;
	}
	
	public static int getDayNumberOld(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return cal.get(Calendar.DAY_OF_WEEK);
	}


@SuppressWarnings("deprecation")
public String req5(Date pHora) throws ParseException
{
	if (pHora.getMinutes() >= 0 && pHora.getMinutes() <= 29)
	{
		pHora.setMinutes(00);
	}
	else 
	{
		pHora.setMinutes(30);
	}
	
	if (pHora.getSeconds() >= 0 && pHora.getSeconds() <=29)
	{
		pHora.setSeconds(00);
	}
	else 
	{
		pHora.setSeconds(30);
	}
	String date = "00:00:00";
	String date2 = "23:59:59";
	Date horaFinal = new SimpleDateFormat("hh:mm:ss").parse(date2);
	Date init = new SimpleDateFormat("hh:mm:ss").parse(date);
	ArregloDinamico<Accidente> todosLosAccidentes = RBTHorario.valuesInRange(init, horaFinal);
	ArregloDinamico<Accidente> valuesHora = RBTHorario.valuesInRange(init, pHora);
	ArregloDinamico<Accidente> severidad0 = new ArregloDinamico<Accidente>();
	ArregloDinamico<Accidente> severidad1 = new ArregloDinamico<Accidente>();
	ArregloDinamico<Accidente> severidad2 = new ArregloDinamico<Accidente>();
	ArregloDinamico<Accidente> severidad3 = new ArregloDinamico<Accidente>();
	int totalAccidentes = valuesHora.size();
	int cantidadAccidentesComparacion = todosLosAccidentes.size();
	
	int porcentaje = (totalAccidentes * 100) / cantidadAccidentesComparacion;
	
	for (int i = 0; i < valuesHora.size(); i++)
	{
		Accidente valor = valuesHora.getElement(i);
		if (valor.darGravedad() == 0)
		{
			severidad0.addLast(valor);
		}
		else if (valor.darGravedad() == 1)
		{
			severidad1.addLast(valor);
		}
		else if (valor.darGravedad() == 2)
		{
			severidad2.addLast(valor);
		}
		else
		{
			severidad3.addLast(valor);
		}
	}
	String respuesta = "La cantidad de accidentes en ese rango horario es de: " + totalAccidentes + " \n El porcentaje de accidentes entre ese rango: " + porcentaje + " \n Accidentes con gravedad 0 fueron: " + severidad0.size() + "\n" +severidad0.toString() + " \n Accidentes con gravedad 1 fueron: " + severidad1.size() + "\n" +severidad1.toString() + " \n Accidentes con gravedad 2 fueron: " + severidad2.size() + "\n" + severidad2.toString() + "\n Accidentes con gravedad 3 fueron: " + severidad3.size() + "\n" + severidad3.toString();
	return respuesta;
}

}
