package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ArregloDinamico;
import model.data_structures.BST;

public class TestBST {

	private BST<Integer,String> binaryTree;

	@Before
	public void setUp1() {
		binaryTree = new BST<Integer,String>();
	}

	@Before
	public void setUp2() {
		binaryTree = new BST<Integer, String>();
		binaryTree.put(0, "Cero");
		binaryTree.put(4, "Cuatro");
		binaryTree.put(10, "Diez");
		binaryTree.put(2, "Dos");
		binaryTree.put(83, "Ochenta y tres");
		binaryTree.put(45, "Cuarenta y cinco");
	}

	@Test
	public void testSize() {
		setUp1();
		assertEquals("El tamaño debe ser cero", binaryTree.size(), 0);
		setUp2();
		assertEquals("El tamaño debe ser seis", 6,binaryTree.size());
		System.out.println(binaryTree);
		binaryTree.put(45, "Cuarenta y cuatro");
		binaryTree.put(45, "Cuarenta y 2");
		binaryTree.put(45, "Cuarenta y 1");
		System.out.println(binaryTree);
		assertEquals("El tamaño debe ser seis",6,binaryTree.size());
	}

	@Test
	public void testEmpty() {
		setUp1();
		assertTrue("El árbol debe estar vacío",binaryTree.isEmpty());
		setUp2();
		assertTrue("El árbol no debe estar vacío",!binaryTree.isEmpty());
	}

	@Test
	public void testGet() {
		setUp1();
		ArregloDinamico<String> prueba;
		try {
			prueba = binaryTree.get(3);
			fail("El árbol está vacío, no debería ser posible obtener un valor");
		}catch(Exception e) {

		}

		setUp2();
		try {
			prueba = binaryTree.get(14);
			fail("El árbol está vacío, no debería ser posible obtener un valor");
		}catch(Exception e) {

		}
		prueba = binaryTree.get(2);
		assertTrue("La respuesta debería ser dos", prueba.isPresent("Dos")!=-1);
	}
	
	
	
	@Test
	public void testContains() {
		setUp2();
		assertTrue("El árbol debe contener al número 4",binaryTree.contains(4));
		assertTrue("El árbol no debe contener el número 14",binaryTree.contains(4));
	}
	
	@Test
	public void testPut() {
		setUp1();
		binaryTree.put(11, "Once");
		assertTrue("No debería estar vacío",!binaryTree.isEmpty());
		assertEquals("El tamaño debería ser 1",binaryTree.size(),1);
		
		setUp2();
		int size = binaryTree.size();
		binaryTree.put(6, "Seis");
		binaryTree.put(7, "Siete");
		binaryTree.put(8, "Ocho");
		assertEquals("El tamaño debió aumentar en 3",size+3,binaryTree.size());
		assertTrue("El árbol debe tener al 7",binaryTree.contains(7));
		
	}
	
	@Test
	public void testMinMax() {
		setUp2();
		assertEquals("El mínimo debe ser 0",(int) binaryTree.min(),0);
		assertEquals("El máximo debe ser 83",(int) binaryTree.max(),83);
		binaryTree.put(-4, "- Cuatro");
		assertEquals("El mínimo debe ser -4",(int)binaryTree.min(), -4);
	}
	
	@Test
	public void testKeySet() {
		setUp2();
		ArregloDinamico<Integer> keySet = (ArregloDinamico<Integer>) binaryTree.keySet();
		assertTrue("El arreglo tiene todas las llaves",keySet.isPresent(0) != -1 && keySet.isPresent(2) != -1 && keySet.isPresent(4) != -1 && keySet.isPresent(10) != -1 && keySet.isPresent(83) != -1 && keySet.isPresent(45) != -1    );
		
	}
	
	@Test
	public void testKeysRange() {
		setUp2();
		binaryTree.put(41, "Cuarenta y uno");
		binaryTree.put(42, "Cuarenta y dos");
		binaryTree.put(43, "Cuarenta y tres");
		binaryTree.put(44, "Cuarenta y cuatro");
		binaryTree.put(46, "Cuarenta y seis");
		binaryTree.put(47, "Cuarenta y siete");
		binaryTree.put(48, "Cuarenta y ocho");
		binaryTree.put(49, "Cuarenta y nueve");
		ArregloDinamico<Integer> keySet = (ArregloDinamico<Integer>) binaryTree.keysInRange(40, 50);
		assertTrue("El arreglo tiene todas las llaves",keySet.isPresent(41) != -1 && keySet.isPresent(42) != -1 && keySet.isPresent(43) != -1 && keySet.isPresent(44) != -1 && keySet.isPresent(45) != -1 && keySet.isPresent(46) != -1  && keySet.isPresent(47) != -1  && keySet.isPresent(48) != -1  && keySet.isPresent(49) != -1    );
	}
	
	@Test
	public void testHeight() {
		setUp2();
		assertEquals("La altura debería ser 5",binaryTree.height(),5);
		
		setUp2();
		binaryTree.put(1, "Uno");
		assertEquals("La altura debería ser 3",binaryTree.getHeight(4),3);
		
	}
	
	
}
