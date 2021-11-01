import java.time.Month;

/**
 * The Experience class holds base details for work experiences and extracurricular activities, 
 * which are separate sections displayed on a student's resume
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
   * @param position member, president, etc.
   * @param startMonth month experience began
   * @param startYear year experience began
   */
  public Experience(String position, Month startMonth, int startYear) {
    this.position = position;
    this.startMonth = startMonth;
    this.startYear = startYear;
    this.ongoing = true;
  }

  /**
   * Constructor method for adding the end date of the experience.
   * @param endMonth month experience ended
   * @param endYear year experience ended
   */
  public void addEndDate(Month endMonth, int endYear) {
    if (endMonth != null && endYear >= 2000) {
      this.endMonth = endMonth;
      this.endYear = endYear;
      this.ongoing = false;
    }
  }

  /** 
   * Getter method for the position at their experience.
   * @return position name
  */
  public String getPostion() {
    return this.position;
  }

  /**
   * Getter method for the starting month at the experience.
   * @return month experience started
   */
  public Month getStartMonth() {
    return this.startMonth;
  }

  /**
   * Getter method for the starting year at the experience.
   * @return year experience started
   */
  public int getStartYear() {
    return this.startYear;
  }

  /**
   * Getter method to determine if the experience has ended
   * @return true if experience is ongoing; false if experience ended
   */
  public boolean getOngoing() {
    return this.ongoing;
  }

  /**
   * Getter method for the ending month of the experience.
   * @return month experience ended
   */
  public Month getEndMonth() {
    return this.endMonth;
  }

  /**
   * Getter method for the ending year of the experience.
   * @return year experience ended
   */
  public int getEndYear() {
    return this.endYear;
  }

  /**
   * Sets experience position if a valid String is provided
   * @param position name
   */
  public void setPosition(String position) {
    if (position != null && position != "") {
      this.position = position;
    }
  }

  /**
   * Sets month experience started, if a valid month (1 through 12), is given
   * @param month numerical representation of a typical calendar month (January = 1)
   */
  public void setStartMonth(int month) {
    if (month > 0 && month < 13) {
      this.startMonth = Month.values()[month-1];
    }
  }

  /**
   * Sets year experience started 
   * @param year experience started
   */
  public void setStartYear(int year) {
    if (year >= 2000) {
      this.startYear = year;
    }
  }



}
