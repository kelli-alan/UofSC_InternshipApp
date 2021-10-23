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
    private ArrayList<Observer> observers;

    public Listing(UUID id, String jobTitle, String city, String state, String startDate, int hoursPerWeek, double pay, boolean isRemote) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.city = city;
        this.state = state;
        this.startDate = startDate;
        this.hoursPerWeek = hoursPerWeek;
        this.pay = pay;
        this.isRemote = isRemote;
        this.desiredSkills = new ArrayList<String>();
        this.duties = new ArrayList<String>();
        this.applications = new ArrayList<Resume>();
        this.observers = new ArrayList<Observer>();
    }

    public void addSkills(String skill) {
        if(skill != null && !containsSkill(skill))
            this.desiredSkills.add(skill);
    }

    public void addDuties(String duty) {
        if(duty != null && !containsDuties(duty))
            this.duties.add(duty);
    }

    public boolean containsSkill(String skill) {
        for (int i = 0; i < this.desiredSkills.size(); i++) {
            if (this.desiredSkills.get(i).equalsIgnoreCase(skill)) {
              return true;
            }
          }
          return false;
    }

    private boolean containsDuties(String duty) {
        for (int i = 0; i < this.duties.size(); i++) {
            if (this.duties.get(i).equalsIgnoreCase(duty)) {
              return true;
            }
          }
          return false;
    }

    /**
     * Updates list of applications
     * @param resume Resume to add to the applications
     */
    public void updateApplications(Resume resume) {
        this.applications.add(resume);
        notifyObservers(resume);
    }

    public String toString() {
       String ret = "";
        ret += this.jobTitle + "\n";

        ret += this.city + ", " + this.state + "\n";

        ret += "Start Date: " + this.startDate;

        ret += this.hoursPerWeek + " hours per week\n";

        ret += "$" + this.pay + " per hour\n";

        if(this.isRemote)
            ret += "Remote Internship\n";
        else
            ret += "In-Person Internship\n";

        ret += "Desired Skills: \n";

        for(int i = 0; i < this.desiredSkills.size(); i++)
            ret += "\t- " + this.desiredSkills.get(i);

        ret += "Duties: \n";

        for(int i = 0; i < this.duties.size(); i++)
            ret += "\t- " + this.duties.get(i);
       
        return ret;
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void notifyObservers(Resume resume) {
        for(Observer observers : this.observers)
            observers.update(resume);
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public ArrayList<String> getSkill() {
        return desiredSkills;
    }

    public String getLocation() {
        return state;
    }

    public Double getPay() {
        return pay;
    }

}
