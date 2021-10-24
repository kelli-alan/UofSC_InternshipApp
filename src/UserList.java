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

  public ArrayList<User> getAllUsers() {
    return users;
  }
}
