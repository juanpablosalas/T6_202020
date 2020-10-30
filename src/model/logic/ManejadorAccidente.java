package model.logic;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	public static final String DATOS_ACCIDENTES = "data/us_accidents_small.csv";

	//public static final String DATOS_ACCIDENTES = "data/us_accidents_dis_2017.csv";

	public static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat FORMATO_HORA = new SimpleDateFormat("HH:mm");


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
		String csv = DATOS_ACCIDENTES;
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
				Date hInicial = new SimpleDateFormat("HH:mm").parse(horaInicio);
				Date hFinal = new SimpleDateFormat("HH:mm").parse(horaFinal);
				int anoInicio = Integer.parseInt(fechaInicio.split("-")[0]);

				if(anoInicio==ano) 
				{
					Accidente nuevo = new Accidente(id, fInicio, fFinal, county, gravedad, hInicial, hFinal, latitud, longitud, estado);
					nuevo.cambiarMinutos();
					nuevo.cambiarSegundos();
					//arbolAccidentes.put(fechaInicio, nuevo);
					RBTAccidentes.put(fInicio, nuevo);
					RBTHorario.put(hInicial, nuevo);
				}

			}

		} catch (Exception e) 
		{
			e.printStackTrace();
		}	

		String info = "";
		info += "Número total de accidentes en "+ano+": "+RBTAccidentes.valuesSize()+"\n";
		info += "Número total de llaves: "+RBTAccidentes.size()+"\n";
		info += "Altura árbol: "+RBTAccidentes.height()+"\n";
		info += "Valor mínimo: "+FORMATO_FECHA.format(RBTAccidentes.min())+"\n";
		info += "Valor máximo: "+FORMATO_FECHA.format(RBTAccidentes.max())+"\n";

		return info;
	}


	public String buscarAccidenteBST(String fecha) throws Exception{


		int total = 0;
		int grav0 = 0;
		int grav1 = 0;
		int grav2 = 0;
		int grav3 = 0;

		long TInicio = System.nanoTime();
		ArregloDinamico<Accidente> accFecha = arbolAccidentes.get(fecha);
		long TFin = System.nanoTime();
		total = accFecha.size();
		long tiempo = TFin - TInicio;

		if(total == 0) {
			throw new Exception("No hubo accidentes en esta fecha");
		}

		Comparable[] ordenado = accFecha.toArray();
		ShellSort.sort(ordenado);


		for(int i = 0; i < ordenado.length; i++) {
			if(((Accidente)ordenado[i]).darGravedad() == 0) {
				grav0++;
			} 
			else if (((Accidente)ordenado[i]).darGravedad() == 1) {
				grav1++;
			}
			else if (((Accidente)ordenado[i]).darGravedad() == 2) {
				grav2++;
			}
			else if (((Accidente)ordenado[i]).darGravedad() == 3) {
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

		Comparable[] ordenado = accFecha.toArray();
		ShellSort.sort(ordenado);


		for(int i = 0; i < ordenado.length; i++) {
			if(((Accidente)ordenado[i]).darGravedad() == 0) {
				grav0++;
			} 
			else if (((Accidente)ordenado[i]).darGravedad() == 1) {
				grav1++;
			}
			else if (((Accidente)ordenado[i]).darGravedad() == 2) {
				grav2++;
			}
			else if (((Accidente)ordenado[i]).darGravedad() == 3) {
				grav3++;
			}
		}



		return "Total de accidentes: " + total + "\n Accidentes con gravedad 0: " + grav0+ " \n Accidentes con gravedad 1: " + grav1 + "\n Accidentes con gravedad 2: " + grav2 + "\n Accidentes con gravedad 3: " + grav3 + "\n Tiempo de ejecución: " + Long.toString(tiempo);
	}

	public String req2 (Date pFecha) throws Exception
	{

		Date init = new Date(RBTAccidentes.min().getTime()-100);

		ArregloDinamico<Date> accidentesFecha = RBTAccidentes.keysInRange(init, pFecha);

		ArregloDinamico<Accidente> valoresFecha = RBTAccidentes.valuesInRange(init, pFecha);
		int totalAccidentes = valoresFecha.size();
		Date fechaMasAccidentes = null;
		if(totalAccidentes == 0) {
			throw new Exception("No se encontró la fecha");
		}


		int mayorFreq = 0;
		for(int i=1; i<=accidentesFecha.size(); i++) {
			Date fecha = accidentesFecha.getElement(i);
			int numAccidentes = RBTAccidentes.get(fecha).size();
			if(numAccidentes>mayorFreq) {
				mayorFreq = numAccidentes;
				fechaMasAccidentes = fecha;
			}

		}



		return "El total de accidentes ocurridos antes de la fecha dada es: " + totalAccidentes + " \n La fecha con más accidentes es: " + fechaMasAccidentes + "\n";
	}

	public String req3(Date fechaIn, Date fechaFin) {
		Date fecha1 = new Date(fechaIn.getTime()-10);
		Date fecha2 = new Date(fechaFin.getTime()+10);
		ArregloDinamico<Accidente> accidentes =  RBTAccidentes.valuesInRange(fecha1, fecha2);

		int totalAccidentes = accidentes.size();

		return "En el rango (inclusivo) de: " + FORMATO_FECHA.format(fechaIn) + " - " + FORMATO_FECHA.format(fechaFin) + " hubo un total de " + totalAccidentes;
	}

	public String req4(Date fechaIn, Date fechaFin) {

		Date fecha1 = new Date(fechaIn.getTime()-10);
		Date fecha2 = new Date(fechaFin.getTime()+10);
		ArregloDinamico<Accidente> accidentes =  RBTAccidentes.valuesInRange(fecha1, fecha2);

		String estadoMasAccidentado = "";
		int numAccidentes = -10;

		for(int i=1; i<accidentes.size()+1;i++) {
			String estadoActual = accidentes.getElement(i).darEstado();
			int ocurrencias = 0;
			for(int j=i;j<accidentes.size()+1;j++) {
				String estadoJ = accidentes.getElement(j).darEstado();
				if(estadoJ.equals(estadoActual)) {
					ocurrencias++;
				}
			}
			if(ocurrencias>numAccidentes) {
				numAccidentes = ocurrencias;
				estadoMasAccidentado = estadoActual;
			}
		}



		return "El estado donde más accidentes ha habido entre el " +FORMATO_FECHA.format(fechaIn)+" y "+FORMATO_FECHA.format(fechaFin)+ " (inclusivo) es "+estadoMasAccidentado + " y la fecha donde más accidentes hubo fue "+FORMATO_FECHA.format(fechaMasAccidentes(accidentes));
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

	private double calcularDistancia(double lat1, double lat2, double lon1, double lon2) { 

		// The math module contains a function 
		// named toRadians which converts from 
		// degrees to radians. 
		lon1 = Math.toRadians(lon1); 
		lon2 = Math.toRadians(lon2); 
		lat1 = Math.toRadians(lat1); 
		lat2 = Math.toRadians(lat2); 

		// Haversine formula  
		double dlon = lon2 - lon1;  
		double dlat = lat2 - lat1; 
		double a = Math.pow(Math.sin(dlat / 2), 2) 
				+ Math.cos(lat1) * Math.cos(lat2) 
				* Math.pow(Math.sin(dlon / 2),2); 

		double c = 2 * Math.asin(Math.sqrt(a)); 

		// Radius of earth in kilometers. Use 3956  
		// for miles 
		double r = 6371; 

		// calculate the result 
		return(c * r); 
	}  

	public static int getDayNumberOld(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public Calendar convert(Date source) 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		return calendar;
	}

	public Date round(Date fecha) {


		long longDate = fecha.getTime();
		Calendar cal = convert(fecha);
		int minutos = cal.get(Calendar.MINUTE);
		//int hora = cal.get(Calendar.HOUR);

		if(minutos<15) {
			longDate -= minutos*60000;
		}else if(minutos<=30) {
			longDate += (30-minutos)*60000;
		}else {
			longDate += (60-minutos)*60000;
		}

		Date nueva = new Date(longDate);


		return nueva;
	}

	public String req5(Date horaIn, Date horaFin) throws ParseException
	{

		Date horaInit = new Date(round(horaIn).getTime()-10);
		Date horaFinal = new Date(round(horaFin).getTime()+10);

		ArregloDinamico<Accidente> todosLosAccidentes = RBTHorario.valuesInRange(new Date(RBTHorario.min().getTime()-10), new Date(RBTHorario.max().getTime()+10));
		ArregloDinamico<Accidente> valuesHora = RBTHorario.valuesInRange(horaInit, horaFinal);
		ArregloDinamico<Accidente> severidad0 = new ArregloDinamico<Accidente>();
		ArregloDinamico<Accidente> severidad1 = new ArregloDinamico<Accidente>();
		ArregloDinamico<Accidente> severidad2 = new ArregloDinamico<Accidente>();
		ArregloDinamico<Accidente> severidad3 = new ArregloDinamico<Accidente>();
		int totalAccidentes = valuesHora.size();
		int cantidadAccidentesComparacion = todosLosAccidentes.size();

		double porcentaje = (totalAccidentes * 100.0) / (double) cantidadAccidentesComparacion;

		porcentaje = ((int)(porcentaje*100))/100.0;

		for (int i = 1; i < valuesHora.size()+1; i++)
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
		String respuesta = "La cantidad de accidentes en ese rango horario es de: " + totalAccidentes + " \n El porcentaje de accidentes entre ese rango: " + porcentaje + "%" + " \n \t Accidentes con gravedad 0 fueron: " + severidad0.size() + " \n \t Accidentes con gravedad 1 fueron: " + severidad1.size() + " \n \t Accidentes con gravedad 2 fueron: " + severidad2.size() + "\n \t Accidentes con gravedad 3 fueron: " + severidad3.size() +"\n";
		return respuesta;
	}

	public Date fechaMasAccidentes(ArregloDinamico<Accidente> accidentes) {
		Date fechaMasAccidentes = null;
		int numAccidentes = -10;

		for(int i=1; i<accidentes.size()+1;i++) {
			Date fechaActual = accidentes.getElement(i).darFechaInicio();
			int ocurrencias = 0;
			for(int j=i;j<accidentes.size()+1;j++) {
				Date fechaJ = accidentes.getElement(j).darFechaInicio();
				if(fechaJ.equals(fechaActual)) {
					ocurrencias++;
				}
			}
			if(ocurrencias>numAccidentes) {
				numAccidentes = ocurrencias;
				fechaMasAccidentes = fechaActual;
			}
		}

		return fechaMasAccidentes;
	}

}
