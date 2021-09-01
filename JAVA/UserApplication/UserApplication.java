// Kevin Kim 111378737
// CIS 331 Section 3
// PA 7
// This work was done in accordance to the JMU Honor Code
package assignment7;

// Java imported needed for this assignment
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserApplication {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Scanner to read inputs from user
        Scanner input = new Scanner(System.in);
        // Initial value for choice
        int choice = 0;
        // Does the loop before the while statement
        do {
            System.out.print("Enter:\n(1) Read user file\n(2) Add new user\n(3) List users\n(4) Display information about a user\n(5) User login\n(6) Add an admin user\n(7) List admin users\n(8) Write user file\n(9) Quit\n\nWhat is your choice? (1-9)\n");
            choice = input.nextInt();

            // Choice 1: reads text file and implements the data into the array Users
            if (choice == 1) {
                String[] first = new String[User.MAXUSERS];
                String[] last = new String[User.MAXUSERS];
                String[] id = new String[User.MAXUSERS];
                String[] password = new String[User.MAXUSERS];
                int totUsers = 0;
                try {
                    Scanner file;
                    file = new Scanner(new File("users.txt".trim()));

                    while (file.hasNext()) {
                        first[totUsers] = file.next();
                        last[totUsers] = file.next();
                        id[totUsers] = file.next();
                        password[totUsers] = file.next();
                        totUsers++;
                    }
                    
                    boolean result = true;
                    
                    for (int i = 0; i < totUsers; i++) {
                        result = User.addUser(first[i], last[i], id[i], password[i]);
                    }
                    if(result){
                        System.out.println("File read successfully");
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not read successfully");
                }

                // Choice 2: adds a new user as long as there is space in the array. Asks for first name, last name, userID, and password to create into a new array element
                } else if (choice == 2) {
                    System.out.println("Enter the user's first and last names, separated with a space: ");
                    String f = input.next();
                    String l = input.next();
                    System.out.println("Enter the user id: ");
                    String id = input.next();
                    System.out.println("Enter the user password: ");
                    String pass = input.next();
                    boolean add = User.addUser(f, l, id, pass);
                    if (add){
                        System.out.println("User added");
                    } else{
                        System.out.println("Maximum users reached");
                    }
                
                // Choice 3: lists all the users in the Users array
                } else if (choice == 3) {
                    System.out.println("User list");
                    System.out.println(User.listUsers());

                // Choice 4: searches the array for first and last name of user input. Displays information if found and displays no information if not in array
                } else if (choice == 4) {
                    System.out.println("Enter the user first and last name to search for: ");
                    String f = input.next();
                    String l = input.next();
                    int position = User.findUser(f,l);
                    if (position >= 0){
                        User person = User.getUser(position);
                        System.out.println(person.userInfo(true));
                    } else{
                        System.out.println(f + " " + l + " not found");
                    }

                // Choice 5: allow user to login if userID and password are correct
                } else if (choice == 5) {
                    System.out.println("Enter user id and password");
                    String id = input.next();
                    String pw = input.next();
                    
                    String result = User.userLogin(id, pw);
                    System.out.println(result);
                
                // Choice 6: adds an admin user into the array  
                } else if (choice == 6){
                    System.out.println("Enter the user's first and last names, separted with a space: ");
                    String f = input.next();
                    String l = input.next();
                    System.out.println("Enter the user id: ");
                    String id = input.next();
                    System.out.println("Enter the user password: ");
                    String pw = input.next();
                    System.out.println("Enter permissions separted by a space: ");
                    String pm1 = input.next();
                    String pm2 = input.next();
                    String permission = pm1 + " " + pm2;
                    
                    boolean add = AdminUser.addAdminUser(f, l, id, pw, permission);
                    if (add){
                        System.out.println("User added");
                    } else{
                        System.out.println("Maximum users reached");
                    }
                    
                // Choice 7: lists all the admin users in the array    
                } else if (choice == 7){
                    System.out.println("Which permission do you want to focus on? (database, cloud, security, or any)");
                    String type = input.next();
                    System.out.println("Admin users with " + type.toLowerCase() + " permission");
                    System.out.println(AdminUser.listAdminUsers(type));

                
                // Choice 8: creates a new text file to display all the current users in Users array
                }else if (choice == 8){
                    java.io.File file = new java.io.File(("newUser.txt").trim());
                    java.io.FileWriter output = new java.io.FileWriter(file);
                for (int i = 0; i < User.totUsers; i++){
                    User user = User.getUser(i);
                    output.write(user.getFirst() + "\t" + user.getLast() + "\t" + user.getUserID() + "\t" + user.getPassword() + "\n");
                }
                output.close();
                System.out.println("User file written");

                // Choice 9: ends the program
                } else if (choice == 9) {
                    System.out.print("Goodbye");
                
                // Any other number tells user invalid choice
                } else {
                    System.out.print("Invalid choice. Please try again.");
                }
            } while (choice != 9);
    }
}