package model.data_structures;

public interface TablaSimbolosOrdenada<K extends Comparable<K>,V extends Comparable<V>> {


	public int size();

	public boolean isEmpty();

	public ArregloDinamico<V> get(K key);

	public int getHeight(K key) ;

	public boolean contains(K key);

	public void put(K key, V value);

	public int height();

	public K min() ;

	public K max();
	
	public Lista<K> keySet();

	public Lista<K> keysInRange(K init, K end);

	public Lista<V> valuesInRange(K init, K end);

}
