package model.data_structures;

public interface IArregloDinamico<T extends Comparable<T>> extends Lista<T>{

	/**
	 * Retornar el numero de elementos maximo en el arreglo
	 * @return
	 */
	int darCapacidad( );

	/**
	 * Retornar el numero de elementos presentes en el arreglo
	 * @return
	 */
	int size( );

	/**
	 * Agrega un elemento al principio del arreglo
	 * @param element: Elemento a agregar al principio
	 */
	public void addFirst(T element);

	/**
	 * Agrega un elemento al final del arreglo
	 * @param element: Elemento a agregar al final element!= null
	 */
	public void addLast(T element);

	/**
	 * Agrega un elemento en una posici�n v�lida. Los elementos que est�n a partir de la posici�n
	 * dada deben correrse una posici�n a la derecha. Las posiciones v�lidas son posiciones donde
	 * ya hay un elemento en el arreglo. La posici�n del primer elemento es 1, la del segundo es 2
	 * y as� sucesivamente
	 * @param element: Elemento a agregar
	 * @param pos: Posici�n del elemento, inicia en 1
	 */
	public void insertElement(T element, int pos);

	/**
	 * Elimina el primer elemento. Se retorna el elemento eliminado
	 * @return Elemento eliminado
	 */
	T removeFirst();

	/**
	 * Elimina el �ltimo elemento. Se retorna el elemento eliminado
	 * @return Elemento eliminado
	 */
	T removeLast();

	/**
	 * Elimina el elemento de una posici�n v�lida. Se retorna el elemento eliminado
	 * @return Elemento eliminado
	 */
	T deleteElement(int pos);

	/**
	 * Retorna el primer elemento
	 * @return Primer elemento
	 */
	T firstElement();

	/**
	 * Retorna el �ltimo elemento
	 * @return �ltimo elemento
	 */
	T lastElement();


	/**
	 * Retorna el elemento en una posici�n v�lida. La posici�n del
	 * primer elemento es 1, la del segundo es 2 y as�
	 * sucesivamente.
	 * @param pos: Posici�n buscada
	 * @return Elemento de la posici�n dada
	 */
	T getElement(int pos);

	/**
	 * Retorna true si el arreglo No tiene datos. false en caso
	 * contrario
	 * @return true si el arreglo no tiene dados, false de lo contrario
	 */
	boolean isEmpty();

	/**
	 * Retorna la posici�n v�lida de un elemento. La b�squeda
	 * debe usar el m�todo compareTo() definido en el tipo T.
	 * Si no se encuentra el elemento, el valor retornado es -1.
	 * @param element: Elemento cuya posici�n se busca
	 * @return: La posici�n del elemento, -1 si no est� en la lista
	 */
	int isPresent(T element);

	/**
	 * Intercambia la informaci�n de los elementos en dos
	 * posiciones v�lidas.
	 * @param pos1 Posici�n uno para intercambiar
	 * @param pos2 Posici�n dos para intercambiar
	 */
	public void exchange(int pos1, int pos2);

	/**
	 * Actualiza el elemento en una posici�n v�lida.
	 * @param pos: Posici�n del elemento a actualizar
	 * @param elem: Elemento actualizado
	 */
	public void changeInfo(int pos, T elem);

	public T[] toArray();


}
