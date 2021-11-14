import java.util.ArrayList;
import java.io.FileWriter;
import java.util.UUID;

/**
 * Contains core functions of the application, such as the login and logout, which requires 
 * multiple areas of data from different JSON files, or different user accounts.
 * 
 * @authors Evan Grunewald, Robbie Clark, Kelli Alan
 */
public class InternshipApp {
  private ArrayList<User> users;
  private ArrayList<Student> students;
  private ArrayList<Employer> employers;
  private ArrayList<Listing> listings;
  private ArrayList<Resume> resumes;

  /**
   * Constructor for the InternshipApp that gathers the instance of all the
   * various ArrayList in the application such as the users, resumes, and
   * listings.
   */
  public InternshipApp() {
    this.users = UserList.getInstance().getAllUsers();
    this.students = UserList.getInstance().getAllStudents();
    this.employers = UserList.getInstance().getAllEmployers();
    this.listings = ListingList.getInstance().getAllListings();
    this.resumes = ResumeList.getInstance().getResumes();
  }

  /**
   * Logs user in if the account exists and the user's credentials match what is on file in the JSON
   * @param username attempt by user
   * @param password attempt by user
   * @return the user associated with the given username and password, if one exists
   */
  public User login(String username, String password) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).username.equals(username) && users.get(i).password.equals(password))
        return users.get(i);
    }
    return null;
  }

  /**
   * Logs the user out of the application, shows an exit message, ends the
   * app, and calls Data Writers to update JSON files
   */
  public void logout() {
    System.out.println("Thank you for using the Internship App");
    DataWriter.saveUsers();
    DataWriter.saveResumes();
    DataWriter.saveListings();
    System.exit(0);
  }

  /**
   * Determines if username is already taken by another account
   * @param username new user wants
   * @return true if another user has the username already; false if the username is available
   */
  public boolean usernameTaken(String username) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).username.equals(username)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Finds a student account based on a given resume ID
   * @param resumeID unique ID of resume
   * @return Student associated when the given resume ID, if found; 
   * null if the resume ID cannot be matched to any student
   */
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
  
  /**
   * Retrieves all resumes in the system
   * @return ArrayList of all resumes from all student users
   */
  public ArrayList<Resume> getResumes() {
    return this.resumes;
  }

  /**
   * Helper method that finds a resume based on its unique ID
   * @param resumeID UUID of resume to retrieve
   * @return Resume object matching given resumeID
   */
  private Resume getResume(UUID resumeID) {
    for (int i = 0; i < resumes.size(); i++) {
      if (resumes.get(i).getUUID().toString().equals(resumeID.toString())) {
        return resumes.get(i);
      }
    }
    return null; // resume not found
  }

  /**
   * Helper method to determine which index in a given student's ArrayList of resumes matches a 
   * given UUID
   * @param student user who has the resume of interest
   * @param resumeID the UUID of the resume of interest
   * @return index of student's ArrayList<Resume> the UUID corresponds to; -1 if an ID match is 
   * not found
   */
  private int getResumeIndex(Student student, UUID resumeID) {
    for (int i = 0; i < student.getResumes().size(); i++) {
      if (student.getResumes().get(i).getUUID().toString().equals(resumeID.toString())) {
        return i;
      }
    }
    return -1;  // index not found
  }
  
  /**
   * Helper method find an employer by matching its UUID
   * @param employerID UUID of employer of interest
   * @return Employer profile if found; null if given ID cannot be matched
   */ 
  private Employer getEmployer(UUID employerID) {
    for (int i = 0; i < this.employers.size(); i++) {
      if (this.employers.get(i).getID().toString().equals(employerID.toString())) {
        return this.employers.get(i);
      }
    }
    return null; // employer not found
  }

  /**
   * Retrieves all the listings available in the system
   * @return ArrayList of all listings created by all employers
   */
  public ArrayList<Listing> getListings() {
    return this.listings;
  }

  /**
   * Helper method to retrieve listing based on a given UUID
   * @param listingID UUID to match
   * @return Listing with matching UUID; null if match is not found
   */
  private Listing getListing(UUID listingID) {
    for (int i = 0; i < listings.size(); i++) {
      if (listings.get(i).getID().toString().equals(listingID.toString())) {
        return listings.get(i);
      }
    }

    return null; // listing not found
  }

  /**
   * Helper method to determine if a resume matching a given ID exists in the system
   * @param resumeID UUID to match
   * @return true if resume matching given ID is found, false if match can not be made
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
   * Helper method to determine if a listing matching a given ID exists in the system
   * @param listingID UUID to match
   * @return true if listing found with matching ID, false if match can not be made
   */
  public boolean hasListing(UUID listingID) {
    for (int i = 0; i < this.listings.size(); i++) {
      if (this.listings.get(i).getID().toString().equals(listingID.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Helper method to determine if a student's resume has already been submitted to a given
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
   * Method to add a user to the ArrayList of users.
   * @param user created from create account
   */
  public void addUser(User user) {
    this.users.add(user);
    if(user.type == Users.EMPLOYER) {
      this.employers.add(getEmployer(user.getID()));
    }
    if(user.type == Users.STUDENT) {
      this.students.add(getStudent(user.getID()));
    }
  }

  /**
   * Adds a listing to the ArrayList of listings, if it is not already in the system
   * @param listing created by employer in create listing menu
   */
  public void addListing(Listing listing) {
    if (!hasListing(listing.getID()))
      this.listings.add(listing);
  }

  /**
   * Adds a resume to the ArrayList of resumes, if it is not already in the system
   * @param resume created by student in create resume menu
   */
  public void addResume(Resume resume) {
    if (!hasResume(resume.getUUID()))
      this.resumes.add(resume);
  }

  /**
   * Used by moderators;
   * Removes resume from system's list of resumes and from the student's list of resumes, 
   * who created it, if resume exists
   * @param resume to delete from the system
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
   * Helper method to update a student's list of resumes, when one of their resumes is removed 
   * by a moderator
   * @param resume to delete
   * @param student user that resume belongs to
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
   * Used by moderators;
   * Deletes a listing from the system, if the listing exists
   * @param listing to delete from system
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
   * Adds a student's resume to a given listing's list of applications, if not previously
   * submitted
   * @param listingID UUID of listing student is applying to
   * @param resumeID UUID of resume student is applying with
   * @return true if application process was successful; false if student already applied with the 
   * same resume
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

  /**
   * Used by moderator and student to see all available listings in the system
   * @return String representation of all listings in a numbered list, 1 indexed
   */
  public String viewAllListings() {
    String ret = "";
    int k = 1;
    for (int i = 0; i < this.employers.size(); i++) {
      for (int j = 0; j < this.employers.get(i).getListings().size(); j++) {
        ret += (k) + ": " + this.employers.get(i).displayListing(j) + "\n\n";
        k++;
      }
      
    }
    return ret;
  }

  /**
   * Concatenates filtered listings 
   * @param filtered ArrayList of listings after student filtered them
   * @return String representation of the filtered listings in a numbered list, 1 indexed 
   */
  public String viewFilteredListings(ArrayList<Listing> filtered) {
    String ret = "";
    for (int i = 0; i < filtered.size(); i++) {
      int j = i + 1;
      ret += (j) + ": " + filtered.get(i).toString() + "\n\n";
    }
    return ret;
  }

  /**
   * Used by moderator to see all resumes in the system
   * @return String representation of all resumes in a numbered list, 1 indexed
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
   * Concatenates all listings and appends each listing's applications for a given employer
   * @param employerID the UUID of the employer
   * @return String representation of all listings created by the given employerID, with their 
   * applications
   */
  public String displayListingsWithApplications(UUID employerID) {
    // determine employer and display listings
    Employer employer = getEmployer(employerID);
    String ret = "";
    
    for (int i = 0; i < employer.getListings().size(); i++) {
       ret+= employer.displayListing(i) + "\n";
      
      if (employer.getListings().get(i).getApplications().size() == 0) {
        ret += "No applications yet! Check back soon!\n________________________________________________________________\n"; 
      } else {
          ret += "Applicants: \n\n";
          
          /* match listing applications with student account, so that applications will have the 
          * student's name and contact information at the top 
          */
          for (int k = 0; k < employer.getListings().get(i).getApplications().size(); k++) {  
            Student student = getStudent(employer.getListings().get(i).getApplications().get(k).getUUID());
        
            /* if student has resume ID matching listing application, add the correct resume from 
            * the student's ArrayList of resumes to the return String
            */
            if (student.hasResume(employer.getListings().get(i).getApplications().get(k).getUUID())) {
              ret += student.displayResume(getResumeIndex(student, 
                                              employer.getListings().get(i).getApplications().get(k).getUUID()));
              } 
            }
      }
    }

   
    return ret;

  }

  /**
   * Concatenates a single provided listing with its current applications
   * @param employerID unique ID of employer
   * @param index of listing employer wants to see applications for
   * @return String representation of employer's listing at given index, 
   * with applications appended, if any exist
   */
  public String displayListingWithApplications(UUID employerID, int index) {
    Employer employer = getEmployer(employerID);
    Listing listing = employer.getListings().get(index-1);
    String ret = employer.displayListing(index-1);

    
    if (listing.getApplications().size() == 0) {
      ret += "\nNo applications yet! Check back soon!\n________________________________________________________________\n"; 
    } else {
        ret += "\nApplicants: \n\n";


        for (int i = 0; i < listing.getApplications().size(); i++) {
          Student student = getStudent(listing.getApplications().get(i).getUUID());
  
        /* if student has resume ID matching listing application, add the correct resume from 
        * the student's ArrayList of resumes to the return String
        */
          if (student.hasResume(listing.getApplications().get(i).getUUID())) {
          ret += student.displayResume(getResumeIndex(student, 
                                        listing.getApplications().get(i).getUUID()));
          } 
        }
      }
      return ret;


  }

  /**
   * Generic method for writing to a file with a given path and content
   * @param content to write to a file
   * @param path filename
   */
  public void writeFile(String content, String path) {
    try (FileWriter writer = new FileWriter(path)) {
      writer.write(content);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
