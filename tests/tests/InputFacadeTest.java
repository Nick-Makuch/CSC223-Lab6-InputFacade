package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.InputFacade;
import input.builder.DefaultBuilder;
import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.parser.JSONParser;
import input.visitor.ComponentNodeVisitor;
import input.visitor.UnparseVisitor;

public class InputFacadeTest
{

	static String unparse(ComponentNode node) {
		StringBuilder sb = new StringBuilder();
		ComponentNodeVisitor unparseVisitor = new UnparseVisitor();

		node.accept(unparseVisitor, new AbstractMap.SimpleEntry<>(sb, 0));

		return sb.toString();
	}
	
	/*
	 * each json file
	 */
	@Test
	void testExtractFigure_collinear()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/collinear_line_segments.json");
		
		String expected = "{\n"
				+ "    Figure:\n"
				+ "    {\n"
				+ "        Description: A seqeunce of collinear line segments mimicking one line with 6 points.,\n"
				+ "        Points:\n"
				+ "        {\n"
				+ "            Point(A)(0.0, 0.0)\n"
				+ "            Point(B)(4.0, 0.0)\n"
				+ "            Point(C)(9.0, 0.0)\n"
				+ "            Point(D)(11.0, 0.0)\n"
				+ "            Point(E)(16.0, 0.0)\n"
				+ "            Point(F)(26.0, 0.0)\n"
				+ "        }\n"
				+ "        Segments:\n"
				+ "        {\n"
				+ "            A : B \n"
				+ "            B : A C \n"
				+ "            E : D F \n"
				+ "            C : B D \n"
				+ "            D : E C \n"
				+ "            F : E \n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
		
	  assertEquals(expected, InputFacadeTest.unparse(node));
	}
	@Test
	void testExtractFigure_crossing_symmetric()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/crossing_symmetric_triangle.json");
		
		String expected = "{\n"
				+ "    Figure:\n"
				+ "    {\n"
				+ "        Description: Crossing symmetric triangle construction.,\n"
				+ "        Points:\n"
				+ "        {\n"
				+ "            Point(D)(0.0, 0.0)\n"
				+ "            Point(E)(6.0, 0.0)\n"
				+ "            Point(B)(2.0, 4.0)\n"
				+ "            Point(C)(4.0, 4.0)\n"
				+ "            Point(A)(3.0, 6.0)\n"
				+ "        }\n"
				+ "        Segments:\n"
				+ "        {\n"
				+ "            A : B C \n"
				+ "            B : A C D E \n"
				+ "            C : A B D E \n"
				+ "            D : B C E \n"
				+ "            E : B C D \n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
		
	  assertEquals(expected, InputFacadeTest.unparse(node));
	}
	@Test
	void testExtractFigure_irregulat_poly()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/fully_connected_irregular_polygon.json");
		
		String expected = "{\n"
				+ "    Figure:\n"
				+ "    {\n"
				+ "        Description: Irregular pentagon in which each vertex is connected to each other.,\n"
				+ "        Points:\n"
				+ "        {\n"
				+ "            Point(A)(0.0, 0.0)\n"
				+ "            Point(B)(4.0, 0.0)\n"
				+ "            Point(C)(6.0, 3.0)\n"
				+ "            Point(D)(3.0, 7.0)\n"
				+ "            Point(E)(-2.0, 4.0)\n"
				+ "            Point(F)(26.0, 0.0)\n"
				+ "        }\n"
				+ "        Segments:\n"
				+ "        {\n"
				+ "            A : B C E D \n"
				+ "            B : A C E D \n"
				+ "            C : A B E D \n"
				+ "            E : A B C D \n"
				+ "            D : A B C E \n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
		
	  assertEquals(expected, InputFacadeTest.unparse(node));
	}
	@Test
	void testExtractFigure_lineseg()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/lineseg.json");
		
		String expected = "{\n"
				+ "    Figure:\n"
				+ "    {\n"
				+ "        Description: One line segment consisting of two points on y-axis.,\n"
				+ "        Points:\n"
				+ "        {\n"
				+ "            Point(A)(0.0, 0.0)\n"
				+ "            Point(B)(0.0, 1.0)\n"
				+ "        }\n"
				+ "        Segments:\n"
				+ "        {\n"
				+ "            A : B \n"
				+ "            B : A \n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
		
	  assertEquals(expected, InputFacadeTest.unparse(node));
	}
	@Test
	void testExtractFigure_point()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/point.json");
		
		String expected = "{\n"
				+ "    Figure:\n"
				+ "    {\n"
				+ "        Description: A single point,\n"
				+ "        Points:\n"
				+ "        {\n"
				+ "            Point(A)(0.0, 0.0)\n"
				+ "        }\n"
				+ "        Segments:\n"
				+ "        {\n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
		
	  assertEquals(expected, InputFacadeTest.unparse(node));
	}
	@Test
	void testExtractFigure_tri()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/single_triangle.json");
		
		String expected = "{\n"
				+ "    Figure:\n"
				+ "    {\n"
				+ "        Description: Right Triangle in the first quadrant.,\n"
				+ "        Points:\n"
				+ "        {\n"
				+ "            Point(A)(0.0, 0.0)\n"
				+ "            Point(B)(1.0, 1.0)\n"
				+ "            Point(C)(1.0, 0.0)\n"
				+ "        }\n"
				+ "        Segments:\n"
				+ "        {\n"
				+ "            A : B C \n"
				+ "            B : A C \n"
				+ "            C : A B \n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
		
	  assertEquals(expected, InputFacadeTest.unparse(node));
	}
	@Test
	void testExtractFigure_snake()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/snake.json");
		
		String expected = "{\n"
				+ "    Figure:\n"
				+ "    {\n"
				+ "        Description: Three triangles glued by vertex in a row,\n"
				+ "        Points:\n"
				+ "        {\n"
				+ "            Point(A)(0.0, 0.0)\n"
				+ "            Point(B)(0.0, 1.0)\n"
				+ "            Point(C)(1.0, 0.0)\n"
				+ "            Point(D)(2.0, 0.0)\n"
				+ "            Point(E)(2.0, 1.0)\n"
				+ "            Point(F)(3.0, 1.0)\n"
				+ "            Point(G)(3.0, 0.0)\n"
				+ "        }\n"
				+ "        Segments:\n"
				+ "        {\n"
				+ "            A : B C \n"
				+ "            B : A C \n"
				+ "            C : A B D E \n"
				+ "            D : C E \n"
				+ "            E : C D F G \n"
				+ "            F : E G \n"
				+ "            G : E F \n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
		
	  assertEquals(expected, InputFacadeTest.unparse(node));
	}
	@Test
	void testExtractFigure_squaretri()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/square_tri.json");
		
		String expected = "{\n"
				+ "    Figure:\n"
				+ "    {\n"
				+ "        Description: A square and triangle that share one edge.,\n"
				+ "        Points:\n"
				+ "        {\n"
				+ "            Point(A)(0.0, 0.0)\n"
				+ "            Point(B)(0.0, 2.0)\n"
				+ "            Point(C)(2.0, 0.0)\n"
				+ "            Point(D)(2.0, 2.0)\n"
				+ "            Point(E)(1.0, 3.0)\n"
				+ "        }\n"
				+ "        Segments:\n"
				+ "        {\n"
				+ "            A : B C \n"
				+ "            B : A D E \n"
				+ "            C : A D \n"
				+ "            D : B C E \n"
				+ "            E : B D \n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
		
	  assertEquals(expected, InputFacadeTest.unparse(node));
	}
	@Test
	void testExtractFigure_triquads()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/Tri_Quad.json");
		
		String expected = "{\n"
				+ "    Figure:\n"
				+ "    {\n"
				+ "        Description: Tri Quad Shape.,\n"
				+ "        Points:\n"
				+ "        {\n"
				+ "            Point(A)(4.0, 0.0)\n"
				+ "            Point(B)(8.0, 0.0)\n"
				+ "            Point(C)(4.0, 5.0)\n"
				+ "            Point(D)(8.0, 5.0)\n"
				+ "            Point(E)(0.0, 10.0)\n"
				+ "            Point(F)(12.0, 10.0)\n"
				+ "            Point(G)(4.0, 12.0)\n"
				+ "            Point(H)(8.0, 12.0)\n"
				+ "            Point(I)(6.0, 10.0)\n"
				+ "        }\n"
				+ "        Segments:\n"
				+ "        {\n"
				+ "            A : B C \n"
				+ "            B : A D \n"
				+ "            C : A D E I \n"
				+ "            D : B C F I \n"
				+ "            E : C G \n"
				+ "            G : E I \n"
				+ "            H : F I \n"
				+ "            I : C D G H \n"
				+ "            F : D H \n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
		
	  assertEquals(expected, InputFacadeTest.unparse(node));
	}
	
	/*
	 * each json file
	 */
	@Test
	void testToGeometryRepresentation_point()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/point.json");
		PointDatabase points = geo.getKey();
		Set<Segment> segments = geo.getValue();
		
		ArrayList<Point> expectedPoints = new ArrayList<Point>();
		expectedPoints.add(new Point("A", 0, 0));
		
		LinkedHashSet<Segment> expectedSegments = new LinkedHashSet<Segment>();

		assertEquals(expectedPoints.size(), points.size());
		for (Point pt : expectedPoints)
		{
			assertTrue(points.getPoint(pt) != null);
			assertTrue(expectedPoints.contains(points.getPoint(pt)));
		}
		assertEquals(expectedSegments, segments);
	}
	@Test
	void testToGeometryRepresentation_colinear()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/collinear_line_segments.json");
		PointDatabase points = geo.getKey();
		Set<Segment> segments = geo.getValue();
		
		ArrayList<Point> expectedPoints = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 4, 0);
		Point pt3 = new Point("C", 9, 0);
		Point pt4 = new Point("D", 11, 0);
		Point pt5 = new Point("E", 16, 0);
		Point pt6 = new Point("F", 26, 0);
		expectedPoints.add(pt1);
		expectedPoints.add(pt2);
		expectedPoints.add(pt3);
		expectedPoints.add(pt4);
		expectedPoints.add(pt5);
		expectedPoints.add(pt6);
		
		LinkedHashSet<Segment> expectedSegments = new LinkedHashSet<Segment>();
		expectedSegments.add(new Segment(pt1, pt2));
		expectedSegments.add(new Segment(pt2, pt3));
		expectedSegments.add(new Segment(pt3, pt4));
		expectedSegments.add(new Segment(pt4, pt5));
		expectedSegments.add(new Segment(pt5, pt6));

		assertEquals(expectedPoints.size(), points.size());
		for (Point pt : expectedPoints)
		{
			assertTrue(points.getPoint(pt) != null);
			assertTrue(expectedPoints.contains(points.getPoint(pt)));
		}
		
		assertEquals(expectedSegments.size(), segments.size());
		for (Segment seg : expectedSegments)
		{
			assertTrue(segments.contains(seg));
		}
	}
	@Test
	void testToGeometryRepresentation_crossing_symmetric_tri()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/crossing_symmetric_triangle.json");
		PointDatabase points = geo.getKey();
		Set<Segment> segments = geo.getValue();
		
		ArrayList<Point> expectedPoints = new ArrayList<Point>();
		Point pt1 = new Point("A", 3, 6);
		Point pt2 = new Point("B", 2, 4);
		Point pt3 = new Point("C", 4, 4);
		Point pt4 = new Point("D", 0, 0);
		Point pt5 = new Point("E", 6, 0);
		expectedPoints.add(pt1);
		expectedPoints.add(pt2);
		expectedPoints.add(pt3);
		expectedPoints.add(pt4);
		expectedPoints.add(pt5);
		
		LinkedHashSet<Segment> expectedSegments = new LinkedHashSet<Segment>();
		expectedSegments.add(new Segment(pt1, pt2));
		expectedSegments.add(new Segment(pt1, pt3));
		expectedSegments.add(new Segment(pt2, pt3));
		expectedSegments.add(new Segment(pt2, pt4));
		expectedSegments.add(new Segment(pt2, pt5));
		expectedSegments.add(new Segment(pt3, pt4));
		expectedSegments.add(new Segment(pt3, pt5));
		expectedSegments.add(new Segment(pt4, pt5));

		assertEquals(expectedPoints.size(), points.size());
		for (Point pt : expectedPoints)
		{
			assertTrue(points.getPoint(pt) != null);
			assertTrue(expectedPoints.contains(points.getPoint(pt)));
		}
		
		assertEquals(expectedSegments.size(), segments.size());
		for (Segment seg : expectedSegments)
		{
			assertTrue(segments.contains(seg));
		}
	}
	@Test
	void testToGeometryRepresentation_irregular_poly()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/fully_connected_irregular_polygon.json");
		PointDatabase points = geo.getKey();
		Set<Segment> segments = geo.getValue();
		
		ArrayList<Point> expectedPoints = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 4, 0);
		Point pt3 = new Point("C", 6, 3);
		Point pt4 = new Point("D", 3, 7);
		Point pt5 = new Point("E", -2, 4);
		Point pt6 = new Point("F", 26, 0);
		expectedPoints.add(pt1);
		expectedPoints.add(pt2);
		expectedPoints.add(pt3);
		expectedPoints.add(pt4);
		expectedPoints.add(pt5);
		expectedPoints.add(pt6);
		
		LinkedHashSet<Segment> expectedSegments = new LinkedHashSet<Segment>();
		expectedSegments.add(new Segment(pt1, pt2));
		expectedSegments.add(new Segment(pt1, pt3));
		expectedSegments.add(new Segment(pt1, pt4));
		expectedSegments.add(new Segment(pt1, pt5));
		expectedSegments.add(new Segment(pt2, pt3));
		expectedSegments.add(new Segment(pt2, pt4));
		expectedSegments.add(new Segment(pt2, pt5));
		expectedSegments.add(new Segment(pt3, pt4));
		expectedSegments.add(new Segment(pt3, pt5));
		expectedSegments.add(new Segment(pt4, pt5));

		assertEquals(expectedPoints.size(), points.size());
		for (Point pt : expectedPoints)
		{
			assertTrue(points.getPoint(pt) != null);
			assertTrue(expectedPoints.contains(points.getPoint(pt)));
		}
		
		assertEquals(expectedSegments.size(), segments.size());
		for (Segment seg : expectedSegments)
		{
			assertTrue(segments.contains(seg));
		}
	}
	@Test
	void testToGeometryRepresentation_lineseg()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/lineseg.json");
		PointDatabase points = geo.getKey();
		Set<Segment> segments = geo.getValue();
		
		ArrayList<Point> expectedPoints = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 0, 1);
		expectedPoints.add(pt1);
		expectedPoints.add(pt2);
		
		LinkedHashSet<Segment> expectedSegments = new LinkedHashSet<Segment>();
		expectedSegments.add(new Segment(pt1, pt2));

		assertEquals(expectedPoints.size(), points.size());
		for (Point pt : expectedPoints)
		{
			assertTrue(points.getPoint(pt) != null);
			assertTrue(expectedPoints.contains(points.getPoint(pt)));
		}
		
		assertEquals(expectedSegments.size(), segments.size());
		for (Segment seg : expectedSegments)
		{
			assertTrue(segments.contains(seg));
		}
	}
	@Test
	void testToGeometryRepresentation_single_triangle()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/single_triangle.json");
		PointDatabase points = geo.getKey();
		Set<Segment> segments = geo.getValue();
		
		ArrayList<Point> expectedPoints = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 1, 1);
		Point pt3 = new Point("C", 1, 0);
		expectedPoints.add(pt1);
		expectedPoints.add(pt2);
		expectedPoints.add(pt3);

		
		LinkedHashSet<Segment> expectedSegments = new LinkedHashSet<Segment>();
		expectedSegments.add(new Segment(pt1, pt2));
		expectedSegments.add(new Segment(pt1, pt3));
		expectedSegments.add(new Segment(pt2, pt3));

		assertEquals(expectedPoints.size(), points.size());
		for (Point pt : expectedPoints)
		{
			assertTrue(points.getPoint(pt) != null);
			assertTrue(expectedPoints.contains(points.getPoint(pt)));
		}
		
		assertEquals(expectedSegments.size(), segments.size());
		for (Segment seg : expectedSegments)
		{
			assertTrue(segments.contains(seg));
		}
	}
	@Test
	void testToGeometryRepresentation_snake()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/snake.json");
		PointDatabase points = geo.getKey();
		Set<Segment> segments = geo.getValue();
		
		ArrayList<Point> expectedPoints = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 0, 1);
		Point pt3 = new Point("C", 1, 0);
		Point pt4 = new Point("D", 2, 0);
		Point pt5 = new Point("E", 2, 1);
		Point pt6 = new Point("F", 3, 1);
		Point pt7 = new Point("G", 3, 0);
		expectedPoints.add(pt1);
		expectedPoints.add(pt2);
		expectedPoints.add(pt3);
		expectedPoints.add(pt4);
		expectedPoints.add(pt5);
		expectedPoints.add(pt6);
		expectedPoints.add(pt7);
		
		LinkedHashSet<Segment> expectedSegments = new LinkedHashSet<Segment>();
		expectedSegments.add(new Segment(pt1, pt2));
		expectedSegments.add(new Segment(pt1, pt3));
		expectedSegments.add(new Segment(pt2, pt3));
		expectedSegments.add(new Segment(pt3, pt4));
		expectedSegments.add(new Segment(pt3, pt5));
		expectedSegments.add(new Segment(pt4, pt5));
		expectedSegments.add(new Segment(pt5, pt6));
		expectedSegments.add(new Segment(pt5, pt7));
		expectedSegments.add(new Segment(pt6, pt7));

		assertEquals(expectedPoints.size(), points.size());
		for (Point pt : expectedPoints)
		{
			assertTrue(points.getPoint(pt) != null);
			assertTrue(expectedPoints.contains(points.getPoint(pt)));
		}
		
		assertEquals(expectedSegments.size(), segments.size());
		for (Segment seg : expectedSegments)
		{
			assertTrue(segments.contains(seg));
		}
	}
	@Test
	void testToGeometryRepresentation_square_tri()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/square_tri.json");
		PointDatabase points = geo.getKey();
		Set<Segment> segments = geo.getValue();
		
		ArrayList<Point> expectedPoints = new ArrayList<Point>();
		Point pt1 = new Point("A", 0, 0);
		Point pt2 = new Point("B", 0, 2);
		Point pt3 = new Point("C", 2, 0);
		Point pt4 = new Point("D", 2, 2);
		Point pt5 = new Point("E", 1, 3);
		expectedPoints.add(pt1);
		expectedPoints.add(pt2);
		expectedPoints.add(pt3);
		expectedPoints.add(pt4);
		expectedPoints.add(pt5);
		
		LinkedHashSet<Segment> expectedSegments = new LinkedHashSet<Segment>();
		expectedSegments.add(new Segment(pt1, pt2));
		expectedSegments.add(new Segment(pt1, pt3));
		expectedSegments.add(new Segment(pt2, pt4));
		expectedSegments.add(new Segment(pt2, pt5));
		expectedSegments.add(new Segment(pt3, pt4));
		expectedSegments.add(new Segment(pt4, pt5));

		assertEquals(expectedPoints.size(), points.size());
		for (Point pt : expectedPoints)
		{
			assertTrue(points.getPoint(pt) != null);
			assertTrue(expectedPoints.contains(points.getPoint(pt)));
		}
		
		assertEquals(expectedSegments.size(), segments.size());
		for (Segment seg : expectedSegments)
		{
			assertTrue(segments.contains(seg));
		}
	}
	@Test
	void testToGeometryRepresentation_tri_quad()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/Tri_Quad.json");
		PointDatabase points = geo.getKey();
		Set<Segment> segments = geo.getValue();
		
		ArrayList<Point> expectedPoints = new ArrayList<Point>();
		Point pt1 = new Point("A", 4, 0);
		Point pt2 = new Point("B", 8, 0);
		Point pt3 = new Point("C", 4, 5);
		Point pt4 = new Point("D", 8, 5);
		Point pt5 = new Point("E", 0, 10);
		Point pt6 = new Point("F", 12, 10);
		Point pt7 = new Point("G", 4, 12);
		Point pt8 = new Point("H", 8, 12);
		Point pt9 = new Point("I", 6, 10);
		expectedPoints.add(pt1);
		expectedPoints.add(pt2);
		expectedPoints.add(pt3);
		expectedPoints.add(pt4);
		expectedPoints.add(pt5);
		expectedPoints.add(pt6);
		expectedPoints.add(pt7);
		expectedPoints.add(pt8);
		expectedPoints.add(pt9);
		
		LinkedHashSet<Segment> expectedSegments = new LinkedHashSet<Segment>();
		expectedSegments.add(new Segment(pt1, pt2));
		expectedSegments.add(new Segment(pt1, pt3));
		expectedSegments.add(new Segment(pt2, pt4));
		expectedSegments.add(new Segment(pt3, pt4));
		expectedSegments.add(new Segment(pt3, pt5));
		expectedSegments.add(new Segment(pt3, pt9));
		expectedSegments.add(new Segment(pt4, pt6));
		expectedSegments.add(new Segment(pt4, pt9));
		expectedSegments.add(new Segment(pt5, pt7));
		expectedSegments.add(new Segment(pt6, pt8));
		expectedSegments.add(new Segment(pt7, pt9));
		expectedSegments.add(new Segment(pt8, pt9));

		assertEquals(expectedPoints.size(), points.size());
		for (Point pt : expectedPoints)
		{
			assertTrue(points.getPoint(pt) != null);
			assertTrue(expectedPoints.contains(points.getPoint(pt)));
		}
		
		assertEquals(expectedSegments.size(), segments.size());
		for (Segment seg : expectedSegments)
		{
			assertTrue(segments.contains(seg));
		}
	}
}