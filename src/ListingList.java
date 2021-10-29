import java.util.ArrayList;

/**
 * 
 * @authors Robbie Clark, Kelli Alan
 */
public class ListingList {

  private static ListingList listingList;
  private ArrayList<Listing> listings;

  /* 
   * Calls the DataLoader to load up the data from the listing.json
   * to the listings ArrayList
   */
  private ListingList() {
    listings = DataLoader.loadListings();
  }

  /**
   *  Method to get an instance of a listingList if one doesn't already exist.
   * @return A listingList instance
   */
  public static ListingList getInstance() {
    if (listingList == null)
      listingList = new ListingList();

    return listingList;
  }

  // Getter method for the ArrayList of listings, listings
  public ArrayList<Listing> getAllListings() {
    return listings;
  }
}