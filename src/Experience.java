import java.time.Month;

public abstract class Experience {
    protected String position;
    protected Month startMonth;
    protected int startYear;
    protected Month endMonth;
    protected int endYear;
    protected boolean ongoing;

    public Experience(String position, Month startMonth, int startYear) {
      this.position = position;
      this.startMonth = startMonth;
      this.startYear = startYear;
      this.ongoing = true;
    }
    public void addEndDate(Month endMonth, int endYear) {
      this.endMonth = endMonth;
      this.endYear = endYear;
      this.ongoing = false;
    }
}
