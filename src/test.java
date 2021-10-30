import java.util.ArrayList;

//used to compile code to push it onto git
public class test {
    
    public static void main(String[] args) {
      ArrayList<Student> students = DataLoader.loadStudents();
      
      System.out.println(students.get(0).displayAllResumes());
    }
  }
