package model.logic;

import java.util.Date;

public class Accidente implements Comparable<Accidente>
{
	private Date fechaInicio;

	private Date fechaFinal;

	private String county;

	private int gravedad;

	private String horaInicio;

	private String horaFinal;
	
	private String id;
	
	private Double latitud;
	
	private Double longitud;

	public Accidente (String pId, Date pFechaInicio, Date pFechaFinal, String pCounty, int pGravedad, String pHoraInicial, String pHoraFinal, Double pLatitud, Double pLongitud)
	{
		id = pId;
		fechaInicio = pFechaInicio;
		fechaFinal = pFechaFinal;
		county = pCounty;
		gravedad = pGravedad;
		horaInicio = pHoraInicial;
		horaFinal = pHoraFinal;
		latitud = pLatitud;
		longitud = pLongitud;
	}

	public Date darFechaInicio()
	{
		return fechaInicio;
	}

	public Date darFechaFinal()
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
