package model.data_structures;

public class NodoBST<K extends Comparable<K>,V extends Comparable<V>> {

	private K key;

	private V value;

	private NodoBST<K,V> left;

	private NodoBST<K,V> right;

	private int size;

	public NodoBST(K key, V value, int size) {
		this.key = key;
		this.value = value;
		this.size = size;
	}

	public K key() {
		return key;
	}

	public V value() {
		return value;
	}

	public int size() {
		return size;
	}

	public void setLeft(NodoBST<K,V> pLeft){
		size += pLeft.size();
		this.left = pLeft;
	}

	public void setRight(NodoBST<K,V> pRight){
		size += pRight.size();
		this.right = pRight;
	}

	public NodoBST<K,V> getLeft(){
		return left;
	}

	public NodoBST<K,V> getRight(){
		return right;
	}

	public V get(K pKey) {
		V valor = null;
		int cmp = pKey.compareTo(key);

		if(cmp<0) {
			valor = left.get(pKey);
		}else if(cmp>0) {
			valor = right.get(pKey);
		}else if(cmp==0) {
			valor = value;
		}
		return valor;
	}

	public void put(K pKey, V pValue){

		int cmp = pKey.compareTo(key);
		if(cmp<0) {
			if(left==null) {
				left = new NodoBST<K,V>(pKey,pValue,1);
			}else {
				left.put(pKey, pValue);
			}
			size++;
		}else if(cmp>0) {
			if(right==null) {
				right = new NodoBST<K,V>(pKey,pValue,1);
			}else {
				right.put(pKey, pValue);
			}
			size++;
		}else {
			this.value =pValue;
		}
	}

	public NodoBST<K,V> min(NodoBST<K,V> nodo) {
		if(nodo.getLeft()==null) {
			return nodo;
		}

		return nodo.min(nodo.getLeft());
	}

	public NodoBST<K,V> max(NodoBST<K,V> nodo) {
		if(nodo.getRight()==null) {
			return nodo;
		}

		return nodo.min(nodo.getRight());
	}

	public int height() {
		int heightLeft = 0;
		int heightRight = 0;
		if(left!=null) {
			heightLeft = 1;
			heightLeft += left.height();
		}
		if(right!=null) {
			heightRight = 1;
			heightRight += right.height();
		}
		return heightLeft>heightRight ? heightLeft: heightRight;
	}

	public Lista<K> keySet(){
		ArregloDinamico<K> keyset = new ArregloDinamico<K>();
		if(left!=null) {
			ArregloDinamico<K> keysetleft = (ArregloDinamico<K>) left.keySet();
			for(int i=1; i<keysetleft.size()+1; i++) {
				keyset.addLast(keysetleft.getElement(i));
			}
		}
		keyset.addLast(this.key());
		if(right!=null) {
			ArregloDinamico<K> keysetright = (ArregloDinamico<K>) right.keySet();
			for(int i=1; i<keysetright.size()+1; i++) {
				keyset.addLast(keysetright.getElement(i));
			}
		}
		return keyset;
	}

	public Lista<K> keysInRange(K kInit, K kFin){
		ArregloDinamico<K> keyset = new ArregloDinamico<K>();
		if(left!=null && left.key().compareTo(kInit)>0 && left.key().compareTo(kFin)<0) {
			ArregloDinamico<K> keysetleft = (ArregloDinamico<K>) left.keysInRange(kInit, kFin);
			for(int i=1; i<keysetleft.size()+1; i++) {
				keyset.addLast(keysetleft.getElement(i));
			}
		}
		if(key.compareTo(kInit)>0 && key.compareTo(kFin)<0) {
			keyset.addLast(this.key());
		}

		if(right!=null && left.key().compareTo(kInit)>0 && left.key().compareTo(kFin)<0 ) {
			ArregloDinamico<K> keysetright = (ArregloDinamico<K>) right.keysInRange(kInit,kFin);
			for(int i=1; i<keysetright.size()+1; i++) {
				keyset.addLast(keysetright.getElement(i));
			}
		}
		return keyset;
	}

	public Lista<V> valuesInRange(K kInit, K kFin){
		ArregloDinamico<V> valueSet = new ArregloDinamico<V>();
		if(left!=null && left.key().compareTo(kInit)>0 && left.key().compareTo(kFin)<0) {
			ArregloDinamico<V> valueSetleft = (ArregloDinamico<V>) left.valuesInRange(kInit, kFin);
			for(int i=1; i<valueSetleft.size()+1; i++) {
				valueSet.addLast(valueSetleft.getElement(i));
			}
		}
		if(key.compareTo(kInit)>0 && key.compareTo(kFin)<0) {
			valueSet.addLast(this.value());
		}

		if(right!=null && left.key().compareTo(kInit)>0 && left.key().compareTo(kFin)<0 ) {
			ArregloDinamico<V> valueSetright = (ArregloDinamico<V>) right.valuesInRange(kInit,kFin);
			for(int i=1; i<valueSetright.size()+1; i++) {
				valueSet.addLast(valueSetright.getElement(i));
			}
		}
		return valueSet;

	}

	public int getHeight(K pkey) {
		int height = -1;
		if(key.equals(pkey)) {
			height = 1;;
		}
		if(left!= null && key.compareTo(pkey)>0) {
			height += left.getHeight(pkey);
		}

		if(right!= null && key.compareTo(pkey)<0) {
			height += right.getHeight(pkey);
		}
		return height;
	}

	public String toString(int start) {
		String respuesta = "("+key.toString()+","+value.toString()+")";
		int length = respuesta.length()/2;

		String spaces = "";
		for(int j=0; j<start+length; j++) {
			spaces += " ";
		}

		if(left!=null) {
			respuesta += "\n"+spaces+"L|----"+left.toString(start+5);
		}
		if(right!=null) {
			respuesta += "\n"+spaces+"R|----"+right.toString(start+5);
		}

		return respuesta;
	}



}
