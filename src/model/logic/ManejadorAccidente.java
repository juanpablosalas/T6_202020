package model.logic;

import java.io.FileReader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.BST;

public class ManejadorAccidente 
{
	public static final String datosAccidentes = "data/us_accidents_small.csv";
	
	private BST<String, String> arbolAccidentes;
	
	public ManejadorAccidente()
	{
		
		arbolAccidentes = new BST<String,String>();
	}
	
	public void leerArchivo() throws Exception
	{
		String csv = datosAccidentes;
		CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
		try 
		{
			CSVReader reader = new CSVReaderBuilder(new FileReader(csv)).withSkipLines(1).withCSVParser(parser).build();
			String[] campos;
			while ((campos = reader.readNext()) != null)
			{
				int gravedad = Integer.parseInt(campos[3]);
				String fechaHoraInicio = campos[4];
				String fechaHoraFinal = campos[5];
				String county = campos[16];
				String fechaInicio = fechaHoraInicio.split(" ")[0];
				String horaInicio = fechaHoraInicio.split(" ")[1];
				String fechaFinal = fechaHoraFinal.split(" ")[0];
				String horaFinal = fechaHoraFinal.split(" ")[1];
				
				Accidente nuevo = new Accidente(fechaInicio, fechaFinal, county, gravedad, horaInicio, horaFinal);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}		 

	}
}
