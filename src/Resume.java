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

  // constructor for reading from database
  public Resume(UUID id) {
    this.id = id;
    this.skills = new ArrayList<String>();
    this.education = new ArrayList<Education>();
    this.workExperiences = new ArrayList<WorkExperience>();
    this.extracurriculars = new ArrayList<Extracurricular>();
  }

  // constructor for new resumes that do not have an id yet
  public Resume() {
    this.id = UUID.randomUUID();
    this.skills = new ArrayList<String>();
    this.education = new ArrayList<Education>();
    this.workExperiences = new ArrayList<WorkExperience>();
    this.extracurriculars = new ArrayList<Extracurricular>();
  }

  public UUID getUUID() {
    return this.id;
  }

  public ArrayList<String> getSkills() {
    return this.skills;
  }

  public ArrayList<Education> getEducations() {
    return this.education;
  }

  public ArrayList<WorkExperience> getWorkExperiences() {
    return this.workExperiences;
  }

  public ArrayList<Extracurricular> getExtracurriculars() {
    return this.extracurriculars;
  }

  public void addWorkExperience(WorkExperience workXP) {
    this.workExperiences.add(workXP);
  }

  public void addExtracurricular(Extracurricular extrac) {
    this.extracurriculars.add(extrac);
  }

  public void addEducation(Education edu) {
    this.education.add(edu);
  }

  public void addSkill(String skill) {
    this.skills.add(skill);
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
    String ret = "Education:\n";
    for (int i = 0; i < education.size(); i++) {
      ret += "\t" + education.get(i).toString() + "\n";
    }

    ret += "Work Experiences:\n";
    for (int i = 0; i < workExperiences.size(); i++) {
      ret += "\t" + workExperiences.get(i).toString() + "\n";
    }

    ret += "Extracurriculars:\n";
    for (int i = 0; i < extracurriculars.size(); i++) {
      ret += "\t" + extracurriculars.get(i).toString() + "\n";
    }

    ret += "Skills:\n";

    for (int i = 0; i < skills.size(); i++) {
      ret += "\t- " + skills.get(i) + "\n";
    }
    return ret;
  }
}
