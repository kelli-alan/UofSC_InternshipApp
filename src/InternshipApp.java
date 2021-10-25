import java.util.ArrayList;
/**
 * 
 * @authors Evan Grunewald, Robbie Clark
 */
public class InternshipApp {
    
    private UserList userlist;
    private ListingList listinglist;
    private User user;

    public InternshipApp() {
        this.userlist = userlist.getInstance();
        this.listinglist = listinglist.getInstance();
    }

    public User login(String username, String password) {
        return userlist.hasUser(username, password);
    }

    public void logout() {
        System.out.println("Thank you for using the Internship App");
        System.exit(0);
    }

    public void viewAllListings() {
        this.listinglist.getAllListings();
    }

    public User createUser(String firstName, String lastName, String username, String password, Users type) {
      return null;
    }
}
