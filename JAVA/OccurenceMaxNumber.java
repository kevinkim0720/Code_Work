// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 3
// This work was done in accordance to the JMU Honor Code
package assignment2;

import java.util.Scanner;

public class OccurenceMaxNumber {
    // Finds the max number the user inputs and counts how many times the user enter that number

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter numbers: ");

        int number = input.nextInt();
        int max = number;
        int count = 0;

        // While loop continues until the user entered 0 and finds the max and counts the max
        while (number != 0) {
            number = input.nextInt();
            if (number > max) {
                max = number;
            }
            if (max == number) {
                count++;
            }
        }
        System.out.println("The largest number is " + max + "\nThe occurrence count of the largest number is " + count);

    }
}
