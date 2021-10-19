import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;
import java.time.Month;
/*
 *
 * @authors Yousef Afshar
 */
public class Student extends User {
    
    private ArrayList<Resume> resumes;
    private ArrayList<Listing> listing;
    private ArrayList<Listing> savedListings;
    private FilterBehavior filterBehavior;
    private Scanner key = new Scanner(System.in);
    private Month month;
    private Major major;

    public Student(UUID id, String firstName, String lastName, String username,
        String password, Users USER_TYPE_STUDENT) {
            super(id, firstName, lastName, username, password, USER_TYPE_STUDENT);
            this.resumes = new ArrayList<Resume>();
            this.savedListings = new ArrayList<Listing>();
        }

    public void createResume() {
        UUID id = UUID.randomUUID();
        String firstName = this.firstName;
        String lastName = this.lastName;
        
        System.out.println("Enter email: ");
        String eMail = key.nextLine();

        System.out.println("Enter phone number: ");
        String phoneNum = key.nextLine();

        clearScreen();
        
        String cont = "y";

        ArrayList<String> skills = new ArrayList<String>();

        while(cont.equalsIgnoreCase("y")) {
            System.out.println("Enter your skills (enter \"done\" when finished): ");
            if(!key.nextLine().equalsIgnoreCase("done"))
                skills.add(key.nextLine());
            else {cont = key.nextLine();}
        }

        clearScreen();

        ArrayList<Education> education = new ArrayList<Education>();
        
        cont = "y";

        while(cont.equalsIgnoreCase("y")) {
            
            System.out.println("Enter the university: ");
            String university = key.nextLine();

            System.out.println("Enter the city: ");
            String city = key.nextLine();
            
            System.out.println("Enter the state: ");
            String state = key.nextLine();

            System.out.println("Enter the degree type: ");
            String degreeType = key.nextLine();

            
            boolean hasMajor = false;

            while(!hasMajor) {
            System.out.println("Enter the number for your major:\n"+
            "1. Computer Science\n2. Computer Information Systems\n3. Computer Engineering\n4. Integrated Information Systems");
            int case1 = key.nextInt();
            key.nextLine();
            hasMajor = true;
            switch(case1) {
                case 1: major = Major.COMPUTER_SCIENCE;
                break;
                case 2: major = Major.COMPUTER_INFORMATION_SYSTEMS;
                break;
                case 3: major = Major.COMPUTER_ENGINEERING;
                break;
                case 4: major = Major.INTEGRATED_INFORMATION_TECHNOLOGY;
                break;
                default: System.out.println("INVALID MAJOR");
                hasMajor = false;
            }

            
            boolean hasMonth = false;

            while(!hasMonth) {
                System.out.println("Enter the number of your graduation month (January is 1, February is 2, etc.): ");
                case1 = key.nextInt();
                key.nextLine();
                hasMonth = true;
                switch (case1) {
                    case 1: month = Month.JANUARY;
                    break;
                    case 2: month = Month.FEBRUARY;
                    break;
                    case 3: month = Month.MARCH;
                    break;
                    case 4: month = Month.APRIL;
                    break;
                    case 5: month = Month.MAY;
                    break;
                    case 6: month = Month.JUNE;
                    break;
                    case 7: month = Month.JULY;
                    break;
                    case 8: month = Month.AUGUST;
                    break;
                    case 9: month = Month.SEPTEMBER;
                    break;
                    case 10: month = Month.OCTOBER;
                    break;
                    case 11: month = Month.NOVEMBER;
                    break;
                    case 12: month = Month.DECEMBER;
                    break;
                    default: System.out.println("INVALID MONTH");
                            hasMonth = false;
                }
            }

            System.out.println("Enter your graduation year: ");
            int gradYear = key.nextInt();
            key.nextLine();

            education.add(new Education(university, city, state, degreeType, major, month, gradYear));
            System.out.println("Would you like to enter another education? (y)es or (n)o?");
            cont = key.nextLine();
        }

        ArrayList<WorkExperience> workExperiences = new ArrayList<WorkExperience>();
        ArrayList<Extracurricular> extracurriculars = new ArrayList<Extracurricular>();


            if(!key.nextLine().equalsIgnoreCase("done")) {

            }
            else {cont = key.nextLine();}
        }

        
        


    }

    public void deleteResume(int id) {

    }

    public void applyToListing(Listing listing,  Resume resume) {

    }

    public ArrayList<Listing> filterListings(ArrayList<Listing> savedListings) {
        return savedListings;
    }

    public void setFilterBehavior(FilterBehavior filterBehavior) {

    }

    public void saveListing(Listing listing) {

    }

    public ArrayList<Listing> viewAllListings() {
        return listing;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
