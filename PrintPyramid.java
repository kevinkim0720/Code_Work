// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 1
// This work was done in accordance to the JMU Honor Code
package assignment2;

import java.util.Scanner;

public class PrintPyramid {
    // Creates a palindrome number pryamid from user input of rows

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of lines (1 to 15): ");

        int lines = input.nextInt();

        // Creates a blank space for the pyramid
        for (int i = 1; i <= lines; i++) {
            for (int j = 1; j <= (lines - i) * 2; j++) {
                System.out.print(" ");
            }

            // Creates the left side triangle
            for (int k = i; k >= 1; k--) {
                System.out.print(" " + k);
            }

            // Creates the right side triangle without starting at row 1
            for (int l = 2; l <= i; l++) {
                System.out.print(" " + l);
            }

            System.out.println();
        }
    }

}
