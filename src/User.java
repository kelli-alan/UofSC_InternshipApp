//Necessary for declaration of the UUID id in the User class.
import java.util.UUID;

enum Users {
  STUDENT, EMPLOYER, MODERATOR;
}

/**
 * The User class defines what is contained with a user as well as enumerating
 * what the three distinct user types are in: student, employer, and moderator.
 * @author Yousef Afshar
 */
public class User {

  protected UUID id;
  protected String firstName;
  protected String lastName;
  protected String username;
  protected String password;
  protected Users type;

  /*
   * Constructors for loading user with a pre-existing ID.
   */
  public User(UUID id, String firstName, String lastName, String username, String password, Users type) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.type = type;
  }

  /** 
   *  Constructor for creating users who need to have to a new random UUID, id.
   */
  public User(String firstName, String lastName, String username, String password, Users type) {
    this.id = UUID.randomUUID();
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.type = type;
  }

  /**
   * Gets the user's unique UUID ID on their account.
   * @return this instance of id.
   */
  public UUID getID() {
    return this.id;
  }

  /**
   * Gets the user's first name tied to their account.
   * @return this instance of firstname.
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Gets the user's last name tied to their account.
   * @return this instance of lastname.
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Gets a the user's username tied to their account.
   * @return this instance of username. 
   */
  public String getUserName() {
    return this.username;
  }

  /**
   * Gets the password that users have tied to their account.
   * @return this instance of a password.
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Gets a type of user (types being STUDENT, EMPLOYER, and MODERATOR)
   * @return this instance of type. 
   */
  public Users getType() {
    return this.type;
  }

  // Returns the user's information to a string containing their user type, name, username, and password.
  public String toString() {
    return this.type + "\n" + this.firstName + " " + this.lastName + "\nUsername: " + this.username + "\nPassword: "
        + this.password;
  }
}
