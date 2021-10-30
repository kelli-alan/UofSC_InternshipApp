import java.util.ArrayList;
import java.io.FileWriter;
import java.util.UUID;

/**
 * Contains core functions of the application such as the login and logout.
 * @authors Evan Grunewald, Robbie Clark
 */
public class InternshipApp {
    
    private UserList userList;
    private ArrayList<User> users;

    private ListingList listingList;
    private ArrayList<Listing> listings;

    private ResumeList resumeList;
    private ArrayList<Resume> resumes;
  

    /**
     * Constructor for the InternshipApp that gathers the instance of all
     * the various ArrayList in the application such as the users, resumes, 
     * or the listings.
     */
    public InternshipApp() {
        this.userList = UserList.getInstance();
        this.users = this.userList.getAllUsers();
        this.listingList = ListingList.getInstance();
        this.listings = this.listingList.getAllListings();
        this.resumes = ResumeList.getInstance().getResumes();
    }

    // Logs user in from userlist's arraylist.
    public User login(String username, String password) {
        
        return hasUser(username, password);
    }

    // Logs the user out of the application, shows an exit message, and ends the app.
    public void logout() {
        System.out.println("Thank you for using the Internship App");
        DataWriter.saveUsers();
        //DataWriter.saveResumes();
        DataWriter.saveListings();
        System.exit(0);
    }

    // Displays all of the listings on the entire Internship application.
    public void viewAllListings() {
        
        for(int i = 0; i < this.listings.size(); i++) {
            System.out.println((i+1)+": "+this.listings.get(i).toString());
        }
    }


    /**
     * Method to add a user to the ArrayList of users.
     * @param user
     */
    public void addUser(User user) {

        this.users.add(user);
    }

    public void addListing(Listing listing) {
      this.listings.add(listing);
    }

    public void applyToListing(UUID listingID, UUID resumeID) {
      Resume resume = null;
      for (int i = 0; i < resumes.size(); i++) {
        if (resumes.get(i).getUUID().toString().equals(resumeID.toString())) {
          resume = resumes.get(i);
          break;
        }
      }
      for (int i = 0; i < listings.size(); i++) {
        if (listings.get(i).getID().toString().equals(listingID.toString())) {
          listings.get(i).updateApplications(resume);
          break;
        }
      }
    }

    public Listing getListing(int index) {
      return listings.get(index);
    }

    public User hasUser(String username, String password) {
      for(int i = 0; i < users.size(); i++) {
          if(users.get(i).username.equals(username) && users.get(i).password.equals(password))
              return users.get(i);
      }
      return null;
    }

    public boolean usernameTaken (String username) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).username.equals(username)) {
          return true;
        }
      }
      return false;
    }
  
    // Generic method for writing to a file with a given path and content
    public void writeFile(String content, String path)
    {
        try(FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
