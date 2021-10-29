import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Kelli Alan
 * Writes data to the appropriate JSON file
 */
public class DataWriter extends DataConstants {

  /**
   * Write user data to JSON file matching a user's type
   * Combines functionality of saveStudents, saveEmployers, and saveModerators into one call for
   * simplicity
   */
  public static void saveUsers() {
    saveStudents();
    saveEmployers();
    saveModerators();
  }

  /**
   * Writes all listings to listing.json
   */
  public static void saveListings() {
    ListingList listings = ListingList.getInstance();
    ArrayList<Listing> listingList = listings.getAllListings();
    JSONArray jsonListings = new JSONArray();

    for (int i = 0; i < listingList.size(); i++) {
      jsonListings.add(getListingJSON(listingList.get(i)));
    }

    try (FileWriter file = new FileWriter(LISTING_FILE_NAME)) {
      file.write(jsonListings.toJSONString());
      file.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes all resumes to resume.json
   */
  public static void saveResumes() {
    ResumeList resumes = ResumeList.getInstance();
    ArrayList<Resume> resumeList = resumes.getResumes();
    JSONArray jsonResumes = new JSONArray();

    for (int i = 0; i < resumeList.size(); i++) {
      jsonResumes.add(getResumeJSON(resumeList.get(i)));
    }

    try (FileWriter file = new FileWriter(RESUME_FILE_NAME)) {
      file.write(jsonResumes.toJSONString());
      file.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Write all student data to student.json
   */
  public static void saveStudents() {
    ArrayList<Student> studentList = UserList.getInstance().getAllStudents();
    JSONArray jsonStudents = new JSONArray();

    for (int i = 0; i < studentList.size(); i++) {
      jsonStudents.add(getStudentJSON(studentList.get(i)));
    }

    try (FileWriter file = new FileWriter(STUDENT_FILE_NAME)) {
      file.write(jsonStudents.toJSONString());
      file.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Write all employer data to employer.json
   */
  public static void saveEmployers() {
    ArrayList<Employer> employerList = UserList.getInstance().getAllEmployers();
    JSONArray jsonEmployers = new JSONArray();

    for (int i = 0; i < employerList.size(); i++) {
      jsonEmployers.add(getEmployerJSON(employerList.get(i)));
    }

    try (FileWriter file = new FileWriter(EMPLOYER_FILE_NAME)) {
      file.write(jsonEmployers.toJSONString());
      file.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Write all moderator data to moderator.json
   */
  public static void saveModerators() {
    ArrayList<Moderator> moderatorList = UserList.getInstance().getAllModerators();
    JSONArray jsonModerators = new JSONArray();

    for (int i = 0; i < moderatorList.size(); i++) {
      jsonModerators.add(getModeratorJSON(moderatorList.get(i)));
    }

    try (FileWriter file = new FileWriter(MODERATOR_FILE_NAME)) {
      file.write(jsonModerators.toJSONString());
      file.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Maps a student's attributes to JSON constants for storage
   * @param student a student user
   * @return JSONObject representing a given student
   */
  public static JSONObject getStudentJSON(Student student) { 
    
    // map base properties of a student
    HashMap<String,Object> studentDetails = new HashMap<String,Object>();
    studentDetails.put(USER_ID, student.getID().toString());
    studentDetails.put(USER_FIRST_NAME, student.getFirstName());
    studentDetails.put(USER_LAST_NAME, student.getLastName());
    studentDetails.put(USER_USERNAME, student.getUserName());
    studentDetails.put(USER_PASSWORD, student.getPassword());
    studentDetails.put(STUDENT_EMAIL, student.getEmail());
    studentDetails.put(STUDENT_PHONE_NUMBER, student.getPhoneNumber());
    studentDetails.put(USER_TYPE, student.getType().toString());

    
    // store resume UUIDs for reference to their resumes
    ArrayList<Resume> studentResumes = student.getResumes();
    ArrayList<String> studentResumeIDs = new ArrayList<String>();
    for (Resume resume : studentResumes) {
      studentResumeIDs.add(resume.getUUID().toString());
    }
    studentDetails.put(STUDENT_RESUMES, studentResumeIDs);
    
    // store listing UUIDs for reference to their saved listings
    ArrayList<Listing> savedListings = student.getSavedListings();
    ArrayList<String> savedListingIDs = new ArrayList<String>();
    for (Listing savedListing : savedListings) {
      savedListingIDs.add(savedListing.getID().toString());
    }
    studentDetails.put(STUDENT_SAVED_LISTINGS, savedListingIDs);

    return new JSONObject(studentDetails);
  }

  /**
   * Maps an employer's attributes to JSON constants for storage
   * @param employer an employer user
   * @return JSONObject representing a given employer
   */
  public static JSONObject getEmployerJSON(Employer employer) {
    // maps base properties of an employer
    HashMap<String,Object> employerDetails = new HashMap<String,Object>();
    employerDetails.put(USER_ID, employer.getID().toString());
    employerDetails.put(USER_FIRST_NAME, employer.getFirstName());
    employerDetails.put(USER_LAST_NAME, employer.getLastName());
    employerDetails.put(USER_USERNAME, employer.getUserName());
    employerDetails.put(USER_PASSWORD, employer.getPassword());
    employerDetails.put(USER_TYPE, employer.getType().toString());
    employerDetails.put(EMPLOYER_COMPANY_NAME, employer.getCompanyName());
    employerDetails.put(EMPLOYER_COMPANY_DESCRIPTION, employer.getCompanyDescription());

    // stores employer's listings as UUIDs
    ArrayList<Listing> employerListings = employer.getListings();
    ArrayList<String> employerListingIDs = new ArrayList<String>();
    for (Listing listing : employerListings) {
      employerListingIDs.add(listing.getID().toString());
    }

    employerDetails.put(EMPLOYER_INTERNSHIP_LISTINGS, employerListingIDs);

    return new JSONObject(employerDetails);
  }

  /**
   * Maps a moderator's attributes to JSON constants for storage
   * @param moderator a moderator user
   * @return JSONObject representing a given moderator
   */
  public static JSONObject getModeratorJSON(Moderator moderator) {
    HashMap<String,Object> moderatorDetails = new HashMap<String,Object>();
    moderatorDetails.put(USER_ID, moderator.getID().toString());
    moderatorDetails.put(USER_FIRST_NAME, moderator.getFirstName());
    moderatorDetails.put(USER_LAST_NAME, moderator.getLastName());
    moderatorDetails.put(USER_USERNAME, moderator.getUserName());
    moderatorDetails.put(USER_PASSWORD, moderator.getPassword());
    moderatorDetails.put(USER_TYPE, moderator.getType().toString());

    return new JSONObject(moderatorDetails);
  }

  /**
   * Maps a listing's attributes to JSON constants for storage
   * @param listing a job listing
   * @return JSONObject representation of a job listing
   */
  public static JSONObject getListingJSON(Listing listing) {
    // maps all listing properties directly except applications
    HashMap<String,Object> listingDetails = new HashMap<String,Object>();
    listingDetails.put(LISTING_ID, listing.getID().toString());
    listingDetails.put(LISTING_TITLE, listing.getJobTitle());
    listingDetails.put(LISTING_CITY, listing.getCity());
    listingDetails.put(LISTING_STATE, listing.getState());
    listingDetails.put(LISTING_START_MONTH, listing.getStartMonth().getValue());
    listingDetails.put(LISTING_START_YEAR, listing.getStartYear());
    listingDetails.put(LISTING_HOURS_PER_WEEK, listing.getHoursPerWeek());
    listingDetails.put(LISTING_PAY, listing.getPay());
    listingDetails.put(LISTING_IS_REMOTE, listing.getIsRemote());
    listingDetails.put(LISTING_DESIRED_SKILLS, listing.getSkills());
    listingDetails.put(LISTING_DUTIES, listing.getDuties());
    
    /* stores a listing's list of applications as the UUID's associated with each application(the 
       student's resume) */
    ArrayList<Resume> listingApplications = listing.getApplications();
    ArrayList<String> applicationIDs = new ArrayList<String>();

    for (Resume application : listingApplications) {
      applicationIDs.add(application.getUUID().toString());
    }

    listingDetails.put(LISTING_APPLICATIONS, applicationIDs);

    return new JSONObject(listingDetails);
  }

  /**
   * Maps resume properties to JSON constants for storage
   * @param resume a student's resume
   * @return JSONObject representation of a student's resume
   */
  public static JSONObject getResumeJSON(Resume resume) {
    HashMap<String,Object> resumeDetails = new HashMap<String, Object>();
    resumeDetails.put(RESUME_ID, resume.getUUID().toString());

    // education section
    ArrayList<Education> educationList = resume.getEducations();
    ArrayList<JSONObject> jsonEducations = new ArrayList<JSONObject>();

    for (int i = 0; i < educationList.size(); i++) {
      jsonEducations.add(getEducationJSON(educationList.get(i)));

    }

    resumeDetails.put(RESUME_EDUCATION_SECTION, jsonEducations);

    // work experience section
    ArrayList<WorkExperience> workExperienceList = resume.getWorkExperiences();
    ArrayList<JSONObject> jsonWorkExperience = new ArrayList<JSONObject>();

    for (int i = 0; i < educationList.size(); i++) {
      jsonWorkExperience.add(getWorkExperienceJSON(workExperienceList.get(i)));

    }

    resumeDetails.put(RESUME_WORK_EXPERIENCES_SECTION, jsonWorkExperience);

    // extracurriculars section
    ArrayList<Extracurricular> extracurricularsList = resume.getExtracurriculars();
    ArrayList<JSONObject> jsonExtracurricular = new ArrayList<JSONObject>();

    for (int i = 0; i < extracurricularsList.size(); i++) {
      jsonExtracurricular.add(getExtracurricularsJSON(extracurricularsList.get(i)));
    }

    resumeDetails.put(RESUME_EXTRACURRICULARS_SECTION, jsonExtracurricular);

    resumeDetails.put(RESUME_SKILLS, resume.getSkills());

    return new JSONObject(resumeDetails);
  }

  /**
   * Maps education properties to JSON constants for storage
   * @param education a degree (earned or ongoing) and its details
   * @return JSONObject representation of an educational experience
   */
  public static JSONObject getEducationJSON(Education education) {
    HashMap<String, Object> educationDetails = new HashMap<String, Object>();
    educationDetails.put(EDUCATION_UNIVERSITY, education.getUniversity());
    educationDetails.put(EDUCATION_CITY, education.getCity());
    educationDetails.put(EDUCATION_STATE, education.getState());
    educationDetails.put(EDUCATION_DEGREE_TYPE, education.getDegreeType());
    educationDetails.put(EDUCATION_MAJOR, education.getMajor());
    educationDetails.put(EDUCATION_MINOR, education.getMinor());
    educationDetails.put(EDUCATION_GRAD_MONTH, education.getGradMonth().getValue());
    educationDetails.put(EDUCATION_GRAD_YEAR, education.getGradYear());
    educationDetails.put(EDUCATION_GPA, education.getGPA());

    return new JSONObject(educationDetails);
  }

  /**
   * Maps work experience properties to JSON constants for storage
   * @param workExperience a job (previous or ongoing) and its details
   * @return JSONObject representation of a work experience
   */
  public static JSONObject getWorkExperienceJSON(WorkExperience workExperience) {
    HashMap<String, Object> workExperienceDetails = new HashMap<String, Object>();
    workExperienceDetails.put(EXPERIENCE_POSITION, workExperience.getPostion());
    workExperienceDetails.put(EXPERIENCE_START_MONTH, workExperience.getStartMonth().getValue());
    workExperienceDetails.put(EXPERIENCE_START_YEAR, workExperience.getStartYear());
    workExperienceDetails.put(EXPERIENCE_ONGOING, workExperience.getOngoing());

    // ensure an end date exists before storing, so null data is not stored
    if (!workExperience.getOngoing()) {
      workExperienceDetails.put(EXPERIENCE_END_MONTH, workExperience.getEndMonth().getValue());
      workExperienceDetails.put(EXPERIENCE_END_YEAR, workExperience.getEndYear());
    }

    workExperienceDetails.put(WORK_EXPERIENCE_COMPANY, workExperience.getCompany());
    workExperienceDetails.put(WORK_EXPERIENCE_CITY, workExperience.getCity());
    workExperienceDetails.put(WORK_EXPERIENCE_STATE, workExperience.getState());
    workExperienceDetails.put(WORK_EXPERIENCE_RESPONSIBILITIES, 
                                                              workExperience.getResponsibilities());

    return new JSONObject(workExperienceDetails);
  }

  /**
  * Maps extracurricular properties to JSON constants for storage
  * @param extracurricular any valuable experience (not covered in education or work experience
  *                        sections) and its details
  * @return JSONObject representation of an extracurricular activity
  */
  public static JSONObject getExtracurricularsJSON(Extracurricular extracurricular) {
    HashMap<String, Object> extracurricularDetails = new HashMap<String, Object>();
    extracurricularDetails.put(EXPERIENCE_POSITION, extracurricular.getPostion());
    extracurricularDetails.put(EXPERIENCE_START_MONTH, extracurricular.getStartMonth().getValue());
    extracurricularDetails.put(EXPERIENCE_START_YEAR, extracurricular.getStartYear());
    extracurricularDetails.put(EXPERIENCE_ONGOING, extracurricular.getOngoing());

    // ensure an end date exists, before saving it
    if (!extracurricular.getOngoing()) {
      extracurricularDetails.put(EXPERIENCE_END_MONTH, extracurricular.getEndMonth().getValue());
      extracurricularDetails.put(EXPERIENCE_END_YEAR, extracurricular.getEndYear());
    }

    extracurricularDetails.put(EXTRACURRICULAR_TITLE, extracurricular.getTitle());
    extracurricularDetails.put(EXTRACURRICULAR_ACTIVITIES,extracurricular.getActivities());

    return new JSONObject(extracurricularDetails);
  }

}