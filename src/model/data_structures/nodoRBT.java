package model.data_structures;

public class nodoRBT<K extends Comparable <K>, V extends Comparable <V>>
{

	K key;
	
	private ArregloDinamico<V> values;
	
	nodoRBT<K,V> right;
	
	nodoRBT<K,V> left;
	
	int size;
	
	boolean color;
	
	public nodoRBT(K pKey, V pValue, int N, boolean pColor)
	{
		this.key = pKey;
		this.values = new ArregloDinamico<V>();
		values.addLast(pValue);
		this.size = N;
		this.color = pColor;
	}
	
	public nodoRBT<K,V> left(){
		return left;
	}
	
	public nodoRBT<K,V> right(){
		return right;
	}
	
	public K key(){
		return key;
	}
	
	public boolean color(){
		return color;
	}
	
	public ArregloDinamico<V> values(){
		return values;
	}
	
	public int size() {
		return size;
	}
	
	public void setLeft(nodoRBT<K,V> pNodo){
		left = pNodo;
	}
	
	public void setRight(nodoRBT<K,V> pNodo){
		right = pNodo;
	}
	
	public void setColor(boolean col){
		color = col;
	}
	
	public void setKey( K llave){
		key = llave;
	}
	
	public void setValues( ArregloDinamico<V> valores){
		values = valores;
	}
	
	public void setSize( int pSize){
		size = pSize;
	}
	
	public void addLast(V v) {
		values.addLast(v);
	}
	
	public nodoRBT<K,V> delete(nodoRBT<K,V> pNodo, K key) { 

		if (key.compareTo(pNodo.key()) < 0)  {
			if (!esRojo(pNodo.left()) && !esRojo(pNodo.left().left()))
				pNodo = moveRedLeft(pNodo);
			pNodo.setLeft(delete(pNodo.left(), key));
		}
		else {
			if (esRojo(pNodo.left())) {
				pNodo = rotateRight(pNodo);
			}
			if (key.compareTo(pNodo.key()) == 0 && pNodo.right() == null) {
				return null;	
			}
			if (!esRojo(pNodo.right()) && !esRojo(pNodo.right().left())) {
				pNodo = moveRedRight(pNodo);
			}
			if (key.compareTo(pNodo.key()) == 0) {
				nodoRBT<K,V> x = min(pNodo.right());
				pNodo.setKey(x.key());
				pNodo.setValues(x.values());
				pNodo.setRight(deleteMin(pNodo.right()));
			}
			else{
				pNodo.setRight(delete(pNodo.right(),key));
			}
		}
		return balance(pNodo);
	}

	private nodoRBT<K,V> rotateRight(nodoRBT<K,V> pNodo) 
	{
		nodoRBT<K,V> x = pNodo.left();
		pNodo.setLeft(x.right());
		x.setRight(pNodo);
		x.setColor(x.right().color());
		x.right().setColor(RBT.RED);
		x.setSize(size(pNodo));
		pNodo.setSize(size(pNodo.left())+size(pNodo.right())+1);
		return x;
	}

	private nodoRBT<K,V> rotateLeft(nodoRBT<K,V> pNodo) 
	{
		nodoRBT<K,V> x = pNodo.right();
		pNodo.setRight(x.left());
		x.setLeft(pNodo);
		x.setColor(pNodo.color());
		pNodo.setColor(RBT.RED);
		x.setSize(size(pNodo));
		pNodo.setSize(size(pNodo.left()) + size(pNodo.right()) + 1);
		return x;
	}

	private void flipColors(nodoRBT<K,V> pNodo) {
		pNodo.setColor(RBT.RED);
		pNodo.left().setColor(RBT.BLACK);
		pNodo.right().setColor(RBT.BLACK );
	}

	private nodoRBT<K,V> moveRedLeft(nodoRBT<K,V> h) {
		flipColors(h);
		if (esRojo(h.right().left())) { 
			h.setRight(rotateRight(h.right()));
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	private nodoRBT<K,V> moveRedRight(nodoRBT<K,V> h) {
		flipColors(h);
		if (esRojo(h.left().left())) { 
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	private nodoRBT<K,V> balance(nodoRBT<K,V> pNodo) 
	{
		if (esRojo(pNodo.right())) {
			pNodo = rotateLeft(pNodo);
		}
		if (esRojo(pNodo.left()) && esRojo(pNodo.left().left())) {
			pNodo = rotateRight(pNodo);
		}
		if (esRojo(pNodo.left()) && esRojo(pNodo.right()))  {
			flipColors(pNodo);
		}

		pNodo.setSize(size(pNodo.left()) + size(pNodo.right()) + 1);
		return pNodo;
	}

	public int size(nodoRBT<K,V> pNodo) 
	{
		if(pNodo == null) 
		{
			return 0;
		}
		return pNodo.size();
	}

	public boolean esRojo(nodoRBT<K,V> pNodo)
	{
		if (pNodo == null)
		{
			return false;
		}
		return pNodo.color() == RBT.RED;
	}

	public ArregloDinamico<V> get(nodoRBT<K,V> pNodo, K key) {
		while (pNodo != null) {
			int cmp = key.compareTo(pNodo.key());
			if      (cmp < 0) 
			{
				pNodo = pNodo.left();
			}
			else if (cmp > 0) 
			{
				pNodo = pNodo.right();
			}
			else      
			{
				return pNodo.values();
			}
		}
		return null;
	}

	public nodoRBT<K,V> put(nodoRBT<K,V> pNodo, K key, V val) { 
		if (pNodo == null) {
			return new nodoRBT<K,V>(key, val, 1, RBT.RED);
		}

		int cmp = key.compareTo(pNodo.key());
		if      (cmp < 0) {
			pNodo.setLeft(put(pNodo.left(),  key, val)); 
		}
		else if (cmp > 0) {
			pNodo.setRight( put(pNodo.right(), key, val)); 
		}
		else             {
			pNodo.addLast(val); ;
		}

		if (esRojo(pNodo.right()) && !esRojo(pNodo.left())) {
			System.out.println(pNodo.toString(0));
			System.out.println(pNodo.key()+"ROTA IZQ");
			pNodo = rotateLeft(pNodo);
			System.out.println(pNodo.toString(0));
		}
		if (esRojo(pNodo.left())  &&  esRojo(pNodo.left().left())) {
			System.out.println("ROTA DER");
			pNodo = rotateRight(pNodo);
		}
		if (esRojo(pNodo.left())  &&  esRojo(pNodo.right()))    {
			System.out.println("FLIP COLS");
			flipColors(pNodo);
		}
		pNodo.setSize(size(pNodo.left()) + size(pNodo.right()) + 1);

		return pNodo;
	}

	public int height(nodoRBT<K,V> pNodo) {
		if (pNodo == null) {
			return -1;
		}
		return 1 + Math.max(height(pNodo.left()), height(pNodo.right()));
	}

	public nodoRBT<K,V> deleteMin(nodoRBT<K,V> h) { 
		if (h.left() == null)
			return null;

		if (!esRojo(h.left()) && !esRojo(h.left().left()))
			h = moveRedLeft(h);

		h.setLeft(deleteMin(h.left()));
		return balance(h);
	}

	public nodoRBT<K,V> min(nodoRBT<K,V> x) { 
		if (x.left() == null) {
			return x; 
		}
		else               {
			return min(x.left()); 
		}
	} 
	
	public nodoRBT<K,V> max(nodoRBT<K,V> x) { 
		if (x.right() == null) {
			return x; 
		}
		else               {
			return max(x.right()); 
		}
	} 
	
	public int getHeight(K pkey) {		
		int height = -1;
		if(key.equals(pkey)) {
			height = 1;
		} else if(left!= null && key.compareTo(pkey)>0) {
			height = 1+left.getHeight(pkey);
		} else if(right!= null && key.compareTo(pkey)<0) {
			height = 1+right.getHeight(pkey);

		}
		return height;
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
	
	public String toString(int start) {
		String respuesta = "("+key.toString()+","+values.toString()+") - size ="+size +" - color = "+color;
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
