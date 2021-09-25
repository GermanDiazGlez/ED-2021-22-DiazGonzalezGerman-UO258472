package p1Algoritmia;

import static org.junit.Assert.*;

import org.junit.Test;
import p1Algoritmia.Algorithms;

public class AlgorithmsTest {
	
	Algorithms alg = new Algorithms();

	@Test
	public void testFact() {
		assertEquals(6, alg.factorial(3));
		assertEquals(24, alg.factorial(4));
		assertEquals(40320, alg.factorial(8));
		assertEquals(5040, alg.factorial(7));
		assertEquals(120, alg.factorial(5));
	}
	
	@Test
	public void testFibb() {
		assertEquals(0, alg.fibonacci(0));
		assertEquals(1, alg.fibonacci(1));
		assertEquals(1, alg.fibonacci(2));
		assertEquals(2, alg.fibonacci(3));
		assertEquals(3, alg.fibonacci(4));
		assertEquals(5, alg.fibonacci(5));
		assertEquals(34, alg.fibonacci(9));
	}
	
	@Test
	public void testPow1() {
		assertEquals(1, alg.pow2Rec1(0));
		assertEquals(2, alg.pow2Rec1(1));
		assertEquals(4, alg.pow2Rec1(2));
		assertEquals(8, alg.pow2Rec1(3));
		assertEquals(16, alg.pow2Rec1(4));
		assertEquals(32, alg.pow2Rec1(5));
		assertEquals(64, alg.pow2Rec1(6));
		assertNotEquals(0, alg.pow2Rec1(60));
		assertEquals(0, alg.pow2Rec1(100));
	}
	
	@Test
	public void testPow2() {
		assertEquals(1, alg.pow2Rec2(0));
		assertEquals(2, alg.pow2Rec2(1));
		assertEquals(4, alg.pow2Rec2(2));
		assertEquals(8, alg.pow2Rec2(3));
		assertEquals(16, alg.pow2Rec2(4));
		assertEquals(32, alg.pow2Rec2(5));
		assertEquals(64, alg.pow2Rec2(6));
	}
	
	@Test
	public void testPow3() {
		assertEquals(1, alg.pow2Rec3(0));
		assertEquals(2, alg.pow2Rec3(1));
		assertEquals(4, alg.pow2Rec3(2));
		assertEquals(8, alg.pow2Rec3(3));
		assertEquals(16, alg.pow2Rec3(4));
		assertEquals(32, alg.pow2Rec3(5));
		assertEquals(64, alg.pow2Rec3(6));
		assertNotEquals(0, alg.pow2Rec3(60));
		assertEquals(0, alg.pow2Rec3(100));
	}
	
	@Test
	public void testPow4() {
		assertEquals(1, alg.pow2Rec4(0));
		assertEquals(2, alg.pow2Rec4(1));
		assertEquals(4, alg.pow2Rec4(2));
		assertEquals(8, alg.pow2Rec4(3));
		assertEquals(16, alg.pow2Rec4(4));
		assertEquals(32, alg.pow2Rec4(5));
		assertEquals(64, alg.pow2Rec4(6));
		assertNotEquals(0, alg.pow2Rec4(60));
		assertEquals(0, alg.pow2Rec4(100));
	}
	
	@Test
	public void testPow2Iter() {
		assertEquals(1, alg.pow2Iter(0));
		assertEquals(2, alg.pow2Iter(1));
		assertEquals(4, alg.pow2Iter(2));
		assertEquals(8, alg.pow2Iter(3));
		assertEquals(16, alg.pow2Iter(4));
		assertEquals(32, alg.pow2Iter(5));
		assertEquals(64, alg.pow2Iter(6));
		assertEquals(0, alg.pow2Rec1(100));
	}
	
	@Test
	public void testPotRec() {
		assertEquals(0, alg.potenciaRec(0, 1));
		assertEquals(1, alg.potenciaRec(1, 0));
		assertEquals(1, alg.potenciaRec(7, 0));
		assertEquals(5, alg.potenciaRec(5, 1));
		assertEquals(25, alg.potenciaRec(5, 2));
		assertEquals(16, alg.potenciaRec(2, 4));
		assertEquals(27, alg.potenciaRec(3, 3));
	}
	
	@Test
	public void testRestoDivRec() {
		assertEquals(0, alg.restoDivRec(0, 1));
		assertEquals(1, alg.restoDivRec(1, 2));
		assertEquals(0, alg.restoDivRec(2, 2));
		assertEquals(0, alg.restoDivRec(4, 2));
		assertEquals(2, alg.restoDivRec(8, 3));
		assertEquals(6, alg.restoDivRec(16, 10));
		assertEquals(10, alg.restoDivRec(10, 16));
	}
	
	@Test
	public void testDivEntRec() {
		assertEquals(0, alg.divEntRec(0, 1));
		assertEquals(1, alg.divEntRec(1, 1));
		assertEquals(2, alg.divEntRec(5, 2));
		assertEquals(10, alg.divEntRec(10, 1));
		assertEquals(3, alg.divEntRec(15, 5));
	}
	
	@Test
	public void testInvertirEnteroRec() {
		assertEquals("1", alg.invertirEnteroRec(1));
		assertEquals("01", alg.invertirEnteroRec(10));
		assertEquals("51", alg.invertirEnteroRec(15));
		assertEquals("45265", alg.invertirEnteroRec(56254));
	}
	
	
	

}
