package model.logic;

public class Estacion implements Comparable<Estacion> {

	private String stationName;

	private int stationID;

	private double longitud;

	private double latitud;
	
	private double tiempoViaje;



	public Estacion (String stationName, int stationID, double longitud, double latitud, double tiempoViaje)
	{
		this.stationName = stationName;
		this.stationID = stationID;
		this.longitud = longitud;
		this.latitud = latitud;
		this.tiempoViaje = tiempoViaje;
	}


	public String darStationName() {
		return stationName;
	}

	public int darStationID() {
		return stationID;
	}

	public double darLongitud() {
		return longitud;
	}

	public double darLatitud() {
		return latitud;
	}
	
	public double darTiempoViaje()
	{
		return tiempoViaje;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public int compareTo(Estacion o) {
		return 0;
	}




}
