package model.logic;

import java.io.FileReader;
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

	public ManejadorAccidente()
	{

		arbolAccidentes = new BST<String,Accidente>();
		RBTAccidentes = new RBT<Date, Accidente>();
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

				Date fInicio = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicio);
				Date fFinal = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinal);
				int anoInicio = Integer.parseInt(fechaInicio.split("-")[0]);

				if(anoInicio==ano) {
					Accidente nuevo = new Accidente(id, fInicio, fFinal, county, gravedad, horaInicio, horaFinal);
					arbolAccidentes.put(fechaInicio, nuevo);
					RBTAccidentes.put(fInicio, nuevo);
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
		int totalAccidentes = accidentesFecha.size();
		Date fechaMasAccidentes = null;
		if(totalAccidentes == 0) {
			throw new Exception("No se encontro la fecha");
		}
		
		int contador = 0;
		for (int i = 0; i < accidentesFecha.size(); i++)
		{
			Date fechaAccidentes = accidentesFecha.getElement(i);
			for (int j = 0; j < accidentesFecha.size(); j++)
			{
				Date fechaComparacion = accidentesFecha.getElement(j);
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
}
