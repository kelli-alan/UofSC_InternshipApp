import java.util.ArrayList;

enum Month {

    January, February, March, April, May, June, July, August,
    September, October, November, December;
}

enum Major {

    COMPUTER_SCIENCE, COMPUTER_INFORMATION_SYSTEMS, 
    COMPUTER_ENGINEERING, INTEGRATED_INFORMATION_TECHNOLOGY;
}

/**
 * 
 * @authors Robbie Clark
 */
public class Resume {
    
    //insert ID
    private String firstName;
    private String lastName;
    private String eMail;
    private String phoneNum;
    private ArrayList<Education> education;
    //insert work experience
    //insert extracurricular

    //add ID
    public Resume(String firstName, String lastName) { 

    }

    public void addWorkExperience(String company, String position, Month startMonth, int startYear, Month endMonth, int endYear) {

    }

    public void addEducation(String university, String city, String state, String degreeType, Major major, String minor, Month gradMonth, int gradYear, double GPA) {

    }

    
    public void addSkill(String skill) {

    }

    public void addEmail(String eMail) {

    }

    public void addPhoneNum(String phoneNum) {

    }

    /*
    public void deleteWorkExperience(WorkExperience workExperience) {

    }
    
    public void deleteExtraCurricular(Extracurricular extracurricular) {

    }

    */

    public void deleteSkill(String skill) {

    }

    /*
    public bool contains(WorkExperience workExperience) {

    }

    public bool contains(Extracurricular extracurricular) {

    }

    */

    public String toString() {
        return "";
    }
}
