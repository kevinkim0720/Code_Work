// Kevin Kim 111378737
// CIS 331 Section 3
// PA 7
// This work was done in accordance to the JMU Honor Code
package assignment7;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

class User {

    // Private variables
    private String first;
    private String last;
    private String userID;
    private String password;
    
    protected static User users[];
    protected static int totUsers = 0;
    protected static int MAXUSERS = 4;
    

    public User() {
        // Default literals for user information
        first = "FIRST";
        last = "LAST";
        userID = "N/A";
        password = "N/A";
    }

    public User(String f, String l, String id, String pass) {
        // Set literals for user information
        setFirst(f);
        setLast(l);
        setUserID(id);
        setPassword(pass);
    }

    // Get first name for user information
    public String getFirst() {
        return first;
    }

    // Set first name of user to Aaaa
    public void setFirst(String f) {
        first = f.substring(0, 1).toUpperCase() + f.substring(1).toLowerCase();
    }

    // Get last name for user information
    public String getLast() {
        return last;
    }

    // Set last name of user to Aaaa
    public void setLast(String l) {
        last = l.substring(0, 1).toUpperCase() + l.substring(1).toLowerCase();
    }

    // Get userID for user information
    public String getUserID() {
        return userID;
    }

    // Set userID
    public void setUserID(String id) {
        // If statement to check if given user ID starts with a letter and only contains letters and numbers
        if (!id.contains(" ") && id.substring(0, 1).matches("\\w") && id.matches("[a-zA-Z0-9]*$")) {
            userID = id;
        } else {
            userID = "N/A";
        }
    }

    // Get password for user information
    public String getPassword() {
        return password;
    }

    // Set password
    public void setPassword(String pass) {
        // Test to see if the password given is valid
        if (isValidPassword(pass)) {
            try {
                // Change password to cryptographic hashing
                password = toHexString(getSHA(pass));
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Exception thrown for incorrect algorithm: " + ex);
            }
        } else {
            password = "N/A";
        }
    }

    // Method to check if user password is valid
    public static boolean isValidPassword(String p) {

        // Returns true if password does not contain a space, has one uppercase, has one lowercase, has one number, has one special character, and length of password is at least 8
        return !p.contains(" ") && p.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*(_|[^\\w])).+$") && p.length() >= 8;
    }

    // Method to display all user information if information is valid or display just first and last name
    public String userInfo(boolean valid) {
        if (valid) {
            return toString() + "\tFirst Name: " + first + "\tLast Name: " + last + "\nUser ID: " + userID + "\nHashed Password: " + password;
        } else {
            return toString() + "\t" + first + " " + last;
        }
    }

    // Method to see if first and last name for a user matches another user's first and last name
    public boolean equals(String name) {
        String userName = this.first + " " + this.last;
        return userName.equalsIgnoreCase(name);
    }

    // Got from GFG class
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    // Got from GFG class
    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros 
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
    
    // Method to list all the users' first name, last name, and userID in the Users array
    public static String listUsers() {
          StringBuilder userList = new StringBuilder();
          for (int i= 0; i < totUsers; i++){
              userList.append(users[i].userInfo(false) + " (" + users[i].getUserID() + ")\n");
          }
          return userList.toString();
    }
    
    // Method to find a user in the Users array when user inputs first and last name
    public static int findUser(String f, String l) {
        int position = -1;
        String u = f + " " + l;
        if (users != null && totUsers > 0) {
            for (int i = 0; i < totUsers; i++) {
                if (users[i].equals(u)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }
    
    // Method to add a user into the Users array if there is available space
    public static boolean addUser(String f, String l, String id, String pass){
        if(totUsers == MAXUSERS){
            return false;
        }
        if (users == null){
            users = new User[MAXUSERS];
        }
        User User = new User(f, l ,id, pass);
        if (pass.length() > 60){
            User.password = pass;
        }
        users[totUsers] = User;
        totUsers++;
        return true;
    }
    
    // Method to get user information
    public static User getUser(int index){
        User User = null;
        if(users != null && totUsers > 0 && index >= 0 && index < totUsers){
            User = users[index];
        }
        return User;
    }
    
    // Method to check if userID and user password is correct to login successfully
    public static String userLogin(String id, String pw) {
        String result = null;
        try {
            if(isValidPassword(pw)){
            String hashed_pass = toHexString(getSHA(pw));
            for (int i = 0; i < totUsers; i++) {
                if (users[i].userID.equals(id)) {
                    if (users[i].password.equals(hashed_pass)) {
                        result = "login successful. Welcome " + users[i].first + " " + users[i].last + "!";
                        break;
                    } else if (!users[i].password.equals(hashed_pass)) {
                        result = "Password does not match user ID";
                        break;
                    }
                }
                if (!users[i].userID.equals(id)) {
                    result = "User " + id + " does not exist";
                }
            }
            } else {
                result = pw + " is an Invalid password";
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Exception thrown for incorrect algorithm: " + ex);
        }
        return result;
    }
    
    // Return if instance refers to User class
    public String toString(){
        return "User ";
    }
}

