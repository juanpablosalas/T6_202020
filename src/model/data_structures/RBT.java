package model.data_structures;

import java.util.NoSuchElementException;

public class RBT<K extends Comparable<K>, V extends Comparable<V>> implements TablaSimbolosOrdenada<K,V>{

	private nodoRBT<K,V> root;

	public static final boolean RED = true;

	public static final boolean BLACK = false;

	private nodoRBT<K,V> aux;




	public RBT()
	{
		aux = new nodoRBT<K, V>(null, null, 0, BLACK);
	}


	public int size() {
		if(root==null) {
			return 0;
		}else {
			return root.size(root);
		}
	}

	public boolean isEmpty() 
	{
		return root == null;
	}

	public ArregloDinamico<V> get(K key) {
		if (key == null) 
		{
			throw new IllegalArgumentException("La llave no puede ser nula");
		}
		return root.get(root, key);
	}

	
	public int getHeight(K key) {
		int height = 0;
		if(root!=null) {
			height = root.getHeight(key);
		}
		return height;
	}
	
	public boolean contains(K key) 
	{
		if(root==null) {
			return false;
		}
		return get(key)!=null;
	}


	public void put(K key, V value)
	{
		if (key == null)
		{
			throw new IllegalArgumentException("La llave no puede ser nula");
		}
		if (value == null)
		{
			delete(key);
			return;
		}
		if(root != null) {
			root = root.put(root, key, value);
			root.color = BLACK;
		}else {
			root = new nodoRBT<K,V>(key, value, 1, RBT.BLACK);
		}
		
	}

	public void delete(K key) { 
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return;

		if (!aux.esRojo(root.left()) && !aux.esRojo(root.right()))
			root.color = RED;

		root = root.delete(root, key);
		if (!isEmpty()) root.color = BLACK;
	}

	public int height() {
		return aux.height(root);
	}

	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");
		if (!aux.esRojo(root.left()) && !aux.esRojo(root.right()))
			root.color = RED;

		root = aux.deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
	}

	public K min() {
		if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
		return root.min(root).key;
	} 
	
	public K max() {
		if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
		return root.max(root).key;
	} 
	
	public Lista<K> keySet() {
		if(root==null) {
			return new ArregloDinamico<K>();
		}
		return root.keySet();
	}
	
	public ArregloDinamico<K> keysInRange(K init, K end) {
		if(root==null) {
			return new ArregloDinamico<K>();
		}
		return root.keysInRange(init,end);
	}
	
	public Lista<V> valuesInRange(K init, K end) {
		if(root==null) {
			return new ArregloDinamico<V>();
		}
		return root.valuesInRange(init,end);
	}

}
