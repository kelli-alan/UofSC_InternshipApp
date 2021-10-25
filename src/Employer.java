import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

/*
 *
 * @authors Yousef Afshar, Kelli Alan, Robbie Clark
 */
public class Employer extends User {
  private String companyName;
  private String companyDescription;
  private ArrayList<Listing> internshipListings;
  private Scanner key = new Scanner(System.in);
  private UUID id;

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
      Users EMPLOYER, String companyName, String companyDescription) {
    super(firstName, lastName, username, password, EMPLOYER);
    this.companyName = companyName;
    this.companyDescription = companyDescription;
    this.internshipListings = new ArrayList<Listing>();
  }

  public void addListing(Listing listing) {
    this.internshipListings.add(listing);
  }

  public String getCompanyName() {
    return this.companyName;
  }

  public String getCompanyDescription() {
    return this.companyDescription;
  }

  public ArrayList<Listing> getListings() {
    return this.internshipListings;
  }

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

  /* Unlike Moderator, doesn't ahve to filtter through as there is no
   * input from the user required after the method is called whereas
   * Moderator must check the user's name and sort for that specific
   * users resumes.
   */
  public ArrayList<Resume> viewAllApplications(Listing listing) {
    return listing.getApplications();
  }

  //This method probably doesn't need to exist in Employer after UI handles
  //almost all of our interface. 
  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

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
