import java.util.ArrayList;
import java.time.Month;
import java.util.UUID;

public class Listing {

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

  public UUID getID() {
    return this.id;
  }

  public String getJobTitle() {
    return this.jobTitle;
  }

  public String getCity() {
    return this.city;
  }

  public String getState() {
    return this.state;
  }

  public Month getStartMonth() {
    return this.startMonth;
  }

  public int getStartYear() {
    return this.startYear;
  }

  public int getHoursPerWeek() {
    return this.hoursPerWeek;
  }

  public Double getPay() {
    return this.pay;
  }

  public boolean getIsRemote() {
    return this.isRemote;
  }

  public ArrayList<String> getSkills() {
    return this.desiredSkills;
  }

  public ArrayList<String> getDuties() {
    return this.duties;
  }

  public ArrayList<Resume> getApplications() {
    return this.applications;
  }

  public String getLocation() {
    return this.city + ", " + this.state;
  }

  public void addSkills(String skill) {
    if (skill != null && !containsSkill(skill))
      this.desiredSkills.add(skill);
  }

  public void addDuties(String duty) {
    if (duty != null && !containsDuties(duty))
      this.duties.add(duty);
  }

  public boolean containsSkill(String skill) {
    for (int i = 0; i < this.desiredSkills.size(); i++) {
      if (this.desiredSkills.get(i).equalsIgnoreCase(skill)) {
        return true;
      }
    }
    return false;
  }

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
    this.applications.add(resume);
  }

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

}
