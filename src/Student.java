import java.util.ArrayList;
import java.util.UUID;

/*
 *
 * @authors Yousef Afshar
 */
public class Student extends User {
    
    private ArrayList<Resume> resumes;
    private ArrayList<Listing> listing;
    private ArrayList<Listing> savedListings;
    private FilterBehavior filterBehavior;

    public Student(UUID id, String firstName, String lastName, String username,
        String password, Users USER_TYPE_STUDENT) {
            super(id, firstName, lastName, username, password, USER_TYPE_STUDENT);
        }

    public Resume createResume() {
        return null;
    }

    public void deleteResume(int id) {

    }

    public void applyToListing(Listing listing,  Resume resume) {

    }

    public ArrayList<Listing> filterListings(ArrayList<Listing> savedListings) {
        return savedListings;
    }

    public void setFilterBehavior(FilterBehavior filterBehavior) {

    }

    public void saveListing(Listing listing) {

    }

    public ArrayList<Listing> viewAllListings() {
        return listing;
    }
}
