import java.util.UUID;
import java.util.ArrayList;
import java.util.Scanner;
/*
 *
 * @authors Yousef Afshar
 */
public class Moderator extends User {

    private ArrayList<Resume> resumes;
    private ArrayList<Listing> listings;
    Scanner reader = new Scanner(System.in);
    //Index 4 is the username field in each of the user classes.
    String usernameIndex = resumes.get(4).toString();

    public Moderator(UUID id, String firstName, String lastName, String username,
            String password, Users USER_TYPE_MODERATOR) {
                super(id, firstName, lastName, username, password, USER_TYPE_MODERATOR);
                this.resumes = new ArrayList<Resume>();
                this.listings = new ArrayList<Listing>();
            }
    
    public void viewUserResumes(String username) {
    System.out.println("Enter username to display resumes for: ");
    username = reader.nextLine();
    while (true) {
            if(username == usernameIndex) {
                System.out.println(resumes);
                break;
            }
            else {
                System.out.println("Invalid username. Please try again.");
            }
    }
}

    public void viewUserListings(String username) {
        System.out.println("Enter username to display listings for: ");
        username = reader.nextLine();
        while (true) {
                if(username == usernameIndex) {
                    System.out.println("User listing:\n");
                    System.out.println(listings);
                    break;
                }
                else {
                    System.out.println("Invalid username. Please try again.");
                }
        }
    }

    public void removeUserResume(String username, Resume resume) {
        System.out.println("Enter username to remove resumes from: ");
        username = reader.nextLine();
        while (true) {
                if(username == usernameIndex) {
                    System.out.println("Deleting resume...");
                    resumes.removeAll(resumes);
                    System.out.println("Done!");
                    break;
                }
                else {
                    System.out.println("Invalid username. Please try again.");
                }
        }
    }

    public void removeUserListing(String username, Listing listing) {
        System.out.println("Enter username to display resumes from: ");
        username = reader.nextLine();
        while (true) {
            if(username == usernameIndex) {
                System.out.println("Deleting listing...");
                listings.removeAll(listings);
                System.out.println("Done!");
                break;
            }
            else {
                System.out.println("Invalid username. Please try again.");
            }
    }
    }
}
