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
  private ArrayList<Listing> internshipListings;

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


  public void deleteListing(int index) {
    if(index < this.internshipListings.size()) 
      this.internshipListings.remove(index);
  }

  /**
   * Gets the listing at the specified index
   * @param index of employer's internship listings to display
   * @return String representing the requested listing
   */ 
  public String displayListing(int index) {
    return this.internshipListings.get(index).toString();
  }

  /**
   * Concatenates all listings the employer has created
   * @return String representing all listings
   */
  public String displayAllListings() {
    String ret = "";

    for (int i = 0; i < this.internshipListings.size(); i++) {
      int j = i+1;
      ret+= (j) + ".\n" + displayListing(i) + "\n\n";
    }

    return ret;
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
