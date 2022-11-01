package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
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

	// extractFigure
	// toGeometryRepresentation
	
	private static final JSONParser NULL_PARSER = new JSONParser(new DefaultBuilder());
	private static final JSONParser PARSER = new JSONParser(new GeometryBuilder());

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
	void testToGeometryRepresentation()
	{
		Map.Entry<PointDatabase, Set<Segment>> geo = InputFacade.toGeometryRepresentation("jsonfiles/point.json");
		System.out.print(geo.toString());
	}
}