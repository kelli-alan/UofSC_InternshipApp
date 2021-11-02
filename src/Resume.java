import java.util.ArrayList;
import java.util.UUID;

/**
 * A student's resume and all its fields
 * @authors Robbie Clark, Evan Grunewald
 */
public class Resume {

  private UUID id;
  private ArrayList<Education> educations;
  private ArrayList<WorkExperience> workExperiences;
  private ArrayList<Extracurricular> extracurriculars;
  private ArrayList<String> skills;

  /**
   * Constructor for reading a resume from the database using pre-existing ID's.
   * @param id of resume from database
   */
  public Resume(UUID id) {
    this.id = id;
    this.skills = new ArrayList<String>();
    this.educations = new ArrayList<Education>();
    this.workExperiences = new ArrayList<WorkExperience>();
    this.extracurriculars = new ArrayList<Extracurricular>();
  }

  /**
   * Constructor for creating new resumes from the student UI
   */
  public Resume() {
    this.id = UUID.randomUUID();
    this.skills = new ArrayList<String>();
    this.educations = new ArrayList<Education>();
    this.workExperiences = new ArrayList<WorkExperience>();
    this.extracurriculars = new ArrayList<Extracurricular>();
  }

  /**
   * Getter method for the resume's unique UUID, id.
   * @return UUID associated with the resume
   */
  public UUID getUUID() {
    return this.id;
  }

  /**
   * Getter method for the ArrayList of skills on the resume
   * @return ArrayList of skills
   */
  public ArrayList<String> getSkills() {
    return this.skills;
  }

  /**
   * Getter method for an ArrayList containing the student's education.
   * @return ArrayList of all educations on the resume
   */
  public ArrayList<Education> getEducations() {
    return this.educations;
  }

  /**
   * Getter method for an ArrayList of the students past and current work experiences.
   * @return ArrayList of all work experiences on the resume
   */
  public ArrayList<WorkExperience> getWorkExperiences() {
    return this.workExperiences;
  }

  /**
   * Getter method for an ArrayList of past and present extracurricular activities.
   * @return ArrayList of all extracurriculars on the resume
   */
  public ArrayList<Extracurricular> getExtracurriculars() {
    return this.extracurriculars;
  }

  /**
   * Adds a new work experience to the ArrayList workExperiences, if it is not 
   * already on the resume.
   * @param workExperience a work experience to add to the resume
   */
  public void addWorkExperience(WorkExperience workExperience) {
    if (!containsWorkExperience(workExperience))
      this.workExperiences.add(workExperience);
  }

  /**
   * Adds a new extracurricular activity to the ArrayList extracurriculars, if it is not
   * already on the resume
   * @param extracurricular an extracurriuclar experience to add to the resume
   */
  public void addExtracurricular(Extracurricular extracurricular) {
    if (!containsExtracurricular(extracurricular))
      this.extracurriculars.add(extracurricular);
  }

  /**
   * Adds a new education to the ArrayList of a student's education section, 
   * if it is not already on their resume
   * @param education to add to the resume
   */
  public void addEducation(Education education) {
    if(!containsEducation(education))
      this.educations.add(education);
  }

  /**
   * Adds a new skill to the ArrayList skills, if skill is not already on the resume.
   * @param skill to add to the resume
   */
  public void addSkill(String skill) {
    if (!containsSkill(skill))
      this.skills.add(skill);
  }

  /**
   * Removes the education entry at the specified index from the ArrayList of educations 
   * @param index of education to remove
   */
  public void deleteEducation(int index) {
    educations.remove(index);
  }

  /**
   * Removes the work experience entry at the specified index from the ArrayList of work experiences
   * @param index of work experience to remove
   */
  public void deleteWorkExperience(int index) {
    workExperiences.remove(index);
  }

  /**
   * Removes the extracurricular entry at the specified index from the ArrayList of extracurriculars
   * @param index of extracurricular to remove
   */
  public void deleteExtraCurricular(int index) {
    extracurriculars.remove(index);
  }

  /**
   * Removes a skill at the specified index from the ArrayList of skills
   * @param index of skill to remove
   */
  public void deleteSkill(int index) {
    skills.remove(index);
  }

  /**
   * Helper method to check if a workExperience exists within the ArrayList of workExperiences.
   * A work experience with the same position title and company is considered a duplicate. All 
   * fields of an existing work experience can be edited from the UI 
   * @param workExperience student is attempting to add or remove from their resume
   * @return true if the work experience is already on the resume, false if it is not
   */
  private boolean containsWorkExperience(WorkExperience workExperience) {
    for (int i = 0; i < this.workExperiences.size(); i++) {
      if (this.workExperiences.get(i).getCompany().equalsIgnoreCase(workExperience.getCompany()) 
          && this.workExperiences.get(i).getPostion().equalsIgnoreCase(workExperience.getPostion()))
            return true;
    }
    return false;
  }

  /**
   * Helper method to check if an extracurricular exists within the ArrayList of extracurriculars.
   * An extracurricular with a matching organization title is considered a duplicate. All fields 
   * of an existing extracurricular can be edited from the UI
   * @param extracurricular student is attempting to add or remove from their resume
   * @return true if the extracurricular is already on the resume, false if it is not
   */
  private boolean containsExtracurricular(Extracurricular extracurricular) {
    for (int i = 0; i < this.extracurriculars.size(); i++) {
      if (this.extracurriculars.get(i).getTitle().equalsIgnoreCase(extracurricular.getTitle()))
        return true;
    }
    return false;
  }

  /**
   * Helper method to check if an education exists within the ArrayList of educations.
   * An education with matching university name and grad date is considered a duplicate.
   * All fields of an existing education can be edited from the UI
   * @param education student is attempting to add or remove from their resume
   * @return true if the education already on the resume, false if not
   */
  private boolean containsEducation(Education education) {
    for (int i = 0; i < this.educations.size(); i++) {
      if (this.educations.get(i).getUniversity().equalsIgnoreCase(education.getUniversity())
            && this.educations.get(i).getGradYear() == education.getGradYear())
              return true;
    }
    return false;
  }

  /**
   * Helper method to check if skill exists within the ArrayList of skills
   * @param skill student is attempting to add or remove from their resume
   * @return true if skill is already on the resume, false if it is not
   */
  private boolean containsSkill (String skill) {
    for (int i = 0; i < this.skills.size(); i++) {
      if (this.skills.get(i).equalsIgnoreCase(skill))
        return true;
    }
    return false;
  }


  /**
   * Concatenates all educations on the resume into a list
   * @return String representation of education list, 1 indexed
   */

  public String displayEducations() {
    String ret = "";
    for (int i = 0; i < this.educations.size(); i++) {
      int j = i+1;
      ret+= (j + ": " + educations.get(i).toString()) + "\n\n";
    }
    return ret;
  }

  /**
   * Concatenates all work experiences on the resume into a list
   * @return String representation of the work experience list, 1 indexed
   */
  public String displayWorkExperiences() {
    String ret = "";
    for (int i = 0; i < this.workExperiences.size(); i++) {
      int j = i+1;
      ret += j + ": " + workExperiences.get(i).toString() + "\n\n";
    }
    return ret;
  }

  /**
   * Concatenates all extracurriculars on the resume into a list
   * @return String representation of the extracurriculars list, 1 indexed
   */
  public String displayExtracurriculars() {
    String ret = "";
    for (int i = 0; i < this.extracurriculars.size(); i++) {
      int j = i+1;
      ret += j + ": " + extracurriculars.get(i).toString() + "\n\n";
    }
    return ret;
  }

  /**
   * Concatenates all skills on the resume into a list
   * @return list of skills, 1 indexed
   */
  public String displaySkills() {
    String ret = "";
    for (int i = 0; i < this.skills.size(); i++) {
      int j = i+1;
      ret+= (j + ": " + skills.get(i)) + "\n\n";
    }
    return ret;
  }

  /**
   * Concatenates together all of the elements that make up the resume including the student's 
   * education, work experiences, extracurricular activites, and skills they bring to the job.
   * @return String representation of a student's resume body
   */
  public String toString() {
    String ret = "\nEducation\n";
    for (int i = 0; i < educations.size(); i++) {
      ret += educations.get(i).toString() + "\n\n";
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
