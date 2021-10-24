public abstract class DataConstants {

  // constants for all users
  protected static final String USER_ID = "id";
  protected static final String USER_FIRST_NAME = "firstName";
  protected static final String USER_LAST_NAME = "lastName";
  protected static final String USER_USERNAME = "username";
  protected static final String USER_PASSWORD = "password";
  protected static final String USER_TYPE = "type";
  
  // student specific constants
  protected static final String STUDENT_FILE_NAME = "src/student.json";
  protected static final String STUDENT_RESUMES = "resumes";
  protected static final String STUDENT_SAVED_LISTINGS = "savedListings";

  // employer specific constants
  protected static final String EMPLOYER_FILE_NAME = "src/employer.json";
  protected static final String EMPLOYER_COMPANY_NAME = "companyName";
  protected static final String EMPLOYER_COMPANY_DESCRIPTION = "companyDescription";
  protected static final String EMPLOYER_INTERNSHIP_LISTINGS = "internshipListings";

  // moderator specific constants
  protected static final String MODERATOR_FILE_NAME = "src/moderator.json";


  // listing constants
  protected static final String LISTING_FILE_NAME = "src/listing.json";
  protected static final String LISTING_ID = "id";
  protected static final String LISTING_TITLE = "jobTitle";
  protected static final String LISTING_CITY = "city";
  protected static final String LISTING_STATE = "state";
  protected static final String LISTING_START_MONTH = "startMonth";
  protected static final String LISTING_START_YEAR = "startYear";
  protected static final String LISTING_START_DATE = "startDate";
  protected static final String LISTING_HOURS_PER_WEEK = "hoursPerWeek";
  protected static final String LISTING_PAY = "pay";
  protected static final String LISTING_IS_FILLED = "isFilled";
  protected static final String LISTING_IS_REMOTE = "isRemote";
  protected static final String LISTING_DESIRED_SKILLS = "desiredSkills";
  protected static final String LISTING_DUTIES = "duties";
  protected static final String LISTING_APPLICATIONS = "applications";
  protected static final String LISTING_OBSERVERS = "observers";


 // resume constants
 protected static final String RESUME_FILE_NAME = "src/resume.json";
 protected static final String RESUME_ID  = "id";
 protected static final String RESUME_EMAIL = "eMail";
 protected static final String RESUME_PHONE_NUM = "phoneNum";

 protected static final String RESUME_EDUCATION_SECTION = "education";
 protected static final String EDUCATION_UNIVERSITY = "university";
 protected static final String EDUCATION_CITY  = "city";
 protected static final String EDUCATION_STATE = "state";
 protected static final String EDUCATION_DEGREE_TYPE = "degreeType";
 protected static final String EDUCATION_MAJOR = "major";
 protected static final String EDUCATION_MINOR = "minor";
 protected static final String EDUCATION_GRAD_MONTH = "gradMonth";
 protected static final String EDUCATION_GRAD_YEAR = "gradYear";
 protected static final String EDUCATION_GPA = "gpa";

 // experience constants for work experiences and extracurriculars
 protected static final String EXPERIENCE_POSITION = "position";
 protected static final String EXPERIENCE_START_MONTH = "startMonth";
 protected static final String EXPERIENCE_START_YEAR = "startYear";
 protected static final String EXPERIENCE_END_MONTH = "endMonth";
 protected static final String EXPERIENCE_END_YEAR = "endYear";
 protected static final String EXPERIENCE_ONGOING = "ongoing";

 // work experience specific constants
 protected static final String RESUME_WORK_EXPERIENCES_SECTION = "workExperiences";
 protected static final String WORK_EXPERIENCE_COMPANY = "company";
 protected static final String WORK_EXPERIENCE_CITY = "city";
 protected static final String WORK_EXPERIENCE_STATE = "state";
 protected static final String WORK_EXPERIENCE_RESPONSIBILITIES = "responsibilities";

 // extracurricular specific constants
 protected static final String RESUME_EXTRACURRICULARS_SECTION = "extracurriculars";
 protected static final String EXTRACURRICULAR_TITLE = "title";
 protected static final String EXTRACURRICULAR_ACTIVITIES = "activities";

 protected static final String RESUME_SKILLS  = "skills";

 // user types
 protected static final String USER_TYPE_STUDENT = "Student";
 protected static final String USER_TYPE_EMPLOYER = "Employer";
 protected static final String USER_TYPE_MODERATOR = "Moderator";
}
