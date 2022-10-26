package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;

public class PointTest
{
	
	public static final String ANONYMOUS = "__UNNAMED";
	
	// constructor double double
	// constructor string double double
	// isUnnamed
	// hashCode    ???? how to test ????
	// compareTo
	
	/*
	 * check x, y, and name values with getters
	 * normal
	 */
	@Test
	void testPointDoubleDouble()
	{
		Point pt = new Point(3.5, 3.0);
		assertEquals(ANONYMOUS, pt.getName());
		assertEquals(3.5, pt.getX());
		assertEquals(3.0, pt.getY());
	}
	
	/*
	 * check x, y, and name values with getters
	 * give __UNNAMED as name
	 * give "" as name
	 * give null as name
	 * normal
	 */
	@Test
	void testPointStringDoubleDouble_ANONYMOUS()
	{
		Point pt = new Point(ANONYMOUS, 3.5, 3.0);
		assertEquals(ANONYMOUS, pt.getName());
		assertEquals(3.5, pt.getX());
		assertEquals(3.0, pt.getY());
	}
	@Test
	void testPointStringDoubleDouble_unnamed()
	{
		Point pt = new Point("__UNNAMED", 3.5, 3.0);
		assertEquals(ANONYMOUS, pt.getName());
		assertEquals(3.5, pt.getX());
		assertEquals(3.0, pt.getY());
	}
	@Test
	void testPointStringDoubleDouble_empty()
	{
		Point pt = new Point("", 3.5, 3.0);
		assertEquals(ANONYMOUS, pt.getName());
		assertEquals(3.5, pt.getX());
		assertEquals(3.0, pt.getY());
	}
	@Test
	void testPointStringDoubleDouble_null()
	{
		Point pt = new Point(null, 3.5, 3.0);
		assertEquals(ANONYMOUS, pt.getName());
		assertEquals(3.5, pt.getX());
		assertEquals(3.0, pt.getY());
	}
	@Test
	void testPointStringDoubleDouble_normal()
	{
		Point pt = new Point("B", 3.5, 3.0);
		assertEquals("B", pt.getName());
		assertEquals(3.5, pt.getX());
		assertEquals(3.0, pt.getY());
	}
	
	
	/*
	 * true
	 * false
	 */
	@Test
	void testIsUnnamed_true()
	{
		Point pt = new Point("__UNNAMED", 3.5, 3.0);
		assertTrue(pt.isUnnamed());
	}
	@Test
	void testIsUnnamed_false()
	{
		Point pt = new Point("B", 3.5, 3.0);
		assertFalse(pt.isUnnamed());
	}
		
	/*
	 *  different
	 *  same
	 */
	@Test
	void testHashCode_different()
	{
		Point pt = new Point("B", 3.5, 1.0);
		Point pt2 = new Point("C", 3.5, 3.0);
		assertFalse(pt.hashCode() == pt2.hashCode());
	}
	@Test
	void testHashCode_same()
	{
		Point pt = new Point("B", 3.5, 3.0);
		Point pt2 = new Point("C", 3.5, 3.0);
		assertTrue(pt.hashCode() == pt2.hashCode());
	}
	
	/*
	 * that is null
	 * x1 < x2, ys the same
	 * x1 > x2, ys the same
	 * xs the same, y1 < y2
	 * xs the same, y1 > y2
	 * xs the same, ys the same
	 */
	@Test
	void testCompareTo_null()
	{
		Point pt = new Point("B", 3.5, 3.0);
		assertEquals(1, pt.compareTo(null));
	}
	@Test
	void testCompareTo_byxgreater()
	{
		Point pt = new Point("B", 3.5, 3.0);
		Point pt2 = new Point("C", 1.5, 3.0);
		assertEquals(1, pt.compareTo(pt2));
	}
	@Test
	void testCompareTo_byxless()
	{
		Point pt = new Point("B", 1.5, 3.0);
		Point pt2 = new Point("C", 3.5, 3.0);
		assertEquals(-1, pt.compareTo(pt2));
	}
	@Test
	void testCompareTo_byygreater()
	{
		Point pt = new Point("B", 3.5, 3.0);
		Point pt2 = new Point("C", 3.5, 1.0);
		assertEquals(1, pt.compareTo(pt2));
	}
	@Test
	void testCompareTo_byyless()
	{
		Point pt = new Point("B", 3.5, 1.0);
		Point pt2 = new Point("C", 3.5, 3.0);
		assertEquals(-1, pt.compareTo(pt2));
	}
	@Test
	void testCompareTo_equal()
	{
		Point pt = new Point("B", 3.5, 3.0);
		Point pt2 = new Point("C", 3.5, 3.0);
		assertEquals(0, pt.compareTo(pt2));
	}
}