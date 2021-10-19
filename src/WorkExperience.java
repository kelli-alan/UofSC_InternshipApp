import java.time.Month;
import java.util.ArrayList;

public class WorkExperience extends Experience {

    private String company;
    private String city;
    private String state;
    private ArrayList<String> responsibilities;
    
    public WorkExperience(String position, Month startMonth, int startYear, String company, String city, String state, ArrayList<String> responsibilities, Month endMonth, int endYear) {
        super(position, startMonth, startYear);
        this.company = company;
        this.city = city;
        this.state = state;
        this.responsibilities = new ArrayList<String>();
        this.responsibilities = responsibilities;
        super.addEndDate(endMonth, endYear);
    }

    public void addResponsibility(String responsibility, WorkExperience workexperience) {

    }

    public boolean contains(String responsibility, WorkExperience workexperience) {
        return false;
    }

    public String toString() {
        return "";
    }
}
