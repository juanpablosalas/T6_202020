package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T extends Comparable <T>> implements IArregloDinamico<T>, Lista<T>{
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max ) throws Exception
	{
		if(max<1) {
			throw new Exception("El tamaño máximo debe ser mayor a cero.");
		}

		elementos=(T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public ArregloDinamico() {
		elementos=(T[]) new Comparable[10];
		tamanoMax = 10;
		tamanoAct = 0;
	}

	public int darCapacidad() {
		return tamanoMax;
	}

	public int size() {
		return tamanoAct;
	}

	public void addFirst(T element) {
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			elementos[0] = element;
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i+1] = copia[i];
			} 
			//System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}else {
			for(int i=tamanoAct; i>0; i--) {
				elementos[i] = elementos[i-1];
			}
			elementos[0] = element;
		}
		tamanoAct++;

	}

	@Override
	public void addLast(T element) {
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			//System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct] = element;
		tamanoAct++;

	}

	@Override
	public void insertElement(T element, int pos) {
		if(pos<1 || pos>tamanoAct) {
			throw new IndexOutOfBoundsException("El índice "+pos+" se sale del rango del arreglo.");
		}

		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			elementos[pos-1] = element;
			for ( int i = 0; i < tamanoAct+1; i++)
			{
				if(i<pos-1) {
					elementos[i] = copia[i];
				}else if(i>pos-1) {
					elementos[i] = copia[i-1];
				}

			} 
			//System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}else {
			for(int i=tamanoAct-1; i>pos-2; i++) {
				elementos[i+1] = elementos[i];
			}
			elementos[pos-1] = element;
		}

		tamanoAct++;

	}


	@Override
	public boolean isEmpty() {
		return tamanoAct == 0;
	}


	@Override
	public void exchange(int pos1, int pos2) {
		if(pos1<1 || pos1>tamanoAct) {
			throw new IndexOutOfBoundsException("El índice "+pos1+" se sale del rango del arreglo.");
		}

		if(pos2<1 || pos2>tamanoAct) {
			throw new IndexOutOfBoundsException("El índice "+pos2+" se sale del rango del arreglo.");
		}

		T a = elementos[pos1-1];
		elementos[pos1-1] = elementos[pos2-1];
		elementos[pos2-1] = a;

	}


	@Override
	public T removeFirst() {
		T eliminado = elementos[0];
		elementos[0] = null;
		for(int i=0; i<tamanoAct-1; i++) {
			elementos[i] = elementos[i+1];
		}

		tamanoAct--;
		return eliminado;
	}

	@Override
	public T removeLast() {
		T eliminado = elementos[tamanoAct-1];
		elementos[tamanoAct-1] = null;
		tamanoAct--;
		return eliminado;
	}

	@Override
	public T deleteElement(int pos) {
		if(pos<1 || pos>tamanoAct) {
			throw new IndexOutOfBoundsException("El índice "+pos+" se sale del rango del arreglo.");
		}

		T eliminado = elementos[pos-1];

		elementos[pos-1] = null;
		for(int i=pos-1;i<tamanoAct-1; i++) {
			elementos[i] = elementos[i+1];
		}
		tamanoAct--;
		return eliminado;
	}

	@Override
	public T firstElement() {
		return elementos[0];
	}

	@Override
	public T lastElement() {
		return elementos[tamanoAct-1];
	}

	@Override
	public T getElement(int pos) {
		if (pos == 0)
		{
			return elementos[pos];
		}
		else
		{
			return elementos[pos-1];
		}
	}

	@Override
	public int isPresent(T element) {
		int present = -1;
		for(int i=0; i<tamanoAct && present==-1; i++) {
			if(elementos[i].compareTo(element)==0) {
				present = i+1;
			}
		}
		return present;
	}

	@Override
	public void changeInfo(int pos, T elem) {
		if(pos<1 || pos>tamanoAct) {
			throw new IndexOutOfBoundsException("El índice "+pos+" se sale del rango del arreglo.");
		}

		elementos[pos-1] = elem;
	}


	public String toString() {
		String rta = "{";
		for(int i=0; i<tamanoAct; i++) {

			rta += elementos[i].toString() + ',';


		}
		return rta+"}";
	}

	public T[] toArray() {
		T[] arreglo = (T[]) new Comparable[tamanoAct];
		for(int i=0; i<tamanoAct;i++) {
			arreglo[i]=elementos[i];
		}
 		return arreglo;
	}


}
