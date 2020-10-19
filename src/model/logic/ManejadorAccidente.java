package model.logic;

import java.io.FileReader;

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
	
	private RBT<String, Accidente> RBTAccidentes;

	public ManejadorAccidente()
	{

		arbolAccidentes = new BST<String,Accidente>();
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

				int anoInicio = Integer.parseInt(fechaInicio.split("-")[0]);

				if(anoInicio==ano) {
					Accidente nuevo = new Accidente(id, fechaInicio, fechaFinal, county, gravedad, horaInicio, horaFinal);
					ArregloDinamico<Accidente> valores = new ArregloDinamico<Accidente>();
					valores.addLast(nuevo);
					arbolAccidentes.put(fechaInicio, nuevo);
					RBTAccidentes.put(fechaInicio, valores);
				}

			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	

		String info = "";
		info += "Número total de accidentes en "+ano+": "+arbolAccidentes.valuesSize()+"\n";
		info += "Número total de llaves en BST: "+arbolAccidentes.size()+"\n";
		info += "Altura árbol: "+arbolAccidentes.height()+"\n";
		info += "Valor mínimo: "+arbolAccidentes.min()+"\n";
		info += "Valor máximo: "+arbolAccidentes.max()+"\n";
		//info += arbolAccidentes.toString();

		return info;
	}
	
	public String requerimiento1(String fecha) throws Exception {
		ArregloDinamico<Accidente> accFecha = arbolAccidentes.get(fecha);
		int total = accFecha.size();
		
		if(total==0) {
			throw new Exception("No se encontró la fecha");
		}
		
		Accidente[] ordenado = accFecha.toArray();
		ShellSort.sort(ordenado);
		
		String rta = "Total: "+total + "\n Accidentes: ";
		for(int i=0; i<ordenado.length; i++) {
			rta += ordenado[i].toString();
		}
		return "T";
	}
}
