package p2Grafos;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
		assertEquals(0, g.addEdge(1, 3, 2.0));
		assertEquals(0, g.addEdge(3, 6, 4.5));
		assertEquals(0, g.addEdge(1, 5, 11.11));
		System.out.println(g.toString());
		assertEquals(-1, g.addEdge(2, 3, 9.99));
		assertEquals(-3, g.addEdge(2, 4, 6.66));
		assertEquals(-12, g.addEdge(1, 3, -2));
		assertEquals(-8, g.addEdge(1, 6, -2));
		assertEquals(-9, g.addEdge(7, 3, -2));
		assertEquals(-10, g.addEdge(1, 9, -2));
		assertEquals(-4, g.addEdge(1, 3, 7.77));
		assertEquals(-11, g.addEdge(2, 4, -2));
		

		assertEquals(11.11, g.getEdge(1, 5));
		
		System.out.println(g.toString());
		
		
		//existEdge
		assertEquals(true, g.existsEdge(1, 3));
		assertEquals(true, g.existsEdge(1, 5));
		assertEquals(false, g.existsEdge(4, 5));
		assertEquals(false, g.existsEdge(9, 24));
		assertEquals(false, g.existsEdge(-9, 24));
		//getEdge
		assertEquals(2.0, g.getEdge(1, 3));
		assertEquals(4.5, g.getEdge(3, 6));
		assertEquals(-1, g.getEdge(4, 6));
		assertEquals(-2, g.getEdge(1, 4));
		assertEquals(-3, g.getEdge(4, 8));
		assertEquals(-4, g.getEdge(1, 6));
		assertEquals(-3, g.getEdge(2, 4));
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
	
	
	@Test
	public void test2Grafos() {

		Graph<Integer> g = new Graph<>(3);
		assertEquals(false, g.existsNode(1));
		assertEquals(false, g.existsNode(2));
		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addNode(2));
		assertEquals(true, g.existsNode(1));
		assertEquals(true, g.existsNode(2));
		assertEquals(-1, g.addNode(1));
		assertEquals(-1, g.addNode(2));
		assertEquals(0, g.addNode(3));
		assertEquals(-2, g.addNode(4));
		assertEquals(false, g.existsNode(4));
		assertEquals(false, g.existsNode(5));
	}

	@Test
	public void test3Grafos() {
		Graph<Integer> g=new Graph<Integer>(3);
		assertEquals(false, g.existsEdge(1,2));
		assertEquals(-3.0, g.getEdge(1,2));
		assertEquals(0, g.addNode(1));
		assertEquals(false, g.existsEdge(1,2));
		assertEquals(-2.0, g.getEdge(1,2));
		assertEquals(false, g.existsEdge(2,1));
		assertEquals(-1.0, g.getEdge(2,1));
		assertEquals(0, g.addNode(2));
		assertEquals(false, g.existsEdge(1,2));
		assertEquals(-4.0, g.getEdge(1,2));
		assertEquals(0, g.addEdge(1,2,12.12));
		assertEquals(-4, g.addEdge(1,2,12.1212));
		assertEquals(12.12, g.getEdge(1,2));
		assertEquals(0, g.addNode(3));
		assertEquals(false, g.existsEdge(1,3));
		assertEquals(false, g.existsEdge(3,2));
		assertEquals(-4.0, g.getEdge(1,3));
		assertEquals(-4.0, g.getEdge(3,2));
		assertEquals(0, g.addEdge(1,1,11.11));
		assertEquals(-4, g.addEdge(1,2,12.12));
		assertEquals(0, g.addEdge(1,3,13.13));
		assertEquals(0, g.addEdge(2,1,21.21));
		assertEquals(0, g.addEdge(2,2,22.22));
		assertEquals(0, g.addEdge(2,3,23.23));
		assertEquals(0, g.addEdge(3,1,31.31));
		assertEquals(0, g.addEdge(3,2,32.32));
		assertEquals(0, g.addEdge(3,3,33.33));
		assertEquals(-4, g.addEdge(1,1,11.11));
		assertEquals(-4, g.addEdge(1,2,12.12));
		assertEquals(-4, g.addEdge(1,3,13.13));
		assertEquals(-4,  g.addEdge(2,1,21.21));
		assertEquals(-4, g.addEdge(2,2,22.22));
		assertEquals(-4, g.addEdge(2,3,23.2323));
		assertEquals(-4, g.addEdge(3,1,31.31));
		assertEquals(-4, g.addEdge(3,2,32.32));
		assertEquals(-4, g.addEdge(3,3,33.33));
		assertEquals(11.11, g.getEdge(1,1));
		assertEquals(12.12, g.getEdge(1,2));
		assertEquals(13.13, g.getEdge(1,3));
		assertEquals(21.21, g.getEdge(2,1));
		assertEquals(22.22, g.getEdge(2,2));
		assertEquals(23.23, g.getEdge(2,3));
		assertEquals(31.31, g.getEdge(3,1));
		assertEquals(32.32, g.getEdge(3,2));
		assertEquals(33.33, g.getEdge(3,3));
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
		
		assertEquals(0,w[0]);
		assertEquals(3,w[1]);
		assertEquals(4,w[2]);
		assertEquals(1,w[3]);
		
	}
	
	@Test
	public void testDijkstra2() {
		Graph<String> g = new Graph<>(5);
		assertEquals(0,g.addNode("V1"));
		assertEquals(0,g.addNode("V2"));
		assertEquals(0,g.addNode("V3"));
		assertEquals(0,g.addNode("V4"));
		assertEquals(0,g.addNode("V5"));
		assertEquals(0,g.addEdge("V1", "V2", 1));
		assertEquals(0,g.addEdge("V1", "V4", 3));
		assertEquals(0,g.addEdge("V1", "V5", 10));
		assertEquals(0,g.addEdge("V2", "V3", 5));
		assertEquals(0,g.addEdge("V3", "V5", 1));
		assertEquals(0,g.addEdge("V4", "V3", 2));
		assertEquals(0,g.addEdge("V4", "V5", 6));
		
		double w[] = g.dijkstra("V1");
		
		System.out.print("Dijkstra - Nodo A ->  [");
		for (int i=0; i<w.length-1; i++) System.out.print(w[i]+",");
		System.out.println(w[w.length-1]+"]");
		
		assertEquals(0,w[0]);
		assertEquals(1,w[1]);
		assertEquals(5,w[2]);
		assertEquals(3,w[3]);
		assertEquals(6,w[4]);
		
	}
	
	@Test
	public void testDijkstra3() {
		Graph<String> g = new Graph<>(6);
		assertEquals(0,g.addNode("V1"));
		assertEquals(0,g.addNode("V2"));
		assertEquals(0,g.addNode("V3"));
		assertEquals(0,g.addNode("V4"));
		assertEquals(0,g.addNode("V5"));
		assertEquals(0,g.addNode("V6"));
		assertEquals(0,g.addEdge("V1", "V2", 3));
		assertEquals(0,g.addEdge("V1", "V3", 4));
		assertEquals(0,g.addEdge("V1", "V5", 8));
		assertEquals(0,g.addEdge("V2", "V5", 5));
		assertEquals(0,g.addEdge("V3", "V5", 3));
		assertEquals(0,g.addEdge("V5", "V4", 7));
		assertEquals(0,g.addEdge("V5", "V6", 3));
		assertEquals(0,g.addEdge("V6", "V4", 2));
		
		double w[] = g.dijkstra("V1");
		
		System.out.print("Dijkstra - Nodo A ->  [");
		for (int i=0; i<w.length-1; i++) System.out.print(w[i]+",");
		System.out.println(w[w.length-1]+"]");
		
		assertEquals(0,w[0]);
		assertEquals(3,w[1]);
		assertEquals(4,w[2]);
		assertEquals(12,w[3]);
		assertEquals(7,w[4]);
		assertEquals(10,w[5]);
		
	}

}
