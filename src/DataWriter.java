import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

  public static void saveUsers() {
    UserList users = UserList.getInstance();
    ArrayList<Student> studentList = users.getAllStudents();
    JSONArray JSONStudents = new JSONArray();

    for (int i = 0; i < studentList.size(); i++) {
      JSONStudents.add(getStudentJSON(studentList.get(i)));
    }

    try (FileWriter file = new FileWriter(STUDENT_FILE_NAME)) {
      file.write(JSONStudents.toJSONString());
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

  public void saveListings() {

  }

}
