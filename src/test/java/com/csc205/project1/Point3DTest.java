package com.csc205.project1;

public class Point3DTest {
    public static void main(String[] args) {
        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(4, 6, 8);

        System.out.println("Distance: " + p1.distanceTo(p2));
        System.out.println("Midpoint: " + p1.midpoint(p2));
        System.out.println("Rotate Z (90°): " + p1.rotateAroundZ(90));
        System.out.println("Rotate X (45°): " + p1.rotateAroundX(45));
        System.out.println("Rotate Y (30°): " + p1.rotateAroundY(30));
    }
}