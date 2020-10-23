package model.data_structures;

public class nodoRBT<K extends Comparable <K>, V extends Comparable <V>>
{
	private K key;

	private ArregloDinamico<V> values;

	private nodoRBT<K,V> left;

	private nodoRBT<K,V> right;
	
	private static final boolean RED = true;
	
	private static final boolean BLACK = false;

	private int size;
	
	private boolean color;

	public nodoRBT(K key, V value, int size, boolean pColor) 
	{
		this.key = key;
		values = new ArregloDinamico<V>();
		values.addLast(value);
		this.size = size;
		color = pColor;
	}
	
	public K key() {
		return key;
	}

	public ArregloDinamico<V> values() {
		return values;
	}

	public int size() {
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

	public void setLeft(nodoRBT<K,V> pLeft){
		size += pLeft.size();
		this.left = pLeft;
	}

	public void setRight(nodoRBT<K,V> pRight){
		size += pRight.size();
		this.right = pRight;
	}

	public nodoRBT<K,V> getLeft(){
		return left;
	}

	public nodoRBT<K,V> getRight(){
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
				left = new nodoRBT<K,V>(pKey,pValue,1, RED);
			}else {
				left.put(pKey, pValue);
			}
			size++;
		}else if(cmp>0) {
			if(right==null) {
				right = new nodoRBT<K,V>(pKey,pValue,1, RED);
			}else {
				right.put(pKey, pValue);
			}
			size++;
		}else {
			values.addLast(pValue);
		}
	}

	public nodoRBT<K,V> min(nodoRBT<K,V> nodo) {
		if(nodo.getLeft()==null) {
			return nodo;
		}

		return nodo.min(nodo.getLeft());
	}

	public nodoRBT<K,V> max(nodoRBT<K,V> nodo) {
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
		if(left!=null && left.key().compareTo(kInit)>0 && left.key().compareTo(kFin)<0) {
			ArregloDinamico<K> keysetleft = (ArregloDinamico<K>) left.keysInRange(kInit, kFin);
			for(int i=1; i<keysetleft.size()+1; i++) {
				keyset.addLast(keysetleft.getElement(i));
			}
		}
		if(key.compareTo(kInit)>0 && key.compareTo(kFin)<0) {
			keyset.addLast(this.key());
		}

		if(right!=null && right.key().compareTo(kInit)>0 && right.key().compareTo(kFin)<0 ) {
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
		String respuesta = "("+key.toString()+","+values.toString()+")";
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
	
	public nodoRBT<K,V> delete(nodoRBT<K,V> pNodo, K key) { 

        if (pNodo.key.compareTo(key) < 0)  {
            if (!esRojo(pNodo.left) && !esRojo(pNodo.left.left))
                pNodo = moveRedLeft(pNodo);
            pNodo.left = delete(pNodo.left, key);
        }
        else {
            if (esRojo(pNodo.left))
                pNodo = rotateRight(pNodo);
            if (key.compareTo(pNodo.key) == 0 && (pNodo.right == null))
                return null;
            if (!esRojo(pNodo.right) && !esRojo(pNodo.right.left))
                pNodo = moveRedRight(pNodo);
            if (key.compareTo(pNodo.key) == 0) {
            	nodoRBT<K,V> x = min(pNodo.right);
                pNodo.key = x.key;
                pNodo.values = x.values;
                pNodo.right = deleteMin(pNodo.right);
            }
            else pNodo.right = delete(pNodo.right, key);
        }
        return balance(pNodo);
    }

	public nodoRBT<K,V> rotateRight (nodoRBT<K,V> pNodo) 
	{
		nodoRBT<K,V> x = pNodo.left;
        pNodo.left = x.right;
        x.right = pNodo;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = pNodo.size;
        pNodo.size = getSize(pNodo.left) + getSize(pNodo.right) + 1;
        return x;
    }
	
	public int getSize (nodoRBT<K,V> pNodo) 
	{
		if(pNodo == null) 
		{
			return 0;
		}
		return pNodo.size;
	}
	
	public nodoRBT<K,V> rotateLeft (nodoRBT<K,V> pNodo) 
	{
        nodoRBT<K,V> x = pNodo.right;
        pNodo.right = x.left;
        x.left = pNodo;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = pNodo.size;
        pNodo.size = getSize(pNodo.left) + getSize(pNodo.right) + 1;
        return x;
    }
	
	public boolean esRojo (nodoRBT<K,V> pNodo)
	{
		if (pNodo == null)
		{
			return false;
		}
		return pNodo.color == RED;
	}
	
	public nodoRBT<K,V> putNodoRBT (nodoRBT<K,V> pNodo, K key, V val) { 
        if (pNodo == null) return new nodoRBT<K,V>(key, val, 1, RED);

        int cmp = key.compareTo(pNodo.key);
        if      (cmp < 0) pNodo.left  = putNodoRBT(pNodo.left,  key, val); 
        else if (cmp > 0) pNodo.right = putNodoRBT(pNodo.right, key, val); 
        else              pNodo.values.addLast(val); ;

        if (esRojo(pNodo.right) && !esRojo(pNodo.left))      pNodo = rotateLeft(pNodo);
        if (esRojo(pNodo.left)  &&  esRojo(pNodo.left.left)) pNodo = rotateRight(pNodo);
        if (esRojo(pNodo.left)  &&  esRojo(pNodo.right))     flipColors(pNodo);
        pNodo.size = getSize(pNodo.left) + getSize(pNodo.right) + 1;

        return pNodo;
    }
	
	public nodoRBT<K,V> balance (nodoRBT<K,V> pNodo) 
	{
        if (esRojo(pNodo.right))                      pNodo = rotateLeft(pNodo);
        if (esRojo(pNodo.left) && esRojo(pNodo.left.left)) pNodo = rotateRight(pNodo);
        if (esRojo(pNodo.left) && esRojo(pNodo.right))     flipColors(pNodo);

        pNodo.size = getSize(pNodo.left) + getSize(pNodo.right) + 1;
        return pNodo;
    }
	
	public nodoRBT<K,V> moveRedRight (nodoRBT<K,V> h) {
        flipColors(h);
        if (esRojo(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

	public nodoRBT<K,V> moveRedLeft (nodoRBT<K,V> h) {
        flipColors(h);
        if (esRojo(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
	
	public void flipColors (nodoRBT<K,V> pNodo) {
        pNodo.color = !pNodo.color;
        pNodo.left.color = !pNodo.left.color;
        pNodo.right.color = !pNodo.right.color;
    }
	
	public nodoRBT<K,V> deleteMin (nodoRBT<K,V> h) { 
        if (h.left == null)
            return null;

        if (!esRojo(h.left) && !esRojo(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }
	
	public void setColor(Boolean pColor)
	{
		color = pColor;
	}
}
