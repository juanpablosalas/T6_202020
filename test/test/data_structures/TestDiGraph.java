package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.DiGraph;
import model.data_structures.Edge;
import model.data_structures.Lista;
import model.data_structures.Vertex;

/**
 * La clase test crea un grafo con los enteros del 1 al 100 donde cada número apunta a sus múltiplos
 * @author juanpablosalas
 *
 */
public class TestDiGraph {

	private DiGraph<Integer, Integer> multiplos;

	@Before
	public void setUp1() {
		multiplos = new DiGraph<Integer, Integer>();
	}

	@Before
	public void setUp2() {
		multiplos = new DiGraph<Integer, Integer>();
		try {
			for(int i=1; i<=100; i++) {
				multiplos.insertVertex(i, i);
			}
			for(int i=1; i<=100; i++) {
				for(int j=2; j*i<=100;j++) {
					multiplos.addEdge(i, j*i, 1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testContainsVertex() {
		setUp1();
		assertFalse(multiplos.containsVertex(1));

		setUp2();
		assertTrue(multiplos.containsVertex(50));
		assertFalse(multiplos.containsVertex(101));
	}

	@Test
	public void testNumVertices() {
		setUp1();
		assertEquals("El grafo no debe tener vértices pues esá vacío",0,multiplos.numVertices());

		setUp2();
		assertEquals("El grafo debe tener 100 vértices",100,multiplos.numVertices());

	}

	@Test
	public void testNumEdges() {
		setUp1();
		assertEquals("El grafo no debe tener arcos pues esá vacío",0,multiplos.numEdges());

		setUp2();
		// sum n=1 n=50 floor(100/n)-1 = 382
		assertEquals("El grafo debe tener 100 vértices",382,multiplos.numEdges());

	}

	@Test
	public void testInsertVertex() {
		setUp1();
		try {
			multiplos.insertVertex(101, 101);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue("El grafo debe contener al vértice añadido 101",multiplos.containsVertex(101));

		setUp2();

		try {
			int n = multiplos.numVertices();
			multiplos.insertVertex(102, 102);
			assertEquals("El número de vertices debio haber aumentado",n+1,multiplos.numVertices());
			multiplos.insertVertex(50, 50);
			fail("El grafo no debería poder insertar un vértice que ya está en el grafo");
		} catch (Exception e) {

		}
	}

	@Test
	public void testAddEdge() {
		setUp2();
		int n = multiplos.numEdges();

		// Cuando los vértices ya están puestos, se cambia el valor de su peso
		try {
			multiplos.addEdge(7, 49, 3.0);
			assertEquals("El número de arcos debe permanecer igual",n,multiplos.numEdges());
			assertEquals("El peso del arco debe ser 3",(Double)3.0, (Double)multiplos.getEdge(7, 49).weight());
		} catch (Exception e) {
		}

		// Cuando los vértices no existen
		try {
			multiplos.addEdge(50, 150, 1);
			fail("No se puede añadir un arco entre vértices que no existan");
		} catch (Exception e) {

		}

		try {
			multiplos.addEdge(3, 4, 1);
			assertEquals("Eñ número de arcos debe aumentar",n+1,multiplos.numEdges());
			assertNotNull("Debe encontrarse un arco entre 3 y 3",multiplos.getEdge(3, 4));
		} catch (Exception e) {
		}
	}

	@Test
	public void testGetVertex() {
		setUp1();
		assertNull("No hay tal vértices porque el grafo está vacío",multiplos.getVertex(3));

		setUp2();
		assertNotNull("Encontró el vértice",multiplos.getVertex(3));
		assertTrue("El vértice tiene el valor esperado",multiplos.getVertex(3).getValue().equals(3));
	}

	@Test
	public void testGetEdge() {
		setUp1();
		assertNull("No hay tal arco porque el grafo está vacío",multiplos.getEdge(3, 9));

		setUp2();
		assertNull("No hay tal arco porque 7 no es múltiplo de 3", multiplos.getEdge(3, 7));
		assertNotNull("Sí hay tal arco porque 9 es un múltiplo de 3",multiplos.getEdge(3, 9));
		assertNull("El arco sólo va en una dirección",multiplos.getEdge(9, 3));
	}

	@Test
	public void testAdjacentEdgesVertices() {
		setUp2();
		Lista<Edge<Integer,Integer>> arcos = multiplos.adjacentEdges(20);
		Lista<Vertex<Integer,Integer>> vertices = multiplos.adjacentVertex(20);

		boolean vert = vertices.isPresent(multiplos.getVertex(40))!=-1 && vertices.isPresent(multiplos.getVertex(60))!=-1 && vertices.isPresent(multiplos.getVertex(80))!=-1 && vertices.isPresent(multiplos.getVertex(100))!=-1;
		boolean arc = arcos.isPresent(multiplos.getEdge(20, 40)) !=-1 && arcos.isPresent(multiplos.getEdge(20, 60)) !=-1 && arcos.isPresent(multiplos.getEdge(20, 80)) !=-1 && arcos.isPresent(multiplos.getEdge(20, 40)) !=-1;
		assertTrue("La lista de vertices debe contener todos estos valores",vert);
		assertTrue("La lista de arcos debe contener todos estos valores",arc);
	}

	@Test
	public void testIndegree() {
		setUp2();
		int nFactores33 = multiplos.indegree(33); //1,3,11
		int nFactores74 = multiplos.indegree(74); //1,2,37
		int nFactores96 = multiplos.indegree(96); //1,2,3,4,6,8,12,16,24,32,48

		assertEquals("33 tiene 3 factores: 1,3,11",3,nFactores33);
		assertEquals("74 tiene 3 factores: 1,2,37",3,nFactores74);
		assertEquals("96 tiene 11 factores: 1,2,3,4,6,8,12,16,24,32,48",11,nFactores96);
	}

	@Test
	public void testOutdegree() {
		setUp2();
		int nMultiplos7 = multiplos.outdegree(7); //14,21,28,35,42,49,56,63,70,77,84,91,98
		int nMultiplos13 = multiplos.outdegree(13); //26,39,52,65,78,91
		int nMultiplos24 = multiplos.outdegree(24); //48,72,96

		assertEquals("7 tiene 12  multiplos: 14,21,28,35,42,49,56,63,70,77,84,91,98",13,nMultiplos7);
		assertEquals("13 tiene 6  multiplos: 26,39,52,65,78,91",6,nMultiplos13);
		assertEquals("24 tiene 3  multiplos:48,72,96",3,nMultiplos24);

	}

	@Test
	public void testVertices() {
		setUp2();
		Lista<Vertex<Integer,Integer>> vert = multiplos.vertices();

		boolean estanTodos = true;

		for(int i=1; i<=100 && !estanTodos;i++) {
			estanTodos = estanTodos && vert.isPresent(multiplos.getVertex(i))!=-1;
		}

		assertTrue("Deben estar todos los números del 1 al 100",estanTodos);

	}
	
	@Test
	public void testEdges() {
		setUp2();
		Lista<Edge<Integer,Integer>> arcos = multiplos.edges();
		assertEquals("Debe haber 382 arcos",382,arcos.size());
	}
	


}
