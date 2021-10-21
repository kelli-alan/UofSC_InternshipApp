import java.time.Month;
import java.util.ArrayList;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author Kelli Alan
 */
public class WorkExperience extends Experience {

    private String company;
    private String city;
    private String state;
    private ArrayList<String> responsibilities;
    
    public WorkExperience(String position, Month startMonth, int startYear, 
                            String company, String city, String state) {
        super(position, startMonth, startYear);
        this.company = company;
        this.city = city;
        this.state = state;
        this.responsibilities = new ArrayList<String>();
    }

    public WorkExperience(String position, Month startMonth, int startYear,
                           String company, String city, String state, Month endMonth, int endYear) {
        super(position, startMonth, startYear);
        this.company = company;
        this.city = city;
        this.state = state;
        this.responsibilities = new ArrayList<String>();
    }

    public void addResponsibility(String responsibility) {
      if(!listed(responsibility)) {
        this.responsibilities.add(responsibility);
      }
    }
 
    public boolean listed(String responsibility) {
      for (int i = 0; i < this.responsibilities.size(); i++) {
        if (this.responsibilities.get(i).equalsIgnoreCase(responsibility)) {
          return true;
        }
      }
      return false;
    }

    /**
     * Work format
     * company, city, state
     * position, start date - end date  (ongoing ? : in-line if-else)
     *    * responsibility 1
     *         ...
     *    * responsibility n
     */
    public String toString() {
      String ret = this.company + ", " + this.city + ", " + this.state + "\n" + 
      this.position + ", " + this.startMonth.getDisplayName(TextStyle.FULL, Locale.US) + " " + 
      this.startYear + " - " 
      + (ongoing ? "present" : this.endMonth.getDisplayName(TextStyle.FULL, Locale.US) + 
                                " " + this.endYear) + "\n";
      
      for(int i = 0; i < responsibilities.size(); i++) {
        ret += "  * " + responsibilities.get(i) + "\n";
      }
      return ret;
    }
}
