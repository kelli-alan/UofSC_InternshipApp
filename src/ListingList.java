import java.util.ArrayList;

public class ListingList {
    
    private ListingList listinglist;
    private ArrayList<Listing> listings;

    public ListingList getInstance() {
        if(listinglist == null)
            listinglist = new ListingList();

        return listinglist;
    }

    private ListingList() {
        listings = new ArrayList<Listing>();
    }

    public ArrayList<Listing> getListing(String keyword) {
        return listings;
    }
}