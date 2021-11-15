import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.time.Month;
import java.util.ArrayList;

/**
 * @author Kelli Alan
 */
public class DataLoaderTester {

  private UserList users = UserList.getInstance();
  private ResumeList resumes = ResumeList.getInstance();
  private ListingList listings = ListingList.getInstance();

  private ArrayList<User> userList = users.getAllUsers();
  private ArrayList<Student> studentList = users.getAllStudents();
  private ArrayList<Employer> employerList = users.getAllEmployers();
  private ArrayList<Moderator> moderatorList = users.getAllModerators();
  private ArrayList<Resume> resumeList = resumes.getResumes();
  private ArrayList<Listing> listingList = listings.getAllListings();

  @BeforeEach
  public void setup() {
    userList.clear();
    studentList.clear();
    employerList.clear();
    moderatorList.clear();
    resumeList.clear();
    listingList.clear();

    userList.add(new Student("Kelli", "Alan", "kalan", "kelli'sPassword", 
                                    "alankelli64@gmail.com", "(803) - 789 - 7897", Users.STUDENT));
    userList.add(new Student("Ricky","Underwood", "r_under", "inTheBand", "underwood.r@gmail.com", "(634) - 555 - 1234", Users.STUDENT));

    // blank resume
    resumeList.add(new Resume());
    
    // full resume
    Resume testResume = new Resume();
    testResume.addEducation(new Education("UofSC","Columbia","SC", "Bachelors of Science", "Computer Science", Month.MAY, 2022));
    testResume.addWorkExperience(new WorkExperience("Cashier", Month.JUNE, 2016, "Office Depot", "Lexington", "SC"));
    testResume.addExtracurricular(new Extracurricular("Member", Month.AUGUST, 2018, "Association of Computing Machinery"));

    testResume.addSkill("Java");
    testResume.addSkill("C++");
    testResume.addSkill("JUnit Testing");
    
    resumeList.add(testResume);


    userList.add(new Employer("Reid", "White", "rwhite", "reid'sPassword", Users.EMPLOYER, "Tesla", "Tesla is transforming the way we drive"));
    userList.add(new Employer("Shannon", "Matthews", "sMatthews", "shannon'sPassword", Users.EMPLOYER, "P&G", "Procter & Gamble makes every day more than ordinary"));
    

    listingList.add(new Listing(UUID.randomUUID(), "Testing Engineer", "Fremont", "CA", Month.JUNE, 2022, 40, 19.50, false));
    listingList.add(new Listing(UUID.randomUUID(), "Software Engineer", "Cincinnati", "OH", Month.DECEMBER, 2021, 30, 22.00, true));

    userList.add(new Moderator("Portia", "Plante", "portiaportia", "plante", Users.MODERATOR));
    DataWriter.saveUsers();
    DataWriter.saveResumes();
    DataWriter.saveListings();
    
  }

  @AfterEach
  public void tearDown() { 
    UserList.getInstance().getAllUsers().clear();
    ResumeList.getInstance().getResumes().clear();
    ListingList.getInstance().getAllListings().clear();
    DataWriter.saveUsers();
    DataWriter.saveResumes();
    DataWriter.saveListings();
  }

  // test that the quantity of provided data in BeforeEach is loaded
  @Test
  public void testStudentsSize() {
    studentList = DataLoader.loadStudents();
    assertEquals(2, studentList.size());
  }

  @Test
  public void testEmployersSize() {
    employerList = DataLoader.loadEmployers();
    assertEquals(2, employerList.size());
  }

  @Test
  public void testModeratorsSize() {
    moderatorList = DataLoader.loadModerators();
    assertEquals(1, moderatorList.size());
  }

  @Test
  public void testUsersSize() {
    userList = DataLoader.loadUsers();
    assertEquals(5, userList.size());
  }

  @Test
  public void testResumesSize() {
    resumeList = DataLoader.loadResumes();
    assertEquals(2, resumeList.size());  
  }

  @Test
  public void testListingsSize() {
    listingList = DataLoader.loadListings();
    assertEquals(2, listingList.size());
  }
  ////////////////////////////////////////////////////////////////
  
  // test that areas of no data can still be "loaded"
  @Test
  public void testResumesSizeZero() {
    ResumeList.getInstance().getResumes().clear();
    DataWriter.saveResumes();
    assertEquals(0, resumeList.size());
  }

  @Test
  public void testUsersSizeZero() {
    UserList.getInstance().getAllUsers().clear();
    DataWriter.saveUsers();
    assertEquals(0, userList.size());
  }

  @Test
  public void testListingsSizeZero() {
    ListingList.getInstance().getAllListings().clear();
    DataWriter.saveListings();
    assertEquals(0, listingList.size());
  }

  ///////////////////////////////////////////////////////////////

  //test student loading possibilities
  @Test
  public void testGetFirstStudentEmail() {
    studentList = UserList.getInstance().getAllStudents();
    assertEquals("alankelli64@gmail.com", studentList.get(0).getEmail());
  }

  @Test
  public void testGetSecondStudentPhoneNumber() {
    studentList = UserList.getInstance().getAllStudents();
    assertEquals("(634) - 555 - 1234", studentList.get(1).getPhoneNumber());
  }

  @Test
  public void testLoadStudentWithResume() {
    UserList.getInstance().getAllStudents().get(0).addResume(resumeList.get(1));
    DataWriter.saveUsers();
    studentList = UserList.getInstance().getAllStudents();
    assertEquals(resumeList.get(1).getUUID(), studentList.get(0).getResumes().get(0).getUUID());
  }

  @Test
  public void testLoadStudentWithSavedListing() {
    UserList.getInstance().getAllStudents().get(1).saveListing(listingList.get(1));
    DataWriter.saveUsers();
    studentList = UserList.getInstance().getAllStudents();
    assertEquals(listingList.get(1).getID(), studentList.get(1).getSavedListings().get(0).getID());
  }
  /////////////////////////////////////////////////////////////////


  // test employer loading possibilities
  @Test
  public void testGetFirstEmployerCompanyName() {
    employerList = UserList.getInstance().getAllEmployers();
    assertEquals("Tesla", employerList.get(0).getCompanyName());
  }

  @Test
  public void testGetSecondEmployerCompanyDescription() {
    employerList = UserList.getInstance().getAllEmployers();
    assertEquals("Procter & Gamble makes every day more than ordinary", employerList.get(1).getCompanyDescription());
  }

  @Test
  public void testLoadEmployerWithListing() {
    UserList.getInstance().getAllEmployers().get(0).addListing(listingList.get(0));
    DataWriter.saveUsers();
    employerList = UserList.getInstance().getAllEmployers();
    assertEquals(listingList.get(0).getID(), employerList.get(0).getListings().get(0).getID());
  }

  @Test
  public void testEmployerListingSizeZero() {
    employerList = UserList.getInstance().getAllEmployers();
    assertEquals(0, employerList.get(1).getListings().size());
  }
  /////////////////////////////////////////////////////////////////

  // test resume loading possibilities
  @Test
  public void testLoadResumeWithNoEducation() {
   Resume testResume = resumeList.get(1);
   testResume.deleteEducation(0);
   DataWriter.saveResumes();
   resumeList = ResumeList.getInstance().getResumes();
   assertEquals(0, resumeList.get(1).getEducations().size());
  }

  @Test
  public void testLoadResumeWithEducationNoMinor() {
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals("", resumeList.get(1).getEducations().get(0).getMinor());
  }

  @Test
  public void testLoadResumeWithEducationNoGPA() {
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(0.0, resumeList.get(1).getEducations().get(0).getGPA());
  }


  @Test
  public void testLoadResumeWithEducation() {
    DataWriter.saveResumes();
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(1, resumeList.get(1).getEducations().size());
  }

  @Test
  public void testLoadResumeWithEducationMinor() {
    ResumeList.getInstance().getResumes().get(1).getEducations().get(0).addMinor("Mathematics");
    DataWriter.saveResumes();
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals("Mathematics", resumeList.get(1).getEducations().get(0).getMinor());
  }

  
  @Test
  public void testLoadResumeWithEducationGPA() {
    ResumeList.getInstance().getResumes().get(1).getEducations().get(0).addGPA(3.65);
    DataWriter.saveResumes();
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(3.65, resumeList.get(1).getEducations().get(0).getGPA());
  }

  @Test
  public void testLoadResumeWithNoWorkExperience() {
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(0, resumeList.get(0).getWorkExperiences().size());
  }

  @Test
  public void testLoadResumeWithWorkExperience() {
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(1, resumeList.get(1).getWorkExperiences().size());
  }

  @Test
  public void testLoadResumeWithWorkExperienceWithEndDate() {
    ResumeList.getInstance().getResumes().get(1).getWorkExperiences().get(0).addEndDate(Month.FEBRUARY, 2017);
    DataWriter.saveResumes();
    resumeList = ResumeList.getInstance().getResumes();
    assertFalse(resumeList.get(1).getWorkExperiences().get(0).getOngoing());

    DataWriter.saveResumes();
  }

  @Test
  public void testLoadResumeWithWorkExperienceWithResponsibilities() {
    Resume testResume = ResumeList.getInstance().getResumes().get(1);
    testResume.getWorkExperiences().get(0).addResponsibility("Shelving ink");
    testResume.getWorkExperiences().get(0).addResponsibility("Helped customer in customer service");
    DataWriter.saveResumes();
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(2, resumeList.get(1).getWorkExperiences().get(0).getResponsibilities().size());
  }

  @Test
  public void testLoadResumeNoExtracurriculars() {
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(0, resumeList.get(0).getExtracurriculars().size());
  }

  @Test
  public void testLoadResumeWithExtracurricular() {
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(1, resumeList.get(1).getExtracurriculars().size());
  }

  @Test
  public void testLoadResumeWithExtracurricularWithEndDate() {
    ResumeList.getInstance().getResumes().get(1).getExtracurriculars().get(0).addEndDate(Month.MAY, 2020);
    DataWriter.saveResumes();
    resumeList = ResumeList.getInstance().getResumes();
    assertFalse(resumeList.get(1).getExtracurriculars().get(0).getOngoing());
  }

  @Test
  public void testLoadResumeWithExtracurricularWithActivities() {
    Resume testResume = ResumeList.getInstance().getResumes().get(1);
    testResume.getExtracurriculars().get(0).addExtracurricularActivity("Participated in a Code-a-thon");
    testResume.getExtracurriculars().get(0).addExtracurricularActivity("Led a booth at E-Week");

    DataWriter.saveResumes();
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(2, resumeList.get(1).getExtracurriculars().get(0).getActivities().size());
  }

  @Test
  public void testLoadResumeWithNoSkills() {
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(0, resumeList.get(0).getSkills().size());
  }

  @Test
  public void testLoadResumeWithSkills() {
    resumeList = ResumeList.getInstance().getResumes();
    assertEquals(3, resumeList.get(1).getSkills().size());

  }

  
  // Test listing loading possibilities
  @Test
  public void testLoadListingWithNoApplicants() {
    listingList = ListingList.getInstance().getAllListings();
    assertEquals(0, listingList.get(0).getApplications().size());
  } 

  @Test
  public void testLoadListingWithApplicants() {
    Listing testListing = ListingList.getInstance().getAllListings().get(0);
    resumeList = ResumeList.getInstance().getResumes();
    testListing.updateApplications(resumeList.get(0));
    testListing.updateApplications(resumeList.get(1));

    DataWriter.saveListings();
    
    listingList = ListingList.getInstance().getAllListings();
    assertEquals(2, listingList.get(0).getApplications().size());
  }

  @Test
  public void testLoadListingWithNoDesiredSkills() {
    listingList = ListingList.getInstance().getAllListings();
    assertEquals(0, listingList.get(0).getSkills().size());
  }

  @Test
  public void testLoadListingWithDesiredSkills() {
    Listing testListing = ListingList.getInstance().getAllListings().get(0);
    testListing.addSkills("Python");
    testListing.addSkills("Matlab");

    DataWriter.saveListings();

    listingList = ListingList.getInstance().getAllListings();

    assertEquals(2, listingList.get(0).getSkills().size());
  }

  @Test
  public void testLoadListingWithNoDuties() {
    listingList = ListingList.getInstance().getAllListings();
    assertEquals(0, listingList.get(1).getDuties().size());
  }

  @Test
  public void testLoadListingWithDuty() {
    Listing testListing = ListingList.getInstance().getAllListings().get(1);
    testListing.addDuties("Support the lead engineer with development of new software");
    DataWriter.saveListings();
    listingList = ListingList.getInstance().getAllListings();
    assertEquals(1, listingList.get(1).getDuties().size());
  }
  //////////////////////////////////////////////////////////////////////////////////////
}
