import java.util.ArrayList;

//used to compile code to push it onto git
public class test {
    
    public static void main(String[] args) {
      ArrayList<Resume> resumes = DataLoader.loadResumes();
      for (int i = 0; i < resumes.size(); i++) {
       System.out.println(resumes.get(i).toString());
      }
    }
}
