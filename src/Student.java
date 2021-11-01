import java.util.ArrayList;
import java.util.UUID;

/**
 * A user type, who can manage their resumes, and view, filter, apply, and save listings.
 * @authors Yousef Afshar, Robbie Clark, Kelli Alan
 */
public class Student extends User {
  private String email;
  private String phoneNumber;
  private ArrayList<Resume> resumes;
  private ArrayList<Listing> savedListings;


  /**
   * Constructor for loading students from JSON who have a pre-existing id.
   * @param id the student's unique ID
   * @param firstName of student
   * @param lastName of student
   * @param username of student
   * @param password of student
   * @param email of student
   * @param phoneNumber of student
   * @param type STUDENT
   */
  public Student(UUID id, String firstName, String lastName, String username, String password, 
                    String email, String phoneNumber, Users type) {
    super(id, firstName, lastName, username, password, type);
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.resumes = new ArrayList<Resume>();
    this.savedListings = new ArrayList<Listing>();
  }

  /**
   * Constructor for creating students who need an id.
   * New student account created from the UI
   * @param firstName of student
   * @param lastName of student
   * @param username of student
   * @param password of student
   * @param email of student
   * @param phoneNumber of student
   * @param type STUDENT
   */
  public Student(String firstName, String lastName, String username, String password, String email, String phoneNumber, Users type) {
    super(firstName, lastName, username, password, type);
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.resumes = new ArrayList<Resume>();
    this.savedListings = new ArrayList<Listing>();
  }

  /**
   * Getter for a student's email.
   * @return student's email address
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Getter for a student's phone number.
   * @return student's phone number
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * Getter for a student's ArrayList of resumes.
   * @return ArrayList of all resumes the student has created
   */
  public ArrayList<Resume> getResumes() {
    return this.resumes;
  }

  /**
   * Getter for an ArrayList of listings for the listings a student has saved. 
   * @return ArrayList of student's saved listings
   */
  public ArrayList<Listing> getSavedListings() {
    return this.savedListings;
  }

  /**
   * Adds a resume to the student's ArrayList of resumes, if that resume is not already 
   * part of their list (checked by comparing UUID)
   * @param resume to add to the student's ArrayList of resumes
   */
  public void addResume(Resume resume) {
    if (!hasResume(resume.getUUID()))
      this.resumes.add(resume);
  }

  /**
   * Deletes a given resume, if the resume is currently on the student's ArrayList of resumes;
   * Deletes resume of student's list that matches the UUID of the resume passed in
   * @param resume student wants to remove from their account
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

  /**
   * Searches through a student's ArrayList of resumes to determine if they have a resume with the 
   * same ID as the one passed in
   * @param resumeID UUID to look for
   * @return true if student has the resume, false if they do not
   */
  public boolean hasResume(UUID resumeID) {
    for (int i = 0; i < this.resumes.size(); i++) {
      if (this.resumes.get(i).getUUID().toString().equals(resumeID.toString())) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Attempts to delete a listing from the student's saved listings
   * @param listing student wants to remove from their saved list
   * @return true if delete is successful, false if listing cannot be found on the student's list 
   * (previously deleted, or wrong user)
   */
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

  /**
   * Concatenates all of the student's saved listings into a list
   * @return String representation of all saved listings in a list, 1 indexed.
   */
  public String viewAllSavedListings() {
    String ret = "";
    for (int i = 0; i < savedListings.size(); i++) {
      ret+= (i+1) + ". " + savedListings.get(i).toString();
    }
    return ret;
  }

  /**
   * Concatenates all fields of a resume, add the index specified
   * @param i index of student's ArrayList of resumes
   * @return String representation of student's resume at index i
   */
  public String displayResume(int i) {
    return "\t\t\t" +  this.firstName + " " + this.lastName + "\n" + this.phoneNumber +"\t\t\t\t" + this.email + "\n" + this.resumes.get(i).toString();
  }

  /**
   * Concatenates all fields of all resumes
   * @return String representation of all of the student's resumes in a list, 1 indexed.
   */
  public String displayAllResumes() {
    String ret = "";
    for (int i = 0; i < resumes.size(); i++) {
      ret+= (i + 1) + ".\n" + displayResume(i) + "\n\n";
    }
    return ret;
  }
}