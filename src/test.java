import java.util.ArrayList;

//used to compile code to push it onto git
public class test {
    
    public static void main(String[] args) {
     ArrayList<Listing> listings = DataLoader.loadListings();
      System.out.println(listings.get(3));
    }
  }
