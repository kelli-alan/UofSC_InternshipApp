import java.util.ArrayList;

/**
 * 
 * @authors Robbie Clark, Kelli Alan
 */
public class ResumeList {
  private static ResumeList resumeList;
  private ArrayList<Resume> resumes;

  /* 
   * Calls the DataLoader to load up the data from the resume.json
   * to the resumes ArrayList
   */
  private ResumeList() {
    resumes = DataLoader.loadResumes();
  }

  /**
   *  Method to get an instance of a resumeList if one doesn't already exist.
   * @return A resumeList instance
   */
  public static ResumeList getInstance() {
    if (resumeList == null)
      resumeList = new ResumeList();
    return resumeList;
  }

  // Getter method for an ArrayList of resumes, resumes.
  public ArrayList<Resume> getResumes() {
    return this.resumes;
  }
}
