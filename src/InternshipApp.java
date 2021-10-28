import java.util.ArrayList;
/**
 * 
 * @authors Evan Grunewald, Robbie Clark
 */
public class InternshipApp {
    
    private UserList userlist;
    private ListingList listinglist;
    private User user;
    private ArrayList<User> users;
    private ResumeList resumeList;
    private ArrayList<Listing> listing;

    public InternshipApp() {
        this.userlist = userlist.getInstance();
        this.users = this.userlist.getAllUsers();
        this.listinglist = listinglist.getInstance();
        this.resumeList = resumeList.getInstance();
        this.listing = new ArrayList<Listing>();
        this.listing = this.listinglist.getAllListings();
    }

    //logs user in from userlist's arraylist
    public User login(String username, String password) {
        
        return userlist.hasUser(username, password);
    }

    public void logout() {
        System.out.println("Thank you for using the Internship App");
       // DataWriter.saveUsers();
       // DataWriter.saveResumes();
       // DataWriter.saveListings();
        System.exit(0);
    }

    public void viewAllListings() {
        
        for(int i = 0; i < this.listing.size(); i++) {
            System.out.println((i+1)+": "+this.listing.get(i).toString());
        }
    }

    public void addUser(User user) {

        this.users.add(user);
    }
}
