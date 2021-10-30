import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author Kelli Alan
 */
public class Extracurricular extends Experience {

  private String title;
  private ArrayList<String> activities;

  public Extracurricular(String position, Month startMonth, int startYear, String title) {
    super(position, startMonth, startYear);
    this.title = title;
    this.activities = new ArrayList<String>();
  }

  public void addExtracurricularActivity(String activity) {
    if (!listed(activity))
      this.activities.add(activity);
  }

  public String getTitle() {
    return this.title;
  }

  public ArrayList<String> getActivities() {
    return this.activities;
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
