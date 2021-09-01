// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 3
// This work was done in accordance to the JMU Honor Code
package assignment1;

import java.util.Scanner;

public class FutureInvestmentValue {

    // Reads investment amount, annual interest rate, and number of years to display the future investment value
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter investment amount: ");
        double investment = input.nextFloat();

        System.out.print("Enter annual interest rate in percentage: ");
        double rate = input.nextFloat();

        System.out.print("Enter number of years: ");
        double year = input.nextFloat();

        // Equation for finding the future value
        double futureValue = investment * Math.pow(1 + (rate / 100) / 12, year * 12);

        // Prints the value in money format
        System.out.println("Future value is $" + (int) (futureValue * 100) / 100.0);
    }
}
