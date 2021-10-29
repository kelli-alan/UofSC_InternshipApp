import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

/**
 *
 * @authors Yousef Afshar, Robbie Clark, Kelli Alan
 */
public class Student extends User {
  private ArrayList<Student> students;
  private ArrayList<Resume> resumes;
  private ArrayList<Listing> listings;
  private ArrayList<Resume> listingApplications;
  private ArrayList<Listing> savedListings;
  private Scanner key = new Scanner(System.in);
  private String major;
  private String phoneNumber;
  private String email;

  /**
   * Constructor for loading students who have a pre-existing id.
   * @param id
   * @param firstName
   * @param lastName
   * @param username
   * @param password
   * @param email
   * @param phoneNumber
   * @param STUDENT
   */
  public Student(UUID id, String firstName, String lastName, String username, String password, String email, String phoneNumber, Users STUDENT) {
    super(id, firstName, lastName, username, password, STUDENT);
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.resumes = new ArrayList<Resume>();
    this.listings = DataLoader.loadListings();
    this.savedListings = new ArrayList<Listing>();
    this.listingApplications = new ArrayList<Resume>();
  }

  /**
   * Constructor for creating students who need an id.
   * @param firstName
   * @param lastName
   * @param username
   * @param password
   * @param USER_TYPE_STUDENT
   */
  public Student(String firstName, String lastName, String username, String password, Users USER_TYPE_STUDENT) {
    super(firstName, lastName, username, password, USER_TYPE_STUDENT);
    this.resumes = new ArrayList<Resume>();
    this.listings = DataLoader.loadListings();
    this.savedListings = new ArrayList<Listing>();
    this.listingApplications = new ArrayList<Resume>();
  }

  // Getter for a student's email.
  public String getEmail() {
    return this.email;
  }

  // Getter for a student's phone number.
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  // Getter for the ArrayList of resumes.
  public ArrayList<Resume> getResumes() {
    return this.resumes;
  }

  // Getter for an ArrayList of listings for the listings a student has saved.
  public ArrayList<Listing> getSavedListings() {
    return this.savedListings;
  }

  /**
   * Method adds a new resume through the ArrayList method add
   * and adds a new resume to the ArrayList.
   */
  public void addResume(Resume resume) {
    this.resumes.add(resume);
  }

  /**
   * This method deletes a user's resume and uses a do while loop with a boolean
   * false that when it sets itself to true, breaks the loop. The method uses a
   * nested for-if-for loop to remove the respective resume. If the input, which
   * is the resume's unique ID value, it will output an error message back to the user.
   * @param id
   */
  public void deleteResume(UUID id) {
    boolean found = false;
    do {
      for (int i = 0; i < this.students.size(); i++) {
        if (id == students.get(i).id) {
          for (int j = 0; j < (students.get(i)).getResumes().size(); j++) {
            students.remove(j);
            found = true;
          }
          break;
        }
      }
      if (!found) {
        System.out.println("Invalid ID. Please try again.");
      }
    } while (!found);
  }

  /**
   * This method allows a student to apply to a listing by adding their resume
   * to the list of listingApplications, where it can be seen by an employer.
   * @param listing
   * @param resume
   */
  public void applyToListing(Listing listing, Resume resume) {
    if ( resumes.contains(resume)) {
      listingApplications.add(resume);
    }
  }

  /**
   * Allows the student to save a listing to the ArrayList of saved listings
   * that they can go back and view using the viewAllSavedListings() method.
   * @param listing
   */
  public void saveListing(Listing listing) {
    this.savedListings.add(listing);
  }

  // Returns the ArrayList of savedListings back to the student.
  public ArrayList<Listing> viewAllSavedListings() {
    for (int i = 0; i < savedListings.size(); i++)
    savedListings.get(i);
      return savedListings;
  }

  // Concatenates and returns together the student's name, phone number, email, and their resume to a string.
  public String displayResume(int i) {
    return "\t\t\t" +  this.firstName + " " + this.lastName + "\n" + this.phoneNumber +"\t\t\t\t" + this.email + this.resumes.get(i).toString();
  }

  // Returns both the resumes and the saved listings.
  public String toString() {
    String ret = super.toString();
    for (int i = 0; i < this.resumes.size(); i++) {
      ret += displayResume(i);
    }

    ret += "Saved Listings: " + "\n";
    for (int i = 0; i < this.savedListings.size(); i++) {
      ret += savedListings.get(i).toString();
    }
    return ret;
  }

  // When called, clears the console of any text to allow for a cleaner UI.
  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  
}