import java.util.Scanner;

public class InternshipUI {
    private static final String WELCOME = "Welcome to the Internship App";
    private static final String[] OPEN_OPTIONS = {"Create Account", "Log In"};
    private static final String[] EMPLOYER_OPTIONS = {"Listings", "Ratings", "Logout"};
    private static final String[] STUDENT_OPTIONS = {"Resumes", "Find Internship Listings", "Ratings", "Logout"};

    
    private Scanner scanner;
    private InternshipApp app;
    private User user;

    InternshipUI() {
        scanner = new Scanner(System.in);
        app = new InternshipApp();
    }

    public void run() {
        System.out.println(WELCOME);

            displayMainMenu();

            int command = scanner.nextInt();
            scanner.nextLine();

            if(command > 1 || command < OPEN_OPTIONS.length) {

                switch (command) {
                    case 1: createAccount();
                    break;
                    case 2: Login();
                    break;
                }

            }

        if(user.type == Users.STUDENT) {
            displayStudentOptions();
        }
        else if(user.type == Users.EMPLOYER) {
            displayEmployerOptions();
        }
    }

    private void displayMainMenu() {
        System.out.println("\t\tWelcome to the Internship App\n**************** Choose an option ****************");
        for(int i = 0; i < OPEN_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + OPEN_OPTIONS[i]);
        }
        System.out.println();
    }

    private void displayEmployerOptions() {
        System.out.println("Welcome Employer: "+user.firstName + " " +user.lastName+"\n**************** Choose an option ****************");
        for(int i = 0; i < EMPLOYER_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + EMPLOYER_OPTIONS[i]);
        }
        System.out.println();
    }

    private void displayStudentOptions() {
        System.out.println("\n\tWelcome Student: "+user.firstName + " " +user.lastName+"\n**************** Choose an option ****************");
        for(int i = 0; i < STUDENT_OPTIONS.length; i++) {
            System.out.println( (i+1) + ". " + STUDENT_OPTIONS[i]);
        }
        System.out.println();
    }

    private void Login() {
        do {
            System.out.print("Enter your Username: ");
            String username = scanner.nextLine();
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();
            user = app.login(username, password);
        } while (user == null);
        
    }

    private void createAccount() {

    }

    public static void main(String[] args) {
        InternshipUI internshipui = new InternshipUI();
        internshipui.run();
    }
}
