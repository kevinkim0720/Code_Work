// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 2
// This work was done in accordance to the JMU Honor Code
package assignment1;

import java.util.Scanner;

public class GeometryDistanceBetweenTwoPoints {

    // User enters 2 points, and code displays their distance
    public static void main(String[] args) {
        Scanner point = new Scanner(System.in);

        // Ask user for a point
        System.out.print("Enter x1 and y1: ");
        double x1 = point.nextFloat();
        double y1 = point.nextFloat();

        // Ask user for another point
        System.out.print("Enter x2 and y2: ");
        double x2 = point.nextFloat();
        double y2 = point.nextFloat();

        // Math for finding the distance between the two points
        double d_x = Math.pow(x2 - x1, 2);
        double d_y = Math.pow(y2 - y1, 2);
        double distance = Math.pow(d_x + d_y, 0.5);

        System.out.println("The distance between the two points is " + distance);
    }
}
