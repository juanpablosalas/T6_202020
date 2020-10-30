package model.logic;

import java.util.Calendar;
import java.util.Date;

public class Accidente implements Comparable<Accidente>
{
	private Date fechaInicio;

	private Date fechaFinal;

	private String county;

	private int gravedad;

	private Date horaInicio;

	private Date horaFinal;
	
	private String id;
	
	private Double latitud;
	
	private Double longitud;
	
	private String estado;

	public Accidente (String pId, Date pFechaInicio, Date pFechaFinal, String pCounty, int pGravedad, Date pHoraInicial, Date pHoraFinal, Double pLatitud, Double pLongitud, String pEstado)
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
		estado = pEstado;
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

	public Date darHoraInicio()
	{
		return horaInicio;
	}

	public Date darHoraFinal()
	{
		return horaFinal;
	}

	public double darLongitud() {
		return longitud;
	}
	
	public double darLatitud() {
		return latitud;
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

	@SuppressWarnings("deprecation")
	public void cambiarMinutos()
	{
		if (horaInicio.getMinutes() >= 0 && horaInicio.getMinutes() <= 29)
		{
			horaInicio.setMinutes(00);
		}
		else 
		{
			horaInicio.setMinutes(30);;
		}

	}
	
	@SuppressWarnings("deprecation")
	public void cambiarSegundos()
	{
		if (horaInicio.getSeconds() >= 0 && horaInicio.getSeconds() <=29)
		{
			horaInicio.setSeconds(0);
		}
		else 
		{
			horaInicio.setSeconds(30);
		}
	}
	
	
	
	@Override
	public String toString() {
		return "ID: "+id+" Gravedad: "+gravedad;
	}


}
