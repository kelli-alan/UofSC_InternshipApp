import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @authors Yousef Afshar, Robbie Clark, Kelli Alan
 */
public class Student extends User {
  private String email;
  private String phoneNumber;
  private ArrayList<Resume> resumes;
  private ArrayList<Listing> savedListings;


  /**
   * Constructor for loading students who have a pre-existing id.
   * @param id
   * @param firstName
   * @param lastName
   * @param username
   * @param password
   * @param email
   * @param phoneNumber
   * @param type 
   */
  // constructor for loading students who have an id
  public Student(UUID id, String firstName, String lastName, String username, String password, String email, String phoneNumber, Users type) {
    super(id, firstName, lastName, username, password, type);
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.resumes = new ArrayList<Resume>();
    this.savedListings = new ArrayList<Listing>();
  }

  /**
   * Constructor for creating students who need an id.
   * @param firstName
   * @param lastName
   * @param username
   * @param password
   * @param USER_TYPE_STUDENT
   */
  public Student(String firstName, String lastName, String username, String password, String email, String phoneNumber, Users type) {
    super(firstName, lastName, username, password, type);
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.resumes = new ArrayList<Resume>();
    this.savedListings = new ArrayList<Listing>();
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
   * This method deletes a user's resume at the specified index in their resume ArrayList
   * @param index of resume to delete
   */
  public void deleteResume(int index) {
    this.resumes.remove(index);
  }

//TODO MOVE
  /**
   * This method allows a student to apply to a listing by adding their resume
   * to the list of listingApplications, where it can be seen by an employer.
   * @param listing
   * @param resume
   */
  public void applyToListing(int listingIndex, int resumeIndex) {

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
    return "\t\t\t" +  this.firstName + " " + this.lastName + "\n" + this.phoneNumber +"\t\t\t\t" + this.email + "\n" + this.resumes.get(i).toString();
  }

  public String displayAllResumes() {
    String ret = "";
    for (int i = 0; i < resumes.size(); i++) {
      ret+= (i + 1) + ".\n" + displayResume(i) + "\n\n";
    }
    return ret;
  }

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
}