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
  public void deleteResume(Resume resume) {
    if (hasResume(resume.getUUID())) {
      for (int i = 0; i < this.resumes.size(); i++) {
        if (resumes.get(i).getUUID().toString().equals(resume.getUUID().toString())) {
          this.resumes.remove(i);
        }
      }
    }
  }

  private boolean hasResume(UUID resumeID) {
    for (int i = 0; i < this.resumes.size(); i++) {
      if (this.resumes.get(i).getUUID().toString().equals(resumeID.toString())) {
        return true;
      }
    }
    return false;
  }
  
  public boolean deleteSavedListing(Listing listing) {
    if (isSaved(listing)) {
      savedListings.remove(listing);
      return true;
    }
    return false;
  }


  /**
   * Allows the student to save a listing to their ArrayList of saved listings, if it was not 
   * previously saved so that they can go back and view using the viewAllSavedListings() method.
   * @param listing to save
   * @return true if save was successful, false if listing is already saved
   */
  public boolean saveListing(Listing listing) {
    
    if (!isSaved(listing)) {
      this.savedListings.add(listing);
      return true;
    }
    return false;
  }
  /**
   * Determines if listing already is student's ArrayList of saved listings
   * @param listing the listing the student is attempting to save
   * @return true if listing is already saved; false if listing is not saved
   */
  public boolean isSaved(Listing listing) {
    for (int i = 0; i < this.savedListings.size();  i++) {
      if (this.savedListings.get(i).getID().toString().equals(listing.getID().toString()))
      {
        return true;
      }
    }
    return false;
  }
  // Returns the ArrayList of savedListings back to the student.
  public String viewAllSavedListings() {
    String ret = "";
    for (int i = 0; i < savedListings.size(); i++) {
      ret+= (i+1) + ". " + savedListings.get(i).toString();
    }
    return ret;
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
}