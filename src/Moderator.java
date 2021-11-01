//UUID's and ArrayList are both utilized in the Moderator class.
import java.util.UUID;

/**
 * A user who has the ability to view and delete any listings or resumes from the system;
 * functionality in InternshipApp
 * @authors Yousef Afshar, Kelli Alan
 */
public class Moderator extends User {

  /**
   * Constructor for a moderator that already has an existing ID.
   * @param id UUID tied to moderator's account
   * @param firstName of moderator
   * @param lastName of moderator
   * @param username of moderator
   * @param password of moderator
   * @param type MODERATOR
   */
  public Moderator(UUID id, String firstName, String lastName, String username, String password,
      Users type) {
    super(id, firstName, lastName, username, password, type);
  }

  /**
   * Constructor for creating a moderator; needs an ID. 
   * @param firstName of moderator
   * @param lastName of moderator
   * @param username of moderator
   * @param password of moderator
   * @param type MODERATOR
   */
  public Moderator(String firstName, String lastName, String username, String password,
      Users type) {
    super(firstName, lastName, username, password, type);
  }

  /**
   * Concatenates all of a moderator's properties
   * @return String representation of a moderator
   */
  public String toString() {
    return super.toString();
  }
}
