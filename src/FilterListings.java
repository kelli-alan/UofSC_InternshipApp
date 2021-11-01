import java.util.ArrayList;

/**
 * Various ways to filter listings
 * @authors Evan Grunewald
 */
public class FilterListings {

  /**
   * A filtering method that allows the student to view listings that require the same or fewer
   * hours per week than comparison 
   * @param listings list of listings to filter
   * @param comparison maximum number of hours per week
   * @return The new filtered array
   */
  public static ArrayList<Listing> filterByHoursPerWeek(ArrayList<Listing> listings, int comparison) {

    ArrayList<Listing> newArray = new ArrayList<Listing>();

    for (int i = 0; i < listings.size(); i++) {
      Listing currentListing = listings.get(i);
      if (currentListing.getHoursPerWeek() <= comparison) {
        newArray.add(currentListing);
      }
    }
    return newArray;
  }

  /**
   * A filtering method that allows the student to view listings in specified location
   * @param listings list of listings to filter
   * @param comparison city or state name
   * @return The new filtered array
   */
  public static ArrayList<Listing> filterByLocation(ArrayList<Listing> listings, String comparison) {

    ArrayList<Listing> newArray = new ArrayList<Listing>();

    for (int i = 0; i < listings.size(); i++) {
      Listing currentListing = listings.get(i);
      if (currentListing.getLocation().toLowerCase().contains(comparison.toLowerCase())) {
        newArray.add(currentListing);
      }
    }
    return newArray;
  }

  /**
   * A filtering method that allows the student to view listings that match a given skill
   * @param listings list of listings to filter
   * @param comparison skill name
   * @return The new filtered array
   */
  public static ArrayList<Listing> filterBySkill(ArrayList<Listing> listings, String comparison) {

    ArrayList<Listing> newArray = new ArrayList<Listing>();

    for (int i = 0; i < listings.size(); i++) {
      Listing currentListing = listings.get(i);
      if (currentListing.containsSkill(comparison)) {
        newArray.add(currentListing);
      }
    }
    return newArray;
  }

  /**
   * A filtering method that allows the student to view listings that pay at least the comparison
   * value per hour
   * @param listings list of listings to filter
   * @param comparison minimum pay per hour
   * @return The new filtered array
   */
  public static ArrayList<Listing> filterByPay(ArrayList<Listing> listings, double comparison) {

    ArrayList<Listing> newArray = new ArrayList<Listing>();

    for (int i = 0; i < listings.size(); i++) {
      Listing currentListing = listings.get(i);
      if (currentListing.getPay() >= comparison) {
        newArray.add(currentListing);
      }
    }
    return newArray;
  }
}
