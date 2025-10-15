package com.csc205.project1;

public class Line3DTest {
    public static void main(String[] args) {
        Point3D p1 = new Point3D(0,0,0);
        Point3D p2 = new Point3D(1,1,1);
        Point3D p3 = new Point3D(0,1,0);
        Point3D p4 = new Point3D(1,2,1);

        Line3D line1 = new Line3D(p1, p2);
        Line3D line2 = new Line3D(p3, p4);

        System.out.println("Line1: " + line1);
        System.out.println("Line2: " + line2);
        System.out.println("Length of Line1: " + line1.length());
        System.out.println("Are lines parallel? " + line1.isParallelTo(line2));
        System.out.println("Shortest distance between lines: " + line1.shortestDistanceTo(line2));
        System.out.println("Distance from point (0,0,1) to Line1: " + line1.pointToLineDistance(new Point3D(0,0,1)));
    }
}