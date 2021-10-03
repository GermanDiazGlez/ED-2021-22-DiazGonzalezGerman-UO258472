package p2Grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testGraph() {
		Graph<Integer> g = new Graph<>(4);
		//Nodes
		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addNode(3));
		assertEquals(0, g.addNode(6));
		assertEquals(-1, g.addNode(6));
		assertEquals(0, g.addNode(5));
		assertEquals(-2, g.addNode(7));
		assertEquals(-3, g.addNode(6));
		//Edges
		assertEquals(0, g.addEdge(1, 3, 2));
		assertEquals(0, g.addEdge(3, 6, 4));
		assertEquals(-1, g.addEdge(2, 3, 2));
		assertEquals(-3, g.addEdge(2, 4, 2));
		assertEquals(-8, g.addEdge(1, 6, -2));
		assertEquals(-9, g.addEdge(7, 3, -2));
		assertEquals(-10, g.addEdge(1, 9, -2));
		assertEquals(-12, g.addEdge(1, 3, -2));
		assertEquals(-11, g.addEdge(2, 4, -2));
		assertEquals(true, g.existsEdge(1, 3));
		assertEquals(false, g.existsEdge(1, 5));
		assertEquals(false, g.existsEdge(4, 5));
		assertEquals(2, g.getEdge(1, 3));
		assertEquals(4, g.getEdge(3, 6));
		assertEquals(-1, g.getEdge(4, 6));
		assertEquals(-2, g.getEdge(1, 4));
		assertEquals(-3, g.getEdge(4, 8));
		assertEquals(-4, g.getEdge(1, 6));
	}

}
