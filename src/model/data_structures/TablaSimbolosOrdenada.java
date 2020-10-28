package model.data_structures;

public interface TablaSimbolosOrdenada<K extends Comparable<K>,V extends Comparable<V>> {


	/**
	 * Retornar el número de parejas [Llave,Valor] del árbol
	 * @return
	 */
	public int size();

	/**
	 * Informa si el árbol es vacío
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * Retorna el arreglo de valores V asociado a la llave key dada. Si la llave no se
	 * encuentra se retorna el valor null.
	 * @param key
	 * @return
	 */
	public ArregloDinamico<V> get(K key);

	
	/**
	 * Retorna la altura del camino desde la raiz para llegar a la llave key (si
	 * la llave existe). Retorna valor –1 si la llave No existe
	 * @param key
	 * @return
	 */
	public int getHeight(K key) ;

	/**
	 * Indica si la llave key se encuentra en el árbo
	 * @param key
	 * @return
	 */
	public boolean contains(K key);

	/**
	 * Inserta la pareja [key, val] en el árbol. Si la llave ya existe se reemplaza
	 * el valor. 
	 * @param key
	 * @param value
	 */
	public void put(K key, V value);

	/**
	 * Retorna la altura del árbol definida como la altura de la rama más alta
	 * (aquella que tenga mayor número de enlaces desde la raíz a una hoja)
	 * @return
	 */
	public int height();

	/**
	 * Retorna la llave más pequeña del árbol. Valor null si árbol vacío
	 * @return
	 */
	public K min() ;

	/**
	 * Retorna la llave más grande del árbol. Valor null si árbol vacío
	 * @return
	 */
	public K max();
	
	/**
	 * Retorna las llaves del árbol. Para su implementación en BST o RBT
	 * deben retornarse usando un recorrido en Inorden.
	 * @return
	 */
	public Lista<K> keySet();

	/**
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango
	 * de llaves dado. Las llaves en el rango deben retornarse en orden
	 * ascendente. Por eficiencia, debe intentarse No recorrer todo el árbol.
	 * @param init
	 * @param end
	 * @return
	 */
	public Lista<K> keysInRange(K init, K end);

	/**
	 * Retorna todos los valores V en el árbol que estén asociados al rango de llaves dado. 
	 * Por eficiencia, debe intentarse No recorrer todo el árbol.
	 * @param init
	 * @param end
	 * @return
	 */
	public Lista<V> valuesInRange(K init, K end);

}
