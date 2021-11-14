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
public class StudentTester {

    UUID id = UUID.randomUUID();
    private Student student = new Student(id,"Eric", "Vale", "evale", "pass", "evale@gmail.com","803-399-2421",Users.STUDENT);
    private InternshipApp internshipApp = new InternshipApp();
    private UserList userList = UserList.getInstance();
    private ArrayList<Student> students = userList.getAllStudents();

    @BeforeEach
    public void setup() {
    UserList.getInstance().getAllStudents().clear();
    ResumeList.getInstance().getResumes().clear();
    ListingList.getInstance().getAllListings().clear();
    DataWriter.saveStudents();
    DataWriter.saveResumes();
    DataWriter.saveListings();
    }

    @AfterEach
    public void tearDown() {
        UserList.getInstance().getAllStudents().clear();
        ResumeList.getInstance().getResumes().clear();
        ListingList.getInstance().getAllListings().clear();
        DataWriter.saveStudents();
        DataWriter.saveResumes();
        DataWriter.saveListings();
    }

    @Test
    public void testHasResume() {
        UUID resumeID = UUID.randomUUID();
        boolean hasResume = student.hasResume(resumeID);
        assertFalse(hasResume);
    }

    @Test
    public void testAddResume() {
        UUID resumeID = UUID.randomUUID();
        Resume res = new Resume(resumeID);
        student.addResume(res);
        boolean hasResume = student.hasResume(resumeID);
        assertTrue(hasResume);
    }

    @Test
    public void testDeleteResume() {
        UUID resumeID = UUID.randomUUID();
        Resume res = new Resume(resumeID);
        student.addResume(res);
        student.deleteResume(res);
        boolean hasResume = student.hasResume(resumeID);
        assertFalse(hasResume);
    }

    @Test
    public void testSaveListing() {
        Listing listing = new Listing(id, "Tech", "Columbia", "SC", Month.DECEMBER, 2021, 30, 25, false);
        student.saveListing(listing);
        boolean hasSavedListing = internshipApp.hasListing(id);
        assertTrue(hasSavedListing);
    }

    @Test
    public void testDeleteSavedListing() {
       Listing listing = new Listing(id, "Tech", "Columbia", "SC", Month.DECEMBER, 2021, 30, 25, false);
        student.saveListing(listing);
        student.deleteSavedListing(listing);
        boolean hasSavedListing = internshipApp.hasListing(id);
        assertFalse(hasSavedListing);
    }

    @Test
    public void testIsSaved() {
        Listing listing = new Listing(id, "Tech", "Columbia", "SC", Month.DECEMBER, 2021, 30, 25, false);
        student.isSaved(listing);
        boolean hasSavedListing = internshipApp.hasListing(id);
        assertFalse(hasSavedListing);
    }
}
