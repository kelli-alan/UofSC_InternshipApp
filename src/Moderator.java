import java.util.UUID;
import java.util.ArrayList;
/*
 *
 * @authors Yousef Afshar
 */
public class Moderator extends User {

    private ArrayList<Resume> resumes;

    public Moderator(UUID id, String firstName, String lastName, String username,
            String password, Users USER_TYPE_MODERATOR) {
                super(id, firstName, lastName, username, password, USER_TYPE_MODERATOR);
            }
    
    public void viewUserResumes(String username) {

    }

    public void viewUserListings(String username) {

    }

    public void removeUserResume(String username, Resume resume) {

    }

    public void removeUserListing(Listing listing) {

    }
}
