import java.util.ArrayList;
import java.time.Month;
import java.util.UUID;

/**
 * 
 * @authors Robbie Clark, Evan Grunewald, Kelli Alan
 */

public class Listing {

  private static final int HOURS_IN_WEEK = 112;
  private static final int STATE_ABBREVIATION_LENGTH = 2;
  private static final int CURRENT_YEAR = 2021;

  private UUID id;
  private String jobTitle;
  private String city;
  private String state;
  private Month startMonth;
  private int startYear;
  private int hoursPerWeek;
  private double pay;
  private boolean isRemote;
  private ArrayList<String> desiredSkills;
  private ArrayList<String> duties;
  private ArrayList<Resume> applications;

  /**
   * The main constructor for Listing, takes in all needed fields;
   * Used for loading in listing from listing.json
   * 
   * @param id The Listing's unique identifier
   * @param jobTitle The Listing's title
   * @param city The Listing's city
   * @param state The Listing's state
   * @param startMonth The Listing's starting month (enumeration)
   * @param startYear The Listing's starting year
   * @param hoursPerWeek The Listing's hours per week
   * @param pay The Listing's pay
   * @param isRemote The Listing's flag for whether or not it is remote
   */
  public Listing(UUID id, String jobTitle, String city, String state, Month startMonth, int startYear, int hoursPerWeek,
      double pay, boolean isRemote) {
    this.id = id;
    this.jobTitle = jobTitle;
    this.city = city;
    this.state = state;
    this.startMonth = startMonth;
    this.startYear = startYear;
    this.hoursPerWeek = hoursPerWeek;
    this.pay = pay;
    this.isRemote = isRemote;
    this.desiredSkills = new ArrayList<String>();
    this.duties = new ArrayList<String>();
    this.applications = new ArrayList<Resume>();
  }

  /**
   * Default constructor; generates a new UUID and initializes all necessary arrays
   * Used for creating listings in the UI
   */
  public Listing() {
    this.id = UUID.randomUUID();
    this.desiredSkills = new ArrayList<String>();
    this.duties = new ArrayList<String>();
    this.applications = new ArrayList<Resume>();
  }

  /**
   * Return the Listings unique ID for global access
   * 
   * @return the user ID as a UUID
   */
  public UUID getID() {
    return this.id;
  }

  /**
   * Return the Listings job title for global access
   * 
   * @return the job title as a String
   */
  public String getJobTitle() {
    return this.jobTitle;
  }

  /**
   * Return the Listings city for global access
   * 
   * @return the locations city as a String
   */
  public String getCity() {
    return this.city;
  }

  /**
   * Return the Listings state for global access
   * 
   * @return the locations state as a String
   */
  public String getState() {
    return this.state;
  }

  /**
   * Return the Listings start month for global access
   * 
   * @return the starting month as a Month
   */
  public Month getStartMonth() {
    return this.startMonth;
  }

  /**
   * Return the Listings start year for global access
   * 
   * @return the starting year as an int
   */
  public int getStartYear() {
    return this.startYear;
  }

  /**
   * Return the Listings hours per week for global access
   * 
   * @return the hours per week as an int
   */
  public int getHoursPerWeek() {
    return this.hoursPerWeek;
  }

  /**
   * Return the Listings pay value for global access
   * 
   * @return the pay as a double
   */
  public Double getPay() {
    return this.pay;
  }

  /**
   * Return the Listings isRemote flag for global access
   * 
   * @return Boolean for if the Listing is remote work
   */
  public boolean getIsRemote() {
    return this.isRemote;
  }

  /**
   * Return the Listings skills for global access
   * 
   * @return the list of skills
   */
  public ArrayList<String> getSkills() {
    return this.desiredSkills;
  }

  /**
   * Return the Listings duties for global access
   * 
   * @return the list of duties
   */
  public ArrayList<String> getDuties() {
    return this.duties;
  }

  /**
   * Return the Listings applications for global access
   * 
   * @return the list of applications
   */
  public ArrayList<Resume> getApplications() {
    return this.applications;
  }

  /**
   * Return the Listings location for global access
   * 
   * @return the location as a String
   */
  public String getLocation() {
    return this.city + ", " + this.state;
  }

  public void setJobTitle(String title) {
    if (isValidString(title)) {
      this.jobTitle = title;
    }
  }

  public void setCity(String city) {
    if (isValidString(city)) {
      this.city = city;
    }
  }

  public void setState(String state) {
    if (isValidString(state) && state.length() >= STATE_ABBREVIATION_LENGTH) {
      this.state = state;
    }
  }

  public void setStartMonth(int month) {
    if (month > 0 && month < 13) {
      this.startMonth = Month.values()[month-1];
    }
  }

  public void setStartYear(int year) {
    if (year >= CURRENT_YEAR) {
      this.startYear = year;
    }
  }

  public void setHoursPerWeek(int hours) {
    if (hours > 0 && hours <= HOURS_IN_WEEK) {
      this.hoursPerWeek = hours;
    }
  }

  public void setPay(double pay) {
    if (pay >= 0) {
      this.pay = pay;
    }
  }

  public void setRemote(boolean isRemote) {
    this.isRemote = isRemote;
  }

  /**
   * Adds a new skill to the Listings list of skills
   * 
   * @param skill The skill to add to the list
   */
  public void addSkills(String skill) {
    if (skill != null && !containsSkill(skill))
      this.desiredSkills.add(skill);
  }

  /**
   * Adds a new duty to the Listings list of duties
   * 
   * @param duty The duty to add to the list
   */
  public void addDuties(String duty) {
    if (duty != null && !containsDuties(duty))
      this.duties.add(duty);
  }

  /**
   * Check all skills in the Listing to see if any match what is provided
   * 
   * @param skill The name of the skill to look for
   * @return Boolean for if the skill was found
   */
  public boolean containsSkill(String skill) {
    for (int i = 0; i < this.desiredSkills.size(); i++) {
      if (this.desiredSkills.get(i).toLowerCase().contains(skill.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check all duties in the Listing to see if any match what is provided
   * 
   * @param duty The name of the duty to look for
   * @return Boolean for if the duty was found
   */
  private boolean containsDuties(String duty) {
    for (int i = 0; i < this.duties.size(); i++) {
      if (this.duties.get(i).equalsIgnoreCase(duty)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Updates list of applications
   * 
   * @param resume Resume to add to the applications
   */
  public void updateApplications(Resume resume) {
    if (resume != null) {
      this.applications.add(resume);
    }
    
  }

  /**
   * Return a String object of all fields of the Listing put together
   * 
   * @return The completed String of fields
   */
  public String toString() {
    String ret = "";
    ret += this.jobTitle + "\n\t";

    ret += this.city + ", " + this.state + "\n\t";

    ret += "Start Date: " + this.startMonth + " " + this.startYear + "\n\t";

    ret += this.hoursPerWeek + " hours per week\n\t";

    ret += "$" + this.pay + " per hour\n\t";

    if (this.isRemote)
      ret += "Remote Internship\n\t";
    else
      ret += "In-Person Internship\n\t";

    ret += "Desired Skills: \n\t";

    for (int i = 0; i < this.desiredSkills.size(); i++)
      ret += "\t- " + this.desiredSkills.get(i) + "\n\t";

    ret += "Duties: \n\t";

    for (int i = 0; i < this.duties.size(); i++)
      ret += "\t- " + this.duties.get(i) + "\n\t";

    return ret;
  }

  private boolean isValidString(String string) {
    return (!string.equals(null) || !string.equals(""));
  }

}
