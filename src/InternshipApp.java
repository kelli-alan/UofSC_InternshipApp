import java.util.ArrayList;
/**
 * Contains core functions of the application such as the login and logout.
 * @authors Evan Grunewald, Robbie Clark
 */
public class InternshipApp {
    
    private UserList userList;
    private ListingList listingList;
    private ArrayList<User> users;
    private ResumeList resumeList;
    private ArrayList<Listing> listing;

    /**
     * Constructor for the InternshipApp that gathers the instance of all
     * the various ArrayList in the application such as the users, resumes, 
     * or the listings.
     */
    public InternshipApp() {
        this.userList = UserList.getInstance();
        this.users = this.userList.getAllUsers();
        this.listingList = ListingList.getInstance();
        this.resumeList = ResumeList.getInstance();
        this.listing = new ArrayList<Listing>();
        this.listing = this.listingList.getAllListings();
    }

    // Logs user in from userlist's arraylist.
    public User login(String username, String password) {
        
        return userList.hasUser(username, password);
    }

    // Logs the user out of the application, shows an exit message, and ends the app.
    public void logout() {
        System.out.println("Thank you for using the Internship App");
       // DataWriter.saveUsers();
       // DataWriter.saveResumes();
       // DataWriter.saveListings();
        System.exit(0);
    }

    // Displays all of the listings on the entire Internship application.
    public void viewAllListings() {
        
        for(int i = 0; i < this.listing.size(); i++) {
            System.out.println((i+1)+": "+this.listing.get(i).toString());
        }
    }

    /**
     * Method to add a user to the ArrayList of users.
     * @param user
     */
    public void addUser(User user) {

        this.users.add(user);
    }
}
