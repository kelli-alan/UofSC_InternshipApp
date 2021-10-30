import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

/**
 *
 * @authors Yousef Afshar, Robbie Clark, Kelli Alan
 */
public class Student extends User {
  private String email;
  private String phoneNumber;
  private ArrayList<Resume> resumes;
  private ArrayList<Listing> savedListings;


  // constructor for loading students who have an id
  public Student(UUID id, String firstName, String lastName, String username, String password, String email, String phoneNumber, Users type) {
    super(id, firstName, lastName, username, password, type);
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.resumes = new ArrayList<Resume>();
    this.savedListings = new ArrayList<Listing>();
  }

  // constructor for creating students who need an id
  public Student(String firstName, String lastName, String username, String password, String email, String phoneNumber, Users type) {
    super(firstName, lastName, username, password, type);
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.resumes = new ArrayList<Resume>();
    this.savedListings = new ArrayList<Listing>();
  }

  public String getEmail() {
    return this.email;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  
  public ArrayList<Resume> getResumes() {
    return this.resumes;
  }

  public ArrayList<Listing> getSavedListings() {
    return this.savedListings;
  }

  public void addResume(Resume resume) {
    this.resumes.add(resume);
  }

  /*public void deleteResume(UUID id) {
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
  }*/

  /*public void applyToListing(Listing listing, Resume resume) {
    if ( resumes.contains(resume)) {
      listingApplications.add(resume);
    }
  }*/ 

  /*
   * Is this still necessary?  
   * public ArrayList<Listing> filterListings(ArrayList<Listing> savedListings) {
   * return savedListings; }
   * 
   * public void setFilterBehavior(FilterBehavior filterBehavior) {
   * 
   * }
   */

  public void saveListing(Listing listing) {
    this.savedListings.add(listing);
  }

  public ArrayList<Listing> viewAllSavedListings() {
    for (int i = 0; i < savedListings.size(); i++)
    savedListings.get(i);
      return savedListings;
  }

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

  //public void printResume()   placeholder for file IO

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

  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  
}