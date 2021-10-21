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
        this.GPA = 0;
    }

    public void addGPA(double GPA) {
        if(GPA > 0 && GPA <= 4.0)
            this.GPA = GPA;
    }

    public void addMinor(String minor) {
        this.minor = minor;
    }

    public String toString() {
        
        String ret = this.university + "\n" + this.city + ", " + this.state;
        ret += "\n" + this.degreeType + " in " + this.major;
        if(this.minor != null)
            ret += "\nMinor in" + this.minor; 
        if(this.GPA != 0)
            ret += "\n" + this.GPA + " GPA";
        ret += "\nGraduation " + this.gradMonth + " " + this.gradYear;        
        return ret;
    }

}
