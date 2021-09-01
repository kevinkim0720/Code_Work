// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 4
// This work was done in accordance to the JMU Honor Code

package assignment4;

import java.util.Scanner;
import barchart.*;

public class MethodsAndArrays {
    // Method for finding if user input is a prime number
    static boolean isPrime(int a) {
        for (int i = 2; i <= a / 2; ++i) {
            if (a % i == 0) {
                return false;
            }
        }

        return true;
    }
    // Method for printing all the prime numbers a given user input
    static void listPrime(int x) {
        System.out.print("The prime numbers up to " + x + " are: ");
        for (int i = 2; i <= x; i++) {
            // Calls isPrime method
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println("\n");
    }
    // Method for finding the average of the array user gave
    static double mean(double[] a) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        double avg = sum / a.length;
        return avg;
    }
    // Method for finding the standard deviation of the array user gave
    static double standardDeviation(double[] a) {
        double squaredSum = 0;
        double avg = mean(a);
        for (int i = 0; i < a.length; i++) {
            squaredSum += Math.pow(a[i] - avg, 2);
        }
        double stdv = Math.pow(squaredSum / (a.length - 1), 0.5);
        return stdv;
    }
    // Method for finding a number if it is in the array given by the user
    static int search(double[] x, double n) {
        int position = 0;
        for (int i = 0; i < x.length; i++) {
            if (n == x[i]) {
                position = i;
                break;
            } else{
                position = -1;
            }
        }

        return position;
    }
    // Method for displaying a bar chart for the array numbers user has given
    static void displayBarChart(double[] a){
        int[] values = new int[a.length];
        String[] labels = new String[a.length];
        String[] info = new String[a.length];
        double avg = mean(a);
        for (int i = 0; i < a.length; i++){
            values[i] = (int) a[i];
            labels[i] = "Bar" + a[i];
            if (a[i] < avg){
                info[i] = "Bar " + i + " value is " + a[i] + "\nLower than average";
            } else if (a[i] > avg){
                info[i] = "Bar " + i + " value is " + a[i] + "\nHigher than average";
            } else if (a[i] == avg){
                info[i] = "Bar " + i + " value is " + a[i] + "\nIs the average";
            }
        }
        BarChartFrame bars = new BarChartFrame("Array Values", values, labels, info);
                
    }
    // Main method of the program
    public static void main(String[] args) {
        try {
            // Ask user for length of the array
            Scanner number = new Scanner(System.in);
            System.out.print("How many numbers in the array?\n");
            int length = number.nextInt();
            double[] array = new double[length];

            // User inputs as many numbers as the length provided
            System.out.print("Please enter " + length + " numbers.\n");
            for (int i = 0; i < length; i++) {
                array[i] = number.nextDouble();
            }

            Scanner function = new Scanner(System.in);
            // do-while loop for user to interact with until they decide to terminate the program using a proper array length
            int input = 0;
            do {
                System.out.print("Enter:\n1 -- to determine if a number is prime\n2 -- to list prime numbers below a given value\n3 -- to compute the statistics from an array of numbers\n4 -- to perform linear search finding a nubmer in the array\n5 -- to display a bar chart of values\n0 -- to quit\n\n");
                input = function.nextInt();

                if (input == 1) {
                    System.out.print("Enter a number: ");
                    int x = function.nextInt();
                    if (isPrime(x) == true) {
                        System.out.println(x + " IS a prime number\n");
                    }
                    if (isPrime(x) == false) {
                        System.out.println(x + " is NOT a prime number\n");
                    }

                } else if (input == 2) {
                    System.out.print("Enter a number: ");
                    int x = function.nextInt();
                    listPrime(x);

                } else if (input == 3) {
                    System.out.print("The average of: ");
                    for (int i = 0; i < array.length; i++) {
                        System.out.print(array[i] + " ");
                    }
                    
                    System.out.print("is " + mean(array) + "\n");

                    System.out.print("The standard deviation of: ");
                    for (int i = 0; i < array.length; i++) {
                        System.out.print(array[i] + " ");
                    }
                    System.out.print("is " + standardDeviation(array) + "\n\n");

                } else if (input == 4) {
                    System.out.print("Enter a double value: ");
                    double value = function.nextDouble();
                    int position = search(array, value);
                    if (position != -1) {
                        System.out.println("The value " + value + " is found in position " + position + "\n");
                    } else {
                        System.out.println("The value " + value + " is not in the array\n");
                    }
                } else if (input == 5) {
                    displayBarChart(array);

                } else if (input == 0) {
                    System.out.print("Goodbye");
                } else {
                    System.out.print("Invalid choice. Please try again.");
                }
            } while (input != 0);
            
        } catch (Exception ex) {
            System.out.println("Error. Please try again: " + ex.toString());
            Scanner function = new Scanner(System.in);
            // do-while loop for user to interact with until they decide to terminate the program without a proper array length
            int input = 0;
            do {
                System.out.print("Enter:\n1 -- to determine if a number is prime\n2 -- to list prime numbers below a given value\n3 -- to compute the statistics from an array of numbers\n4 -- to perform linear search finding a nubmer in the array\n5 -- to display a bar chart of values\n0 -- to quit\n\n");
                input = function.nextInt();

                if (input == 1) {
                    System.out.print("Enter a number: ");
                    int x = function.nextInt();
                    if (isPrime(x) == true) {
                        System.out.println(x + " IS a prime number\n");
                    }
                    if (isPrime(x) == false) {
                        System.out.println(x + " is NOT a prime number\n");
                    }

                } else if (input == 2) {
                    System.out.print("Enter a number: ");
                    int x = function.nextInt();
                    listPrime(x);

                } else if (input == 3) {
                    System.out.print("Invalid. No array given.\n");

                } else if (input == 4) {
                    System.out.print("Invalid. No array given.\n");
                    
                } else if (input == 5) {
                    System.out.print("Invalid. No array given.\n");

                } else if (input == 0) {
                    System.out.print("Goodbye");
                } else {
                    System.out.print("Invalid choice. Please try again.");
                }
            } while (input != 0);
        }
    }

}




