// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 4
// This work was done in accordance to the JMU Honor Code
package assignment1;

import java.util.Scanner;

public class NumberOfDaysInAMonth {

    //User enters month and year, and code displays how many days
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter month number and year: ");
        int month = input.nextInt();
        int year = input.nextInt();

        // Empty variables for month name and number of days in that month
        String monthName = " ";
        int days = 0;
        
        // Switch operator for data input to execute the right answer
        switch (month) {
            case 1:
                monthName = "January";
                days = 31;
                break;

            case 2:
                monthName = "February";
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    days = 29;
                } else {
                    days = 28;
                }
                break;

            case 3:
                monthName = "March";
                days = 31;
                break;

            case 4:
                monthName = "April";
                days = 30;
                break;

            case 5:
                monthName = "May";
                days = 31;
                break;

            case 6:
                monthName = "June";
                days = 30;
                break;

            case 7:
                monthName = "July";
                days = 31;
                break;

            case 8:
                monthName = "August";
                days = 31;
                break;

            case 9:
                monthName = "September";
                days = 30;
                break;

            case 10:
                monthName = "October";
                days = 31;
                break;

            case 11:
                monthName = "November";
                days = 30;
                break;

            case 12:
                monthName = "December";
                days = 31;
                break;
        }

        System.out.println(monthName + " " + year + " has " + days + " days");
    }
}
