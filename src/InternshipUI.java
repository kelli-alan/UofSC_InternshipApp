import java.util.Scanner;

/**
 * 
 * @author Robbie Clark
 */
public class InternshipUI {
    private static final String WELCOME = "Welcome to the Internship App";
    private static final String[] OPEN_OPTIONS = {"Create Account", "Log In"};
    private static final String[] EMPLOYER_OPTIONS = {"Listings", "Ratings", "Logout"};
    private static final String[] STUDENT_OPTIONS = {"Resumes", "Find Internship Listings", "Ratings", "Logout"};
    private static final String[] RESUME_OPTIONS = {"View Resumes", "Create New Resume", "Edit Resume", "Back"};
    private static final String[] LISTING_OPTIONS = {"View Listings", "Create New Listing", "Edit Listing", "Back"};

    
    private Scanner scanner;
    private InternshipApp app;
    private User user;
    private Student student;
    private Employer employer;

    InternshipUI() {
        scanner = new Scanner(System.in);
        app = new InternshipApp();
    }

    public void run() {
        System.out.println(WELCOME);

            displayMainMenu();

            int command = scanner.nextInt();
            scanner.nextLine();

            //repeat after making account
            if(command > 1 || command < OPEN_OPTIONS.length) {

                switch(command) {
                    case 1: clearScreen();
                            createAccount();
                    break;
                    case 2: clearScreen();
                            Login();
                    break;
                }

            }

        if(user.type == Users.STUDENT) {
            while(command != 4){
                displayStudentOptions();
                command = scanner.nextInt();
                scanner.nextLine();
                switch(command) {
                    case 1: clearScreen();
                            ResumeMenu();
                    break;
                    case 2: clearScreen();
                            app.viewAllListings();
                    break;
                    case 3: System.out.println("Ratings coming soon");
                    break;
                }
            }
            clearScreen();
            System.out.println("Log out Successful");

        }
        else if(user.type == Users.EMPLOYER) {
            displayEmployerOptions();
        }
    }

    private void displayMainMenu() {
        System.out.println("\tWelcome to the Internship App\n************** Choose an option **************");
        for(int i = 0; i < OPEN_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + OPEN_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    private void displayEmployerOptions() {
        System.out.println("\n\tWelcome Employer: "+user.firstName + " " +user.lastName+"\n************** Choose an option **************");
        for(int i = 0; i < EMPLOYER_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + EMPLOYER_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    private void displayStudentOptions() {
        System.out.println("\n\tWelcome Student: "+user.firstName + " " +user.lastName+"\n************** Choose an option **************");
        for(int i = 0; i < STUDENT_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + STUDENT_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    private void displayResumeOptions() {
        System.out.println("\n\tResume Menu");
        for(int i = 0; i < RESUME_OPTIONS.length; i++) {
            System.out.println((i+1)+". "+RESUME_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    private void displayListingOptions() {
        System.out.println("\n\tResume Menu");
        for(int i = 0; i < LISTING_OPTIONS.length; i++) {
            System.out.println((i+1)+". "+LISTING_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    private void Login() {
        
        System.out.println("\tLog In\n");
        do {
            System.out.print("Enter your Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            user = app.login(username, password);
        } while (user == null);
        if (user.type == Users.STUDENT) {student = (Student)user;}
        else if (user.type == Users.EMPLOYER) {employer = (Employer)user;}
        clearScreen();
    }

    private void createAccount() {
        //TO DO Add writing functionality, check if there is already a user with the same username
        
        System.out.println("\tCreate Account");
        System.out.println("********** Enter Account Type **********");
        System.out.println("(S)tudent\n(E)mployer\n(M)oderator");
        String sType = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastname = scanner.nextLine();

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        if(sType.equalsIgnoreCase("s")) {
            Users type = Users.STUDENT;
            Student newStudent = new Student(firstname, lastname, username, password, type);
        }
        else if(sType.equalsIgnoreCase("e")) {
            Users type = Users.EMPLOYER;
            System.out.print("Enter the name of your company: ");
            String companyName = scanner.nextLine();
            System.out.println("Enter a brief description for your company: ");
            String companyDescription = scanner.nextLine();
            Employer newEmployer = new Employer(firstname, lastname, username, password, type, companyName, companyDescription);
        }
        else if(sType.equalsIgnoreCase("m")) {
            Users type = Users.MODERATOR;
            Moderator mod = new Moderator(firstname, lastname, username, password, type);
        }
        clearScreen();
        System.out.println("User created successfully");
    }

    private void ResumeMenu() {
        displayResumeOptions();
        int command = scanner.nextInt();
        scanner.nextLine();
        while(command != 4) {
            switch(command) {
                case 1: // view all resumes
                break;
                case 2: //create resume
                break;
                case 3: //edit resume
                break;
            }
        }
    }

    private void ListingMenu() {
        displayListingOptions();
        int command = scanner.nextInt();
        scanner.nextLine();
        while(command != 4) {
            switch(command) {
                case 1: 
                break;
                case 2: //create listing
                break;
                case 3: //edit listing
                break;
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }

    public static void main(String[] args) {
        InternshipUI internshipui = new InternshipUI();
        internshipui.run();
    }
}