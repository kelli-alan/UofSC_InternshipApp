import java.util.ArrayList;

/*
 *
 * @authors Evan Grunewald
 */
public class FilterListings {

  /**
   * A filtering method that allows the student to put the listings in order by 
   * hours offered at the job per week.
   * @param listings
   * @param comparison
   * @return The new filtered array in order by hours per week offered.
   */
  public static ArrayList<Listing> filterByHoursPerWeek(ArrayList<Listing> listings, int comparison) {

    ArrayList<Listing> newArray = new ArrayList<Listing>();

    for (int i = 0; i < listings.size(); i++) {
      Listing currentListing = listings.get(i);
      if (currentListing.getHoursPerWeek() >= comparison) {
        newArray.add(currentListing);
      }
    }
    return newArray;
  }

  /**
   * A filtering method that allows the student to put the listings in order by 
   * the location of the job site.
   * @param listings
   * @param comparison
   * @return The new filtered array in order by location of where the jobs are.
   */
  public static ArrayList<Listing> filterByLocation(ArrayList<Listing> listings, String comparison) {

    ArrayList<Listing> newArray = new ArrayList<Listing>();

    for (int i = 0; i < listings.size(); i++) {
      Listing currentListing = listings.get(i);
      if (currentListing.getLocation().equalsIgnoreCase(comparison)) {
        newArray.add(currentListing);
      }
    }
    return newArray;
  }

  /**
   * A filtering method that allows the student to put the listings in order by 
   * the skills employers have on a listing.
   * @param listings
   * @param comparison
   * @return The new filtered array in order by skills.
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
   * A filtering method that allows the student to put the listings in order by 
   * tjhe hourly pay available on the job listings.
   * @param listings
   * @param comparison
   * @return The new filtered array in order by the hourly pay.
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
