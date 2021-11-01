import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Information about other experiences such as volunteer work and club activities
 * Section on a student's resume
 * @author Kelli Alan
 */
public class Extracurricular extends Experience {

  private String title;
  private ArrayList<String> activities;

  /**
   * Constructor for an extracurricular
   * @param position name of title (member, President, volunteer, etc.)
   * @param startMonth month extracurricular began
   * @param startYear year extracurricular began
   * @param title club or organization name
   */
  public Extracurricular(String position, Month startMonth, int startYear, String title) {
    super(position, startMonth, startYear);
    this.title = title;
    this.activities = new ArrayList<String>();
  }

  /**
   * Method that adds an extracurricular activity to the ArrayList of activities.
   * @param activity
   */
  public void addExtracurricularActivity(String activity) {
    if (!listed(activity))
      this.activities.add(activity);
  }

  /**
   * Getter method for the title of extracurricular.
   * @return name of club or organization
   */
  public String getTitle() {
    return this.title;
  }


  /**
   * Getter method for ArrayList of activities the student can have.
   * @return list of activities participated in 
   */
  public ArrayList<String> getActivities() {
    return this.activities;
  }

  /**
   * Sets title if given a valid, non-empty string
   * @param title club or organization name
   */
  public void setTitle(String title) {
    if (title != null && title != "") {
      this.title = title;
    }
  }

  /**
   * Sets position name if given a valid, non-empty string
   * @param position at or in extracurricular (member, volunteer, etc.)
   */
  public void setPostition(String position) {
    super.setPosition(position);
  }

  /**
   * Sets start month if given a number 1 through 12
   * @param month numerical representation of month extracurricular started, January = 1
   */
  public void setStartMonth(int month) {
    super.setStartMonth(month);
  }

  /**
   * Sets start year if given a year after 2000. It is assumed anyone using this internship app
   * would not need to add any information that far back
   * @param year experience started
   */
  public void setStartYear(int year) {
    super.setStartYear(year);
  }

  /**
   * Ensures extracurriculars do not have duplicate details
   * 
   * @param activity detail to add under an extracurricular
   * @return true if activity is in list of activities, false otherwise
   */
  public boolean listed(String activity) {
    for (int i = 0; i < activities.size(); i++) {
      if (activities.get(i).equalsIgnoreCase(activity)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Concatenates all extracurricular activities in a list
   * @return String representation of activities in a list, 1 indexed
   */
  public String displayActivities() {
    String ret = "";
    for (int i = 0; i < this.activities.size(); i++) {
      int j = i+1;
      ret += j + ": " + this.activities.get(i) + "\n\n";
    }
    return ret;
  }

  /**
   * Extracurricular format Name of extracurricular position, start date - end
   * date (ongoing ? : in-line if-else) - activity 1 ... - activity n
   */
  public String toString() {
    String ret = "  " + this.title + "\n    " + this.position + ", " + this.startMonth.getDisplayName(TextStyle.FULL, Locale.US)
        + " " + this.startYear + " - "
        + (ongoing ? "present" : this.endMonth.getDisplayName(TextStyle.FULL, Locale.US) + " " + this.endYear) + "\n";

    for (int i = 0; i < activities.size(); i++) {
      ret += "      - " + activities.get(i) + "\n";
    }
    return ret;
  }
}
