import java.util.ArrayList;

public class ListingList {

  private static ListingList listingList;
  private ArrayList<Listing> listings;

  private ListingList() {
    listings = DataLoader.loadListings();
  }

  public static ListingList getInstance() {
    if (listingList == null)
      listingList = new ListingList();

    return listingList;
  }

  public ArrayList<Listing> getListing(String keyword) {
    return listings;
  }

  public ArrayList<Listing> getAllListings() {
    return listings;
  }
}