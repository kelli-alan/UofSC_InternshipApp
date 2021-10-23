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
      JSONArray resumesJSON = (JSONArray)parser.parse(reader);

      for (int i = 0; i < resumesJSON.size(); i++) {
        JSONObject resumeJSON = (JSONObject)resumesJSON.get(i);
        UUID id = UUID.fromString((String)resumeJSON.get(RESUME_ID));
        String eMail = (String)resumeJSON.get(RESUME_EMAIL);
        String phoneNum = (String)resumeJSON.get(RESUME_PHONE_NUM);

        // creates base resume
        resumes.add(new Resume(id, eMail,phoneNum));
        
        // add education section, an array list of educations
        JSONArray educationsJSON = (JSONArray)resumeJSON.get(RESUME_EDUCATION_SECTION);

        for (int j = 0; j < educationsJSON.size(); j++) {
          JSONObject educationJSON = (JSONObject)educationsJSON.get(j);
          String university = (String)educationJSON.get(EDUCATION_UNIVERSITY);
          String city = (String)educationJSON.get(EDUCATION_CITY);
          String state = (String)educationJSON.get(EDUCATION_STATE);
          String degreeType = (String)educationJSON.get(EDUCATION_DEGREE_TYPE);
          String major = (String)educationJSON.get(EDUCATION_MAJOR);
          String minor = (String)educationJSON.get(EDUCATION_MINOR);
          Month gradMonth = Month.values()[((Long)educationJSON.get(EDUCATION_GRAD_MONTH)).intValue()-1];
          int gradYear = ((Long)educationJSON.get(EDUCATION_GRAD_YEAR)).intValue();
          double gpa = ((Double)educationJSON.get(EDUCATION_GPA)).doubleValue();

          
          Education currEd = new Education(university, city, state, degreeType, major, gradMonth, (int)gradYear);
          
          currEd.addMinor(minor);
          currEd.addGPA(gpa);
          
          resumes.get(i).addEducation(currEd);
        }

        // add work experience section, an array list of work experiences
        JSONArray workExperiencesJSON = (JSONArray)resumeJSON.get(RESUME_WORK_EXPERIENCES_SECTION);

        for (int j = 0; j < workExperiencesJSON.size(); j++) {
          JSONObject workExperienceJSON = (JSONObject)workExperiencesJSON.get(j);
          String position = (String)workExperienceJSON.get(EXPERIENCE_POSITION);
          Month startMonth = Month.values()[((Long)workExperienceJSON.get(EXPERIENCE_START_MONTH)).intValue()-1];
          int startYear = ((Long)workExperienceJSON.get(EXPERIENCE_START_YEAR)).intValue();
          boolean ongoing = (Boolean)workExperienceJSON.get(EXPERIENCE_ONGOING);
          String company = (String)workExperienceJSON.get(WORK_EXPERIENCE_COMPANY);
          String city = (String)workExperienceJSON.get(WORK_EXPERIENCE_CITY);
          String state = (String)workExperienceJSON.get(WORK_EXPERIENCE_STATE);


          // create base work experience
          WorkExperience currXP = new WorkExperience(position, startMonth, startYear, company, city, state);
          if (!ongoing) {
            Month endMonth = Month.values()[((Long)workExperienceJSON.get(EXPERIENCE_END_MONTH)).intValue()-1];
            int endYear = ((Long)workExperienceJSON.get(EXPERIENCE_END_YEAR)).intValue();
             
            // add end date, error checking done within the method
            currXP.addEndDate(endMonth, endYear);
          }
          
          // add responsibilities array to work experience
          JSONArray workExperienceResponsibilitiesJSON = (JSONArray)workExperienceJSON.get(WORK_EXPERIENCE_RESPONSIBILITIES);

          for (int k = 0; k < workExperienceResponsibilitiesJSON.size(); k++) {
            currXP.addResponsibility(workExperienceResponsibilitiesJSON.get(k).toString());
          }

          // add work experience to resume
          resumes.get(i).addWorkExperience(currXP);

        }

        JSONArray extracurricularsJSON = (JSONArray)resumeJSON.get(RESUME_EXTRACURRICULARS_SECTION);
        for (int j = 0; j < extracurricularsJSON.size(); j++) {
          JSONObject extracurricularJSON = (JSONObject)extracurricularsJSON.get(j);
          String position = (String)extracurricularJSON.get(EXPERIENCE_POSITION);
          Month startMonth = Month.values()[((Long)extracurricularJSON.get(EXPERIENCE_START_MONTH)).intValue()-1];
          int startYear = ((Long)extracurricularJSON.get(EXPERIENCE_START_YEAR)).intValue();
          boolean ongoing = (Boolean)extracurricularJSON.get(EXPERIENCE_ONGOING);
          String title = (String)extracurricularJSON.get(EXTRACURRICULAR_TITLE);

          // creates base extracurricular
          Extracurricular currEC = new Extracurricular(position, startMonth, startYear, title);

          // add end date to currEC if applicable
          if (!ongoing) {
            Month endMonth = Month.values()[((Long)extracurricularJSON.get(EXPERIENCE_END_MONTH)).intValue()-1];
            int endYear = ((Long)extracurricularJSON.get(EXPERIENCE_END_YEAR)).intValue(); 
            
            currEC.addEndDate(endMonth, endYear);
          }

          // add array of activities
          JSONArray extracurricularActivitiesJSON = (JSONArray)extracurricularJSON.get(EXTRACURRICULAR_ACTIVITIES);

          for (int k = 0; k < extracurricularActivitiesJSON.size(); k++) {
            currEC.addExtracurricularActivity(extracurricularActivitiesJSON.get(k).toString());
          }

          // add to resume
          resumes.get(i).addExtracurricular(currEC);
        }

        JSONArray skillsJSON = (JSONArray)resumeJSON.get(RESUME_SKILLS);

        for (int j = 0; j < skillsJSON.size(); j++) {
          resumes.get(i).addSkill(skillsJSON.get(j).toString());
        }

      }
    }catch (Exception e) {
      e.printStackTrace();
    }
    return resumes;
  }


  public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        
        try {
          FileReader reader = new FileReader(STUDENT_FILE_NAME);
          JSONParser parser = new JSONParser();
          JSONArray studentsJSON = (JSONArray)parser.parse(reader);
          // load all resumes, so they can be added to the correct student
          ArrayList<Resume> studentResumes = loadResumes();

          for (int i = 0; i < studentsJSON.size(); i++) {
            JSONObject studentJSON = (JSONObject)studentsJSON.get(i);
            UUID id = UUID.fromString((String)studentJSON.get(USER_ID));
            String firstName = (String)studentJSON.get(USER_FIRST_NAME);
            String lastName = (String)studentJSON.get(USER_LAST_NAME);
            String username = (String)studentJSON.get(USER_USERNAME);
            String password = (String)studentJSON.get(USER_PASSWORD);
            Users type = Users.STUDENT;
            
            // create a student
            Student currStudent = new Student(id, firstName, lastName, username, password, type);

            // array list of UUIDs corresponding to resumes
            JSONArray studentresumesJSON = (JSONArray)studentJSON.get(STUDENT_RESUMES);

            // find matching resumes and add to current student
            for (int j = 0; j < studentresumesJSON.size(); j++) {
              for (int k = 0; k < studentResumes.size(); k++) {
                 if (studentresumesJSON.get(j).equals((studentResumes.get(k)).getUUID().toString())) {
                      currStudent.addResume(studentResumes.get(k));
                      break;
                 }
              }
            }

            students.add(currStudent);
          
          
          }
        }
         catch (Exception e) {
          e.printStackTrace();
    }  
      return students;
    }

    public ArrayList<Listing> loadListings() {
      ArrayList<Listing> listings = new ArrayList<Listing>();
      try {
        FileReader reader = new FileReader(LISTING_FILE_NAME);
        JSONParser parser = new JSONParser();
        JSONArray listingsJSON = (JSONArray)parser.parse(reader);
  
        for (int i = 0; i < listingsJSON.size(); i++) {
          JSONObject listingJSON = (JSONObject)listingsJSON.get(i);
          UUID id = UUID.fromString((String)listingJSON.get(LISTING_ID));
          String jobTitle = (String)listingJSON.get(LISTING_TITLE);
          String city = (String)listingJSON.get(LISTING_CITY);
          String state = (String)listingJSON.get(LISTING_STATE);
          String startDate = (String)listingJSON.get(LISTING_START_DATE);
          int hours = (int)listingJSON.get(LISTING_HOURS_PER_WEEK);
          double pay = (double)listingJSON.get(LISTING_PAY);
          Boolean isRemote = (Boolean)listingJSON.get(LISTING_IS_REMOTE);
  
          // creates base listing
          Listing currListing = new Listing(id, jobTitle, city, state, startDate, hours, pay, isRemote);
          listings.add(new Listing(id, jobTitle, city, state, startDate, hours, pay, isRemote));
          
          // add list of skills
          JSONArray skillsJSON = (JSONArray)listingJSON.get(LISTING_DESIRED_SKILLS);
          for (int j = 0; j < skillsJSON.size(); j++) {
            currListing.addSkills(skillsJSON.get(j).toString());
          }

          // add list of duties
          JSONArray dutiesJSON = (JSONArray)listingJSON.get(LISTING_DUTIES);
          for (int j = 0; j < dutiesJSON.size(); j++) {
            currListing.addDuties(dutiesJSON.get(j).toString());
          }

          // add list of applications(resumes from resume id)
          JSONArray appsJSON = (JSONArray)listingJSON.get(LISTING_APPLICATIONS);
          for (int j = 0; j < appsJSON.size(); j++) {
            currListing.updateApplications((Resume)appsJSON.get(j));
          }

          // add list of applications(resumes from resume id)
          JSONArray observerJSON = (JSONArray)listingJSON.get(LISTING_OBSERVERS);
          for (int j = 0; j < observerJSON.size(); j++) {
            currListing.registerObserver((Observer)observerJSON.get(j));
          }
  
        }
      }catch (Exception e) {
        e.printStackTrace();
      }
      return listings;
    }

}