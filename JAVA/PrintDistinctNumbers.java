// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 5
// This work was done in accordance to the JMU Honor Code
package assignment2;

import java.util.Scanner;

public class PrintDistinctNumbers {
    // User inputs 10 numbers and the code finds out how many distinct numbers were given and prints them out
    public static void main(String[] args){
        
        // Initial variables needed for this code
        Scanner input = new Scanner(System.in);
        int[] array = new int[10];
        boolean[] array2 = new boolean[10];
        int count = 0;
        
        System.out.print("Enter 10 numbers: ");
        
        // Stores all values into array and boolean expressions in array2 to see if the number is distinct
        for (int i = 0; i < 10; i++){
            array[i] = input.nextInt();
            array2[i] = true;
            for (int j = 0; j < 10; j++){
                if (array[i] == array[j] && i != j)
                    array2[i] = false;
            }
        }
        
        // If number is distinct then distinct total count increases
        for (int k = 0; k < 10; k++)
            if (array2[k])
                count++;
        
        // Prints number of distinct and the distinct numbers
        System.out.print("The number of distinct numbers is: " + count);
        System.out.print("\nThe distinct numbers are: ");
        for (int l = 0; l < 10; l++)
            if(array2[l])
                System.out.print(array[l] + " ");

    }
    
}
