// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 2
// This work was done in accordance to the JMU Honor Code
package assignment2;

import java.util.Scanner;

public class ComputeCDValue {
    // Computes CD Value for given user's initial deposit, annual percentage yield, and maturity period

    public static void main(String[] args) {

        Scanner number = new Scanner(System.in);

        System.out.print("Enter the intial deposit amount: ");
        double in = number.nextDouble();

        System.out.print("Enter annual percentage yield: ");
        double percent = number.nextDouble();

        System.out.print("Enter maturity period (number of months): ");
        int period = number.nextInt();

        System.out.println("Month\tCD Value");

        // A loop to calculate all periods of CV value and changing initial value to previous CD value 
        for (int i = 1; i <= period; i++) {
            double CD = in + in * (percent / 1200);
            in = CD;
            System.out.print(i + "\t" + (int) (CD * 100) / 100.0 + "\n");
        }
    }

}
