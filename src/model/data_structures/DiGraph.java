package model.data_structures;

public class DiGraph<K extends Comparable<K>,V> {


	int max = 1000;

	int numVertices;

	int numEdges;

	private ArregloDinamico<Vertex<K,V>> adj;


	public DiGraph() {
		this.numVertices = 0;
		this.numEdges = 0;
		adj = new ArregloDinamico<Vertex<K,V>>();

	}

	/**
	 * Retorna true si el vértice con id
suministrado está en el grafo
	 * @param id
	 * @return
	 */
	public boolean  containsVertex(K id) {
		boolean contiene = false;
		for(int i=1; i<=adj.size() && !contiene; i++) {
			contiene = contiene ||adj.getElement(i).getId().equals(id);
		}
		return contiene;
	}


	/**
	 * Devuelve el número de vértices en el grafo
	 * @return
	 */
	public int numVertices() {
		return numVertices;
	}

	/**
	 * Devuelve el número de arcos en el grafo
	 * @return
	 */
	public int numEdges() {
		return numEdges;
	}

	/**
	 * Añade un vértice al grafo con su
identificador y valor
	 * @param id
	 * @param value
	 */
	public void insertVertex(K id, V value) {
		Vertex<K,V> nuevo = new Vertex<K, V>(id, value);
		adj.addLast(nuevo);
		numVertices ++;

	}

	/**
	 * Añade un arco dirigido pesado entre el
vértice source y dest, con peso weight. Si el
arco YA existe se modifica su peso.
	 * @param source
	 * @param dest
	 * @param weight
	 */
	public void addEdge(K source, K dest, double weight) throws Exception {

		// Encuentra los vértices de acuerdo a su ID
		Vertex<K,V> fuente = getVertex(source);
		Vertex<K,V> destino = getVertex(dest);

		if(fuente==null|| destino == null) {
			throw new Exception("Los vértices no existen");
		}

		// Crea el arco entre los vertices fuente y destino
		Edge<K,V> arcoNuevo = new Edge<K, V>(fuente, destino, weight);

		// Revisa si ya hay un arco que vaya entre fuente y destino, en cuyo caso cambia su peso
		// De lo contrario, agrega el arco nuevo.


		if(fuente.getEdge(dest)!=null) {
			fuente.getEdge(dest).setWeight(weight);
		}else {
			fuente.addEdge(arcoNuevo);
		}


		if(destino.getEdge(source)!=null) {
			destino.getEdge(source).setWeight(weight);
		}else {
			destino.addEdge(arcoNuevo);
		}

		numEdges++;

	}

	/**
	 * Retorna el vértice a partir de su
identificador único
	 * @param id
	 * @return
	 */
	public Vertex<K,V> getVertex(K id) {
		Vertex<K,V> buscado = null;
		for(int i=1; i<=adj.size() && buscado == null; i++) {
			if(adj.getElement(i).getId().equals(id)) {
				buscado = adj.getElement(i);
			}
		}
		return buscado;
	}

	/**
	 * Retorna el arco entre los vértices idS y idD
(si existe). Retorna null si no existe.
	 * @param idS
	 * @param idD
	 * @return
	 */
	public Edge<K,V> getEdge(K idS, K idD){
		Vertex<K,V> vertice = getVertex(idS);
		return vertice.getEdge(idD);
	}

	/**
	 * Devuelve una lista de arcos adyacentes
(salientes) al vértice con id
	 * @param id
	 * @return
	 */
	public Lista<Edge<K,V>> adjacentEdges(K id) {
		Vertex<K,V> vertice = getVertex(id);
		return vertice.edges();
	}

	/**
	 * Devuelve una lista de vértices adyacentes
(salientes) al vértice con id
	 * @param id
	 * @return
	 */
	public Lista<Vertex<K,V>> adjacentVertex(K id){
		Vertex<K,V> vertice = getVertex(id);
		return vertice.vertices();
	}

	/**
	 * Devuelve el grado de entrada del vértice
vertex (número de arcos entrantes)
	 * @param vertex
	 * @return
	 */
	public int indegree(K vertex) {
		Vertex<K,V> vertice = getVertex(vertex);
		return vertice.indegree();
	}

	/**
	 * Devuelve el grado de salida del vértice
vertex (número de arcos salientes)
	 * @param vertex
	 * @return
	 */
	public int outdegree(K vertex) {
		Vertex<K,V> vertice = getVertex(vertex);
		return vertice.outdegree();
	}

	/**
	 * Devuelve una lista con todos los arcos del
grafo
	 * @return
	 */
	public Lista<Edge<K,V>> edges(){
		ArregloDinamico<Edge<K,V>> edges = null;
		try {
			edges = new ArregloDinamico<Edge<K,V>>(numEdges);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=1; i<=adj.size(); i++) {
			Lista<Edge<K,V>> edgesi = adj.getElement(i).edges();
			for(int j=1; j<=edgesi.size();j++) {
				if(edges.isPresent(edgesi.getElement(j))==-1) {
					edges.addLast(edgesi.getElement(j));
				}
			}
		}
		return edges;
	}

	/**
	 * Devuelve una lista con los vértices del grafo
	 * @return
	 */
	public Lista<Vertex<K,V>> vertices(){
		return adj;
	}





}
