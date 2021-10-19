import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;
/*
 *
 * @authors Yousef Afshar
 */
public class Employer extends User implements EmployerObserver {
    private String companyName;
    private String companyDescription;
    private ArrayList<Listing> internshipListings;
    private SortBehavior sortBehavior;
    private ArrayList<Resume> resumes;
    private Scanner key = new Scanner(System.in);
    private UUID id;

    public Employer(UUID id, Subject subject, String firstName, String lastName, String username,
        String password, Users USER_TYPE_EMPLOYER, String companyName, String companyDescription) {
            super(id, firstName, lastName, username, password, USER_TYPE_EMPLOYER);
            this.companyName = companyName;
            this.companyDescription = companyDescription;
            internshipListings = new ArrayList<Listing>();

    }

    public void createListing() {
        String jobTitle;
        String city;
        String state;
        String startDate;
        int hoursPerWeek; 
        double pay;
        boolean isRemote;
        ArrayList<String> duties = new ArrayList<String>();
        ArrayList<String> skills = new ArrayList<String>();
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

        clearScreen();

        String cont = "y";

        while(cont.equalsIgnoreCase("y")) {

            System.out.println("Enter duties for this position (Enter \"done\" when finished): ");
            if(!key.nextLine().equalsIgnoreCase("done"))
                duties.add(key.nextLine());
            else {cont = key.nextLine();}
        }

        clearScreen();

        cont = "y";

        while(cont.equalsIgnoreCase("y")) {

            System.out.println("Enter skills for this position (Enter \"done\" when finished): ");
            if(!key.nextLine().equalsIgnoreCase("done"))
                skills.add(key.nextLine());
            else {cont = key.nextLine();}
        }

        clearScreen();
        
        internshipListings.add(new Listing(id, jobTitle, city, state, startDate, hoursPerWeek, pay, isRemote, skills, duties));
    }

    public void fillListing(Listing listing) {

    }

    public ArrayList<Resume> sortApplications(Listing listing) {
        return resumes;
    }

    public ArrayList<Resume> viewAllApplications(Listing listing) {
        return resumes;
    }

    public void update(Listing listing) {

    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
