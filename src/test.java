import java.util.ArrayList;

//used to compile code to push it onto git
public class test {
    
    public static void main(String[] args) {
      ArrayList<Listing> listings = DataLoader.loadListings();
      for (int i = 0; i < listings.size(); i++) {
       System.out.println(listings.get(i).toString());
      }
    }
}
