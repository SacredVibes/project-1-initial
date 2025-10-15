package com.csc205.project1;

public class Cube3DTest {
    public static void main(String[] args) {
        Point3D origin = new Point3D(0, 0, 0);
        Cube3D cube = new Cube3D(origin, 1.0);

        System.out.println("Cube vertices: " + cube);
        System.out.println("Perimeter: " + cube.perimeter());
        System.out.println("Volume: " + cube.volume());
        System.out.println("Is cube valid? " + cube.isValidCube());

        // Translate and rotate cube
        cube.translate(1, 2, 3);
        System.out.println("After translation: " + cube);

        cube.rotateX(Math.PI / 4);
        System.out.println("After rotation around X: " + cube);

        cube.rotateY(Math.PI / 4);
        System.out.println("After rotation around Y: " + cube);

        cube.rotateZ(Math.PI / 4);
        System.out.println("After rotation around Z: " + cube);
    }
}
