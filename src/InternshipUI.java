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
    private static final String[] LISTING_LIST_OPTIONS = {"Apply", "Save", "Back"};

    
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

            //main menu, repeats when the user is null, meaning not logged in
            while(user == null) {
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
        }

        //student's menu, displays student choices
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
                            displayListingListOptions();
                            int choice = scanner.nextInt();
                            scanner.nextLine();
                            if(choice == 1) {
                                System.out.println("Which listing would you like to apply to?");
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                
                            } //apply
                            else if(choice == 2) {
                                System.out.println("Which listing would you like to save?");
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                //student.saveListing();
                            }//save
                            
                    break;
                    case 3: System.out.println("Ratings coming soon");
                    break;
                }
            }
            clearScreen();
            System.out.println("Log out Successful");
            app.logout();

        }
        //employer menu
        else if(user.type == Users.EMPLOYER) {
            while(command != 3) {
                displayEmployerOptions();
                command = scanner.nextInt();
                scanner.nextLine();
                switch(command) {
                    case 1: clearScreen();
                            ListingMenu();
                    break;
                    case 2: clearScreen();
                            System.out.println("Ratings coming soon");
                    break;
                }
            }
            clearScreen();
            System.out.println("Log out Successful");
            app.logout();
        }
    }

    //displays the text options for the main menu
    private void displayMainMenu() {
        System.out.println("************** Choose an option **************");
        for(int i = 0; i < OPEN_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + OPEN_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    //displays the ListingList menu
    private void displayListingListOptions() {
        System.out.println("************** Choose an option **************");
        for(int i = 0; i < LISTING_LIST_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + LISTING_LIST_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    //displays the employer menu
    private void displayEmployerOptions() {
        System.out.println("\n\tWelcome Employer: "+user.firstName + " " +user.lastName+"\n************** Choose an option **************");
        for(int i = 0; i < EMPLOYER_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + EMPLOYER_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    //displays the student menu
    private void displayStudentOptions() {
        System.out.println("\n\tWelcome Student: "+user.firstName + " " +user.lastName+"\n************** Choose an option **************");
        for(int i = 0; i < STUDENT_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + STUDENT_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    //displays the resume menu
    private void displayResumeOptions() {
        System.out.println("\n\tResume Menu");
        for(int i = 0; i < RESUME_OPTIONS.length; i++) {
            System.out.println((i+1)+". "+RESUME_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    //displays the employer's listing menu
    private void displayListingOptions() {
        System.out.println("\n\tResume Menu");
        for(int i = 0; i < LISTING_OPTIONS.length; i++) {
            System.out.println((i+1)+". "+LISTING_OPTIONS[i]);
        }
        System.out.print("\nSelection: ");
    }

    //log in method, asks for username and password and sets user if they are in the list
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

    //creates new account of any type, logs in user after creation
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
            //ADD RESUME CREATOR
            user = newStudent;
            app.addUser(newStudent);
        }
        else if(sType.equalsIgnoreCase("e")) {
            Users type = Users.EMPLOYER;
            System.out.print("Enter the name of your company: ");
            String companyName = scanner.nextLine();
            System.out.println("Enter a brief description for your company: ");
            String companyDescription = scanner.nextLine();
            Employer newEmployer = new Employer(firstname, lastname, username, password, type, companyName, companyDescription);
            user = newEmployer;
            app.addUser(newEmployer);
        }
        else if(sType.equalsIgnoreCase("m")) {
            Users type = Users.MODERATOR;
            Moderator mod = new Moderator(firstname, lastname, username, password, type);
            user = mod;
            app.addUser(mod);
        }
        clearScreen();
        System.out.println("User created successfully");
    }

    //functionality for resume menu
    private void ResumeMenu() {
        displayResumeOptions();
        int command = scanner.nextInt();
        scanner.nextLine();
        while(command != 4) {
            switch(command) {
                case 1: //view all resumes
                break;
                case 2: //create resume
                break;
                case 3: //edit resume
                break;
            }
        }
    }

    //functionality for listing menu
    private void ListingMenu() {
        displayListingOptions();
        int command = scanner.nextInt();
        scanner.nextLine();
        while(command != 4) {
            switch(command) {
                case 1: app.viewAllListings();
                break;
                case 2: //create listing
                break;
                case 3: //edit listing
                break;
            }
        }
    }

    //clears the info on the screen
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }

    public static void main(String[] args) {
        InternshipUI internshipui = new InternshipUI();
        internshipui.run();
    }
}