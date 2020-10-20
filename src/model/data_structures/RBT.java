package model.data_structures;

import java.util.NoSuchElementException;

public class RBT<K extends Comparable<K>, V extends Comparable<V>>
{
	
	private Nodo root;

	private static final boolean RED = true;
	
	private static final boolean BLACK = false;
	
	private class Nodo
	{
		K key;
		
		private ArregloDinamico<V> value;
		
		Nodo derecho;
		
		Nodo izquierdo;
		
		int numeroNodos;
		
		boolean color;
		
		Nodo(K pKey, V pValue, int N, boolean pColor)
		{
			this.key = pKey;
			this.value = new ArregloDinamico<V>();
			value.addLast(pValue);
			this.numeroNodos = N;
			this.color = pColor;
		}
		
		
	}
	
	private Nodo delete(Nodo pNodo, K key) { 

        if (key.compareTo(pNodo.key) < 0)  {
            if (!esRojo(pNodo.izquierdo) && !esRojo(pNodo.izquierdo.izquierdo))
                pNodo = moveRedLeft(pNodo);
            pNodo.izquierdo = delete(pNodo.izquierdo, key);
        }
        else {
            if (esRojo(pNodo.izquierdo))
                pNodo = rotateRight(pNodo);
            if (key.compareTo(pNodo.key) == 0 && (pNodo.derecho == null))
                return null;
            if (!esRojo(pNodo.derecho) && !esRojo(pNodo.derecho.izquierdo))
                pNodo = moveRedRight(pNodo);
            if (key.compareTo(pNodo.key) == 0) {
                Nodo x = min(pNodo.derecho);
                pNodo.key = x.key;
                pNodo.value = x.value;
                pNodo.derecho = deleteMin(pNodo.derecho);
            }
            else pNodo.derecho = delete(pNodo.derecho, key);
        }
        return balance(pNodo);
    }

	private Nodo rotateRight(Nodo pNodo) 
	{
        Nodo x = pNodo.izquierdo;
        pNodo.izquierdo = x.derecho;
        x.derecho = pNodo;
        x.color = x.derecho.color;
        x.derecho.color = RED;
        x.numeroNodos = pNodo.numeroNodos;
        pNodo.numeroNodos = size(pNodo.izquierdo) + size(pNodo.derecho) + 1;
        return x;
    }
	
	private Nodo rotateLeft(Nodo pNodo) 
	{
        Nodo x = pNodo.derecho;
        pNodo.derecho = x.izquierdo;
        x.izquierdo = pNodo;
        x.color = x.izquierdo.color;
        x.izquierdo.color = RED;
        x.numeroNodos = pNodo.numeroNodos;
        pNodo.numeroNodos = size(pNodo.izquierdo) + size(pNodo.derecho) + 1;
        return x;
    }
	
	private void flipColors(Nodo pNodo) {
        pNodo.color = !pNodo.color;
        pNodo.izquierdo.color = !pNodo.izquierdo.color;
        pNodo.derecho.color = !pNodo.derecho.color;
    }
	
	private Nodo moveRedLeft(Nodo h) {
        flipColors(h);
        if (esRojo(h.derecho.izquierdo)) { 
            h.derecho = rotateRight(h.derecho);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
	
	private Nodo moveRedRight(Nodo h) {
        flipColors(h);
        if (esRojo(h.izquierdo.izquierdo)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
	
	private Nodo balance(Nodo pNodo) 
	{
        if (esRojo(pNodo.derecho))                      pNodo = rotateLeft(pNodo);
        if (esRojo(pNodo.izquierdo) && esRojo(pNodo.izquierdo.izquierdo)) pNodo = rotateRight(pNodo);
        if (esRojo(pNodo.izquierdo) && esRojo(pNodo.derecho))     flipColors(pNodo);

        pNodo.numeroNodos = size(pNodo.izquierdo) + size(pNodo.derecho) + 1;
        return pNodo;
    }
	
	private int size(Nodo pNodo) 
	{
		if(pNodo == null) 
		{
			return 0;
		}
		return pNodo.numeroNodos;
	}
	
	private boolean esRojo(Nodo pNodo)
	{
		if (pNodo == null)
		{
			return false;
		}
		return pNodo.color == RED;
	}
	
	private ArregloDinamico<V> get(Nodo pNodo, K key) {
        while (pNodo != null) {
            int cmp = key.compareTo(pNodo.key);
            if      (cmp < 0) 
            	{
            		pNodo = pNodo.izquierdo;
            	}
            else if (cmp > 0) 
            	{
            		pNodo = pNodo.derecho;
            	}
            else      
            	{
            		return pNodo.value;
            	}
        }
        return null;
    }
	
	private Nodo put(Nodo pNodo, K key, V val) { 
        if (pNodo == null) return new Nodo(key, val, 1, RED);

        int cmp = key.compareTo(pNodo.key);
        if      (cmp < 0) pNodo.izquierdo  = put(pNodo.izquierdo,  key, val); 
        else if (cmp > 0) pNodo.derecho = put(pNodo.derecho, key, val); 
        else              pNodo.value.addLast(val); ;

        if (esRojo(pNodo.derecho) && !esRojo(pNodo.izquierdo))      pNodo = rotateLeft(pNodo);
        if (esRojo(pNodo.izquierdo)  &&  esRojo(pNodo.izquierdo.izquierdo)) pNodo = rotateRight(pNodo);
        if (esRojo(pNodo.izquierdo)  &&  esRojo(pNodo.derecho))     flipColors(pNodo);
        pNodo.numeroNodos = size(pNodo.izquierdo) + size(pNodo.derecho) + 1;

        return pNodo;
    }
	
	private int height(Nodo pNodo) {
        if (pNodo == null) return -1;
        return 1 + Math.max(height(pNodo.izquierdo), height(pNodo.derecho));
    }
	
	private Nodo deleteMin(Nodo h) { 
        if (h.izquierdo == null)
            return null;

        if (!esRojo(h.izquierdo) && !esRojo(h.izquierdo.izquierdo))
            h = moveRedLeft(h);

        h.izquierdo = deleteMin(h.izquierdo);
        return balance(h);
    }
	
	private Nodo min(Nodo x) { 
        if (x.izquierdo == null) return x; 
        else                return min(x.izquierdo); 
    } 
	
	public RBT()
	{
		
	}
	
	
	public int size() {
		return size(root);
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
        return get(root, key);
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
		root = put(root, key, value);
		root.color = BLACK;
	}
	
	public void delete(K key) { 
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        if (!esRojo(root.izquierdo) && !esRojo(root.derecho))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }
	
	public int height() {
        return height(root);
    }
	
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!esRojo(root.izquierdo) && !esRojo(root.derecho))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }
    
    public K min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    } 

}
