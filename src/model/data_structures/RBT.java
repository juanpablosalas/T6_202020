package model.data_structures;

import java.util.NoSuchElementException;

import model.data_structures.NodoBST;

public class RBT<K extends Comparable<K>, V extends Comparable<V>> implements TablaSimbolosOrdenada<K,V>
{
	
	private static final boolean RED = true;
	
	private static final boolean BLACK = false;

	
	private nodoRBT<K,V> root;
	
	public RBT()
	{
		
	}
	
	public RBT(K llaveRaiz, V valorRaiz) {
		root = new nodoRBT<K, V>(llaveRaiz, valorRaiz, 1, RED);
	}
	
	public int size() {
		if (root == null)
		{
			return 0;
		}
		return root.size();
	}
	
	public boolean isEmpty() 
	{
		return root == null;
	}

	public ArregloDinamico<V> get(K pKey) {
        if (pKey == null) 
        	{
        		throw new IllegalArgumentException("La llave no puede ser nula");
        	}
        return root.get(root, pKey);
    }

	public boolean contains(K key) 
	{
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
		
		if(root==null) {
			root = new nodoRBT<K, V>(key, value, 1, BLACK);
		}else {
			root.putNodoRBT(root, key, value);
			root.setColor(BLACK);;
		}
		
		
	}
	
	public void delete(K key) { 
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        if (!root.esRojo(root.getLeft()) && !root.esRojo(root.getRight()))
            root.setColor(RED);

        root.delete(root, key);
        if (!isEmpty()) root.setColor(BLACK);
    }
	
	public int height() 
	{
		if (root == null)
		{
			throw new NoSuchElementException("El arbol esta vacio");
		}
        return root.height(root);
    }
	
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("El arbol esta vacio");
        if (!root.esRojo(root.getLeft()) && !root.esRojo(root.getRight()))
            root.setColor(RED);;

        root.deleteMin(root);
        if (!isEmpty()) root.setColor(BLACK);
    }
    
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("El arbol esta vacio");

        if (!root.esRojo(root.getLeft()) && !root.esRojo(root.getRight()))
            root.setColor(RED);;

        root.deleteMax(root);
        if (!isEmpty()) root.setColor(BLACK);;
    }
    
    public K min() {
        if (isEmpty()) throw new NoSuchElementException("El arbol esta vacio");
        return root.min(root).key();
    }
    
	@Override
	public Lista<K> keySet() {
		if(root==null) {
			return new ArregloDinamico<K>();
		}
		return root.keySet();
	}

	@Override
	public Lista<K> keysInRange(K init, K end) {
		if(root==null) {
			return new ArregloDinamico<K>();
		}
		return root.keysInRange(init,end);
	}

	@Override
	public Lista<V> valuesInRange(K init, K end) {
		if(root==null) {
			return new ArregloDinamico<V>();
		}
		return root.valuesInRange(init,end);
	}

	@Override
	public int getHeight(K key) 
	{
		int height = 0;
		if(root!=null) {
			height = root.getHeight(key);
		}
		return height;
	}

	@Override
	public K max() {
		return root.max(root).key();
	}
}
