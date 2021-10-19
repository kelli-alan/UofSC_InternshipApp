import java.util.ArrayList;
import java.util.UUID;

import java.time.Month;

enum Major {

    COMPUTER_SCIENCE, COMPUTER_INFORMATION_SYSTEMS, 
    COMPUTER_ENGINEERING, INTEGRATED_INFORMATION_TECHNOLOGY;
}

/**
 * 
 * @authors Robbie Clark, Evan Grunewald
 */
public class Resume {
    
    private UUID id;
    private String firstName;
    private String lastName;
    private String eMail;
    private String phoneNum;
    private ArrayList<String> skills;
    private ArrayList<Education> education;
    private ArrayList<WorkExperience> workExperiences;
    private ArrayList<Extracurricular> extracurriculars;

    public Resume(UUID id, String firstName, String lastName) { 
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.skills = new ArrayList<String>();
        this.workExperiences = new ArrayList<WorkExperience>();
        this.education = new ArrayList<Education>();
        this.extracurriculars = new ArrayList<Extracurricular>();
    }

    public void addWorkExperience(String company, String position, Month startMonth, int startYear, Month endMonth, int endYear) {
        // City and State are missing here?
        this.workExperiences.add(new WorkExperience(position, startMonth, startYear, company, "city", "state"));
    }

    public void addEducation(String university, String city, String state, String degreeType, Major major, String minor, Month gradMonth, int gradYear, double GPA) {
        Education temp = new Education(university, city, state, degreeType, major, gradMonth, gradYear);
        temp.addGPA(GPA);
        temp.addMinor(minor);
        this.education.add(temp);
    }
    
    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public void addEmail(String eMail) {
        this.eMail = eMail;
    }

    public void addPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    
    public void deleteWorkExperience(WorkExperience workExperience) {
        workExperiences.remove(workExperience);
    }
    
    public void deleteExtraCurricular(Extracurricular extracurricular) {
        extracurriculars.remove(extracurricular);
    }

    public void deleteSkill(String skill) {
        skills.remove(skill);
    }

    public boolean contains(WorkExperience workExperience) {
        return workExperiences.contains(workExperience);
    }

    public boolean contains(Extracurricular extracurricular) {
        return extracurriculars.contains(extracurricular);
    }

    public String toString() {
        String ret = "Resume of\n" + lastName + ", " + firstName + "\n"
        + "E-mail: " + eMail + "\n"
        + "Phone Number: " + phoneNum + "\n"
        + "Skills:\n";

        for(int i = 0; i < skills.size(); i++) {
            ret += skills.get(i) + "\n";
        }

        // This is assuming workexperience and extracurricular and education get a ToString at some point
        ret += "Education:\n";
        for(int i = 0; i < education.size(); i++) {
            ret += education.get(i) + "\n";
        }
    
        ret += "Work Experiences:\n";
        for(int i = 0; i < workExperiences.size(); i++) {
            ret += workExperiences.get(i) + "\n";
        }

        ret += "Extracurriculars:\n";
        for(int i = 0; i < extracurriculars.size(); i++) {
            ret += extracurriculars.get(i) + "\n";
        }

        return ret;
    }
}
