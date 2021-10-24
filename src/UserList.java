import java.util.ArrayList;

public class UserList {

  private static UserList userList;
  private ArrayList<User> users;

  private UserList() {
    users = DataLoader.loadUsers();
  }

  public static UserList getInstance() {
    if (userList == null)
      userList = new UserList();

    return userList;
  }

  public ArrayList<User> getUser(String keyword) {
    return users;
  }

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

  public ArrayList<User> getAllUsers() {
    return users;
  }

  public User hasUser(String username, String password) {
      for(int i = 0; i < users.size(); i++) {
          if(users.get(i).username.equals(username) && users.get(i).password.equals(password))
              return users.get(i);
      }
      System.out.println("Invalid username or password");
      return null;
    }
}
