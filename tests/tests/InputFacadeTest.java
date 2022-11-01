package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.AbstractMap;

import org.junit.jupiter.api.Test;

import input.InputFacade;
import input.components.FigureNode;
import input.visitor.UnparseVisitor;

public class InputFacadeTest
{

	// extractFigure
	// toGeometryRepresentation
	
	/*
	 * each json file
	 */
	@Test
	void testExtractFigure_lineseg()
	{
		FigureNode node = InputFacade.extractFigure("jsonfiles/single_segment.json");
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"One line segment consisting of two points on y-axis.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "        Point(B)(0.0, 1.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  \n"
				+ "    }\n"
				+ "}\n";
		
		StringBuilder sb = new StringBuilder(); 
		UnparseVisitor unparser = new UnparseVisitor();
		unparser.visitFigureNode(node,
				new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		assertEquals(expected, sb.toString());
	}
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
				+ "            A : B  \n"
				+ "            B : C  \n"
				+ "            C : D  \n"
				+ "            D : E  \n"
				+ "            E : F  \n"
				+ "        }\n"
				+ "    }\n"
				+ "}\n";
  
	  StringBuilder sb = new StringBuilder(); 
	  UnparseVisitor unparser = new UnparseVisitor();
	  unparser.visitFigureNode(node,
			new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	 

	  assertEquals(expected, sb.toString());
	}
	
	/*
	 * each json file
	 */
	@Test
	void testToGeometryRepresentation()
	{
		
	}
}