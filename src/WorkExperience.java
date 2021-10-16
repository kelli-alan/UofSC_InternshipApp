import java.time.Month;
import java.util.ArrayList;

public class WorkExperience extends Experience {

    private String company;
    private String city;
    private String state;
    private ArrayList<String> responsibilities;
    
    public WorkExperience(String position, Month startMonth, int startYear, String company, String city, String state) {
        super(position, startMonth, startYear);
    }

    public void addResponsibility(String responsibility, WorkExperience workexperience) {

    }

    public boolean contains(String responsibility, WorkExperience workexperience) {
        return false;
    }
}
