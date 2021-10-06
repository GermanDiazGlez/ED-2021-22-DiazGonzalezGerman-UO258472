package p2Grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testGraph() {
		Graph<Integer> g = new Graph<>(4);
		//addNode
		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addNode(3));
		assertEquals(0, g.addNode(6));
		assertEquals(-1, g.addNode(6));
		assertEquals(0, g.addNode(5));
		assertEquals(-2, g.addNode(7));
		assertEquals(-3, g.addNode(6));
		//getNode
		assertEquals(0, g.getNode(1));
		assertEquals(1, g.getNode(3));
		assertEquals(2, g.getNode(6));
		assertEquals(3, g.getNode(5));
		assertEquals(-1, g.getNode(0));
		assertEquals(-1, g.getNode(7));
		//existNode
		assertEquals(true, g.existsNode(1));
		assertEquals(true, g.existsNode(3));
		assertEquals(true, g.existsNode(6));
		assertEquals(true, g.existsNode(5));
		assertEquals(false, g.existsNode(0));
		assertEquals(false, g.existsNode(7));
		//addEdge
		assertEquals(0, g.addEdge(1, 3, 2));
		assertEquals(0, g.addEdge(3, 6, 4));
		assertEquals(-1, g.addEdge(2, 3, 2));
		assertEquals(-3, g.addEdge(2, 4, 2));
		assertEquals(-8, g.addEdge(1, 6, -2));
		assertEquals(-9, g.addEdge(7, 3, -2));
		assertEquals(-10, g.addEdge(1, 9, -2));
		assertEquals(-12, g.addEdge(1, 3, -2));
		assertEquals(-11, g.addEdge(2, 4, -2));
		//existEdge
		assertEquals(true, g.existsEdge(1, 3));
		assertEquals(false, g.existsEdge(1, 5));
		assertEquals(false, g.existsEdge(4, 5));
		assertEquals(false, g.existsEdge(9, 24));
		assertEquals(false, g.existsEdge(-9, 24));
		//getEdge
		assertEquals(2, g.getEdge(1, 3), 0.01);
		assertEquals(4, g.getEdge(3, 6), 0.01);
		assertEquals(-1, g.getEdge(4, 6), 0.01);
		assertEquals(-2, g.getEdge(1, 4), 0.01);
		assertEquals(-3, g.getEdge(4, 8), 0.01);
		assertEquals(-4, g.getEdge(1, 6), 0.01);
		assertEquals(-3, g.getEdge(2, 4), 0.01);
		//removeEdge
		assertEquals(0, g.removeEdge(1, 3));
		assertEquals(-4, g.removeEdge(1, 3));
		assertEquals(0, g.removeEdge(3, 6));
		assertEquals(-4, g.removeEdge(3, 6));
		assertEquals(-1, g.removeEdge(7, 3));
		assertEquals(-2, g.removeEdge(1, 7));
		assertEquals(-3, g.removeEdge(7, 9));
		assertEquals(-4, g.removeEdge(1, 1));
		assertEquals(-3, g.removeEdge(7, 7));
		//removeNode
		assertEquals(-1, g.removeNode(7));
		assertEquals(0, g.addEdge(1, 3, 2));
		assertEquals(0, g.addEdge(3, 6, 4));
		assertEquals(0, g.removeNode(1));
		assertEquals(-1, g.removeNode(1));
		assertEquals(-1, g.removeNode(1));
		assertEquals(-1, g.removeNode(52));
		assertEquals(0, g.removeNode(6));
		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addEdge(1, 3, 2));
		assertEquals(0, g.removeNode(3));
		assertEquals(0, g.addNode(3));
		assertEquals(0, g.addEdge(1, 3, 2));
		//Probamos a borrar el de la ultima posicion
		assertEquals(0, g.removeNode(3));
		assertEquals(0, g.addNode(3));
		assertEquals(0, g.addEdge(1, 3, 2));
		//Probamos a borrar el de la primera posicion
		assertEquals(0, g.removeNode(5));
		assertEquals(0, g.removeNode(3));
		//Borramos cuando solo queda 1 nodo
		assertEquals(0, g.removeNode(1));
		//Borramos cuando no quedan nodos
		assertEquals(-1, g.removeNode(0));
	}
	
	//dijkstra desde todos los nodos, todos los grafos y todos los ejemplos que hay en teoria
	@Test
	public void testDijkstra() {
		Graph<String> g = new Graph<>(4);
		assertEquals(0,g.addNode("A"));
		assertEquals(0,g.addNode("B"));
		assertEquals(0,g.addNode("C"));
		assertEquals(0,g.addNode("D"));
		assertEquals(0,g.addEdge("A", "D", 1));
		assertEquals(0,g.addEdge("A", "B", 4));
		assertEquals(0,g.addEdge("B", "C", 1));
		assertEquals(0,g.addEdge("D", "B", 2));
		assertEquals(0,g.addEdge("D", "C", 4));
		
		double w[] = g.dijkstra("A");
		
		System.out.print("Dijkstra - Nodo A ->  [");
		for (int i=0; i<w.length-1; i++) System.out.print(w[i]+",");
		System.out.println(w[w.length-1]+"]");
		
		assertEquals(0,w[0], 0.01);
		assertEquals(3,w[1], 0.01);
		assertEquals(4,w[2], 0.01);
		assertEquals(1,w[3], 0.01);
		
	}

}
