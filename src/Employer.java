import java.util.ArrayList;
import java.util.UUID;
/*
 *
 * @authors Yousef Afshar
 */
public class Employer extends User implements EmployerObserver {
    private String companyName;
    private String companyDescription;
    private ArrayList<Listing> internshipListings;
    private SortBehavior sortBehavior;
    private ArrayList<Resume> resumes;

    public Employer(UUID id, Subject subject, String firstName, String lastName, String username,
        String password, Users USER_TYPE_EMPLOYER, String companyName, String companyDescription) {
            super(id, firstName, lastName, username, password, USER_TYPE_EMPLOYER);
            this.companyName = companyName;
            this.companyDescription = companyDescription;
            internshipListings = new ArrayList<Listing>();

    }

    public void createListing(String jobTitle, String city, String state, String startDate,
    int hoursPerWeek, boolean isRemote) {

    }

    public void fillListing(Listing listing) {

    }

    public ArrayList<Resume> sortApplications(Listing listing) {
        return resumes;
    }

    public ArrayList<Resume> viewAllApplications(Listing listing) {
        return resumes;
    }

    public void update(Listing listing) {

    }
}
