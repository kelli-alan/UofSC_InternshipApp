import java.util.ArrayList;
import java.util.UUID;

public class Listing implements Subject {
    
    private UUID id;
    private String jobTitle;
    private String city;
    private String state;
    private String startDate;
    private int hoursPerWeek;
    private double pay;
    private boolean isFilled;
    private boolean isRemote;
    private ArrayList<String> desiredSkills;
    private ArrayList<String> duties;
    private ArrayList<Resume> applications;
    // add in observer list

    public Listing(UUID id, String jobTitle, String city, String state, String startDate, int hoursPerWeek, double pay, boolean isRemote, 
    ArrayList<String> desiredSkills, ArrayList<String> duties) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.city = city;
        this.state = state;
        this.startDate = startDate;
        this.hoursPerWeek = hoursPerWeek;
        this.pay = pay;
        this.isRemote = isRemote;
        this.desiredSkills = desiredSkills;
        this.duties = duties;
    }

    public void setSkills() {

    }

    public void setDuties() {

    }

    public void updateApplications(Resume resume) {
        
    }

    public String toString() {
        return "";
    }

    public void registerObserver(Observer observer) {

    }

    public void notifyObservers(Resume resume) {

    }

}
