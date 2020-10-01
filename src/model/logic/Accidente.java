package model.logic;

public class Accidente 
{
	private String fechaInicio;
	
	private String fechaFinal;
	
	private String county;
	
	private int gravedad;
	
	private String horaInicio;
	
	private String horaFinal;
	
	public Accidente (String pFechaInicio, String pFechaFinal, String pCounty, int pGravedad, String pHoraInicial, String pHoraFinal)
	{
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
	
}
