import java.time.Month;
import java.util.ArrayList;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * A class for a student's prior work experience
 * @author Kelli Alan
 */
public class WorkExperience extends Experience {
  private String company;
  private String city;
  private String state;
  private ArrayList<String> responsibilities;

  /**
   * Creates a work experience with given properties and initializes responsibilities
   * @param position job title
   * @param startMonth the month the work experience started
   * @param startYear  the year the work experience started
   * @param company the name of the company
   * @param city the city the company is located in
   * @param state the state the company is located in
   */
  public WorkExperience(String position, Month startMonth, int startYear, String company, String city, String state) {
    super(position, startMonth, startYear);
    this.company = company;
    this.city = city;
    this.state = state;
    this.responsibilities = new ArrayList<String>();
  }

  /**
   * Adds a job duty to an ArrayList if the specified duty is not already on the list
   * @param responsibility task performed at job
   */
  public void addResponsibility(String responsibility) {
    if (!listed(responsibility)) {
      this.responsibilities.add(responsibility);
    }
  }

  /**
   * Retrieves company name of a work experience 
   * @return company name
   */
  public String getCompany() {
    return this.company;
  }

  /**
   * Retrieves city associated with a work experience
   * @return city name
   */
  public String getCity() {
    return this.city;
  }

  /**
   * Retrieves state associated with a work experience
   * @return state name
   */
  public String getState() {
    return this.state;
  }

  /**
   * Retrieves an ArrayList of responsibilities the student has/had 
   * @return list of responsibilities
   */
  public ArrayList<String> getResponsibilities() {
    return this.responsibilities;
  }

  /**
   * Checks if given responsibility is already on the list of responsibilities held by the work 
   * experience
   * @param responsibility a job duty
   * @return true if given responsibility is on already on the list, case unsensitive; false
   * otherwise
   */
  public boolean listed(String responsibility) {
    for (int i = 0; i < this.responsibilities.size(); i++) {
      if (this.responsibilities.get(i).equalsIgnoreCase(responsibility)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Creates a String representation of a work experience
   * Format:
   * company, city, state
   * position, start date - end date (ongoing ? : in-line if-else) 
   * - responsibility 1
   *  ... 
   * - responsibility n
   * @return String representation of above format
   */
  public String toString() {
    String ret = "  " + this.company + ", " + this.city + ", " + this.state + "\n    " + this.position + ", "
        + this.startMonth.getDisplayName(TextStyle.FULL, Locale.US) + " " + this.startYear + " - "
        + (ongoing ? "present" : this.endMonth.getDisplayName(TextStyle.FULL, Locale.US) + " " + this.endYear) + "\n";

    for (int i = 0; i < responsibilities.size(); i++) {
      ret += "      - " + responsibilities.get(i) + "\n";
    }
    return ret;
  }
}
