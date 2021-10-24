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
        ret.add((Student)getAllUsers().get(i));
      }
    }

    return ret;
  }

  public ArrayList<User> getAllUsers() {
    return users;
  }
}
