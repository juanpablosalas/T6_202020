package model.data_structures;

public class ShellSort {


	/**
	 * Organiza un arreglo genérico comparable utilizando ShellSort
	 * @param a
	 */


	public static void sort(Comparable[] a) {
		int N = a.length;

		int x =   (int) Math.floor(Math.log(2*N+1)/Math.log(3));
		int h = (int) (Math.pow(3, x)-1)/2;

		for(int k=h; k>0; k /= 3) {
			for (int i = k; i < N; i++)
			{ // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
				for (int j = i; j >= k && less(a[j],a[j-k]); j -= k) {
					Comparable p = a[j];
					a[j] = a[j-k];
					a[j-k] = p;
				}
			}
		}
	}
	
	

	private static boolean less(Comparable a1, Comparable a2) {
		return a1.compareTo(a2)==-1;
	}
	
	
	/**
	 * Este método sirve para probar el ShellSort
	 * @param a
	 * @return
	 */
	public static String arrayToString(Comparable[] a) {
		String rta = "{";
		for(int i=0; i<a.length; i++) {
			rta += a[i].toString()+",";
		}

		return rta.substring(0, rta.length()-1)+"}";
	}


}
