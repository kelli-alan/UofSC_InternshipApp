//UUID's and ArrayList are both utilized in the Moderator class.
import java.util.UUID;
import java.util.ArrayList;

/**
 * The Moderator class contains methods that a moderator such as the
 * ability to view any resume or listing, as well as delete any resume
 * or listing as they are the only form of validation in the system.
 * @authors Yousef Afshar, Kelli Alan
 */
public class Moderator extends User {

  private ArrayList<Student> students;
  private ArrayList<Employer> employers;

  // Constructor for a moderator that already has an existing ID.
  public Moderator(UUID id, String firstName, String lastName, String username, String password,
      Users USER_TYPE_MODERATOR) {
    super(id, firstName, lastName, username, password, USER_TYPE_MODERATOR);

   // this.students = DataLoader.loadStudents();
   // this.employers = DataLoader.loadEmployers();
  }

  // Constructor for creating a moderator that does not have an ID. 
  public Moderator(String firstName, String lastName, String username, String password,
      Users USER_TYPE_MODERATOR) {
    super(firstName, lastName, username, password, USER_TYPE_MODERATOR);

  //  this.students = DataLoader.loadStudents();
   // this.employers = DataLoader.loadEmployers();
  }

  /**
   * Method searches for a students resumes after looking up their username
   * and has all of them returned to the moderator. If the username does not
   * exist in the database, an error message is returned to the moderator.
   * @param username
   */
  public void viewUserResumes(String username) {
    boolean found = false;
    do {
      for (int i = 0; i < this.students.size(); i++) {
        if (username == students.get(i).username) {
          for (int j = 0; j < (students.get(i)).getResumes().size(); j++) {
            students.get(i).displayResume(j);
            found = true;
          }
          break;
        }
      }
      if (!found) {
        System.out.println("Invalid username. Please try again.");
      }
    } while (!found);
  }

  /**
   * Method searches for an employers listings after looking up their username
   * and has all of them returned to the moderator. If the username does not
   * exist in the database, an error message is returned to the moderator.
   * @param username
   */
   public void viewUserListings(String username) {
     boolean found = false;
        do {
          for (int i = 0; i < this.employers.size(); i++) {
            if (username == employers.get(i).username) {
              for (int j = 0; j < (employers.get(i)).getListings().size(); j++) {
                employers.get(i).displayListing(j);
                found = true;
              }
              break;
            }
          }
          if (!found) {
            System.out.println("Invalid username. Please try again.");
          }
        } while (!found);
      }
    
  /**
   * Method searches for a students resume by the resumes unique UUID, id, and
   * deletes the resume. Method principal for proper moderation. If the ID does not
   * exist in the database, an error message is returned to the moderator.
   * @param id
   */
    public void removeUserResume(UUID id) {
      boolean found = false;
      do {
        for (int i = 0; i < this.students.size(); i++) {
          if (id == students.get(i).id) {
            for (int j = 0; j < (students.get(i)).getResumes().size(); j++) {
      //        students.get(i).deleteResume(id);
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
   * Method searches for an employers listing by the listings unique UUID, id, and
   * deletes the job listing. Method principal for proper moderation, and if the ID 
   * does not exist in the database, an error message is returned to the moderator.
   * @param id
   */
    public void removeUserListing(UUID id) {
        boolean found = false;
           do {
             for (int i = 0; i < this.employers.size(); i++) {
               if (id == employers.get(i).id) {
                 for (int j = 0; j < (employers.get(i)).getListings().size(); j++) {
                   employers.remove(j);
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

  // Calls super from the parent class User for it's toString() method. 
  public String toString() {
    return super.toString();
  }
}
