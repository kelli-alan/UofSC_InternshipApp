import java.util.ArrayList;
import java.io.FileWriter;
import java.util.UUID;

/**
 * Contains core functions of the application such as the login and logout.
 * 
 * @authors Evan Grunewald, Robbie Clark
 */
public class InternshipApp {
  private ArrayList<User> users;
  private ArrayList<Student> students;
  private ArrayList<Listing> listings;
  private ArrayList<Resume> resumes;

  /**
   * Constructor for the InternshipApp that gathers the instance of all the
   * various ArrayList in the application such as the users, resumes, or the
   * listings.
   */
  public InternshipApp() {
    this.users = UserList.getInstance().getAllUsers();
    this.students = UserList.getInstance().getAllStudents();
    this.listings = ListingList.getInstance().getAllListings();
    this.resumes = ResumeList.getInstance().getResumes();
  }

  // Logs user in from userlist's arraylist.
  public User login(String username, String password) {

    return hasUser(username, password);
  }

  // Logs the user out of the application, shows an exit message, and ends the
  // app.
  public void logout() {
    System.out.println("Thank you for using the Internship App");
    DataWriter.saveUsers();
    // DataWriter.saveResumes();
    DataWriter.saveListings();
    System.exit(0);
  }

  // Displays all of the listings on the entire Internship application.
  public String viewAllListings() {
    String ret = "";
    for (int i = 0; i < this.listings.size(); i++) {
      int j = i + 1;
      ret += (j) + ": " + this.listings.get(i).toString() + "\n\n";
    }
    return ret;
  }

  //Dsiplays all of the listings through a specific listing filter.
  public String viewFilteredListings(ArrayList<Listing> filtered) {
    String ret = "";
    for (int i = 0; i < filtered.size(); i++) {
      int j = i + 1;
      ret += (j) + ": " + filtered.get(i).toString() + "\n\n";
    }
    return ret;
  }

  /**
   * Method to add a user to the ArrayList of users.
   * 
   * @param user
   */
  public void addUser(User user) {

    this.users.add(user);
  }

  /**
   * Method to add a listing to the ArrayList of listings.
   * @param listing
   */
  public void addListing(Listing listing) {
    this.listings.add(listing);
  }

  /**
   * Allows user to apply to a job listing with their resume.
   * @param listingID
   * @param resumeID
   * @return True if the application is made, false otherwise.
   */
  public boolean applyToListing(UUID listingID, UUID resumeID) {
    Listing listing = getListing(listingID);
    Resume resume = getResume(resumeID);

    if (!hasApplication(listing, resume.getUUID())) {
      listing.updateApplications(resume);
      return true;
    }
    return false;
  }

  public Listing getListing(UUID listingID) {
    for (int i = 0; i < listings.size(); i++) {
      if (listings.get(i).getID().toString().equals(listingID.toString())) {
        return listings.get(i);
      }
    }

    return null; // listing not found
  }

  public ArrayList<Listing> getListings() {
    return this.listings;
  }

  private Resume getResume(UUID resumeID) {
    for (int i = 0; i < resumes.size(); i++) {
      if (resumes.get(i).getUUID().toString().equals(resumeID.toString())) {
        return resumes.get(i);
      }
    }
    return null; // resume not found
  }

  private Student getStudent(UUID resumeID) {
    for (int i = 0; i < this.students.size(); i++) {
      for (int j = 0; j < this.students.get(i).getResumes().size(); j++) {
        if (this.students.get(i).getResumes().get(j).getUUID().toString().equals(resumeID.toString())) {
          return this.students.get(i);
        }
      }
    }
    return null; // resume not connected to any students
  }

  //Getter method for resumes.
  public ArrayList<Resume> getResumes() {
    return this.resumes;
  }

  /**
   * Returns all of a student's resumes.
   * @return All of a students resumes.
   */
  public String viewAllResumes() {
    int index = 1;
    String ret = "";
    for (int i = 0; i < this.students.size(); i++) {
      for (int j = 0; j < this.students.get(i).getResumes().size(); j++) {
        ret += index + ". " + this.students.get(i).displayResume(j);
        index++;
      }
    }
    return ret;
  }

  /**
   * Deletes a resume.
   * @param resume
   */
  public void deleteResume(Resume resume) {
    if (hasResume(resume.getUUID())) {
      for (int i = 0; i < this.resumes.size(); i++) { // remove from overall list
        if (this.resumes.get(i).getUUID().toString().equals(resume.getUUID().toString())) {
          this.resumes.remove(i);
        }
      }
      // removes from student's list of resumes
      deleteResumeFromStudent(resume, getStudent(resume.getUUID()));
    }
  }

  /**
   * Deletes a listing.
   * @param listing
   */
  public void deleteListing(Listing listing) {
    if (hasListing(listing.getID())) {
      for (int i = 0; i < this.listings.size(); i++) {
        if (this.listings.get(i).getID().toString().equals(listing.getID().toString())) {
          this.listings.remove(i);
        }
      }
    }
  }

  /**
   * Deletes a specific resume from a student.
   * @param resume
   * @param student
   */
  private void deleteResumeFromStudent(Resume resume, Student student) {
    for (int i = 0; i < students.size(); i++) {
      if (students.get(i).getID().toString().equals(student.getID().toString())) {
        students.get(i).deleteResume(resume);
        break;
      }
    }
  }

  /**
   * Checks if the user with their specific username and password exist in the system.
   * @param username
   * @param password
   * @return the user, null otherwise.
   */
  private User hasUser(String username, String password) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).username.equals(username) && users.get(i).password.equals(password))
        return users.get(i);
    }
    return null;
  }

  /**
   * Checks if a resume with a specific resumeID already exist.
   * @param resumeID
   * @return True if there is a resume, false otherwise.
   */
  private boolean hasResume(UUID resumeID) {
    for (int i = 0; i < this.resumes.size(); i++) {
      if (this.resumes.get(i).getUUID().toString().equals(resumeID.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a listing with a specific listingID already exist.
   * @param listingID
   * @return True if there is a listing, false otherwise.
   */
  private boolean hasListing(UUID listingID) {
    for (int i = 0; i < this.listings.size(); i++) {
      if (this.listings.get(i).getID().toString().equals(listingID.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines if a student's resume has already been submitted to a given
   * listing
   * 
   * @param listing  student is attempting to apply to
   * @param resumeID the UUID associated with the student's resume
   * @return true if student has already applied to the listing, false if they
   *         have not applied
   */
  private boolean hasApplication(Listing listing, UUID resumeID) {
    for (int i = 0; i < listing.getApplications().size(); i++) {
      if (listing.getApplications().get(i).getUUID().toString().equals(resumeID.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a username is already taken by another user in the system.
   * @param username
   * @return True if another user has that username already, false otherwise.
   */
  public boolean usernameTaken(String username) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).username.equals(username)) {
        return true;
      }
    }
    return false;
  }

  // Generic method for writing to a file with a given path and content
  public void writeFile(String content, String path) {
    try (FileWriter writer = new FileWriter(path)) {
      writer.write(content);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
