package model.logic;

public class Accidente implements Comparable<Accidente>
{
	private String fechaInicio;

	private String fechaFinal;

	private String county;

	private int gravedad;

	private String horaInicio;

	private String horaFinal;
	
	private String id;

	public Accidente (String pId, String pFechaInicio, String pFechaFinal, String pCounty, int pGravedad, String pHoraInicial, String pHoraFinal)
	{
		id = pId;
		fechaInicio = pFechaInicio;
		fechaFinal = pFechaFinal;
		county = pCounty;
		gravedad = pGravedad;
		horaInicio = pHoraInicial;
		horaFinal = pHoraFinal;
	}

	public String darFechaInicio()
	{
		return fechaInicio;
	}

	public String darFechaFinal()
	{
		return fechaFinal;
	}

	public String darCounty()
	{
		return county;
	}

	public int darGravedad()
	{
		return gravedad;
	}

	public String darHoraInicio()
	{
		return horaInicio;
	}

	public String darHoraFinal()
	{
		return horaFinal;
	}

	@Override
	public int compareTo(Accidente o) {
		int comp = 1;
		if(gravedad==o.darGravedad()) {
			comp = 0;
		}else if(gravedad<o.darGravedad()) {
			comp = -1;
		}
		return comp;
	}

	
	@Override
	public String toString() {
		return "ID: "+id+" Gravedad: "+gravedad;
	}


}
