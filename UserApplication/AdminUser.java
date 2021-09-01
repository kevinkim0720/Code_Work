// Kevin Kim 111378737
// CIS 331 Section 3
// PA 7
// This work was done in accordance to the JMU Honor Code
package assignment7;

public class AdminUser extends User {
    
    //Private variable
    private String permission;
    
    // Default constructor
    AdminUser(){
        super();
        permission = " ";
    }
    
    // Overloaded constructor
    AdminUser(String first, String last, String id, String password, String permission){
        super(first, last, id, password);
        setPermission(permission);
    }
    
    // Set permission of the user to designated privileges if valid
    public void setPermission(String pm) {
        if (pm.contains(" ")) {
            String pm1 = pm.split(" ")[0];
            String pm2 = pm.split(" ")[1];
            permission = "";
            if (pm1.equalsIgnoreCase("database")) {
                permission += "database ";
            } else if (pm1.equalsIgnoreCase("cloud")) {
                permission += "cloud ";
            } else if (pm1.equalsIgnoreCase("security")) {
                permission += "security ";
            } else {
                permission += " ";
            }
            if (pm2.equalsIgnoreCase("database")) {
                permission += "database";
            } else if (pm2.equalsIgnoreCase("cloud")) {
                permission += "cloud";
            } else if (pm2.equalsIgnoreCase("security")) {
                permission += "security";
            } else {
                permission += " ";
            }
        }
    }

    // Get permission for user information
    public String getPermission(){
        return permission;
    }
    
    // Return if instance refers to AdminUser class
    public String toString(){
        return "Admin User ";
    }
    
    // Overridden instance method for admin users
    public String userInfo(boolean valid){
        if (valid){
            return super.userInfo(true) + "\nAdmin privileges: " + getPermission();
        } else{
            return super.userInfo(false);
        }
    }
    
    // Method to add an admin user into the Users array if there is available space
    public static boolean addAdminUser(String f, String l, String id, String pass, String permission){
        if(totUsers == MAXUSERS){
            return false;
        }
        if (users == null){
            users = new User[MAXUSERS];
        }
        User adminUser = new AdminUser(f, l ,id, pass, permission);
        
        users[totUsers] = adminUser;
        totUsers++;
        return true;
    }
    
    // Method to list all admin users' first name, last name, userID, and permissions in the Users array if permission type matches what the user wants too see
    public static String listAdminUsers(String type) {
        String adminList = " ";
        for (int i = 0; i < totUsers; i++) {
            if (users[i] instanceof AdminUser) {
                if (type.equalsIgnoreCase("any")) {
                    adminList += users[i].getFirst() + " " + users[i].getLast() + " " + users[i].getUserID() + " " + ((AdminUser) users[i]).getPermission() + "\n";
                } else {
                    if (((AdminUser) users[i]).getPermission().contains(type.toLowerCase())) {
                        adminList += users[i].getFirst() + " " + users[i].getLast() + " " + users[i].getUserID() + " " + ((AdminUser) users[i]).getPermission() + "\n";
                    }
                }
            }
        }
        return adminList;
    }
}
