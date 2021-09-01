// Kevin Kim 111378737
// CIS 331 Section 3
// Assignment 5
// This work was done in accordance to the JMU Honor Code

package assignment5;

public class TestUser {

    public static void main(String[] args) {
        // Set user information into individual variables
        User u1 = new User();
        User u2 = new User("John", "Doe", " ", "nice");
        User u3 = new User("Mary", "JANE", "ChocholateCake!", "@Happiness12");
        User u4 = new User("kevin", "kim", "kev456", "CIS331Assignment5!");
        User u5 = new User("mIKE", "MitRI", "mike1mitri", "@CodingisFun1");

        // Print user information with wrong userID or password formatting
        System.out.println(u1.userInfo(u1) + "\n" + u2.userInfo(u2) + "\n" + u3.userInfo(u3));

        // Print user information with right userID and password formatting 
        System.out.println("\n" + u4.userInfo(u4) + "\n\n" + u5.userInfo(u5));

        // Set new literals to user2 and user3 information
        u2.setUserID("Deerhunting");
        u2.setPassword("Ilovewinter#2");
        u3.setUserID("Chocolate4ever");
        u3.setPassword("@haPpiness1");

        // Print the changed literals
        System.out.println("\nUser2: \nUserID: " + u2.getUserID() + "\nPassword: " + u2.getPassword());
        System.out.println("\nUser3: \nUserID: " + u3.getUserID() + "\nPassword: " + u3.getPassword());

        // Test if user1 first and last name matches user4 first and last name
        if (u1.equals(u4)) {
            System.out.println("It's a match");
        } else {
            System.out.println("Not a match");
        }

        // Set user1 first and last name
        u1.setFirst("Kevin");
        u1.setLast("KIM");

        // Test if user1 first and last name matches user4 first and last name
        if (u1.equals(u4)) {
            System.out.println("It's a match");
        } else {
            System.out.println("Not a match");
        }
    }
}
