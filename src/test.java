import java.util.ArrayList;

//used to compile code to push it onto git
public class test {
    
    public static void main(String[] args) {
      ArrayList<Employer> employers = DataLoader.loadEmployers();
      for (int i = 0; i < employers.size(); i++) {
       System.out.println(employers.get(i).toString());
      }
    }
}
