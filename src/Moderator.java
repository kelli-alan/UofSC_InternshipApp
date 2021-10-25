import java.util.UUID;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *
 * @authors Yousef Afshar, Kelli Alan
 */
public class Moderator extends User {

  private ArrayList<Student> students;
  // private ArrayList<Employer> employers;
  // private ArrayList<Listing> listings;
  Scanner reader = new Scanner(System.in);

  public Moderator(UUID id, String firstName, String lastName, String username, String password,
      Users MODERATOR) {
    super(id, firstName, lastName, username, password, MODERATOR);

    this.students = DataLoader.loadStudents();

    // this.listings = DataLoader.loadListings();
  }

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

  /*
   * public void viewUserListings(String username) {
   * System.out.println("Enter username to display listings for: "); username =
   * reader.nextLine(); while (true) { if(username == usernameIndex) {
   * System.out.println("User listing:\n"); System.out.println(listings); break; }
   * else { System.out.println("Invalid username. Please try again."); } } }
   * 
   * public void removeUserResume(String username, Resume resume) {
   * System.out.println("Enter username to remove resumes from: "); username =
   * reader.nextLine(); while (true) { if(username == usernameIndex) {
   * System.out.println("Deleting resume..."); resumes.removeAll(resumes);
   * System.out.println("Done!"); break; } else {
   * System.out.println("Invalid username. Please try again."); } } }
   * 
   * public void removeUserListing(String username, Listing listing) {
   * System.out.println("Enter username to display resumes from: "); username =
   * reader.nextLine(); while (true) { if(username == usernameIndex) {
   * System.out.println("Deleting listing..."); listings.removeAll(listings);
   * System.out.println("Done!"); break; } else {
   * System.out.println("Invalid username. Please try again."); } } }
   */
  public String toString() {
    return super.toString();
  }
}
