import java.time.Month;

public class Education {

    private String university;
    private String city;
    private String state;
    private String degreeType;
    private Major major;
    private String minor;
    private Month gradMonth;
    private int gradYear;
    private double GPA;

    public Education(String university, String city, String state, String degreeType, Major major, Month gradMonth, int gradYear) {
        this.university = university;
        this.city = city;
        this.state = state;
        this.degreeType = degreeType;
        this.major = major;
        this.gradMonth = gradMonth;
        this.gradYear = gradYear;
    }

    public void addGPA(double GPA) {
        if(GPA > 0 && GPA <= 4.0)
            this.GPA = GPA;
    }

    public void addMinor(String minor) {
        this.minor = minor;
    }

    public String toString() {
        return "";
    }

}
