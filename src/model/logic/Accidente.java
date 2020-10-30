package model.logic;

import java.util.Date;

public class Accidente implements Comparable<Accidente> {

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
		estado = abbrvToState(pEstado);
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

	public String darEstado() {
		return estado;
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


	public String abbrvToState(String abbr) {
		String state = "";

		String [] abbrv = "AL	AK	AZ	AR	CA	CO	CT	DE	DC	FL	GA	HI	ID	IL	IN	IA	KS	KY	LA	ME	MD	MA	MI	MN	MS	MO	MT	NE	NV	NH	NJ	NM	NY	NC	ND	OH	OK	OR	PA	RI	SC	SD	TN	TX	UT	VT	VA	WA	WV	WI	WY	PR".split("	");
		String [] estados = "Alabama	Alaska	Arizona	Arkansas	California	Colorado	Connecticut	Delaware	District of Columbia	Florida	Georgia	Hawaii	Idaho	Illinois	Indiana	Iowa	Kansas	Kentucky	Louisiana	Maine	Maryland	Massachusetts	Michigan	Minnesota	Mississippi	Missouri	Montana	Nebraska	Nevada	New Hampshire	New Jersey	New Mexico	New York	North Carolina	North Dakota	Ohio	Oklahoma	Oregon	Pennsylvania	Rhode Island	South Carolina	South Dakota	Tennessee	Texas	Utah	Vermont	Virginia	Washington	West Virginia	Wisconsin	Wyoming	Puerto Rico".split("	");

		for(int i=0; i<abbrv.length && state.equals("");i++) {
			if(abbr.equals(abbrv[i])) {
				state = estados[i];
			}
		}

		return state;
	}


	@Override
	public String toString() {
		return "ID: "+id+" Gravedad: "+gravedad+" Fecha: "+ManejadorAccidente.FORMATO_FECHA.format(fechaInicio);
	}

	@Override
	public int compareTo(Accidente o) {
		Integer g1 = gravedad;
		Integer g2 = o.darGravedad();
		return g1.compareTo(g2);
	}




}
