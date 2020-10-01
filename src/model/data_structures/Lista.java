package model.data_structures;

public interface Lista<T extends Comparable<T>> {
	
	public void addFirst(T element);
	
	public void addLast(T element);
	
	public void insertElement(T element, int pos);
	
	public T removeFirst();
	
	public T removeLast();
	
	public T deleteElement(int pos);
	
	public T firstElement();
	
	public T lastElement();
	
	public T getElement(int pos);
	
	public int size();
	
	public boolean isEmpty();
	
	public int isPresent(T element);
	
	public void exchange(int pos1, int pos2);
	
	public void changeInfo(int pos, T newInfo);

}
