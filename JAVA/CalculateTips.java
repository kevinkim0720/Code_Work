// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 1
// This work was done in accordance to the JMU Honor Code
package assignment1;

import java.util.Scanner;

public class CalculateTips {

    // Read subtotal and the gratuity rate to compute the gratuity and total
    public static void main(String[] args) {

        Scanner bill = new Scanner(System.in);
        System.out.print("Enter the subtotal and gratuity rate: ");
        double subtotal = bill.nextFloat();
        double gratuityRate = bill.nextFloat();

        // Math for solving gratuity and total bill
        double gratuity = subtotal * (gratuityRate / 100);
        double total = subtotal + gratuity;

        System.out.println("The gratuity is $" + gratuity + " and the total is $" + total);
    }
}
