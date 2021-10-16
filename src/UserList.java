import java.util.ArrayList;

public class UserList {
    
    private UserList userlist;
    private ArrayList<User> users;

    public UserList getInstance() {
        if(userlist == null)
            userlist = new UserList();

        return userlist;
    }

    private UserList() {
        users = new ArrayList<User>();
    }

    public ArrayList<User> getUser(String keyword) {
        return users;
    }
}
