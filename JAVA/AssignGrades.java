// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 4
// This work was done in accordance to the JMU Honor Code
package assignment2;

import java.util.Scanner;

public class AssignGrades {
    // Gives grades to user inputs of student scores

    public static void main(String[] args) {
        Scanner student = new Scanner(System.in);

        // Finds out how many number are in the array
        System.out.print("Enter the number of students: ");
        int number = student.nextInt();
        double[] grades = new double[number];

        // Puts all given grade numbers into the array
        System.out.print("Enter " + number + " scores: ");
        for (int i = 0; i < number; i++) {
            grades[i] = student.nextDouble();
        }

        // Finds best(max) grade to base the grade letters
        double max = grades[0];
        for (int j = 1; j < grades.length; j++) {
            if (grades[j] > max) {
                max = grades[j];
            }
        }

        // Prints out students and their grade letter
        for (int k = 0; k < grades.length; k++) {
            if (grades[k] >= max - 10) {
                System.out.print("Student " + k + " score is " + grades[k] + " and grade is A\n");
            } else if (grades[k] >= max - 20) {
                System.out.print("Student " + k + " score is " + grades[k] + " and grade is B\n");
            } else if (grades[k] >= max - 30) {
                System.out.print("Student " + k + " score is " + grades[k] + " and grade is C\n");
            } else if (grades[k] >= max - 40) {
                System.out.print("Student " + k + " score is " + grades[k] + " and grade is D\n");
            } else {
                System.out.print("Student " + k + " score is " + grades[k] + " and grade is F\n");
            }
        }
        System.out.println();
    }
}
