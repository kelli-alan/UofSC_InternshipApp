import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;
/*
 *
 * @authors Yousef Afshar
 */
public class Employer extends User implements Observer {
    private Subject subject;
    private String companyName;
    private String companyDescription;
    private ArrayList<Listing> internshipListings;
    private SortBehavior sortBehavior;
    private ArrayList<Resume> applications;
    private Scanner key = new Scanner(System.in);
    private UUID id;

    public Employer(UUID id, Subject subject, String firstName, String lastName, String username,
        String password, Users USER_TYPE_EMPLOYER, String companyName, String companyDescription) {
            super(id, firstName, lastName, username, password, USER_TYPE_EMPLOYER);
            subject.registerObserver(this);
            this.companyName = companyName;
            this.companyDescription = companyDescription;
            this.internshipListings = new ArrayList<Listing>();
            this.applications = new ArrayList<Resume>();

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

        Listing list = new Listing(id, jobTitle, city, state, startDate, hoursPerWeek, pay, isRemote);

        clearScreen();

        String cont = "y";

        while(cont.equalsIgnoreCase("y")) {

            System.out.println("Enter duties for this position (Enter \"done\" when finished): ");
            if(!key.nextLine().equalsIgnoreCase("done"))
                list.addDuties(key.nextLine());
            else {cont = key.nextLine();}
        }

        clearScreen();

        cont = "y";

        while(cont.equalsIgnoreCase("y")) {

            System.out.println("Enter skills for this position (Enter \"done\" when finished): ");
            if(!key.nextLine().equalsIgnoreCase("done"))
                list.addSkills(key.nextLine());
            else {cont = key.nextLine();}
        }

        clearScreen();

        System.out.println("Listing Created!");
        
        this.internshipListings.add(list);
    }

    public void fillListing(Listing listing) {

    }

    public ArrayList<Resume> sortApplications(Listing listing) {
        return applications;
    }

    public ArrayList<Resume> viewAllApplications(Listing listing) {
        return applications;
    }

    public void update(Resume resume) {
        this.applications.add(resume);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
