package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ArregloDinamico;
import model.data_structures.RBT;

public class TestRBT {

	private RBT<String,Integer> RBTree;

	@Before
	public void setUp1() {
		RBTree = new RBT<String,Integer>();
	}

	@Before
	public void setUp2() {
		RBTree = new RBT<String,Integer>();
		RBTree.put("1", 5);
		RBTree.put("1", 3);
		RBTree.put("1", 9);
		RBTree.put("7", 3);
		RBTree.put("7", 5);
		RBTree.put("7", 9);
		RBTree.put("3", 3);
		RBTree.put("0", 1);
		RBTree.put("0", 3);
		RBTree.put("0", 7);
		RBTree.put("3", 6);
		System.out.println(RBTree.toString());
		RBTree.put("10", 4);
		System.out.println(RBTree.toString());
		RBTree.put("13", 5);
		System.out.println(RBTree.toString());
		RBTree.put("12", 5);
		System.out.println(RBTree.toString());
		RBTree.put("8", 5);
		System.out.println(RBTree.toString());
		RBTree.put("6", 5);
		System.out.println(RBTree.toString());
		RBTree.put("20", 1);
		RBTree.put("20", 2);
		RBTree.put("20", 3);
		RBTree.put("20", 4);
		RBTree.put("20", 5);
		System.out.println(RBTree.toString());
		RBTree.put("15", 5);
		RBTree.put("19", 8);
		RBTree.put("13", 2);
	}


	@Test
	public void testSize() {
		setUp1();
		assertEquals("El tamaño del árbol debería ser cero", 0,RBTree.size());
		setUp2();
		assertEquals("El tamaño del árbol debería ser 12",12,RBTree.size());
		RBTree.put("20", 6);
		RBTree.put("20", 7);
		RBTree.put("20", 8);
		RBTree.put("20", 9);
		assertEquals("El tamaño del árbol debería ser 12",12,RBTree.size());
		RBTree.put("18", 8);
		RBTree.put("17", 8);
		RBTree.put("16", 8);
		assertEquals("El tamaño del árbol debería ser 12",15,RBTree.size());
	}

	@Test
	public void testIsEmpty() {
		setUp1();
		assertTrue("El árbol debería estar vacío",RBTree.isEmpty());
		setUp2();
		assertFalse("El árbol NO debería estar vacío",RBTree.isEmpty());
	}

	@Test
	public void testGet() {
		ArregloDinamico<Integer> rta;
		setUp1();

		try {
			rta = RBTree.get("0");
			fail("Debe lanzar un error porque el árbol está vacío");
		}catch(Exception e) {
		}
	

		try {
			rta = RBTree.get(null);
			fail("La llave no puede ser nula.");
		}catch(Exception e) {
		}


		
		setUp2();
		rta = RBTree.get("1");
		assertEquals("El arreglo sólo debe tener tres elementos",3,rta.size());
		assertTrue("El arreglo contiene a 5",rta.isPresent(5)!=-1);
		assertTrue("El arreglo contiene a 3",rta.isPresent(3)!=-1);
		assertTrue("El arreglo contiene a 9",rta.isPresent(9)!=-1);

		rta = RBTree.get("13");
		assertEquals("El arreglo sólo debe tener dos elementos",2,rta.size());
		assertTrue("El arreglo contiene a 5",rta.isPresent(5)!=-1);
		assertTrue("El arreglo contiene a 2",rta.isPresent(2)!=-1);

		rta = RBTree.get("19");
		assertEquals("El arreglo sólo debe tener un elemento",1,rta.size());
		assertTrue("El arreglo contiene a 8",rta.isPresent(8)!=-1);
	}

	@Test
	public void testContains() {
		setUp1();
		assertFalse("El arreglo no debe tener nada",RBTree.contains("0"));

		setUp2();
		assertTrue("El arreglo debe contener a 0",RBTree.contains("0"));
		assertTrue("El arreglo debe contener a 1",RBTree.contains("1"));
		assertTrue("El arreglo debe contener a 7",RBTree.contains("7"));
		assertTrue("El arreglo debe contener a 19",RBTree.contains("19"));
		assertTrue("El arreglo debe contener a 6",RBTree.contains("6"));
	}

	@Test
	public void testPut() {
		setUp1();
		RBTree.put("100", 2);
		RBTree.put("100", 4);
		RBTree.put("200", 10);
		assertFalse("El arreglo ya no es vacío",RBTree.isEmpty());
		assertEquals("El tamaño del arreglo es dos",2,RBTree.size());

		setUp2();
		System.out.println("FIN");
		int size = RBTree.size();
		RBTree.put("20", 6);
		RBTree.put("20", 7);
		RBTree.put("20", 8);
		ArregloDinamico<Integer> valores20 = RBTree.get("20");
		assertEquals("El tamaño no aumenta pues no se agregan llaves nuevas",size,RBTree.size());
		assertTrue("El arreglo de valores contiene al 6",valores20.isPresent(6)!=-1);
		assertTrue("El arreglo de valores contiene al 7",valores20.isPresent(7)!=-1);
		assertTrue("El arreglo de valores contiene al 8",valores20.isPresent(8)!=-1);

		RBTree.put("15", 33);
		ArregloDinamico<Integer> valores15 = RBTree.get("15");
		assertEquals("El tamaño no aumenta pues no se agregan llaves nuevas",size,RBTree.size());
		assertTrue("El arreglo de valores contiene al 33",valores15.isPresent(33)!=-1);
	}

	@Test
	public void testDelete() {
		setUp2();
		int size = RBTree.size();
		RBTree.delete("13");
		assertEquals("El tamaño del árbol debió disminuir",RBTree.size(),size-1);
		assertFalse("El árbol ya no debe contener a 13",RBTree.contains("13"));
	}

	@Test
	public void testMin() {
		setUp2();
		assertEquals("El mínimo debe ser 0",RBTree.min(),"0");
	}

	@Test
	public void testDeleteMin() {
		setUp2();
		int size = RBTree.size();
		RBTree.deleteMin();
		assertEquals("El tamaño disminuyó",size-1,RBTree.size());
		assertFalse("El arregló no debe contener al 0",RBTree.contains("0"));
	}


}
