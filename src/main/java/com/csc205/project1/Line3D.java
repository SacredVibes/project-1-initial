package com.csc205.project1;

import java.util.logging.Logger;

/**
 * Represents a line segment in 3D space defined by two Point3D objects.
 *
 * <p>This class demonstrates the Composition design pattern by combining two
 * Point3D objects to represent a line. Composition allows this class to reuse
 * the behavior and structure of Point3D while maintaining independence.
 */
public final class Line3D {

    private static final Logger logger = Logger.getLogger(Line3D.class.getName());

    private final Point3D start;
    private final Point3D end;

    public Line3D(Point3D start, Point3D end) {
        if (start == null || end == null) {
            logger.severe("Cannot create Line3D with null endpoints.");
            throw new IllegalArgumentException("Start and end points cannot be null.");
        }
        if (start.equals(end)) {
            logger.warning("Start and end points are identical; line length will be zero.");
        }
        this.start = start;
        this.end = end;
        logger.info("Created Line3D from " + start + " to " + end);
    }

    public Point3D getStart() { return start; }
    public Point3D getEnd() { return end; }

    public double length() {
        double len = start.distanceTo(end);
        logger.info("Calculated line length: " + len);
        return len;
    }

    public boolean isParallelTo(Line3D other) {
        if (other == null) {
            logger.warning("Cannot determine parallelism with a null line.");
            return false;
        }

        double dx1 = end.getX() - start.getX();
        double dy1 = end.getY() - start.getY();
        double dz1 = end.getZ() - start.getZ();

        double dx2 = other.end.getX() - other.start.getX();
        double dy2 = other.end.getY() - other.start.getY();
        double dz2 = other.end.getZ() - other.start.getZ();

        double cx = dy1 * dz2 - dz1 * dy2;
        double cy = dz1 * dx2 - dx1 * dz2;
        double cz = dx1 * dy2 - dy1 * dx2;

        double magnitude = Math.sqrt(cx * cx + cy * cy + cz * cz);
        boolean parallel = magnitude < 1e-9;

        if (parallel) {
            logger.info("Lines are parallel.");
        } else {
            logger.info("Lines are not parallel.");
        }
        return parallel;
    }

    public double shortestDistanceTo(Line3D other) {
        if (other == null) {
            logger.warning("Cannot compute distance to a null line.");
            return -1;
        }

        double[] u = { end.getX() - start.getX(), end.getY() - start.getY(), end.getZ() - start.getZ() };
        double[] v = { other.end.getX() - other.start.getX(), other.end.getY() - other.start.getY(), other.end.getZ() - other.start.getZ() };
        double[] w = { start.getX() - other.start.getX(), start.getY() - other.start.getY(), start.getZ() - other.start.getZ() };

        double[] cross = {
                u[1] * v[2] - u[2] * v[1],
                u[2] * v[0] - u[0] * v[2],
                u[0] * v[1] - u[1] * v[0]
        };

        double crossMag = Math.sqrt(cross[0]*cross[0] + cross[1]*cross[1] + cross[2]*cross[2]);
        if (crossMag < 1e-9) {
            logger.warning("Lines are parallel; using point-to-line distance instead.");
            return pointToLineDistance(other.start);
        }

        double numerator = Math.abs(w[0]*cross[0] + w[1]*cross[1] + w[2]*cross[2]);
        double distance = numerator / crossMag;

        logger.info("Calculated shortest distance between lines: " + distance);
        return distance;
    }

    public double pointToLineDistance(Point3D p) {
        if (p == null) {
            logger.severe("Cannot calculate distance to a null point.");
            return -1;
        }

        double[] AB = { end.getX() - start.getX(), end.getY() - start.getY(), end.getZ() - start.getZ() };
        double[] AP = { p.getX() - start.getX(), p.getY() - start.getY(), p.getZ() - start.getZ() };

        double[] cross = {
                AB[1]*AP[2] - AB[2]*AP[1],
                AB[2]*AP[0] - AB[0]*AP[2],
                AB[0]*AP[1] - AB[1]*AP[0]
        };

        double area = Math.sqrt(cross[0]*cross[0] + cross[1]*cross[1] + cross[2]*cross[2]);
        double base = length();
        double height = area / base;

        logger.info("Calculated point-to-line distance: " + height);
        return height;
    }

    @Override
    public String toString() {
        return "Line3D[" + start + " -> " + end + "]";
    }
}
