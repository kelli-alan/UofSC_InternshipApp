import java.time.Month;
import java.util.ArrayList;

public class Extracurricular extends Experience {

    private String title;
    private ArrayList<String> extracurriculars;
    
    public Extracurricular(String position, Month startMonth, int startYear, String title) {
        super(position, startMonth, startYear);
    }

    public void addExtracurricularActivity(String responsibility, Extracurricular extracurricular) {

    }

    public boolean contains(String responsibility, Extracurricular extracurricular) {
        return false;
    }

    public String toString() {
        return "";
    }
}
