// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 5
// This work was done in accordance to the JMU Honor Code
package assignment1;

import java.util.Scanner;

public class ArithmeticOperation {

    // User inputs 2 numbers and an operator for the code to compute
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter two numbers: ");
        double num1 = input.nextFloat();
        double num2 = input.nextFloat();

        System.out.print("Enter the number for the operator you want: \n 1: add \n 2: subtract \n 3: multiply \n 4: divide \n");
        int operator = input.nextInt();

        // Empty variable for the operation number
        double value = 0;

        // Operator for addition
        if (operator == 1) {
            value = num1 + num2;
        } // Operator for subtraction
        else if (operator == 2) {
            System.out.print("Enter the number do you want to subtract: \n 1: num1 - num2 \n 2: num2 - num1 \n");
            int answer = input.nextInt();
            if (answer == 1) {
                value = num1 - num2;
            } else if (answer == 2) {
                value = num2 - num1;
            }
        } // Operator for multiplication
        else if (operator == 3) {
            value = num1 * num2;
        } // Operator for division
        else if (operator == 4) {
            System.out.print("Enter the number do you want to divide: \n 1: num1 / num2 \n 2: num2 / num1 \n");
            int answer = input.nextInt();
            if (answer == 1) {
                value = num1 / num2;
            } else if (answer == 2) {
                value = num2 / num1;
            }
        } // Prints invalid if wrong number was entered
        else {
            System.out.print("invalid operator choice");
        }

        // Prints the answer
        System.out.println(value);
    }
}
