import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

  public static void saveUsers() {
    saveStudents();
    saveEmployers();
    saveModerators();
  }

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

  public static JSONObject getStudentJSON(Student student) {
    JSONObject studentDetails = new JSONObject();
    studentDetails.put(USER_ID, student.getID().toString());
    studentDetails.put(USER_FIRST_NAME, student.getFirstName());
    studentDetails.put(USER_LAST_NAME, student.getLastName());
    studentDetails.put(USER_USERNAME, student.getUserName());
    studentDetails.put(USER_PASSWORD, student.getPassword());
    studentDetails.put(USER_TYPE, student.getType().toString());

    ArrayList<Resume> studentResumes = student.getResumes();
    JSONArray studentResumeIDs = new JSONArray();
    for (Resume resume : studentResumes) {
      studentResumeIDs.add(resume.getUUID().toString());
    }
    studentDetails.put(STUDENT_RESUMES, studentResumeIDs);
    ArrayList<Listing> savedListings = student.getSavedListings();
    JSONArray savedListingIDs = new JSONArray();
    for (Listing savedListing : savedListings) {
      savedListingIDs.add(savedListing.getID().toString());
    }
    studentDetails.put(STUDENT_SAVED_LISTINGS, savedListingIDs);

    return studentDetails;
  }

  public static JSONObject getEmployerJSON(Employer employer) {
    JSONObject employerDetails = new JSONObject();
    employerDetails.put(USER_ID, employer.getID().toString());
    employerDetails.put(USER_FIRST_NAME, employer.getFirstName());
    employerDetails.put(USER_LAST_NAME, employer.getLastName());
    employerDetails.put(USER_USERNAME, employer.getUserName());
    employerDetails.put(USER_PASSWORD, employer.getPassword());
    employerDetails.put(USER_TYPE, employer.getType().toString());
    employerDetails.put(EMPLOYER_COMPANY_NAME, employer.getCompanyName());
    employerDetails.put(EMPLOYER_COMPANY_DESCRIPTION, employer.getCompanyDescription());

    ArrayList<Listing> employerListings = employer.getListings();
    JSONArray employerListingIDs = new JSONArray();
    for (Listing listing : employerListings) {
      employerListingIDs.add(listing.getID().toString());
    }

    employerDetails.put(EMPLOYER_INTERNSHIP_LISTINGS, employerListingIDs);

    return employerDetails;
  }

  public static JSONObject getModeratorJSON(Moderator moderator) {
    JSONObject moderatorDetails = new JSONObject();
    moderatorDetails.put(USER_ID, moderator.getID().toString());
    moderatorDetails.put(USER_FIRST_NAME, moderator.getFirstName());
    moderatorDetails.put(USER_LAST_NAME, moderator.getLastName());
    moderatorDetails.put(USER_USERNAME, moderator.getUserName());
    moderatorDetails.put(USER_PASSWORD, moderator.getPassword());
    moderatorDetails.put(USER_TYPE, moderator.getType().toString());

    return moderatorDetails;
  }

  public static JSONObject getListingJSON(Listing listing) {
    JSONObject listingDetails = new JSONObject();
    listingDetails.put(LISTING_ID, listing.getID().toString());
    listingDetails.put(LISTING_TITLE, listing.getJobTitle());
    listingDetails.put(LISTING_CITY, listing.getCity());
    listingDetails.put(LISTING_STATE, listing.getState());
    listingDetails.put(LISTING_START_MONTH, listing.getStartMonth().getValue());
    listingDetails.put(LISTING_START_YEAR, listing.getStartYear());
    listingDetails.put(LISTING_HOURS_PER_WEEK, listing.getHoursPerWeek());
    listingDetails.put(LISTING_PAY, listing.getPay());
    listingDetails.put(LISTING_IS_REMOTE, listing.getIsRemote());

    ArrayList<String> listingSkills = listing.getSkills();
    JSONArray skills = new JSONArray();
    for (String skill : listingSkills) {
      skills.add(skill);
    }

    listingDetails.put(LISTING_DESIRED_SKILLS, skills);

    ArrayList<String> listingDuties = listing.getDuties();
    JSONArray duties = new JSONArray();
    for (String duty : listingDuties) {
      duties.add(duty);
    }

    listingDetails.put(LISTING_DUTIES, duties);

    ArrayList<Resume> listingApplications = listing.getApplications();
    JSONArray applicationIDs = new JSONArray();

    for (Resume application : listingApplications) {
      applicationIDs.add(application.getUUID().toString());
    }

    listingDetails.put(LISTING_APPLICATIONS, applicationIDs);

    return listingDetails;
  }

  public static void saveResumes() {

  }

}
