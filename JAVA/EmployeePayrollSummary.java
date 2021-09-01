// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 1
// This work was done in accordance to the JMU Honor Code
package assignment3;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class EmployeePayrollSummary {

    // User enters a file containing employee information, and system prints out all the information required
    public static void main(String[] args) throws FileNotFoundException {
        Scanner text = new Scanner(System.in);
        DecimalFormat dollar = new DecimalFormat("$#,##0.00");

        // Gets user input and finds file in the computer
        System.out.print("Enter full path of input file:\n");
        String fileName = text.nextLine().trim();

        Scanner file;
        file = new Scanner(new File(fileName));

        // Initial values for arrays
        int limit = 100;
        String[] name = new String[limit];
        double[] hourlyWage = new double[limit];
        double[] hoursWorked = new double[limit];

        // A while loop allows for splitting up lines in the text file into respective arrays
        int totalEmployees = 0;
        while (file.hasNext()) {
            String employee = file.nextLine();
            String[] info = employee.split("\t");
            name[totalEmployees] = info[0];

            // For loop to take each row in the text file and send them to the appropriate array
            for (String list : info) {
                hourlyWage[totalEmployees] = Double.parseDouble(info[1]);
                double totalHours = Double.parseDouble(info[2]) + Double.parseDouble(info[3]) + Double.parseDouble(info[4]) + Double.parseDouble(info[5]) + Double.parseDouble(info[6]);
                hoursWorked[totalEmployees] = totalHours;
            }

            // Calculates how many employees are in the text file
            totalEmployees++;
        }

        // Calculates total hours combined
        double combineTotalHours = 0;
        for (int i = 0; i < totalEmployees; i++) {
            combineTotalHours += hoursWorked[i];
        }

        // Initial values
        double max = 0;
        String maxName = "";
        double combineTotalPayroll = 0;
        double otHours = 0;
        double otPay = 0;

        System.out.println("EmpID\tHours\tReg Pay\tOT Hrs\tOT Pay\tTot Pay");
        System.out.println("-----\t-----\t-------\t------\t------\t-------");

        // For loop to print necessary information for each individual employee
        for (int i = 0; i < totalEmployees; i++) {
            System.out.print(name[i] + "\t");
            System.out.print(hoursWorked[i] + "\t");
            double regPay = hoursWorked[i] * hourlyWage[i];
            System.out.print(dollar.format(regPay) + "\t");

            // If statement to see which employee(s) worked overtime and deserves more pay
            if (hoursWorked[i] > 40) {
                otHours = hoursWorked[i] - 40;
                otPay = otHours * hourlyWage[i] / 2;
                System.out.print(" " + otHours + "\t" + dollar.format(otPay) + "\t");
            } else {
                System.out.print(" " + otHours + "\t" + dollar.format(otPay) + "\t");
            }
            double totPay = regPay + otPay;

            // If statement to find the max earned regular pay and the employee
            if (regPay > max) {
                max = regPay;
                maxName = name[i];
            }
            // Finds the value of total payroll combined
            combineTotalPayroll += totPay;
            System.out.print(dollar.format(totPay));
            System.out.print("\n");
        }

        System.out.println("\nTotal hours worked:\t" + combineTotalHours);
        System.out.println("Total payroll:\t\t" + dollar.format(combineTotalPayroll));
        System.out.println("\nThe highest paid employee was " + maxName + " who earned " + dollar.format(max));

        // Calculates average hours worked in a week
        double averageHours = combineTotalHours / totalEmployees;

        System.out.println("\nAverage hours worked\t" + averageHours);
        System.out.println("The following employees worked less than the average:");

        // For loop to find employees would worked less than average and prints them out
        for (int i = 0; i < totalEmployees; i++) {
            if (averageHours > hoursWorked[i]) {
                System.out.println("\t" + name[i]);
            }
        }

        System.out.println("Employees printed in reverse order:");

        // For loop to print employee names in reverse order
        for (int i = totalEmployees - 1; i >= 0; i--) {
            System.out.println(name[i]);
        }
    }
}
