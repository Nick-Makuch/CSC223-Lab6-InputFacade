package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

public class PointDatabaseTest
{
	/*
	 * empty
	 * one
	 * multiple
	 */
	@Test
	void testGetPoints_empty()
	{
		PointDatabase pd = new PointDatabase();
		assertEquals(new LinkedHashSet<Point>(), pd.getPoints());
	}
	@Test
	void testGetPoints_one()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point("A", 0, 0));
		
		PointDatabase pd = new PointDatabase(list);
		assertEquals(list.toString(), pd.getPoints().toString());
	}
	@Test
	void testGetPoints_many()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point("A", 0, 0));
		list.add(new Point("B", 1, 0));
		list.add(new Point("C", 2, 0));
		list.add(new Point("D", 3, 0));
		
		PointDatabase pd = new PointDatabase(list);
		assertEquals(list.toString(), pd.getPoints().toString());
	}
	
	/*
	 * check factory created, size 0
	 */
	@Test
	void testPointDatabase()
	{
		PointDatabase pd = new PointDatabase();
		assertEquals(0, pd.size());
	}
	
	/*
	 * null
	 * empty list in
	 * not empty list in
	 * 
	 * check with getPoints and size
	 */
	@Test
	void testPointDatabaseList_null()
	{
		PointDatabase pd = new PointDatabase(null);
		assertEquals(0, pd.size());
	}
	@Test
	void testPointDatabaseList_empty()
	{
		PointDatabase pd = new PointDatabase(new ArrayList<Point>());
		assertEquals(0, pd.size());
	}
	@Test
	void testPointDatabaseList_normal()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point("A", 0, 0));
		list.add(new Point("B", 1, 0));
		list.add(new Point("C", 2, 0));
		list.add(new Point("D", 3, 0));
		
		PointDatabase pd = new PointDatabase(list);
		assertEquals(list.toString(), pd.getPoints().toString());
		assertEquals(4, pd.size());
	}
	
	/*
	 * empty
	 * not empty
	 * put one in
	 */
	@Test
	void testSize_empty()
	{
		PointDatabase pd = new PointDatabase(new ArrayList<Point>());
		assertEquals(0, pd.size());
	}
	@Test
	void testSize_notempty()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point("A", 0, 0));
		
		PointDatabase pd = new PointDatabase(list);
		assertEquals(1, pd.size());
	}
	@Test
	void testSize_afterput()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point("A", 0, 0));
		
		PointDatabase pd = new PointDatabase(list);
		pd.put("B", 2, 1);
		
		assertEquals(2, pd.size());
	}
	
	/*
	 * add to empty
	 * add another
	 * same as one already in
	 * same values, diff name as one already in
	 */
	@Test
	void testPut_toempty()
	{
		PointDatabase pd = new PointDatabase();
		Point pt1 = new Point("B", 2, 1);
		pd.put("B", 2, 1);
		
		assertEquals(1, pd.size());
		assertTrue(pd.getPoints().contains(pt1));
	}
	@Test
	void testPut_tonotempty()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 2, 1);
		list.add(pt1);
		
		PointDatabase pd = new PointDatabase(list);
		pd.put("B", 2, 1);
		
		assertEquals(2, pd.size());
		assertTrue(pd.getPoints().contains(pt1));
		assertTrue(pd.getPoints().contains(pt2));
	}
	
	/*
	 * in
	 * not in
	 */
	@Test
	void testGetNameDoubleDouble_in()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point("A", 0, 0));
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		assertEquals("B", pd.getName(1, 0));
	}
	@Test
	void testGetNameDoubleDouble_notin()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point("A", 0, 0));
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		assertEquals(null, pd.getName(9, 9));
	}
	
	/*
	 * in
	 * not in
	 * null
	 */
	@Test
	void testGetNamePoint_in()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		list.add(pt1);
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		
		assertEquals("A", pd.getName(pt1));
	}
	@Test
	void testGetNamePoint_notin()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		
		assertEquals(null, pd.getName(pt1));
	}
	@Test
	void testGetNamePoint_null()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		list.add(pt1);
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		
		assertEquals(null, pd.getName(null));
	}
	
	/*
	 * in
	 * not in
	 */
	@Test
	void testGetPointString_in()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		list.add(pt1);
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		
		assertEquals(pt1, pd.getPoint("A"));
	}
	@Test
	void testGetPointString_notin()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		
		assertEquals(null, pd.getPoint("A"));
	}
	
	/*
	 * in
	 * not in
	 * null
	 */
	@Test
	void testGetPointPoint_in()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		list.add(pt1);
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		
		assertEquals(pt1, pd.getPoint(pt1));
	}
	@Test
	void testGetPointPoint_notin()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		
		assertEquals(null, pd.getPoint(pt1));
	}
	// upset bc The method getPoint(String) is ambiguous for the type PointDatabase
//	@Test
//	void testGetPointPoint_null()
//	{
//		PointDatabase pd = new PointDatabase();
//		
//		assertEquals(null, pd.getPoint(null));
//	}
	
	/*
	 * in
	 * not in
	 */
	@Test
	void testGetPointDoubleDouble_in()
	{
		ArrayList<Point> list = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		list.add(pt1);
		list.add(new Point("B", 1, 0));
		
		PointDatabase pd = new PointDatabase(list);
		
		assertEquals(pt1, pd.getPoint(0, 0));
	}
	// upset bc The method getPoint(String) is ambiguous for the type PointDatabase
//	@Test
//	void testGetPointDoubleDouble_notin()
//	{
//		
//		ArrayList<Point> list = new ArrayList<Point>();
//		list.add(new Point("B", 1, 0));
//		
//		PointDatabase pd = new PointDatabase(list);
//		
//		assertEquals(null, pd.getPoint(null));
//	}
}