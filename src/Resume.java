import java.util.ArrayList;
import java.util.UUID;

/**
 * 
 * @authors Robbie Clark, Evan Grunewald
 */
public class Resume {

  private UUID id;
  private ArrayList<String> skills;
  private ArrayList<Education> education;
  private ArrayList<WorkExperience> workExperiences;
  private ArrayList<Extracurricular> extracurriculars;

  /**
   * Constructor for reading from the database the pre-existing ID's.
   * @param id
   */
  public Resume(UUID id) {
    this.id = id;
    this.skills = new ArrayList<String>();
    this.education = new ArrayList<Education>();
    this.workExperiences = new ArrayList<WorkExperience>();
    this.extracurriculars = new ArrayList<Extracurricular>();
  }

  // Constructor for new resumes that do not have an id yet.
  public Resume() {
    this.id = UUID.randomUUID();
    this.skills = new ArrayList<String>();
    this.education = new ArrayList<Education>();
    this.workExperiences = new ArrayList<WorkExperience>();
    this.extracurriculars = new ArrayList<Extracurricular>();
  }

  // Getter method for the user's unique UUID, id.
  public UUID getUUID() {
    return this.id;
  }

  // Getter method for an ArrayList of skills, skills.
  public ArrayList<String> getSkills() {
    return this.skills;
  }

  // Getter method for an ArrayList containing the student's education.
  public ArrayList<Education> getEducations() {
    return this.education;
  }

  // Getter method for an ArrayList of the students past and current work experiences.
  public ArrayList<WorkExperience> getWorkExperiences() {
    return this.workExperiences;
  }

  // Getter method for an ArrayList of past and present extracirricular activities.
  public ArrayList<Extracurricular> getExtracurriculars() {
    return this.extracurriculars;
  }

  /**
   * Adds a new work experience, workXP to the ArrayList workExperiences.
   * @param workXP
   */
  public void addWorkExperience(WorkExperience workXP) {
    this.workExperiences.add(workXP);
  }

  /**
   * Adds a new extracurricular activity, extrac to the ArrayList extracurriculars.
   * @param extrac
   */
  public void addExtracurricular(Extracurricular extrac) {
    this.extracurriculars.add(extrac);
  }

  /**
   * Adds a new education to the ArrayList of a student's education, education.
   * @param edu
   */
  public void addEducation(Education edu) {
    this.education.add(edu);
  }

  /**
   * Adds a new skill to the ArrayList skills.
   * @param skill
   */
  public void addSkill(String skill) {
    this.skills.add(skill);
  }

  /**
   * Removes a work experience, workExperience, from the ArrayList of the student's
   * previous or current work experiences, workExperiences.
   * @param workExperience
   */
  public void deleteWorkExperience(WorkExperience workExperience) {
    workExperiences.remove(workExperience);
  }

  /**
   * Removes an extracurricular activity, extracirricular, from the ArrayList of the student's
   * extracurricular activites during college, extracurriculars.
   * @param extracurricular
   */
  public void deleteExtraCurricular(Extracurricular extracurricular) {
    extracurriculars.remove(extracurricular);
  }

  /**
   * Removes a skill, skill, from the ArrayList of the student's job skills, skills.
   * @param skill
   */
  public void deleteSkill(String skill) {
    skills.remove(skill);
  }

  /**
   * Method to check if a workExperience exist within the ArrayList of workExperiences.
   * @param workExperience
   * @return true if the workExperiences ArrayList contains an instance of a workExperience.
   */
  public boolean contains(WorkExperience workExperience) {
    return workExperiences.contains(workExperience);
  }

  /**
   * Method to check if an extracurricular exist within the Arraylist of extracurriculars.
   * @param extracurricular
   * @return true if the extracurriculars ArrayList contains an instance of an extracurricular.
   */
  public boolean contains(Extracurricular extracurricular) {
    return extracurriculars.contains(extracurricular);
  }

  /**
   * Concatenates together all of the elements that make up the resume including the student's 
   * education, work experiences, extracurricular activites, and skills they bring to the job.
   */
  public String toString() {
    String ret = "\nEducation\n";
    for (int i = 0; i < education.size(); i++) {
      ret += education.get(i).toString() + "\n\n";
    }

    ret += "Work Experience\n";
    for (int i = 0; i < workExperiences.size(); i++) {
      ret += workExperiences.get(i).toString() + "\n";
    }

    ret += "Extracurricular Activities\n";
    for (int i = 0; i < extracurriculars.size(); i++) {
      ret += extracurriculars.get(i).toString() + "\n";
    }

    ret += "Skills:\n";

    for (int i = 0; i < skills.size(); i++) {
      ret += "  - " + skills.get(i) + "\n";
    }

    ret += "______________________________________________________________________________________________\n";
    return ret;
  }
}
