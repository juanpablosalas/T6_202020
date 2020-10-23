package model.data_structures;


public class NodoBST<K extends Comparable<K>,V extends Comparable<V>> {

	private K key;

	private ArregloDinamico<V> values;

	private NodoBST<K,V> left;

	private NodoBST<K,V> right;

	private int size;
	
	
	public NodoBST(K key, V value, int size) {
		this.key = key;
		values = new ArregloDinamico<V>();
		values.addLast(value);
		this.size = size;
	}

	public K key() {
		return key;
	}

	public ArregloDinamico<V> values() {
		return values;
	}

	public int size() {
		size = 1;
		if(left!=null) {
			size+=left.size();
		}
		if(right!=null) {
			size+=right.size();
		}
		return size;
	}
	
	public int valuesSize() {
		int valueSize = values.size();
		if(left!=null) {
			valueSize+=left.valuesSize();
		}
		if(right!=null) {
			valueSize+=right.valuesSize();
		}
		
		return valueSize;
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

	public ArregloDinamico<V> get(K pKey) {
		ArregloDinamico<V> valor = null;
		int cmp = pKey.compareTo(key);

		if(cmp<0) {
			valor = left.get(pKey);
		}else if(cmp>0) {
			valor = right.get(pKey);
		}else if(cmp==0) {
			valor = values;
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
		}else if(cmp>0) {
			if(right==null) {
				right = new NodoBST<K,V>(pKey,pValue,1);
			}else {
				right.put(pKey, pValue);
			}
		}else {
			values.addLast(pValue);
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

		return nodo.max(nodo.getRight());
	}

	public int height() {
		int heightLeft = 1;
		int heightRight = 1;
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
		if(left!=null) {
			ArregloDinamico<K> keysetleft = (ArregloDinamico<K>) left.keySet();
			for(int i=1; i<keysetleft.size()+1; i++) {
				K llave = keysetleft.getElement(i);
				if(llave.compareTo(kInit)>0 && llave.compareTo(kFin)<0) {
					keyset.addLast(llave);
				}
				
			}
		}
		
	
		if(this.key().compareTo(kInit)>0 && this.key().compareTo(kFin)<0) {
			keyset.addLast(this.key());
		}

		if(right!=null) {
			ArregloDinamico<K> keysetright = (ArregloDinamico<K>) right.keySet();
			for(int i=1; i<keysetright.size()+1; i++) {
				K llave = keysetright.getElement(i);
				if(llave.compareTo(kInit)>0 && llave.compareTo(kFin)<0) {
					keyset.addLast(llave);
				}
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
			for(int i=1; i<=values.size(); i++) {
				valueSet.addLast(values.getElement(i));
			}
		}

		if(right!=null && left.key().compareTo(kInit)>0 && left.key().compareTo(kFin)<0 ) {
			ArregloDinamico<V> valueSetright = (ArregloDinamico<V>) right.valuesInRange(kInit,kFin);
			for(int i=1; i<valueSetright.size()+1; i++) {
				valueSet.addLast(valueSetright.getElement(i));
			}
		}
		return valueSet;

	}

	public int getHeight(K pKey) {
		int height = 0;
		
		
		
		
		int cmp = pKey.compareTo(key);

		if(cmp<0) {
			height += left.getHeight(pKey);
		}else if(cmp>0) {
			height += left.getHeight(pKey);
		}else if(cmp==0) {
			height += 1;
		}
		return height;
	}

	public String toString(int start) {
		String respuesta = "("+key.toString()+","+values.toString()+") - size ="+size;
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
