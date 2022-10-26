package input;


import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.builder.GeometryBuilder;
import input.components.FigureNode;
import input.components.point.PointNode;
import input.parser.JSONParser;

import java.util.*;

public class InputFacade {
    /**
     * Acquire a figure from the given JSON file.
     *
     * @param filename -- the name of a file
     * @return a FigureNode object corresponding to the input file.
     */
    public static FigureNode extractFigure(String filename) {
        String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
        return (FigureNode) new JSONParser(new GeometryBuilder()).parse(figureStr);
    }

    /**
     * 1) Read in a figure from a JSON file.
     * 2) Convert the PointNode and SegmentNode objects to a Point and Segment objects
     * (those classes have more meaningful, geometric functionality).
     *
     * @param filename
     * @return a pair <set of points as a database, set of segments>
     */
    public static Map.Entry<PointDatabase, Set<Segment>> toGeometryRepresentation(String filename) {
        FigureNode node = extractFigure(filename);

        return new AbstractMap.SimpleEntry<>(new PointDatabase(node.getPointsDatabase().getPoints().stream().map((pointNode) -> new Point(pointNode.getName(), pointNode.getX(), pointNode.getY())).toList()), new HashSet<>(node.getSegments().asUniqueSegmentList().stream().map((segmentNode) -> new Segment(new Point(segmentNode.getPoint1().getName(), segmentNode.getPoint1().getX(), segmentNode.getPoint1().getY()), new Point(segmentNode.getPoint2().getName(), segmentNode.getPoint2().getX(), segmentNode.getPoint2().getY()))).toList()));
    }
}
