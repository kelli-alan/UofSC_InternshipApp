import java.util.ArrayList;
/*
 *
 * @authors Evan Grunewald
 */
public class FilterListings {

    public static ArrayList<Listing> filterByHoursPerWeek(ArrayList<Listing> listings, int comparison) {

        ArrayList<Listing> newArray = new ArrayList<Listing>();

        for (int i = 0; i < listings.size(); i++)
        {
            Listing currentListing = listings.get(i);
            if (currentListing.getHoursPerWeek() >= comparison) {
                newArray.add(currentListing);
            }
        }
        return newArray; 
    }

    public static ArrayList<Listing> filterByLocation(ArrayList<Listing> listings, String comparison) {

        ArrayList<Listing> newArray = new ArrayList<Listing>();

        for (int i = 0; i < listings.size(); i++)
        {
            Listing currentListing = listings.get(i);
            if (currentListing.getLocation().equalsIgnoreCase(comparison)) {
                newArray.add(currentListing);
            }
        }
        return newArray; 
    }

    public static ArrayList<Listing> filterBySkill(ArrayList<Listing> listings, String comparison) {

        ArrayList<Listing> newArray = new ArrayList<Listing>();

        for (int i = 0; i < listings.size(); i++)
        {
            Listing currentListing = listings.get(i);
            if (currentListing.containsSkill(comparison)) {
                newArray.add(currentListing);
            }
        }
        return newArray; 
    }

    public static ArrayList<Listing> filterByPay(ArrayList<Listing> listings, double comparison) {

        ArrayList<Listing> newArray = new ArrayList<Listing>();

        for (int i = 0; i < listings.size(); i++)
        {
            Listing currentListing = listings.get(i);
            if (currentListing.getPay() >= comparison) {
                newArray.add(currentListing);
            }
        }
        return newArray; 
    }
}
