import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import java.time.Month;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

  public static ArrayList<Resume> loadResumes() {
    ArrayList<Resume> resumes = new ArrayList<Resume>();
    try {
      FileReader reader = new FileReader(RESUME_FILE_NAME);
      JSONParser parser = new JSONParser();
      JSONArray resumesJSON = (JSONArray)new JSONParser().parse(reader);

      for (int i=0; i < resumesJSON.size(); i++) {
        JSONObject resumeJSON = (JSONObject)resumesJSON.get(i);
        UUID id = UUID.fromString((String)resumeJSON.get(RESUME_ID));
        String eMail = (String)resumeJSON.get(RESUME_EMAIL);
        String phoneNum = (String)resumeJSON.get(RESUME_PHONE_NUM);

        // creates base resume
        resumes.add(new Resume(id, eMail,phoneNum));
        
        // add education section = array list of educations
        JSONArray educationsJSON = (JSONArray)resumeJSON.get(RESUME_EDUCATION_SECTION);

        for (int j=0; j < educationsJSON.size(); j++) {
          JSONObject educationJSON = (JSONObject)educationsJSON.get(j);
          String university = (String)educationJSON.get(EDUCATION_UNIVERSITY);
          String city = (String)educationJSON.get(EDUCATION_CITY);
          String state = (String)educationJSON.get(EDUCATION_STATE);
          String degreeType = (String)educationJSON.get(EDUCATION_DEGREE_TYPE);
          String major = (String)educationJSON.get(EDUCATION_MAJOR);
          String minor = (String)educationJSON.get(EDUCATION_MINOR);
          long gradMonth = (Long)educationJSON.get(EDUCATION_GRAD_MONTH);
          long gradYear = (Long)educationJSON.get(EDUCATION_GRAD_YEAR);
          double gpa = (Double)educationJSON.get(EDUCATION_GPA);

          Month month = Month.values()[(int)gradMonth-1];
          
          resumes.get(i).addEducation(new Education(university, city, state, degreeType, major, month, (int)gradYear));
        }
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
    return resumes;
  }


  public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
        } catch (Exception e) {
          e.printStackTrace();
    }  
      return null;
    }



    public ArrayList<Listing> getListings() {
        return null;
    }

}