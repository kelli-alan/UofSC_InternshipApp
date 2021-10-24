import java.util.ArrayList;

//used to compile code to push it onto git
public class test {
    
    public static void main(String[] args) {
      ArrayList<Student> students = DataLoader.loadStudents();
      for (int i = 0; i < students.size(); i++) {
       System.out.println(students.get(i).toString());
      }
    }
}
