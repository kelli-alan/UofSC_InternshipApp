import java.util.Scanner;
import java.time.Month;

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
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your phone number: ");
            String phonenum = scanner.nextLine();
            student = new Student(firstname, lastname, username, password, type);
            createResume();
            user = student;
            app.addUser(student);
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
                case 1: displayResumes();
                break;
                case 2: clearScreen();
                createResume();
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


    private void createResume() {

        Resume res = new Resume();

    clearScreen();
    String cont = "y";

    while (cont.equalsIgnoreCase("y")) {
      System.out.println("Enter your skills (enter \"done\" when finished): "); //take out of for loop
      String skill = scanner.nextLine();
      if (!skill.equalsIgnoreCase("done"))
        res.addSkill(skill);
      else {
        cont = skill;
      }
    }

    clearScreen();

    cont = "y";
    while (cont.equalsIgnoreCase("y")) {

      System.out.println("Enter the university: ");
      String university = scanner.nextLine();

      System.out.println("Enter the city: ");
      String city = scanner.nextLine();

      System.out.println("Enter the state: ");
      String state = scanner.nextLine();

      System.out.println("Enter the degree type: ");
      String degreeType = scanner.nextLine();

      System.out.println("Enter your major: ");
      String major = scanner.nextLine();

      System.out.println("Enter the number of your graduation month (January is 1, February is 2, etc.): ");
      int monthNumber = scanner.nextInt();
      scanner.nextLine();
      Month gradMonth = determineMonth(monthNumber);

      System.out.println("Enter your graduation year: ");
      int gradYear = scanner.nextInt();
      scanner.nextLine();

      Education edu = new Education(university, city, state, degreeType, major, gradMonth, gradYear);

      System.out.println("Enter your minor (enter \"none\" if you don't have one): ");
      String minor = scanner.nextLine();
      if (!minor.equalsIgnoreCase("none"))
        edu.addMinor(minor);

      System.out.println("Enter your GPA (Enter -1 if you do not wish to include a GPA): ");
      double gpa = scanner.nextDouble();
      scanner.nextLine();
      if (gpa != -1)
        edu.addGPA(gpa);

      res.addEducation(edu);

      System.out.println("Would you like to enter another education? (y)es or (n)o?");
      cont = scanner.nextLine();
    }

    clearScreen();

    // create work experience section

    System.out.println("Would you like to enter any work experience? (y)es or (n)o?");
    cont = scanner.nextLine();

    while (cont.equalsIgnoreCase("y")) {

      System.out.println("Enter the position: ");
      String position = scanner.nextLine();

      System.out.println("Enter the company: ");
      String company = scanner.nextLine();

      System.out.println("Enter the city: ");
      String Wcity = scanner.nextLine();

      System.out.println("Enter the state: ");
      String Wstate = scanner.nextLine();

      System.out.println("Enter the number of the month you started (January is 1, February is 2, etc.): ");
      int monthNumber = scanner.nextInt();
      scanner.nextLine();
      Month startMonth = determineMonth(monthNumber);

      System.out.println("Enter the year you started: ");
      int startYear = scanner.nextInt();
      scanner.nextLine();

      /*
       * creates a work experience with given position, month, startYear, company,
       * city, state; work experience constructor creates a responsibilities array for
       * this work experience
       */
      WorkExperience workXP = new WorkExperience(position, startMonth, startYear, company, Wcity, Wstate);

      String inner = "no";
      System.out.println("Enter responsibilities (type \"done\" when finished)");
      while (!inner.equalsIgnoreCase("done")) {
        inner = scanner.nextLine();
        if (!inner.equalsIgnoreCase("done"))
          workXP.addResponsibility(inner);
      }

      System.out.println("Are you currently working this position? (y)es or (n)o");
        String response = scanner.nextLine();
        if(response.equalsIgnoreCase("n")) {
            System.out.println("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
            monthNumber = scanner.nextInt();
            scanner.nextLine();
            Month endMonth = determineMonth(monthNumber);

            System.out.println("Enter the year you ended: ");
            int endYear = scanner.nextInt();
            scanner.nextLine();

            workXP.addEndDate(endMonth, endYear);
        }
      res.addWorkExperience(workXP);
      System.out.println("Would you like to add another work experience? (y)es or (n)o?");
      cont = scanner.nextLine();
    }

    clearScreen();

    System.out.println("Would you like to enter any extracurriculars? (y)es or (n)o?");
    cont = scanner.nextLine();

    while (cont.equalsIgnoreCase("y")) {
      System.out.println("Enter the name of the extracurricular organization: ");
      String title = scanner.nextLine();

      System.out.println("Enter your position within the organization: ");
      String position = scanner.nextLine();

      System.out.println("Enter the number of the month you joined (January is 1, February is 2, etc.): ");
      int monthNumber = scanner.nextInt();
      scanner.nextLine();
      Month startMonth = determineMonth(monthNumber);

      System.out.println("Enter the year you joined: ");
      int startYear = scanner.nextInt();
      scanner.nextLine();

      Extracurricular extrac = new Extracurricular(position, startMonth, startYear, title);

      String inner = "no";
      System.out.println("Enter responsibilities/activities (type \"done\" when finished)");
      while (!inner.equalsIgnoreCase("done")) {
        inner = scanner.nextLine();
        if (!inner.equalsIgnoreCase("done"))
          extrac.addExtracurricularActivity(inner);
      }

      System.out.println("Are you currently in this organization? (y)es or (n)o");
      if (scanner.nextLine().equalsIgnoreCase("n")) {
        System.out.println("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
        monthNumber = scanner.nextInt();
        scanner.nextLine();
        Month endMonth = determineMonth(monthNumber);

        System.out.println("Enter the year you ended: ");
        int endYear = scanner.nextInt();
        scanner.nextLine();
        extrac.addEndDate(endMonth, endYear);
      }

      res.addExtracurricular(extrac);

      System.out.println("Would you like to add another extracurricular? (y)es or (n)o?");
      cont = scanner.nextLine();
    }
    clearScreen();

    student.addResume(res); 

    System.out.println("Resume Created!");

    }


    //clears the info on the screen
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }

      private Month determineMonth(int monthNumber) {
        Month month = Month.JANUARY;
        while (monthNumber < 1 || monthNumber > 12) {
          System.out.println("Invalid Month. Please enter a number 1 through 12");
          monthNumber = scanner.nextInt();
          scanner.nextLine();
        }
    
        switch (monthNumber) {
        case 1:
          month = Month.JANUARY;
          break;
        case 2:
          month = Month.FEBRUARY;
          break;
        case 3:
          month = Month.MARCH;
          break;
        case 4:
          month = Month.APRIL;
          break;
        case 5:
          month = Month.MAY;
          break;
        case 6:
          month = Month.JUNE;
          break;
        case 7:
          month = Month.JULY;
          break;
        case 8:
          month = Month.AUGUST;
          break;
        case 9:
          month = Month.SEPTEMBER;
          break;
        case 10:
          month = Month.OCTOBER;
          break;
        case 11:
          month = Month.NOVEMBER;
          break;
        case 12:
          month = Month.DECEMBER;
        }
        return month;
      }

      private void displayResumes() {
          for(int i = 0; i < student.getResumes().size(); i++) {
              System.out.println(student.displayResume(i));
          }
      }

    public static void main(String[] args) {
        InternshipUI internshipui = new InternshipUI();
        internshipui.run();
    }
}