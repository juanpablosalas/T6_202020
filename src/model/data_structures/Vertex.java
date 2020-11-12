package model.data_structures;

public class Vertex<K extends Comparable<K>, V> implements Comparable<Vertex<K,V>> {

	private K id;

	private V value;

	private boolean mark;

	private ArregloDinamico<Edge<K,V>> arcos;


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
		arcos = new ArregloDinamico<Edge<K,V>>(); 
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
		arcos.addLast(edge);

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
		int outdegree = 0;
		for(int i=1; i<=arcos.size(); i++) {
			if(arcos.getElement(i).getSource().equals(this)) {
				outdegree++;
			}
		}
		return outdegree;
	}

	/**
	 * Retorna el número de arcos (entrantes) del vértice
	 * @return
	 */
	public int indegree() {
		int indegree = 0;
		for(int i=1; i<=arcos.size(); i++) {
			if(arcos.getElement(i).getDest().equals(this)) {
				indegree++;
			}
		}
		return indegree;
	}

	/**
	 * Retorna el arco con el vértice vertex (si existe). Retorna null si no existe.
	 * @param vertex
	 * @return
	 */
	public Edge<K,V> getEdge(K vertex){
		Edge<K,V> arcoBuscado = null;
		for(int i=1; i<=arcos.size() && arcoBuscado == null; i++) {
			if(arcos.getElement(i).getDest().getId().equals(vertex) || arcos.getElement(i).getSource().getId().equals(vertex) ) {
				arcoBuscado = arcos.getElement(i);
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
		for(int i=1; i<=arcos.size(); i++) {
			Edge<K,V> arcoi = arcos.getElement(i);
			if(arcoi.getSource().equals(this)) {
				vertices.addLast(arcoi.getDest());
			}
		}
		return vertices;
	}

	/**
	 * Retorna una lista con sus arcos adyacentes
(salientes)
	 * @return
	 */
	public Lista<Edge<K,V>> edges(){
		ArregloDinamico<Edge<K,V>> edges = new ArregloDinamico<Edge<K,V>>();
		for(int i=1; i<=arcos.size(); i++) {
			Edge<K,V> arcoi = arcos.getElement(i);
			if(arcoi.getSource().equals(this)) {
				edges.addLast(arcoi);
			}
		}
		return edges;
	}

	@Override
	public int compareTo(Vertex<K, V> o) {
		return id.compareTo(o.getId());
	}

}
