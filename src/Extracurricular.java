import java.time.Month;
import java.util.ArrayList;

public class Extracurricular extends Experience {

    private String title;
    private ArrayList<String> activities;
    
    public Extracurricular(String position, Month startMonth, int startYear, String title, ArrayList<String> activities) {
        super(position, startMonth, startYear);
        this.activities = new ArrayList<String>();
        this.title = title;
        this.activities = activities;
    }

    public Extracurricular(String position, Month startMonth, int startYear, String title, ArrayList<String> activities, Month endMonth, int endYear) {
        super(position, startMonth, startYear);
        this.activities = new ArrayList<String>();
        this.title = title;
        this.activities = activities;
        super.addEndDate(endMonth, endYear);
    }

    public void addExtracurricularActivity(String responsibility, Extracurricular extracurricular) {

    }

    public boolean contains(String responsibility, Extracurricular extracurricular) {
        return false;
    }
}
