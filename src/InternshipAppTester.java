import static org.junit.jupiter.api.Assertions.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InternshipAppTester {
    
    private InternshipApp internshipApp = new InternshipApp();
    private UserList userList = UserList.getInstance();
    private ResumeList resumeList = ResumeList.getInstance();
    private ListingList listingList = ListingList.getInstance();
    private ArrayList<User> users = userList.getAllUsers();
    private ArrayList<Student> students = userList.getAllStudents();
    private ArrayList<Employer> employers = userList.getAllEmployers();
    private ArrayList<Listing> listings = listingList.getAllListings();
    private ArrayList<Resume> resumes = resumeList.getResumes();

    @BeforeEach
    public void setup() {
        UserList.getInstance().getAllUsers().clear();
        UserList.getInstance().getAllEmployers().clear();
        UserList.getInstance().getAllModerators().clear();
        UserList.getInstance().getAllStudents().clear();
        ResumeList.getInstance().getResumes().clear();
        ListingList.getInstance().getAllListings().clear();
        DataWriter.saveUsers();
        DataWriter.saveStudents();
        DataWriter.saveEmployers();
        DataWriter.saveModerators();
        DataWriter.saveListings();
        DataWriter.saveResumes();
    }

    @AfterEach
    public void tearDown() {
        UserList.getInstance().getAllUsers().clear();
        UserList.getInstance().getAllEmployers().clear();
        UserList.getInstance().getAllModerators().clear();
        UserList.getInstance().getAllStudents().clear();
        ResumeList.getInstance().getResumes().clear();
        ListingList.getInstance().getAllListings().clear();
        DataWriter.saveUsers();
        DataWriter.saveStudents();
        DataWriter.saveEmployers();
        DataWriter.saveModerators();
        DataWriter.saveListings();
        DataWriter.saveResumes();
        
    }

    @Test
    public void testCreateAccount() {
        UUID id = UUID.randomUUID();
        internshipApp.addUser(new User(id, "Bob", "Jones", "JONEYB", "password", Users.EMPLOYER));
        User user = internshipApp.login("JONEYB", "password");
        assertEquals("JONEYB", user.username);
    }

    @Test
    public void testCreateSavedStudent() {
        UUID id = UUID.randomUUID();
        internshipApp.addUser(new Student(id, "Dan", "Jones", "D_JONES", "CheeryWod", "email@email.com", "123456790", Users.STUDENT));
        internshipApp.logout();
        internshipApp = new InternshipApp();
        User user = internshipApp.login("D_JONES", "CheeryWod");
        assertEquals("D_JONES", user.username);
    }

    @Test
    public void testCreateSavedEmployer() {
        UUID id = UUID.randomUUID();
        internshipApp.addUser(new Employer(id, "Dan", "Jones", "D_JONES", "CheeryWod", Users.EMPLOYER, "companyName", "companyDescription"));
        internshipApp.logout();
        internshipApp = new InternshipApp();
        User user = internshipApp.login("D_JONES", "CheeryWod");
        assertEquals("D_JONES", user.username);
    }

    @Test
    public void testDuplicateUsername() {
        UUID id = UUID.randomUUID();
        internshipApp.addUser(new User(id, "Dan", "Jones", "D_JonES", "CheeryWod", Users.STUDENT));
        boolean usernameTaken = internshipApp.usernameTaken("D_JonES");
        assertTrue(usernameTaken);
    }

    @Test
    public void testApplytoListing() {
        UUID listID = UUID.randomUUID();
        UUID resID = UUID.randomUUID();
        Listing listing = new  Listing(listID, "jobTitle", "city", "state", Month.APRIL, 2021, 40, 30.0, true);
        Resume resume = new Resume(resID);
        internshipApp.addListing(listing);
        internshipApp.addResume(resume);
        boolean applySuccess = internshipApp.applyToListing(listID, resID);
        assertTrue(applySuccess);
    }

    @Test
    public void testApplyFail() {
        UUID listID = UUID.randomUUID();
        UUID resID = UUID.randomUUID();
        Listing listing = new  Listing(listID, "jobTitle", "city", "state", Month.APRIL, 2021, 40, 30.0, true);
        Resume resume = new Resume(resID);
        internshipApp.addListing(listing);
        internshipApp.addResume(resume);
        internshipApp.applyToListing(listID, resID);
        boolean applyFail = internshipApp.applyToListing(listID, resID);
        assertFalse(applyFail);
    }

    @Test
    public void testAddResume() {

        UUID resID = UUID.randomUUID();
        Resume resume = new Resume(resID);
        internshipApp.addResume(resume);
        boolean hasRes = internshipApp.hasResume(resID);
        assertTrue(hasRes);
    }

    @Test
    public void testHasResumeFalse() {

        UUID resID = UUID.randomUUID();
        boolean hasRes = internshipApp.hasResume(resID);
        assertFalse(hasRes);
    }

    @Test
    public void testAddListing() {
        UUID listID = UUID.randomUUID();
        Listing listing = new Listing(listID, "jobTitle", "city", "state", Month.AUGUST, 2021, 40, 20.5, false);
        internshipApp.addListing(listing);
        boolean hasList = internshipApp.hasListing(listID);
        assertTrue(hasList);
    }

    @Test
    public void testHasListingFalse() {
        UUID listID = UUID.randomUUID();
        boolean hasList = internshipApp.hasListing(listID);
        assertFalse(hasList);
    }

    @Test
    public void testDeleteListing() {
        UUID listID = UUID.randomUUID();
        Listing listing = new Listing(listID, "jobTitle", "city", "state", Month.AUGUST, 2021, 40, 20.5, false);
        internshipApp.addListing(listing);
        internshipApp.deleteListing(listing);
        boolean hasList = internshipApp.hasListing(listID);
        assertFalse(hasList);
    }

    @Test
    public void testDeleteResume() {
        UUID id = UUID.randomUUID();
        UUID resID = UUID.randomUUID();
        Student student = new Student(id, "Dan", "Jones", "D_JONES", "CheeryWod", "email@email.org", "555-222-1234", Users.STUDENT);
        Resume resume = new Resume(resID);
        internshipApp.addResume(resume);
        internshipApp.deleteResume(resume);
        boolean hasResume = internshipApp.hasResume(resID);
        assertFalse(hasResume);
    }

    
}
