import java.util.ArrayList;

public class ResumeList {
  private static ResumeList resumeList;
  private ArrayList<Resume> resumes;

  private ResumeList() {
    resumes = DataLoader.loadResumes();
  }

  public static ResumeList getInstance() {
    if (resumeList == null)
      resumeList = new ResumeList();
    return resumeList;
  }

  public ArrayList<Resume> getResumes() {
    return this.resumes;
  }
}
