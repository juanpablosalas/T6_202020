package model.data_structures;

public class Vertex<K extends Comparable<K>, V> implements Comparable<Vertex<K,V>> {
	
	private K id;
	
	private V value;

	private boolean mark;
	/**
	 * Crea un vértice con identificador único y
valor (información asociada), el vértice
inicia desmarcado
	 * @param id
	 * @param value
	 */
	public Vertex(K id, V value) {
		this.id = id;
		this.value = value;
		this.mark = false;
	}
	
	/**
	 * Devuelve el identificador del vértice
	 * @return
	 */
	public K getId() {
		return id;
	}
	
	/**
	 * Devuelve el valor asociado al vértice
	 * @return
	 */
	public V getValue() {
		return value;
	}
	
	/**
	 * Retorna si el vértice está marcado o no
	 * @return
	 */
	public boolean getMark() {
		return mark;
	}
	
	/**
	 * Agrega un arco adyacente al vértice
	 */
	public void addEdge( Edge<K,V> edge ) {
		
		
	}
	/**
	 * Marca el vértice
	 */
	public void mark() {
		this.mark = true;
	}
	
	/**
	 * Desmarca el vértice
	 */
	public void unmark() {
		this.mark = false;
	}
	
	/**
	 * Retorna el número de arcos (salientes) del vértice
	 * @return
	 */
	public int outdegree() {
		return 0;
	}
	
	/**
	 * Retorna el número de arcos (entrantes) del vértice
	 * @return
	 */
	public int indegree() {
		return 0;
	}
	
	/**
	 * Retorna el arco con el vértice vertex (si existe). Retorna null si no existe.
	 * @param vertex
	 * @return
	 */
	public Edge<K,V> getEdge(K vertex){
		return null;
	}
	
	/**
	 * Retorna una lista con sus vértices
adyacentes (salientes)
	 * @return
	 */
	public Lista<Vertex<K,V>> vertices(){
		return null;
	}
	
	/**
	 * Retorna una lista con sus arcos adyacentes
(salientes)
	 * @return
	 */
	public Lista<Edge<K,V>> edges(){
		return null;
	}

	@Override
	public int compareTo(Vertex<K, V> o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
