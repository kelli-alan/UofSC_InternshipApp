import java.util.UUID;

enum Users {
  STUDENT, EMPLOYER, MODERATOR;
}

/*
 *
 * @author Yousef Afshar
 */
public class User {

  protected UUID id;
  protected String firstName;
  protected String lastName;
  protected String username;
  protected String password;
  protected Users type;

  // constructors for loading users who already have an id
  public User(UUID id, String firstName, String lastName, String username, String password, Users type) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.type = type;
  }

  // constructor for creating users who need an id
  public User(String firstName, String lastName, String username, String password, Users type) {
    this.id = UUID.randomUUID();
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.type = type;
  }

  public UUID getID() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getUserName() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public Users getType() {
    return this.type;
  }

  public String toString() {
    return this.type + "\n" + this.firstName + " " + this.lastName + "\nUsername: " + this.username + "\nPassword: "
        + this.password;
  }
}
