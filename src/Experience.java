import java.time.Month;

/**
 * The Experience class entails an experience that is not a work experience that
 * a student may want to display on their reusume.
 * @authors Evan Grunewald, Kelli Alan
 */
public abstract class Experience {
  protected String position;
  protected Month startMonth;
  protected int startYear;
  protected Month endMonth;
  protected int endYear;
  protected boolean ongoing;

  /**
   * Constructor method for the Experience class for its variables such as position.
   * @param position
   * @param startMonth
   * @param startYear
   */
  public Experience(String position, Month startMonth, int startYear) {
    this.position = position;
    this.startMonth = startMonth;
    this.startYear = startYear;
    this.ongoing = true;
  }

  /**
   * Constructor method for adding the end date of the experience.
   * @param endMonth
   * @param endYear
   */
  public void addEndDate(Month endMonth, int endYear) {
    if (endMonth != null && endYear >= 2000) {
      this.endMonth = endMonth;
      this.endYear = endYear;
      this.ongoing = false;
    }
  }

  // Getter method for the position at their experience.
  public String getPostion() {
    return this.position;
  }

  // Getter method for the starting month at the experience.
  public Month getStartMonth() {
    return this.startMonth;
  }

  // Getter method for the starting year at the experience.
  public int getStartYear() {
    return this.startYear;
  }

  public boolean getOngoing() {
    return this.ongoing;
  }

  // Getter method for the ending month of the experience.
  public Month getEndMonth() {
    return this.endMonth;
  }

  // Getter method for the ending year of the experience.
  public int getEndYear() {
    return this.endYear;
  }
}
