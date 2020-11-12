package model.logic;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.DiGraph;

public class ManejadorViajes 
{
	public static final String DATOS_VIAJES = "201801-1-citibike-tripdata.csv";

	public static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat FORMATO_HORA = new SimpleDateFormat("HH:mm");


	private DiGraph<Integer,Estacion> grafoViajes;


	public ManejadorViajes()
	{

		grafoViajes = new DiGraph<Integer, Estacion>();
	}

	
	/**
	 * Lee el archivo de datos y añade los viajes al grafo
	 * @return
	 * @throws Exception
	 */
	public String leerArchivo( ) throws Exception
	{
		String csv = DATOS_VIAJES;
		CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
		try 
		{
			CSVReader reader = new CSVReaderBuilder(new FileReader(csv)).withSkipLines(1).withCSVParser(parser).build();
			String[] campos;
			while ((campos = reader.readNext()) != null)
			{
				String id = campos[0];
				String fechaHoraInicio = campos[4];
				String fechaInicio = fechaHoraInicio.split(" ")[0];
				Date fInicio = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicio);
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
		}	

		String info = "";

		return info;
	}

	/**
	 * Para un id de una estación (vértice del grafo) dado por el usuario, debe informarse su grado de entrada y de salida.
	 * @param idEstacion
	 * @return
	 */
	public String grado(int idEstacion) {
		return "";
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



}
