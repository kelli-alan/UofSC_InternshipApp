import java.util.UUID;

enum Users {
    USER_TYPE_STUDENT, USER_TYPE_EMPLOYER, USER_TYPE_MODERATOR;
}

/*
 *
 * @authors Yousef Afshar
 */
public class User {
    
    protected UUID id;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected Users type;

    public User(UUID id, String firstName, String lastName, String username,
    String password, Users type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
