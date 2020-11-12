package model.data_structures;

public class Vertex<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Vertex<K,V>> {

	private K id;

	private V value;

	private boolean mark;
	
	private int indegree;
	
	private ArregloDinamico<Edge<K,V>> arcosSalientes;


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
		arcosSalientes = new ArregloDinamico<Edge<K,V>>(); 
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
	 * Agrega un arco adyacente saliente al vértice
	 */
	public void addEdge( Edge<K,V> edge ) {
		arcosSalientes.addLast(edge);

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
		return arcosSalientes.size();
	}

	/**
	 * Retorna el número de arcos (entrantes) del vértice
	 * @return
	 */
	public int indegree() {
		return indegree;
	}
	
	public void increaseIndegree() {
		indegree++;
	}

	/**
	 * Retorna el arco con el vértice vertex (si existe). Retorna null si no existe.
	 * @param vertex
	 * @return
	 */
	public Edge<K,V> getEdge(K vertex){
		Edge<K,V> arcoBuscado = null;
		for(int i=1; i<=arcosSalientes.size() && arcoBuscado == null; i++) {
			if(arcosSalientes.getElement(i).getDest().getId().equals(vertex)) {
				arcoBuscado = arcosSalientes.getElement(i);
			}
		}
		return arcoBuscado;
	}

	/**
	 * Retorna una lista con sus vértices
adyacentes (salientes)
	 * @return
	 */
	public Lista<Vertex<K,V>> vertices(){
		ArregloDinamico<Vertex<K,V>> vertices = new ArregloDinamico<Vertex<K,V>>();
		for(int i=1; i<=arcosSalientes.size(); i++) {
				vertices.addLast(arcosSalientes.getElement(i).getDest());
		}
		return vertices;
	}

	/**
	 * Retorna una lista con sus arcos adyacentes
(salientes)
	 * @return
	 */
	public Lista<Edge<K,V>> edges(){
		return arcosSalientes;
	}

	@Override
	public int compareTo(Vertex<K, V> o) {
		return value.compareTo(o.getValue());
	}

	public String toString() {
		return "("+id.toString()+","+value.toString()+")";
	}
	
}
