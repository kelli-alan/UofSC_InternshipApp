import java.util.ArrayList;

//used to compile code to push it onto git
public class test {
    
    public static void main(String[] args) {
     DataLoader.loadListings();
     DataWriter.saveListings();

     DataLoader.loadResumes();
     DataWriter.saveResumes();
    }
  }
