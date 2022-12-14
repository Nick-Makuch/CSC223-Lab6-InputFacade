package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointNamingFactory;

public class PointNamingFactoryTest 
{
	/**
	 * Tests put(Point pt) by filling pointNamingFactory with points
	 * then ensure the factory contains said points
	 * */
	@Test
	void TestPutPoint()
	{
		PointNamingFactory testFactory = new PointNamingFactory();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 1, 0);
		Point pt3 = new Point("C", 2, 0);
		Point pt4 = new Point("D", 3, 0);
		Point pt5 = new Point("B", 1, 0);
		
		assertEquals(testFactory.size(), 0);
		
		testFactory.put(pt4);
		testFactory.put(pt3);
		testFactory.put(pt2);
		testFactory.put(pt1);
		
		assertEquals(testFactory.size(), 4);
		assertTrue(testFactory.contains(pt4));
		assertTrue(testFactory.contains(pt3));
		assertTrue(testFactory.contains(pt2));
		assertTrue(testFactory.contains(pt1));
		assertEquals(pt2, testFactory.put(pt5));
		
	}
	
	/**
	 * Tests put(double x, double y) by filling pointNamingFactory with points
	 * then ensure the factory contains said points and were assigned appropriate names
	 * */
	@Test
	void TestPutOrderedPair()
	{
		PointNamingFactory testFactory = new PointNamingFactory();
		
		assertEquals(testFactory.size(), 0);
		
		testFactory.put(0,0);
		testFactory.put(1,1);
		testFactory.put(2,2);
		testFactory.put(3,3);
		
		assertEquals(testFactory.size(), 4);
		assertTrue(testFactory.contains(3,3));
		assertTrue(testFactory.contains(2,2));
		assertTrue(testFactory.contains(1,1));
		assertTrue(testFactory.contains(0,0));
		
		testFactory.clear();
		
		assertEquals(new Point("A", 0, 0), testFactory.put(0,0));
		assertEquals(new Point("B", 1, 1), testFactory.put(1,1));
		assertEquals(new Point("C", 2, 2), testFactory.put(2,2));
		assertEquals(new Point("D", 3, 3), testFactory.put(3,3));
	}
	
	/**
	 * Tests put(String name, double x, double y) by filling pointNamingFactory with points
	 * then ensure the factory contains said points
	 * */
	@Test
	void TestPutOrderedPairAndName()
	{
		PointNamingFactory testFactory = new PointNamingFactory();
		
		assertEquals(testFactory.size(), 0);
		
		testFactory.put("A",0,0);
		testFactory.put("B",1,1);
		testFactory.put("C",2,2);
		testFactory.put("D",3,3);
		
		assertEquals(testFactory.size(), 4);
		assertTrue(testFactory.contains(3,3));
		assertTrue(testFactory.contains(2,2));
		assertTrue(testFactory.contains(1,1));
		assertTrue(testFactory.contains(0,0));
		assertEquals(new Point("*_A", 4,4), testFactory.put(Point.ANONYMOUS, 4,4));
	}
	
	/**
	 * Tests get(Point pt) by filling pointNamingFactory with points
	 * then getting said points
	 * */
	@Test
	void TestGetPoint()
	{
		PointNamingFactory testFactory = new PointNamingFactory();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 1, 0);
		Point pt3 = new Point("C", 2, 0);
		Point pt4 = new Point("D", 3, 0);
		
		assertEquals(testFactory.size(), 0);
		
		testFactory.put(pt4);
		testFactory.put(pt3);
		testFactory.put(pt2);
		testFactory.put(pt1);
		
		assertEquals(testFactory.size(), 4);
		assertEquals(pt1, testFactory.get(pt1));
		assertEquals(pt2, testFactory.get(pt2));
		assertEquals(pt3, testFactory.get(pt3));
		assertEquals(pt4, testFactory.get(pt4));
		assertEquals(null, testFactory.get(null));
	}
	
	/**
	 * Tests get(double x, double y) by filling pointNamingFactory with points
	 * then getting said points
	 * */
	@Test
	void TestGetOrderedPair()
	{
		PointNamingFactory testFactory = new PointNamingFactory();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 1, 0);
		Point pt3 = new Point("C", 2, 0);
		Point pt4 = new Point("D", 3, 0);
		
		assertEquals(testFactory.size(), 0);
		
		testFactory.put(pt4);
		testFactory.put(pt3);
		testFactory.put(pt2);
		testFactory.put(pt1);
		
		assertEquals(testFactory.size(), 4);
		assertEquals(pt1, testFactory.get(0,0));
		assertEquals(pt2, testFactory.get(1,0));
		assertEquals(pt3, testFactory.get(2,0));
		assertEquals(pt4, testFactory.get(3,0));
		assertEquals(null, testFactory.get(null));
		assertNotEquals(pt1, testFactory.get(2, 0));
		assertNotEquals(pt1, testFactory.get(4, 0));
	}
	
	/**
	 * Test contains() by filling a pointNamingFactory
	 * with point and checking if they're there
	 * */
	@Test
	void TestContains()
	{
		PointNamingFactory testFactory = new PointNamingFactory();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 1, 0);
		Point pt3 = new Point("C", 2, 0);
		Point pt4 = new Point("D", 3, 0);
		
		assertEquals(testFactory.size(), 0);
		
		testFactory.put(pt4);
		testFactory.put(pt3);
		testFactory.put(pt2);
		testFactory.put(pt1);
		
		assertEquals(testFactory.size(), 4);
		assertTrue(testFactory.contains(pt4));
		assertTrue(testFactory.contains(pt3));
		assertTrue(testFactory.contains(pt2));
		assertTrue(testFactory.contains(pt1));
		
		assertTrue(testFactory.contains(0,0));
		assertTrue(testFactory.contains(1,0));
		assertTrue(testFactory.contains(2,0));
		assertTrue(testFactory.contains(3,0));
	}
	
	/**
	 * Tests toString() on points with given name and auto named
	 * */
	@Test
	void TestToString()
	{
		PointNamingFactory testFactory = new PointNamingFactory();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 1, 0);
		Point pt3 = new Point("C", 2, 0);
		Point pt4 = new Point("D", 3, 0);
		
		assertEquals(testFactory.size(), 0);
		
		testFactory.put(pt4);
		testFactory.put(pt3);
		testFactory.put(pt2);
		testFactory.put(pt1);
		
		assertEquals(testFactory.size(), 4);
		
		//System.out.println(testFactory.toString());
		assertEquals("D(3.0 , 0.0)\nC(2.0 , 0.0)\nB(1.0 , 0.0)\nA(0.0 , 0.0)\n", testFactory.toString());
		
		testFactory.clear();
		
		testFactory.put(3,0);
		testFactory.put(2,0);
		testFactory.put(1,0);
		testFactory.put(0,0);
		
		//System.out.println(testFactory.toString());
		assertEquals("*_A(3.0 , 0.0)\n*_B(2.0 , 0.0)\n*_C(1.0 , 0.0)\n*_D(0.0 , 0.0)\n", testFactory.toString());
	}
	
	/**
	 * Tests getCurrentName() and updateName()
	 * */
	@Test
	void TestGetCurrName() 
	{
		PointNamingFactory testFactory = new PointNamingFactory();
		assertEquals("*_A", testFactory.getCurrentNameTester());
		assertEquals("*_B", testFactory.getCurrentNameTester());
		assertEquals("*_C", testFactory.getCurrentNameTester());
		assertEquals("*_D", testFactory.getCurrentNameTester());
		assertEquals("*_E", testFactory.getCurrentNameTester());
		assertEquals("*_F", testFactory.getCurrentNameTester());
		assertEquals("*_G", testFactory.getCurrentNameTester());
		assertEquals("*_H", testFactory.getCurrentNameTester());
		assertEquals("*_I", testFactory.getCurrentNameTester());
		assertEquals("*_J", testFactory.getCurrentNameTester());
		assertEquals("*_K", testFactory.getCurrentNameTester());
		assertEquals("*_L", testFactory.getCurrentNameTester());
		assertEquals("*_M", testFactory.getCurrentNameTester());
		assertEquals("*_N", testFactory.getCurrentNameTester());
		assertEquals("*_O", testFactory.getCurrentNameTester());
		assertEquals("*_P", testFactory.getCurrentNameTester());
		assertEquals("*_Q", testFactory.getCurrentNameTester());
		assertEquals("*_R", testFactory.getCurrentNameTester());
		assertEquals("*_S", testFactory.getCurrentNameTester());
		assertEquals("*_T", testFactory.getCurrentNameTester());
		assertEquals("*_U", testFactory.getCurrentNameTester());
		assertEquals("*_V", testFactory.getCurrentNameTester());
		assertEquals("*_W", testFactory.getCurrentNameTester());
		assertEquals("*_X", testFactory.getCurrentNameTester());
		assertEquals("*_Y", testFactory.getCurrentNameTester());
		assertEquals("*_Z", testFactory.getCurrentNameTester());
		
		assertEquals("*_AA", testFactory.getCurrentNameTester());
		assertEquals("*_BB", testFactory.getCurrentNameTester());
		assertEquals("*_CC", testFactory.getCurrentNameTester());
		assertEquals("*_DD", testFactory.getCurrentNameTester());
		
	}
	
	
}
