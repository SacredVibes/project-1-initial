package com.csc205.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents a cube in 3D space.
 *
 * <p>This class demonstrates advanced object composition by using Point3D and Line3D objects.
 * It also showcases object-oriented design patterns like Composition and Encapsulation.
 */
public final class Cube3D {

    private static final Logger logger = Logger.getLogger(Cube3D.class.getName());

    private final List<Point3D> vertices; // 8 vertices of the cube
    private final List<Line3D> edges;     // 12 edges of the cube

    /**
     * Constructs a Cube3D given one corner (origin) and side length.
     *
     * @param origin The corner of the cube
     * @param sideLength Length of each cube edge
     */
    public Cube3D(Point3D origin, double sideLength) {
        if (origin == null) {
            logger.severe("Cube origin cannot be null.");
            throw new IllegalArgumentException("Origin cannot be null.");
        }
        if (sideLength <= 0) {
            logger.severe("Cube side length must be positive.");
            throw new IllegalArgumentException("Side length must be positive.");
        }

        vertices = generateVertices(origin, sideLength);
        edges = generateEdges(vertices);

        logger.info("Created Cube3D with origin " + origin + " and side length " + sideLength);
        if (!isValidCube()) {
            logger.warning("Generated cube may not be valid.");
        }
    }

    /** Generates the 8 vertices of the cube based on origin and side length. */
    private List<Point3D> generateVertices(Point3D origin, double side) {
        List<Point3D> verts = new ArrayList<>(8);
        double x = origin.getX();
        double y = origin.getY();
        double z = origin.getZ();

        verts.add(origin);
        verts.add(new Point3D(x + side, y, z));
        verts.add(new Point3D(x, y + side, z));
        verts.add(new Point3D(x + side, y + side, z));
        verts.add(new Point3D(x, y, z + side));
        verts.add(new Point3D(x + side, y, z + side));
        verts.add(new Point3D(x, y + side, z + side));
        verts.add(new Point3D(x + side, y + side, z + side));

        logger.info("Generated vertices: " + verts);
        return verts;
    }

    /** Generates the 12 edges of the cube from the vertices. */
    private List<Line3D> generateEdges(List<Point3D> verts) {
        List<Line3D> edgeList = new ArrayList<>(12);

        // Bottom square
        edgeList.add(new Line3D(verts.get(0), verts.get(1)));
        edgeList.add(new Line3D(verts.get(1), verts.get(3)));
        edgeList.add(new Line3D(verts.get(3), verts.get(2)));
        edgeList.add(new Line3D(verts.get(2), verts.get(0)));

        // Top square
        edgeList.add(new Line3D(verts.get(4), verts.get(5)));
        edgeList.add(new Line3D(verts.get(5), verts.get(7)));
        edgeList.add(new Line3D(verts.get(7), verts.get(6)));
        edgeList.add(new Line3D(verts.get(6), verts.get(4)));

        // Vertical edges
        edgeList.add(new Line3D(verts.get(0), verts.get(4)));
        edgeList.add(new Line3D(verts.get(1), verts.get(5)));
        edgeList.add(new Line3D(verts.get(2), verts.get(6)));
        edgeList.add(new Line3D(verts.get(3), verts.get(7)));

        logger.info("Generated cube edges: " + edgeList);
        return edgeList;
    }

    /** Validates cube by checking distances between vertices. */
    public boolean isValidCube() {
        double sideLength = edges.get(0).length();
        for (Line3D edge : edges) {
            if (Math.abs(edge.length() - sideLength) > 1e-9) {
                logger.warning("Edge lengths are inconsistent: " + edge.length());
                return false;
            }
        }
        logger.info("Cube validation passed.");
        return true;
    }

    /** Calculates cube perimeter (sum of all edge lengths). */
    public double perimeter() {
        double sum = 0;
        for (Line3D edge : edges) {
            sum += edge.length();
        }
        logger.info("Calculated cube perimeter: " + sum);
        return sum;
    }

    /** Calculates cube volume. */
    public double volume() {
        if (edges.isEmpty()) return 0;
        double side = edges.get(0).length();
        double vol = side * side * side;
        logger.info("Calculated cube volume: " + vol);
        return vol;
    }

    /** Translates cube by dx, dy, dz. */
    public void translate(double dx, double dy, double dz) {
        for (int i = 0; i < vertices.size(); i++) {
            Point3D old = vertices.get(i);
            vertices.set(i, new Point3D(old.getX() + dx, old.getY() + dy, old.getZ() + dz));
        }
        // regenerate edges after translation
        edges.clear();
        edges.addAll(generateEdges(vertices));
        logger.info("Translated cube by (" + dx + ", " + dy + ", " + dz + ")");
    }

    /** Rotates cube around origin along X axis by angle in radians. */
    public void rotateX(double angle) {
        for (int i = 0; i < vertices.size(); i++) {
            Point3D p = vertices.get(i);
            double y = p.getY() * Math.cos(angle) - p.getZ() * Math.sin(angle);
            double z = p.getY() * Math.sin(angle) + p.getZ() * Math.cos(angle);
            vertices.set(i, new Point3D(p.getX(), y, z));
        }
        edges.clear();
        edges.addAll(generateEdges(vertices));
        logger.info("Rotated cube around X by " + angle + " radians");
    }

    /** Rotates cube around origin along Y axis by angle in radians. */
    public void rotateY(double angle) {
        for (int i = 0; i < vertices.size(); i++) {
            Point3D p = vertices.get(i);
            double x = p.getX() * Math.cos(angle) + p.getZ() * Math.sin(angle);
            double z = -p.getX() * Math.sin(angle) + p.getZ() * Math.cos(angle);
            vertices.set(i, new Point3D(x, p.getY(), z));
        }
        edges.clear();
        edges.addAll(generateEdges(vertices));
        logger.info("Rotated cube around Y by " + angle + " radians");
    }

    /** Rotates cube around origin along Z axis by angle in radians. */
    public void rotateZ(double angle) {
        for (int i = 0; i < vertices.size(); i++) {
            Point3D p = vertices.get(i);
            double x = p.getX() * Math.cos(angle) - p.getY() * Math.sin(angle);
            double y = p.getX() * Math.sin(angle) + p.getY() * Math.cos(angle);
            vertices.set(i, new Point3D(x, y, p.getZ()));
        }
        edges.clear();
        edges.addAll(generateEdges(vertices));
        logger.info("Rotated cube around Z by " + angle + " radians");
    }

    @Override
    public String toString() {
        return "Cube3D[vertices=" + vertices + "]";
    }
}
