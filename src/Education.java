import java.time.Month;

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
   * @param university
   * @param city
   * @param state
   * @param degreeType
   * @param major
   * @param gradMonth
   * @param gradYear
   */
  public Education(String university, String city, String state, String degreeType, String major, Month gradMonth,
      int gradYear) {
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

  // Getter method for the university the student lists on their resume.
  public String getUniversity() {
    return this.university;
  }

  /**
   * Setter method if the student put a string in for their university.
   * @param university
   */
  public void setUniversity(String university) {
    if (university != "")
        this.university = university;
  }

  // Getter method for the city of their college university.
  public String getCity() {
    return city;
  }

  /**
   * Setter method for the city of their college university.
   * @param city
   */
  public void setCity(String city) {
    if (city != "")
        this.city = city;
  }

  // Getter method for the state of the student's university.
  public String getState() {
    return state;
  }

  // Setter method for the state of the student's university.
  public void setState(String state) {
    if (state != "")
        this.state = state;
  }

  // Getter method for the students degree type (i.e Bachelor's of Science, etc).
  public String getDegreeType() {
    return degreeType;
  }

  // Setter method for the students degree type.
  public void setDegreeType(String degreeType) {
    if (degreeType != "")
        this.degreeType = degreeType;
  }

  // Getter method for the student's college major.
  public String getMajor() {
    return major;
  }

  // Setter method for the student's college major.
  public void setMajor(String major) {
    if (major != "")
        this.major = major;
  }

  // Getter method for the students college minor.
  public String getMinor() {
    return minor;
  }

  // Getter method for the graduation month of the student.
  public Month getGradMonth() {
    return gradMonth;
  }

  // Setter method for the graduation month of the student.
  public void setGradMonth(Month month) {
      this.gradMonth = month;
  }

  // Getter method for the graduation year of the student.
  public int getGradYear() {
    return gradYear;
  }

  /**
   * Getter method for the graduation month of the student.
   * Also checks that the student's graduation year is within
   * the 21st century.
   * @param year
   */
  public void setGradYear(int year) {
    if(year > 2000 && year < 2100)
        this.gradYear = year;
}

  // Getter method for the student's college GPA.
  public double getGPA() {
    return GPA;
  }

  /**
   * Checks that the student's GPA is within 0 and 4.0.
   * @param GPA
   */
  public void addGPA(double GPA) {
    if (GPA > 0 && GPA <= 4.0)
      this.GPA = GPA;
  }

  /**
   * Checks that the student has input a minor into the 
   * Education section of their resume.
   * @param minor
   */
  public void addMinor(String minor) {
    if (minor != "") {
      this.minor = minor;
    }
  }

  //Returns a string with all the Education fields together.
  public String toString() {

    String ret = university + "\n" + city + ", " + state;
    ret += "\n" + degreeType + " in " + major;
    if (minor != "")
      ret += "\nMinor in " + minor;
    if (GPA != 0)
      ret += "\n" +GPA + " GPA";
    ret += "\nGraduation " + gradMonth + " " + gradYear;
    return ret;
  }

}
