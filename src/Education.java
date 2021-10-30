import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

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

  public String getUniversity() {
    return this.university;
  }

  public void setUniversity(String university) {
    if (university != "")
        this.university = university;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    if (city != "")
        this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    if (state != "")
        this.state = state;
  }

  public String getDegreeType() {
    return degreeType;
  }

  public void setDegreeType(String degreeType) {
    if (degreeType != "")
        this.degreeType = degreeType;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    if (major != "")
        this.major = major;
  }

  public String getMinor() {
    return minor;
  }

  public Month getGradMonth() {
    return gradMonth;
  }

  public void setGradMonth(Month month) {
      this.gradMonth = month;
  }

  public int getGradYear() {
    return gradYear;
  }

  public void setGradYear(int year) {
    if(year > 2000 && year < 2100)
        this.gradYear = year;
}

  public double getGPA() {
    return GPA;
  }

  public void addGPA(double GPA) {
    if (GPA > 0 && GPA <= 4.0)
      this.GPA = GPA;
  }

  public void addMinor(String minor) {
    if (minor != "") {
      this.minor = minor;
    }
  }

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
