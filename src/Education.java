import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author Evan Grunewald
 * Element of Resume class for a students education(s)
 */
public class Education {

  private String university;
  private String city;
  private String state;
  private String degreeType;
  private String major;
  private String minor;
  private Month gradMonth;
  private int gradYear;
  private double GPA;

  /**
   * Constructor for the Education class setting all parameters relating to their 
   * educational background such as their major, the university, etc.
   * @param university school name
   * @param city university is located
   * @param state university is located
   * @param degreeType Bachelor's of Science, Associate's, etc.
   * @param major field of study
   * @param gradMonth month when student graduated or anticipates graduating
   * @param gradYear year when student graduated or anticipates graduating
   */
  public Education(String university, String city, String state, String degreeType, String major, 
                      Month gradMonth, int gradYear) {
    this.university = university;
    this.city = city;
    this.state = state;
    this.degreeType = degreeType;
    this.major = major;
    this.gradMonth = gradMonth;
    this.gradYear = gradYear;
    this.minor = "";
    this.GPA = 0.0;
  }

  /**
   * Getter method for the university the student lists on their resume.
   * @return university name  
   */ 
  public String getUniversity() {
    return this.university;
  }

  /**
   * Setter method if the student put a string in for their university.
   * @param university school name provided
   */
  public void setUniversity(String university) {
    if (university != "")
        this.university = university;
  }

  /**
   * Getter method for the city of their college/university.
   * @return city name
   */
  public String getCity() {
    return city;
  }

  /**
   * Setter method for the city of their college university.
   * @param city name provided
   */
  public void setCity(String city) {
    if (city != "")
        this.city = city;
  }

  /**
   * Getter method for the state of the student's university.
   * @return state name
   */
  public String getState() {
    return state;
  }

  /**
   * Setter method for the state of the student's university.
   * @param state name provided
   */
  public void setState(String state) {
    if (state != "")
        this.state = state;
  }

  /**
   * Getter method for the students degree type (i.e Bachelor's of Science, etc).
   * @return degree title 
   */
  public String getDegreeType() {
    return degreeType;
  }

  /**
   * Setter method for the students degree type. Checks that if the string degreeType
   * is not empty, it will set the degreeType.
   * @param degreeType provided
   */
  public void setDegreeType(String degreeType) {
    if (degreeType != "")
        this.degreeType = degreeType;
  }

  /**
   * Getter method for the student's college major.
   * @return major name
   */
  public String getMajor() {
    return major;
  }

  /**
   * Setter method for the student's college major. Checks that if the string major
   * is not empty, it will set the major.
   * @param major field of study
   */
  public void setMajor(String major) {
    if (major != "")
        this.major = major;
  }

  /**
   * Getter method for the students college minor.
   * @return minor
   */
  public String getMinor() {
    return minor;
  }


  /**
   * Getter method for the graduation month of the student.
   * @return previous or anticipated graduation month
   */
  public Month getGradMonth() {
    return gradMonth;
  }

  /**
   * Setter method for the graduation month of the student.
   * @param month of graduation 
   */ 
  public void setGradMonth(Month month) {
      this.gradMonth = month;
  }

  /**
   *  Getter method for the graduation year of the student.
   *  @return graduation year
   */ 
  public int getGradYear() {
    return gradYear;
  }

  /**
   * Getter method for the graduation month of the student.
   * Also checks that the student's graduation year is within
   * the 21st century as it is unlikely a current college student
   * has already graduated a college before 2000.
   * @param year of graduation
   */
  public void setGradYear(int year) {
    if(year > 2000 && year < 2100)
        this.gradYear = year;
}

  /**
   * Getter method for the student's college GPA.
   * @return grade point average
   */
  public double getGPA() {
    return GPA;
  }

  /**
   * Checks that the student's GPA is within 0 and 4.0.
   * @param GPA of student
   */
  public void addGPA(double GPA) {
    if (GPA > 0 && GPA <= 4.0)
      this.GPA = GPA;
  }

  /**
   * Checks that the student has input a minor into the 
   * Education section of their resume.
   * @param minor field of study
   */
  public void addMinor(String minor) {
    if (minor != "") {
      this.minor = minor;
    }
  }

  /**
   * Concatenates all Education fields together
   * @return String representation of an educational experience
   */
  public String toString() {

    String ret = "  " + university + ", " + gradMonth.getDisplayName(TextStyle.FULL, Locale.US) + " " + gradYear +"\n    " + city + ", " + state;
    ret += "\n    " + degreeType + " in " + major;
    if (minor.length() > 1)
      ret += "\n    Minor in " + minor;
    if (GPA != 0)
      ret += "\n    " +GPA + " GPA";
    return ret;
  }

}
