package com.csc205.project1;

import java.util.logging.Logger;

/**
 * A foundational class representing a point in 3D space.
 *
 * <p>This class demonstrates key object-oriented programming principles:
 * encapsulation, immutability, and abstraction.
 * Each method includes detailed comments similar to the Spring Framework
 * "Getting Started" style for clarity and educational purposes.
 */
public final class Point3D {

    // Create a logger for this class
    private static final Logger logger = Logger.getLogger(Point3D.class.getName());

    // Coordinates of the 3D point
    private final double x;
    private final double y;
    private final double z;

    /**
     * Constructs a new Point3D with the given coordinates.
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param z The z-coordinate
     */
    public Point3D(double x, double y, double z) {
        logger.info("Creating Point3D object with coordinates (" + x + ", " + y + ", " + z + ")");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // ------------------- GETTERS -------------------

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    // ------------------- OPERATIONS -------------------

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other The other Point3D object
     * @return The distance as a double
     */
    public double distanceTo(Point3D other) {
        if (other == null) {
            logger.warning("Attempted to calculate distance to a null point.");
            return -1;
        }
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        logger.info("Calculated distance between points: " + distance);
        return distance;
    }

    /**
     * Rotates this point around the Z-axis by the given angle (in degrees).
     * <p>This operation returns a new Point3D instance because this class is immutable.
     *
     * @param degrees The angle in degrees to rotate
     * @return A new Point3D after rotation
     */
    public Point3D rotateAroundZ(double degrees) {
        double radians = Math.toRadians(degrees);
        double newX = x * Math.cos(radians) - y * Math.sin(radians);
        double newY = x * Math.sin(radians) + y * Math.cos(radians);
        logger.info("Rotating point around Z-axis by " + degrees + " degrees.");
        return new Point3D(newX, newY, z);
    }

    /**
     * Rotates this point around the X-axis by the given angle (in degrees).
     *
     * @param degrees The angle in degrees
     * @return A new rotated Point3D
     */
    public Point3D rotateAroundX(double degrees) {
        double radians = Math.toRadians(degrees);
        double newY = y * Math.cos(radians) - z * Math.sin(radians);
        double newZ = y * Math.sin(radians) + z * Math.cos(radians);
        logger.info("Rotating point around X-axis by " + degrees + " degrees.");
        return new Point3D(x, newY, newZ);
    }

    /**
     * Rotates this point around the Y-axis by the given angle (in degrees).
     *
     * @param degrees The angle in degrees
     * @return A new rotated Point3D
     */
    public Point3D rotateAroundY(double degrees) {
        double radians = Math.toRadians(degrees);
        double newX = z * Math.sin(radians) + x * Math.cos(radians);
        double newZ = z * Math.cos(radians) - x * Math.sin(radians);
        logger.info("Rotating point around Y-axis by " + degrees + " degrees.");
        return new Point3D(newX, y, newZ);
    }

    /**
     * Calculates the midpoint between this point and another point.
     *
     * @param other The other Point3D
     * @return The midpoint as a new Point3D
     */
    public Point3D midpoint(Point3D other) {
        if (other == null) {
            logger.severe("Cannot calculate midpoint with a null point.");
            return null;
        }
        double midX = (x + other.x) / 2;
        double midY = (y + other.y) / 2;
        double midZ = (z + other.z) / 2;
        logger.info("Calculated midpoint between points.");
        return new Point3D(midX, midY, midZ);
    }

    @Override
    public String toString() {
        return String.format("Point3D(x=%.2f, y=%.2f, z=%.2f)", x, y, z);
    }
}
