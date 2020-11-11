package model.data_structures;

public class DiGraph<K extends Comparable<K>,V> {

	/**
	 * Retorna true si el vértice con id
suministrado está en el grafo
	 * @param id
	 * @return
	 */
	public boolean  containsVertex(K id) {
		return false;
	}

	
	/**
	 * Devuelve el número de vértices en el grafo
	 * @return
	 */
	public int numVertices() {
		return 0;
	}

	/**
	 * Devuelve el número de arcos en el grafo
	 * @return
	 */
	public int numEdges() {
		return 0;
	}

	/**
	 * Añade un vértice al grafo con su
identificador y valor
	 * @param id
	 * @param value
	 */
	public void insertVertex(K id, V value) {

	}

	/**
	 * Añade un arco dirigido pesado entre el
vértice source y dest, con peso weight. Si el
arco YA existe se modifica su peso.
	 * @param source
	 * @param dest
	 * @param weight
	 */
	public void addEdge(K source, K dest, double weight) {

	}
	
	/**
	 * Retorna el vértice a partir de su
identificador único
	 * @param id
	 * @return
	 */
	public Vertex<K,V> getVertex(K id) {
		return null;
	}
	
	/**
	 * Retorna el arco entre los vértices idS y idD
(si existe). Retorna null si no existe.
	 * @param idS
	 * @param idD
	 * @return
	 */
	public Edge<K,V> getEdge(K idS, K idD){
		return null;
	}
	
	/**
	 * Devuelve una lista de arcos adyacentes
(salientes) al vértice con id
	 * @param id
	 * @return
	 */
	public Lista<Edge<K,V>> adjacentEdges(K id) {
		return null;
	}
	
	/**
	 * Devuelve una lista de vértices adyacentes
(salientes) al vértice con id
	 * @param id
	 * @return
	 */
	public Lista<Vertex<K,V>> adjacentVertex(K id){
		return null;
	}
	
	/**
	 * Devuelve el grado de entrada del vértice
vertex (número de arcos entrantes)
	 * @param vertex
	 * @return
	 */
	public int indegree(K vertex) {
		return 0;
	}
	
	/**
	 * Devuelve el grado de salida del vértice
vertex (número de arcos salientes)
	 * @param vertex
	 * @return
	 */
	public int outdegree(K vertex) {
		return 0;
	}
	
	/**
	 * Devuelve una lista con todos los arcos del
grafo
	 * @return
	 */
	public Lista<Edge<K,V>> edges(){
		return null;
	}
	
	/**
	 * Devuelve una lista con los vértices del grafo
	 * @return
	 */
	public Lista<Vertex<K,V>> vertices(){
		return null;
	}
	
	



}
