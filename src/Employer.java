import java.util.ArrayList;
import java.util.UUID;

/**
 * A user type, who can create listings
 * @authors Yousef Afshar, Kelli Alan, Robbie Clark
 */
public class Employer extends User {
  private String companyName;
  private String companyDescription;
  private ArrayList<Listing> internshipListings;


  /**
   * Constructor for employers with a UUID; used for loading employers from the JSON files
   * @param id employer's unique ID
   * @param firstName of employer
   * @param lastName of employer
   * @param username of employer
   * @param password of employer
   * @param type EMPLOYER
   * @param companyName name of company the employer is recruiting for
   * @param companyDescription brief overview of company employer is recruiting for
   */
  public Employer(UUID id, String firstName, String lastName, String username, String password,
      Users type, String companyName, String companyDescription) {
    super(id, firstName, lastName, username, password, type);
    this.companyName = companyName;
    this.companyDescription = companyDescription;
    this.internshipListings = new ArrayList<Listing>();
  }

  /**
   * Constructor for new employer accounts, called from the UI
   * @param firstName of employer
   * @param lastName of employer
   * @param username of employer
   * @param password of employer
   * @param type EMPLOYER
   * @param companyName name of company the employer is recruiting for
   * @param companyDescription brief overview of company employer is recruiting for
   */
  public Employer(String firstName, String lastName, String username, String password,
      Users type, String companyName, String companyDescription) {
    super(firstName, lastName, username, password, type);
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
    return this.companyName + "\n" + internshipListings.get(index).toString();
  }

  /**
   * Concatenates all listings the employer has created
   * @return String representing all listings
   */
  public String displayAllListings() {
    String ret = "";

    for (int i = 0; i < this.internshipListings.size(); i++) {
      ret+= displayListing(i) + "\n\n";
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
