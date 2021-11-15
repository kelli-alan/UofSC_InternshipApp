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
public class DataWriterTester {
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

    DataWriter.saveUsers();
    DataWriter.saveListings();
    DataWriter.saveResumes();
  }

  @AfterEach
  public void tearDown() {
    userList.clear();
    studentList.clear();
    employerList.clear();
    moderatorList.clear();
    resumeList.clear();
    listingList.clear();
    DataWriter.saveUsers();
    DataWriter.saveListings();
    DataWriter.saveResumes();
  }

  @Test
  public void testWriteZeroUsers() {
    userList = DataLoader.loadUsers();
    assertEquals(0, userList.size());
  }

  @Test
  public void testWriteOneBasicStudent() {
    userList.add(new Student("Kelli", "Alan", "kalan", "kelli'sPassword", 
    "alankelli64@gmail.com", "(803) - 789 - 7897", Users.STUDENT));
    DataWriter.saveUsers();
    assertEquals("kalan", DataLoader.loadUsers().get(0).getUserName());
  }

  @Test
  public void testWriteOneBasicEmployer() {
    userList.add(new Employer("Reid", "White", "rwhite", "reid'sPassword", Users.EMPLOYER, "Tesla", "Tesla is transforming the way we drive"));
    DataWriter.saveUsers();
    assertEquals("rwhite", DataLoader.loadUsers().get(0).getUserName());
  }

  @Test
  public void testWriteOneModerator() {
    userList.add(new Moderator("Portia", "Plante", "portiaportia", "plante", Users.MODERATOR));
    DataWriter.saveUsers();
    assertEquals("portiaportia", DataLoader.loadUsers().get(0).getUserName());
  }

  @Test
  public void testWriteThreeUsers() {
    userList.add(new Student("Kelli", "Alan", "kalan", "kelli'sPassword", 
    "alankelli64@gmail.com", "(803) - 789 - 7897", Users.STUDENT));

    userList.add(new Employer("Reid", "White", "rwhite", "reid'sPassword", Users.EMPLOYER, "Tesla", "Tesla is transforming the way we drive"));

    userList.add(new Moderator("Portia", "Plante", "portiaportia", "plante", Users.MODERATOR));

    DataWriter.saveUsers();
    assertEquals("rwhite", DataLoader.loadUsers().get(1).getUserName());
  }

  @Test
  public void testWriteEmptyStudent() {
    userList.add(new Student("", "", "", "", "", "", Users.STUDENT));
    DataWriter.saveUsers();
    assertEquals("", DataLoader.loadUsers().get(0).getUserName());
  }

  @Test
  public void testWriteEmptyEmployer() {
    userList.add(new Employer("", "", "", "", Users.EMPLOYER, "", ""));
    DataWriter.saveUsers();
    assertEquals("", DataLoader.loadUsers().get(0).getUserName());
  }

  @Test 
  public void testWriteEmptyModerator() {
    userList.add(new Moderator("", "", "", "", Users.MODERATOR));
    DataWriter.saveUsers();
    assertEquals("", DataLoader.loadUsers().get(0).getUserName());
  }

  @Test
  public void testWriteNullStudent() {
    userList.add(new Student("",  "", null, "", "", "", Users.STUDENT));
    DataWriter.saveUsers();
    assertEquals(null, DataLoader.loadUsers().get(0).getUserName());
  }

  
  @Test
  public void testWriteNullEmployer() {
    userList.add(new Employer("", "", null, "", Users.EMPLOYER, "", ""));
    DataWriter.saveUsers();
    assertEquals(null, DataLoader.loadUsers().get(0).getUserName());
  }

  @Test 
  public void testWriteNullModerator() {
    userList.add(new Moderator("", "", null, "", Users.MODERATOR));
    DataWriter.saveUsers();
    assertEquals(null, DataLoader.loadUsers().get(0).getUserName());
  }

  @Test
  public void testWriteZeroListings() {
    listingList = DataLoader.loadListings();
    assertEquals(0, listingList.size());
  }

  @Test
  public void testWriteOneBasicListing() {
    listingList.add(new Listing(UUID.randomUUID(), "Testing Engineer", "Fremont", "CA", 
                                    Month.JUNE, 2022, 40, 19.50, false));
    DataWriter.saveListings();
    assertEquals("Testing Engineer", DataLoader.loadListings().get(0).getJobTitle());
  }

  @Test
  public void testWriteEmptyListing() {
    listingList.add(new Listing(UUID.randomUUID(), "", "", "", 
                                    Month.JANUARY, 0, 0, 0, false));
    DataWriter.saveListings();
    assertEquals("", DataLoader.loadListings().get(0).getJobTitle());
  }

  @Test
  public void testWriteNullListing() {
    listingList.add(new Listing(UUID.randomUUID(), null, "", "", 
                                    Month.JANUARY, 0, 0, 0, false));
    DataWriter.saveListings();
    assertEquals(null, DataLoader.loadListings().get(0).getJobTitle());
  }

  @Test
  public void testWriteThreeListings() {
    listingList.add(new Listing(UUID.randomUUID(), "Testing Engineer", "Fremont", "CA", 
                                    Month.JUNE, 2022, 40, 19.50, false));
    listingList.add(new Listing(UUID.randomUUID(), "Software Engineer", "Cincinnati", "OH", 
                                    Month.DECEMBER, 2021, 30, 22.00, false));
    listingList.add(new Listing(UUID.randomUUID(), "Part-Time Web Developer", "Cincinnati", "OH",   
                                    Month.JUNE, 2022, 20, 15.00, true));
    DataWriter.saveListings();
    assertEquals("Software Engineer", DataLoader.loadListings().get(1).getJobTitle());
  }

  @Test
  public void testWriteZeroResumes() {
    resumeList = DataLoader.loadResumes();
    assertEquals(0, resumeList.size());
  }

  @Test
  public void testWriteEmptyResume() {
    Resume testResume = new Resume();
    resumeList.add(testResume);
    DataWriter.saveResumes();
    assertEquals(testResume.getUUID(), DataLoader.loadResumes().get(0).getUUID());
  }

  @Test
  public void testWriteResumeWithEducation() {
    Resume testResume = new Resume();
    testResume.addEducation(new Education("UofSC","Columbia","SC", "Bachelors of Science", "Computer Science", Month.MAY, 2022));
    resumeList.add(testResume);
    DataWriter.saveResumes();
 
    assertEquals("UofSC", DataLoader.loadResumes().get(0).getEducations().get(0).getUniversity());
  }

  @Test
  public void testWriteResumeWithWorkExperience() {
    Resume testResume = new Resume();
    WorkExperience testWork = new WorkExperience("Cashier", Month.JUNE, 2016, "Office Depot", "Lexington", "SC");
    testResume.addWorkExperience(testWork);
    
    resumeList.add(testResume);
    DataWriter.saveResumes();

    assertEquals(1, DataLoader.loadResumes().get(0).getWorkExperiences().size());

  }

  @Test
  public void testWriteResumeWithExtracurricular() {
    Resume testResume = new Resume();
    Extracurricular testExtracurricular = new Extracurricular("Member", Month.AUGUST, 2018, "Association of Computing Machinery");
    testResume.addExtracurricular(testExtracurricular);

    resumeList.add(testResume);
    DataWriter.saveResumes();

    assertEquals("Association of Computing Machinery", 
                    DataLoader.loadResumes().get(0).getExtracurriculars().get(0).getTitle());
  }

  @Test
  public void testWriteResumeWithExtracurricularWithEndDate() {
    Resume testResume = new Resume();
    Extracurricular testExtracurricular = new Extracurricular("Member", Month.AUGUST, 2018, "Association of Computing Machinery");
    testExtracurricular.addEndDate(Month.MAY, 2020);
    testResume.addExtracurricular(testExtracurricular);

    resumeList.add(testResume);
    DataWriter.saveResumes();

    assertEquals(Month.MAY, 
                    DataLoader.loadResumes().get(0).getExtracurriculars().get(0).getEndMonth());

  }

  @Test
  public void testWriteResumeWithExtracurricularWithActivities() {
    Resume testResume = new Resume();
    Extracurricular testExtracurricular = new Extracurricular("Member", Month.AUGUST, 2018, "Association of Computing Machinery");
    testExtracurricular.addExtracurricularActivity("Participated in a Code-a-Thon");
    testExtracurricular.addExtracurricularActivity("Taught local high schoolers how to code");
    testResume.addExtracurricular(testExtracurricular);

    resumeList.add(testResume);
    DataWriter.saveResumes();

    assertEquals("Participated in a Code-a-Thon", 
                           DataLoader.loadResumes().get(0).getExtracurriculars().get(0).getActivities().get(0));
  }



  @Test
  public void testWriteResumeWithSkills() {
    Resume testResume = new Resume();
    testResume.addSkill("Java");
    testResume.addSkill("C++");
    testResume.addSkill("Matlab");
    resumeList.add(testResume);

    DataWriter.saveResumes();

    assertEquals("Matlab", DataLoader.loadResumes().get(0).getSkills().get(2));
  }


  @Test
  public void testWriteStudentWithResume() {
    Student testStudent = new Student("Ricky","Underwood", "r_under", "inTheBand", "underwood.r@gmail.com", "(634) - 555 - 1234", Users.STUDENT);
    testStudent.addResume(new Resume());

    resumeList.add(testStudent.getResumes().get(0));

    userList.add(testStudent);
    DataWriter.saveUsers();
    DataWriter.saveResumes();

    assertEquals(testStudent.getResumes().get(0).getUUID(), 
                    DataLoader.loadStudents().get(0).getResumes().get(0).getUUID());
  }

  @Test
  public void testWriteStudentWithSavedListing() {
    Student testStudent = new Student("Ricky","Underwood", "r_under", "inTheBand", 
                                      "underwood.r@gmail.com", "(634) - 555 - 1234", Users.STUDENT);

    Listing testListing = new Listing(UUID.randomUUID(), "Software Engineer", "Cincinnati", "OH", 
                                          Month.DECEMBER, 2021, 30, 22.00, true);
    listingList.add(testListing);
    
    testStudent.saveListing(testListing);
    userList.add(testStudent);
    DataWriter.saveUsers();
    DataWriter.saveListings();

    assertEquals(testListing.getID(), 
                    DataLoader.loadStudents().get(0).getSavedListings().get(0).getID());
  }

  @Test
  public void testWriteEmployerWithListings() {
    Employer testEmployer = new Employer("Shannon", "Matthews", "sMatthews", "shannon'sPassword", Users.EMPLOYER, "P&G", "Procter & Gamble makes every day more than ordinary");

    listingList.add(new Listing(UUID.randomUUID(), "Software Engineer", "Cincinnati", "OH", 
                                  Month.DECEMBER, 2021, 30, 22.00, true));

    listingList.add(new Listing(UUID.randomUUID(), "Part-Time Web Developer", "Cincinnati", "OH",   
                                    Month.JUNE, 2022, 20, 15.00, true));

    for (Listing listing : listingList) {
      testEmployer.addListing(listing);
    }
    userList.add(testEmployer);

    DataWriter.saveUsers();
    DataWriter.saveListings();

    assertEquals(testEmployer.getListings().get(1).getID(), 
                    DataLoader.loadEmployers().get(0).getListings().get(1).getID());
  }

  @Test
  public void testWriteListingWithApplication() {
    Resume testApplication = new Resume();
    listingList.add(new Listing(UUID.randomUUID(), "Testing Engineer", "Fremont", "CA", Month.JUNE, 2022, 40, 19.50, false));

    resumeList.add(testApplication);
    listingList.get(0).updateApplications(testApplication);

    DataWriter.saveResumes();
    DataWriter.saveListings();

    assertEquals(testApplication.getUUID(), 
                    DataLoader.loadListings().get(0).getApplications().get(0).getUUID());
  }

  @Test
  public void testWriteListingWithDesiredSkills() {
    Listing testListing = new Listing(UUID.randomUUID(), "Software Engineer", "Cincinnati", "OH", 
                                          Month.DECEMBER, 2021, 30, 22.00, true);

    testListing.addSkills("Python");
    testListing.addSkills("C++");

    listingList.add(testListing);
    DataWriter.saveListings();

    assertEquals("C++", DataLoader.loadListings().get(0).getSkills().get(1));
  }

  @Test
  public void testWriteListingWithDuties() {
    Listing testListing = new Listing(UUID.randomUUID(), "Software Engineer", "Cincinnati", "OH", 
    Month.DECEMBER, 2021, 30, 22.00, true);

    testListing.addDuties("Develop software for internal operations");
    testListing.addDuties("Assist project lead");

    listingList.add(testListing);
    DataWriter.saveListings();

    assertEquals("Assist project lead", DataLoader.loadListings().get(0).getDuties().get(1));

  }

}
