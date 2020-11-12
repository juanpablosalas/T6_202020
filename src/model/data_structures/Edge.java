package model.data_structures;

public class Edge<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Edge<K,V>>{


	private Vertex<K,V> source;

	private Vertex<K,V> dest;

	private double weight;

	/**
	 * Crea el arco desde el vértice source al
vértice dest con peso weight
	 * @param source
	 * @param dest
	 * @param weight
	 * @throws Exception 
	 */
	public Edge(Vertex<K,V> source, Vertex<K,V> dest, double weight) throws Exception {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}

	/**
	 * Devuelve el vértice origen
	 * @return
	 */
	public Vertex<K,V> getSource(){
		return source;
	}

	/**
	 * Devuelve el vértice destino
	 * @return
	 */
	public Vertex<K,V> getDest(){
		return dest;
	}

	/**
	 * Devuelve el peso del arco
	 * @return
	 */
	public double weight() {
		return weight;
	}

	/**
	 * Modifica el peso del arco
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge<K, V> o) {
		if(weight>o.weight()) {
			return 1;
		}else if(weight<o.weight()) {
			return -1;
		}
		return 0;
	}
	
	public String toString() {
		return "Source: "+source.toString()+" - Destiny: "+dest.toString()+" - Peso: "+weight;
	}

}
