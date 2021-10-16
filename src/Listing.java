import java.util.ArrayList;

public class Listing implements Subject {
    
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

    public Listing(String jobTitle, String city, String state, String startDate, int hoursPerWeek, double pay, boolean isRemote) {

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
