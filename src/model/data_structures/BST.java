package model.data_structures;

public class BST<K extends Comparable<K>,V extends Comparable<V>> implements TablaSimbolosOrdenada<K,V> {

	private NodoBST<K,V> root;


	public BST(){

	}

	public BST(K llaveRaiz, V valorRaiz) {
		root = new NodoBST<K, V>(llaveRaiz, valorRaiz, 1);
	}

	@Override
	public int size() {
		int size = 0;
		if(root != null) {
			size = root.size();
		}
		return size;
	}


	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	@Override
	public ArregloDinamico<V> get(K key) {		
		return root.get(key);
	}

	@Override
	public int getHeight(K key) {
		int height = 0;
		if(root!=null) {
			height = root.getHeight(key);
		}
		return height;
	}

	@Override
	public boolean contains(K key) {
		return root.get(key)!=null;
	}

	@Override
	public void put(K key, V value) {
		if(root==null) {
			root = new NodoBST<K, V>(key, value, 1);
		}else {
			root.put(key, value);	
		}

	}

	@Override
	public int height() {
		int height = 0;
		if(root!=null) {
			height = root.height();

		}
		return height;
	}

	@Override
	public K min() {
		return root.min(root).key();
	}

	@Override
	public K max() {
		return root.max(root).key();
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

	public String toString() {
		if(root==null) {
			return " ";
		}

		return root.toString(0);
	}

}
