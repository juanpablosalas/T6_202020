package model.logic;

public class Estacion implements Comparable<Estacion> {

	private String stationName;

	private int stationID;

	private double longitud;

	private double latitud;
	


	public Estacion (String stationName, int stationID, double longitud, double latitud)
	{
		this.stationName = stationName;
		this.stationID = stationID;
		this.longitud = longitud;
		this.latitud = latitud;
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
	
	@Override
	public String toString() {
		return "<ID: "+stationID+" Name: "+stationName+">";
	}

	@Override
	public int compareTo(Estacion o) {
		return 0;
	}
	
	public boolean equals(Estacion o) {
		return stationID==o.darStationID();
	}




}
