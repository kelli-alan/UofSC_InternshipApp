import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;
import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Yousef Afshar
 */
public class EmployerTester {
    
    UUID id = UUID.randomUUID();
    private Employer employer = new Employer(id,"Tyrell", "Wellick", "twellick", "sweden", Users.EMPLOYER, "ECORP", "Evil Corp");
    private InternshipApp internshipApp = new InternshipApp();
    private UserList userList = UserList.getInstance();
    private ArrayList<Employer> employers = userList.getAllEmployers();

    @BeforeEach
    public void setup() {
        UserList.getInstance().getAllEmployers().clear();
        ResumeList.getInstance().getResumes().clear();
        ListingList.getInstance().getAllListings().clear();
        DataWriter.saveEmployers();
        DataWriter.saveResumes();
        DataWriter.saveListings();
    }

    @AfterEach
    public void tearDown() {
        UserList.getInstance().getAllEmployers().clear();
        ResumeList.getInstance().getResumes().clear();
        ListingList.getInstance().getAllListings().clear();
        DataWriter.saveEmployers();
        DataWriter.saveResumes();
        DataWriter.saveListings();
    }

    @Test
    public void testAddListing() {
        Listing listing = new Listing(id, "Tech", "Columbia", "SC", Month.DECEMBER, 2021, 30, 25, false);
        employer.addListing(listing);
        boolean hasListing = internshipApp.hasListing(id);
        assertTrue(hasListing);
    }

    @Test
    public void testDeleteListing() {
        Listing listing = new Listing(id, "Tech", "Columbia", "SC", Month.DECEMBER, 2021, 30, 25, false);
        employer.addListing(listing);
        employer.deleteListing(0);
        boolean hasListing = internshipApp.hasListing(id);
        assertFalse(hasListing);
    }
}
