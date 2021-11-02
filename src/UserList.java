import java.util.ArrayList;

/**
 * Singleton, how all users (students, employers, and moderators) in the system are accessed
 * @author Kelli Alan
 */
public class UserList {

  private static UserList userList;
  private ArrayList<User> users;

  /**
   * Loads in all users from student, employer, and moderator JSON files
   */
  private UserList() {
    users = DataLoader.loadUsers();
  }

  /**
   * Used to obtain the single instance of the UserList object
   * @param UserList the single instance of UserList to be used by the caller
   */
  public static UserList getInstance() {
    if (userList == null)
      userList = new UserList();

    return userList;
  }

  /**
   * Used to obtain a list of every user in the database
   * @return the list of all Users
   */
  public ArrayList<User> getAllUsers() {
    return users;
  }

  /**
   * Used to obtain a list of every student in the database
   * @return the list of all students
   */
  public ArrayList<Student> getAllStudents() {
    ArrayList<Student> ret = new ArrayList<Student>();
    int numberUsers = getAllUsers().size();
    for (int i = 0; i < numberUsers; i++) {
      Users type = getAllUsers().get(i).type;
      if (type == Users.STUDENT) {
        ret.add((Student) getAllUsers().get(i));
      }
    }

    return ret;
  }

  /**
   * Used to obtain a list of every employer in the database
   * @return the list of all employers
   */
  public ArrayList<Employer> getAllEmployers() {
    ArrayList<Employer> ret = new ArrayList<Employer>();
    int numberUsers = getAllUsers().size();
    for (int i = 0; i < numberUsers; i++) {
      Users type = getAllUsers().get(i).type;
      if (type == Users.EMPLOYER) {
        ret.add((Employer) getAllUsers().get(i));
      }
    }

    return ret;
  }

  /**
   * Used to obtain a list of every moderator in the database
   * @return the list of all moderator
   */  
  public ArrayList<Moderator> getAllModerators() {
    ArrayList<Moderator> ret = new ArrayList<Moderator>();
    int numberUsers = getAllUsers().size();
    for (int i = 0; i < numberUsers; i++) {
      Users type = getAllUsers().get(i).type;
      if (type == Users.MODERATOR) {
        ret.add((Moderator) getAllUsers().get(i));
      }
    }

    return ret;
  }

  /**
   * Method that checks if the user's username and password exist in the system, and if
   * not, will output back an error to the user. 
   * @param username
   * @param password
   * @return the user's existence in the system.
   */
  public User hasUser(String username, String password) {
      for(int i = 0; i < users.size(); i++) {
          if(users.get(i).username.equals(username) && users.get(i).password.equals(password))
              return users.get(i);
      }
      System.out.println("Invalid username or password");
      return null;
    }
}
