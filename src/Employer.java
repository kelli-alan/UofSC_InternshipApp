import java.util.ArrayList;
import java.util.UUID;
//To-be removed after InternshipUI up.
import java.util.Scanner;

/**
 *
 * @authors Yousef Afshar, Kelli Alan, Robbie Clark
 */
public class Employer extends User {
  private String companyName;
  private String companyDescription;
  private ArrayList<Employer> employers;
  private ArrayList<Listing> internshipListings;
  //To-be moved to InternshipUI
  private Scanner key = new Scanner(System.in);

  //This Employer constructor with a UUID is used by the DataLoader.
  public Employer(UUID id, String firstName, String lastName, String username, String password,
      Users EMPLOYER, String companyName, String companyDescription) {
    super(id, firstName, lastName, username, password, EMPLOYER);
    this.companyName = companyName;
    this.companyDescription = companyDescription;
    this.internshipListings = new ArrayList<Listing>();
  }

  //This Employer constructor without a UUID is used by the InternshipUI.
  public Employer(String firstName, String lastName, String username, String password,
      Users USER_TYPE_EMPLOYER, String companyName, String companyDescription) {
    super(firstName, lastName, username, password, USER_TYPE_EMPLOYER);
    this.companyName = companyName;
    this.companyDescription = companyDescription;
    this.internshipListings = new ArrayList<Listing>();
  }

  /**
   * Adds a new listing to the ArrayList of internshipListings.
   * @param listing
   */
  public void addListing(Listing listing) {
    this.internshipListings.add(listing);
  }

  // Getter method for the name of the company.
  public String getCompanyName() {
    return this.companyName;
  }

  // Getter method for the description of the company.
  public String getCompanyDescription() {
    return this.companyDescription;
  }

  // Getter method for the ArrayList of internship listings.
  public ArrayList<Listing> getListings() {
    return this.internshipListings;
  }

  //Move to UI
  public void createListing() {
    String jobTitle;
    String city;
    String state;
    String startDate;
    int hoursPerWeek;
    double pay;
    boolean isRemote;
    UUID listingID = UUID.randomUUID();

    System.out.println("Enter job title: ");
    jobTitle = key.nextLine();

    System.out.println("Enter the city the position is located in: ");
    city = key.nextLine();

    System.out.println("Enter the state the position is located in: ");
    state = key.nextLine();

    System.out.println("Enter the start date: ");
    startDate = key.nextLine();

    System.out.println("Enter hours per week: ");
    hoursPerWeek = key.nextInt();
    key.nextLine();

    System.out.println("Enter the pay per hour: ");
    pay = key.nextDouble();
    key.nextLine();

    System.out.println("Enter \"true\" if the internship is remote or \"false\" if it is not: ");
    isRemote = key.nextBoolean();
    key.nextLine();

    /** rework ui to account for month, year format for date */
    /*
     * Listing list = new Listing(id, jobTitle, city, state, startMonth, startYear,
     * hoursPerWeek, pay, isRemote);
     * 
     * clearScreen();
     * 
     * String cont = "y";
     * 
     * while(cont.equalsIgnoreCase("y")) {
     * 
     * System.out.
     * println("Enter duties for this position (Enter \"done\" when finished): ");
     * if(!key.nextLine().equalsIgnoreCase("done")) list.addDuties(key.nextLine());
     * else {cont = key.nextLine();} }
     * 
     * clearScreen();
     * 
     * cont = "y";
     * 
     * while(cont.equalsIgnoreCase("y")) {
     * 
     * System.out.
     * println("Enter skills for this position (Enter \"done\" when finished): ");
     * if(!key.nextLine().equalsIgnoreCase("done")) list.addSkills(key.nextLine());
     * else {cont = key.nextLine();} }
     * 
     * clearScreen();
     * 
     * System.out.println("Listing Created!");
     * 
     * this.internshipListings.add(list);
     */
  }

  // if a listing is filled, it doesn't need to display to the user
  public void fillListing(Listing listing) {
    this.internshipListings.remove(listing);
  }

  public ArrayList<Resume> sortApplications(Listing listing) {
    //Prompt for how user would like to sort (in InternshipUI)
    //Temp structure here for now. If we want new sorts, this
    //will need to be updated obviously but going for time being
    // we have our default sort and by time recieved.
    Scanner key = new Scanner(System.in);
    System.out.println("Input your desired sort:\n1. Sort by time recieved.\n"
    + "2. Default sort.");
    String userInput = key.nextLine();
    key.close();
    if( userInput == "1") {
      //Calls SortByTimeRecieved (sorting not written yet).
    } else if (userInput == "2") {
      return listing.getApplications();
    } else {
      System.out.println("Invalid input. Please try again with 1 or 2.");
      sortApplications(listing);
    }
    return listing.getApplications();
  }

  /**
   * Allows an employer to view all applications to their job listing.
   * @param listing
   */
  public ArrayList<Resume> viewAllApplications(Listing listing) {
    return listing.getApplications();
  }

  /**
   * Delete's a listing by having an employer enter the listing ID
   * and has a corresponding error message if they input an invalid ID.
   * @param id
   */
  public void deleteListing(UUID id) {
    boolean found = false;
    do {
      for (int i = 0; i < this.employers.size(); i++) {
        if (id == employers.get(i).id) {
          for (int j = 0; j < (employers.get(i)).getListings().size(); j++) {
            employers.remove(j);
            found = true;
          }
          break;
        }
      }
      if (!found) {
        System.out.println("Invalid ID. Please try again.");
      }
    } while (!found);
  }

  /// Displays a listing with their name and the listing.
  public String displayListing(int i) {
    return this.firstName + " " + this.lastName + "\n" + this.internshipListings.get(i).toString();
  }
 // Returns the company and all of their respective listings.
  public String toString() {
    String ret = super.toString();
    ret += "Company: " + this.companyName + "\n\t" + this.companyDescription + "\n";
    ret += "My Listings: \n";

    for (int i = 0; i < internshipListings.size(); i++) {
      ret += internshipListings.get(i).toString() + "\n";
    }
    return ret;
  }
}
