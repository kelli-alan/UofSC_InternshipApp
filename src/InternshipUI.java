import java.util.ArrayList;
import java.util.Scanner;
import java.time.Month;

/**
 * The user interface for the UofSC Internship App
 * @authors Robbie Clark, Evan Grunewald, Kelli Alan
 */
public class InternshipUI {
  private static final String WELCOME = "\t  Welcome to the Internship App";
  private static final String[] OPEN_OPTIONS = { "Create Account", "Log In" };
  

  /* Constants
   * welcome menus for each user type */
  private static final String[] STUDENT_OPTIONS = { "Resumes", "Listings", "Ratings", "Logout" };
  private static final String[] EMPLOYER_OPTIONS = { "Listings", "Ratings", "Logout" };
  private static final String[] MOD_OPTIONS = { "View all Resumes", "View all Listings", "Delete Resume", "Delete Listing", "Logout" };
  
  /* student resume menus */
  private static final String[] RESUME_OPTIONS = { "View Resumes", "Write Resume to File", "Create New Resume", "Edit Resume", "Back" };
  
  private static final String[] RESUME_EDIT_OPTIONS = { "Skills", "Education", "Work Experience", "Extracurricular", "Delete Resume", "Back" };

  private static final String[] EDUCATION_EDIT_OPTIONS = { "Add new Education", "Remove Education", "Edit University", "Edit Location", "Edit Degree Type", "Edit Major", "Edit Minor", "Edit Graduation Date", "Edit GPA", "Back" };

  private static final String[] WORK_EXPERIENCE_EDIT_OPTIONS = { "Add new Work Experience", "Remove Work Experience", "Edit Responsibilities", "Edit Company", "Edit Position", "Edit Location", "Edit Start Date", "Edit End Date", "Back" };
  
  private static final String[] EXTRACURRICULAR_EDIT_OPTIONS = { "Add new Extracurricular", "Remove Extracurricular", "Edit Activities", "Edit Club Title", "Edit Position", "Edit Start Date", "Edit End Date", "Back" };

  /* student listing menus */
  private static final String[] STUDENT_LISTING_OPTIONS = { "View All Listings", "View Saved Listings", "Filter Listings", "Back" };
  
  private static final String[] LISTING_LIST_OPTIONS = { "Apply", "Save", "Back" };
  private static final String[] SAVED_LISTING_OPTIONS = { "Apply", "Remove", "Back" };
  
  private static final String[] LISTING_LIST_FILTERS = { "Skills", "Hours per week", "Location", "Pay", "Back" };
  
  /* employer listing menus */
  private static final String[] EMPLOYER_LISTING_OPTIONS = { "View Listings", "Write Listing to File", "Create New Listing", "Delete Listing", "Edit Listing", "Back" };

  private static final String[] LISTING_EDIT_OPTIONS = { "Edit Job Title", "Edit location", "Edit Start Date", "Edit Hours per Week", "Edit Pay per Hour", "Edit Desired Skills", "Edit Duties", "Back" };
  
  
  private Scanner scanner;
  private InternshipApp app;
  private User user;
  private Student student;
  private Employer employer;

  /**
  * Constructor; initializes scanner for user input and InternshipApp facade
  */
  private InternshipUI() {
    scanner = new Scanner(System.in);
    app = new InternshipApp();
  }

  /**
   * Entry point of user interface; sends users to create account or login
   * Afterwards, sends users to version of app suited for their account type (student, employer, or * moderator)
   */
  public void run() {
    System.out.println(WELCOME);
    displayMainMenu();  // create account, login

    int command = scanner.nextInt();
    scanner.nextLine();

    // main menu, repeats when the user is null, meaning not logged in
    while (user == null) {
      if (command > 1 || command < OPEN_OPTIONS.length) {

        switch (command) {
        case 1:
          clearScreen();
          createAccount();
          break;
        case 2:
          clearScreen();
          Login();
          break;
        }
      }
    }

    // runs appropriate version of app depending on user type
    if (user.type == Users.STUDENT) {
      clearScreen();
      runStudent();
    } else if (user.type == Users.EMPLOYER) {
      clearScreen();
      runEmployer();
    } else if (user.type == Users.MODERATOR) {
      clearScreen();
      runModerator();
    }
  }

  /**
   * Student accounts interact with the student version of the app, until they logout
   */
  private void runStudent() {
    int command = 0;
    while (command != 4) {
      
      displayStudentOptions();
      command = scanner.nextInt();
      scanner.nextLine();
      
      switch (command) {
      case 1: // Resumes
        clearScreen();
        ResumeMenu();
        break;

      case 2: // Listings
        int choice = 0;
        while (choice != 4) {
          clearScreen();
          displayStudentListingOptions();
          choice = scanner.nextInt();
          scanner.nextLine();

          if (choice == 1) { // view all listings
            clearScreen();
            System.out.print(app.viewAllListings());
            StudentListingMenu();
          } else if (choice == 2) { // view saved listings
            clearScreen();
            SavedListingMenu();
          } else if (choice == 3) { // filter listings
            clearScreen();
            FilterMenu();
          }
        }
        clearScreen();
        break;

      case 3: // ratings
        clearScreen();
        System.out.println("Ratings coming soon");
        break;
      }
    }
    System.out.println("Log out Successful");
    app.logout();
  }

  /**
   * Employers interact with the employer version of the app, until they logout
   */
  private void runEmployer() {
    int command = 0;
    while (command != 3) {
      displayEmployerOptions();
      command = scanner.nextInt();
      scanner.nextLine();
      switch (command) {
      case 1:
        clearScreen();
        EmployerListingMenu();  // view, write to file, create, edit
        break;
      case 2:
        clearScreen();
        System.out.println("Ratings coming soon");
        break;
      }
    }
    clearScreen();
    System.out.println("Log out Successful");
    app.logout();
  }


  /**
   * Moderators interact with the moderator version of the app, until they logout
   */
  private void runModerator() {
    int command = 0;
    while (command != 5) {
      displayModOptions();
      command = scanner.nextInt();
      scanner.nextLine();
      switch (command) {
      case 1: // view all resumes
        if (app.getResumes().size() == 0) {
          System.out.println("No resumes found");
        } else {
          System.out.println(app.viewAllResumes());
          System.out.println("Enter \"back\" when you want to go back to menu");
          scanner.nextLine();
          clearScreen();
        }
        break;

      case 2: // view all listings
        if (app.getListings().size() == 0) {
          System.out.println("No listings found");
        } else {
          System.out.println(app.viewAllListings());
          System.out.println("Enter \"back\" when you want to go back to menu");
          scanner.nextLine();
          clearScreen();
        }
        break;

      case 3: // delete resume
        if (app.getResumes().size() == 0) {
          System.out.println("No resumes found");

        } else {
          System.out.println(app.viewAllResumes());
          System.out.print("Enter resume number to delete: ");
          int resumeIndex = scanner.nextInt() - 1;
          scanner.nextLine();
          app.deleteResume(app.getResumes().get(resumeIndex));
          clearScreen();
          System.out.println("Resume deleted!");
        }

        break;

      case 4: // delete listing
        if (app.getListings().size() == 0) {
          System.out.println("No listings found");
        } else {
            System.out.println(app.viewAllListings());
            System.out.print("Enter number of the listing to delete: ");
            int listingIndex = scanner.nextInt();
            scanner.nextLine();
            app.deleteListing(app.getListings().get(listingIndex-1));
            clearScreen();
            System.out.println("Listing deleted");
        }
        break;
      }

    }
    clearScreen();
    System.out.println("Log out successful");
    app.logout();
  }
  
  /**
   * Prints welcome menu for app;
   * Options: Create Account, Logout
   */
  private void displayMainMenu() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < OPEN_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + OPEN_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints student welcome menu
   * Options: Resumes, Listings, Ratings, Logout
   */
  private void displayStudentOptions() {
    System.out.println("\n\tWelcome Student: " + user.firstName + " " + user.lastName
        + "\n************** Choose an option **************");
    for (int i = 0; i < STUDENT_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + STUDENT_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints employer welcome menu
   * Options: Listings, Ratings, Logout
   */
  private void displayEmployerOptions() {
    System.out.println("\n\tWelcome Employer: " + user.firstName + " " + user.lastName
        + "\n************** Choose an option **************");
    for (int i = 0; i < EMPLOYER_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + EMPLOYER_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints moderator welcome menu;
   * Options: View Resumes, View Listings, Remove Resume, Remove Listing, Logout
   */
  private void displayModOptions() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < MOD_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + MOD_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

    
  /**
   * Prints resume menu for students;
   * Options: View, Write to File, Create, Edit, Back
   */
  private void displayResumeOptions() {
    System.out.println("\n\tResume Menu");
    for (int i = 0; i < RESUME_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + RESUME_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints menu for students to edit their resumes;
   * Options: Skills, Education, Work Experience, Extracurricular, Delete, Back
   */
  private void displayResumeEdit() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < RESUME_EDIT_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + RESUME_EDIT_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints menu for students to edit the education section of a resume;
   * Options: Add Education, Remove Education, Edit University, Edit Location, 
   * Edit Degree Type, Edit Major, Edit Minor, Edit Graduation data, Edit GPA, Back
   */
  private void displayEducationEdit() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < EDUCATION_EDIT_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + EDUCATION_EDIT_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints menu for students to edit the work experience section of a resume;
   * Options: Add Work Experience, Remove Work Experience, Edit Responsibilities, 
   * Edit Company, Edit Position, Edit Location, Edit Start Date, Edit End Date, Back 
   */
  private void displayWorkExperienceEdit() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < WORK_EXPERIENCE_EDIT_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + WORK_EXPERIENCE_EDIT_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints menu for students to edit the extracurricular section of a resume;
   * Options: Add Extracurricular, Remove Extracurricular, Edit Activities, Edit Club Title, 
   * Edit Position, Edit Start Date, Edit End Date, Back
   */
  private void displayExtracurricularEdit() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < EXTRACURRICULAR_EDIT_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + EXTRACURRICULAR_EDIT_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints options for students to view listings;
   * Options: View All, View Saved, Filter, Back
   */
  private void displayStudentListingOptions() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < STUDENT_LISTING_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + STUDENT_LISTING_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints listings options when students choose to view all listings;
   * Options: Apply, Save, Back
   */
  private void displayListingListOptions() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < LISTING_LIST_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + LISTING_LIST_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints the saved listings menu for students;
   * Options: Apply, Remove, Back
   */
  private void displaySavedListingOptions() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < SAVED_LISTING_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + SAVED_LISTING_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints filter options for constraining student search;
   * Options: Skills, Hours, Location, Pay, Back
   */
  private void displayListingFilters() {
    System.out.println("************** Choose a filter **************");
    for (int i = 0; i < LISTING_LIST_FILTERS.length; i++) {
      System.out.println((i + 1) + ". " + LISTING_LIST_FILTERS[i]);
    }
    System.out.print("\nSelection: ");
  }


  /**
   * Prints listing menu for employers;
   * Options: View, Write to File, Create, Delete, Edit, Back
   */
  private void displayEmployerListingOptions() {
    System.out.println("\n\tListing Menu");
    for (int i = 0; i < EMPLOYER_LISTING_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + EMPLOYER_LISTING_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * Prints menu for employers to edit their listings;
   * Options: Edit Job Title, Edit location, Edit Start Date, 
   * Edit Hours per Week, Edit Pay per Hour, Edit Desired Skills, Edit Duties, Back 
   */
  private void displayListingEditMenu() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < LISTING_EDIT_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + LISTING_EDIT_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  /**
   * log in method, asks for username and password and sets user if they are in list
   * Loops if credentials are invalid
   */
  private void Login() {

    System.out.println("************** Welcome Back **************\n");
    do {
      System.out.print("Enter your Username: ");
      String username = scanner.nextLine();
      System.out.print("Enter your password: ");
      String password = scanner.nextLine();
      user = app.login(username, password);

      if (user == null) {
        clearScreen();
        System.out.println("Wrong username or password. Please try again\n");
      }
    } while (user == null);
    if (user.type == Users.STUDENT) {
      student = (Student) user;
    } else if (user.type == Users.EMPLOYER) {
      employer = (Employer) user;
    }
    clearScreen();
  }

  /**
   * Creates new account of any type, 
   * does not allow duplicate usernames in the system;
   * logs in user after creation
   * */
  private void createAccount() {

    System.out.println("\t      Create Account");
    System.out.println("********** Enter Account Type **********");
    System.out.println("(S)tudent\n(E)mployer\n(M)oderator");
    System.out.print("Type: ");
    String sType = scanner.nextLine();

    // first name, last name, username, and password required for all users
    System.out.print("Enter first name: ");
    String firstName = scanner.nextLine();

    System.out.print("Enter last name: ");
    String lastName = scanner.nextLine();

    System.out.print("Enter a username: ");
    String username = scanner.nextLine();

    // error checking for unavailable username
    while (app.usernameTaken(username)) {
      System.out.println("Sorry that username is already taken!");
      System.out.print("Enter a username: ");
      username = scanner.nextLine();
    }
    System.out.print("Enter a password: ");
    String password = scanner.nextLine();

    // extra prompts for student accounts
    if (sType.equalsIgnoreCase("s")) {
      Users type = Users.STUDENT;
      System.out.print("Enter your email: ");
      String email = scanner.nextLine();
      System.out.print("Enter your phone number: ");
      String phoneNumber = scanner.nextLine();
      student = new Student(firstName, lastName, username, password, email, phoneNumber, type);
      user = student;
      app.addUser(student);
    
    } else if (sType.equalsIgnoreCase("e")) {  // extra prompts for employer accounts
      Users type = Users.EMPLOYER;
      System.out.print("Enter the name of your company: ");
      String companyName = scanner.nextLine();
      System.out.println("Enter a brief description for your company: ");
      String companyDescription = scanner.nextLine();
      Employer newEmployer = new Employer(firstName, lastName, username, password, 
                                            type, companyName, companyDescription);
      user = newEmployer;
      app.addUser(newEmployer);
    
    } else if (sType.equalsIgnoreCase("m")) {
      Users type = Users.MODERATOR;
      Moderator mod = new Moderator(firstName, lastName, username, password, type);
      user = mod;
      app.addUser(mod);
    }
    
    clearScreen();
    System.out.println("User created successfully");
  }

  /**
   * Functionality for the student resume options: viewing resumes,
   * printing resumes to a file, creating resumes, editing resumes
   */
  private void ResumeMenu() {
    displayResumeOptions();
    int command = scanner.nextInt();
    scanner.nextLine();
    
    while (command != 5) {  // returns to student welcome screen when command == 5
      switch (command) {

      case 1: // view all resumes
        clearScreen();
        if (student.getResumes().size() == 0) {
          System.out.println("You have no resumes on file! You can create one now!");
          break;
        }
        
        System.out.print(student.displayAllResumes());
        System.out.println("Enter \"back\" when you want to go back to menu");
        scanner.nextLine();
        clearScreen();
        break;

      case 2: // print to file
        clearScreen();
        if (student.getResumes().size() == 0) {
          System.out.println("You have no resumes on file! You can create one now!");
          break;
        }
        
        System.out.print(student.displayAllResumes());
        System.out.println("Enter file name to write to: ");
        String path = scanner.nextLine();

        System.out.println("Enter index of resume to write: ");
        int id = scanner.nextInt();
        String content = student.displayResume(id - 1);

        app.writeFile(content, path);

        clearScreen();
        System.out.println("Resume printed!");
        break;

      case 3: // create resume
        clearScreen();
        createResume();
        break;
      case 4: // edit resume
        clearScreen();
        if (student.getResumes().size() == 0) {
          System.out.println("You have no resumes on file! You can create one now!");
          break;
        }
        System.out.println(student.displayAllResumes());
        
        System.out.print("Enter the index of the resume to edit: ");
        int i = scanner.nextInt();
        scanner.nextLine();
        
        clearScreen();
        editResumeMenu(i);
        break;
      }

      displayResumeOptions();
      command = scanner.nextInt();
      scanner.nextLine();
    }
    clearScreen();
  }

  /**
   * Functionality for the student listing list options: applying and saving listings from
   * an all listings search
   */
  private void StudentListingMenu() {
    int command = 0;
    while (command != 3) {  // returns to student listing options when command == 3
      displayListingListOptions();
      command = scanner.nextInt();
      scanner.nextLine();
      
      switch (command) {
      case 1: // apply
        if (student.getResumes().size() == 0) {
          clearScreen();
          System.out.println(app.viewAllListings());
          System.out.println("You must create a resume before you can apply!");
          break;
        } 
      
        System.out.println("Which listing would you like to apply to?");
        int listingIndex = scanner.nextInt();
        scanner.nextLine();

        clearScreen();

        System.out.print(student.displayAllResumes());
        System.out.println("Which resume would you like to apply with?");
        int resumeIndex = scanner.nextInt();
        scanner.nextLine();

        if (app.applyToListing(app.getListings().get(listingIndex - 1).getID(),
            student.getResumes().get(resumeIndex - 1).getUUID())) {
          clearScreen();
          System.out.println("Application sent!\n\n");
        } else {
          clearScreen();
          System.out.println("Application already submitted!\n\n");
        }
        break;

      case 2: // save listing
        System.out.println("Which listing would you like to save?");
        command = scanner.nextInt();
        scanner.nextLine();
        
        if (student.saveListing(app.getListings().get(command - 1))) {
          clearScreen();
          System.out.println("Listing saved!");
        } else {
          System.out.println("Listing was already saved!");
        }
        break;
      }
    }
  }

  /**
   * Functionality for listings saved by student: applying and removing from saved list
   */
  private void SavedListingMenu() {
    int command = 0;
    while (command != 3) {  // returns to student listing options when command == 3
      clearScreen();
      System.out.println("Your saved listings:");
      System.out.print(student.viewAllSavedListings());
      
      displaySavedListingOptions();
      command = scanner.nextInt();
      scanner.nextLine();
      
      switch (command) {
      case 1: // apply
        System.out.println("Which listing would you like to apply to?");
        int listingIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.print(student.displayAllResumes());
        System.out.println("Which resume would you like to apply with?");
        int resumeIndex = scanner.nextInt();
        scanner.nextLine();

        // sends resume to correct listing, if exact resume has not already been sent
        if (app.applyToListing(student.getSavedListings().get(listingIndex - 1).getID(),
            student.getResumes().get(resumeIndex - 1).getUUID())) {
          clearScreen();
          System.out.println("Application sent!\n\n");
        } else {
          clearScreen();
          System.out.println("Application already submitted!\n\n");
        }
        break;
      
      case 2: // remove
        System.out.print("Enter the number of the listing to remove from your saved listings: ");
        listingIndex = scanner.nextInt();
        
        // removes specified saved listing if it was not previously deleted
        if (student.deleteSavedListing(student.getSavedListings().get(listingIndex - 1))) {
          clearScreen();
          System.out.println("Listing Removed");
        } else {
          System.out.println("Listing was already removed");
        }
      }
    }
  }

  /**
   * Functionality for the student listing filters: filter by skill, filter by hours per week,
   * filter by location, filter by pay;
   * Maintains ArrayList of listings that are filtered according to student's decision, 
   * then allows student to apply or save any listings from the filtered search
   */
  private void FilterMenu() {
    int command = 0;
    ArrayList<Listing> filtered = new ArrayList<Listing>();
    while (command != 5) {  // return to student listing menu when command == 5
      clearScreen();
      displayListingFilters();
      command = scanner.nextInt();
      scanner.nextLine();

      switch (command) {
      case 1: // skills
        clearScreen();
        System.out.println("Which skill are you looking for?");
        filtered = FilterListings.filterBySkill(app.getListings(), scanner.nextLine());
        
        if (filtered.size() == 0) {
          System.out.println("Sorry! No jobs found. Please check back later.");
        } else {
          System.out.print(app.viewFilteredListings(filtered));
        }
        break;

      case 2: // max hours
        clearScreen();
        System.out.println("How many hours per week can you work?");
        filtered = FilterListings.filterByHoursPerWeek(app.getListings(), scanner.nextInt());
        scanner.nextLine();
        
        if (filtered.size() == 0) {
          System.out.println("Sorry! No jobs found. Please check back later.");
          break;
        } else {
          System.out.print(app.viewFilteredListings(filtered));
        }
        break;

      case 3: // location
        clearScreen();
        System.out.println("Enter location: city or state");
        filtered = FilterListings.filterByLocation(app.getListings(), scanner.nextLine());

        if (filtered.size() == 0) {
          System.out.println("Sorry! No jobs found. Please check back later.");
          break;
        } else {
          System.out.print(app.viewFilteredListings(filtered));
        }
        break;

      case 4: // minimum pay
        clearScreen();
        System.out.println("How much would you like to make per hour?");
        filtered = FilterListings.filterByPay(app.getListings(), scanner.nextDouble());

        if (filtered.size() == 0) {
          System.out.println("Sorry! No jobs found. Please check back later.");
          break;
        } else {
          System.out.print(app.viewFilteredListings(filtered));
        }
        break;
      }

      // apply, save, back to filters options
      int choice = 0;
      while (command < 5) {
        displayListingListOptions();
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
        case 1: // apply
          System.out.println("Which listing would you like to apply to?");
          int listingIndex = scanner.nextInt();
          scanner.nextLine();

          clearScreen();

          System.out.print(student.displayAllResumes());
          System.out.println("Which resume would you like to apply with?");
          int resumeIndex = scanner.nextInt();
          scanner.nextLine();

          if (app.applyToListing(filtered.get(listingIndex - 1).getID(),
              student.getResumes().get(resumeIndex - 1).getUUID())) {
            System.out.println("Application sent!\n\n");
          } else {
            System.out.println("Application already submitted!\n\n");
          }
          break;

        case 2: // save
          System.out.println("Which listing would you like to save?");
          listingIndex = scanner.nextInt();
          scanner.nextLine();

          if (student.saveListing(filtered.get(listingIndex - 1))) {
            System.out.println("Listing saved!");
          } else {
            System.out.println("Listing was already saved!");
          }
          break;

        case 3: // return to listing filters menu when choice == 3
          command = 6;
          break;
        }
      }
    }
  }


  /**
   * Functionality for the employer listing options: viewing their listings, 
   * writing a listing to a file, creating listing, deleting listings, editing listings
   */
  private void EmployerListingMenu() {
    displayEmployerListingOptions();
    int command = scanner.nextInt();
    scanner.nextLine();
    
    while (command != 6) {  // returns to employer welcome screen when command == 6
      switch (command) {
      case 1: // view all listings
        clearScreen();
        System.out.println(app.displayListingsWithApplications(employer.getID()));
        break;
      
      case 2: // write listing to file
        clearScreen();
        System.out.println(employer.displayAllListings());
        System.out.print("Enter file name to write to: ");
        String path = scanner.nextLine();
        
        System.out.print("Enter index of listing to write: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        String content = employer.getListings().get(id - 1).toString();
        app.writeFile(content, path);

        clearScreen();
        System.out.println("Listing printed!");
        break;
      
      case 3: // create listing
        clearScreen();
        createListing();
        break;
      
      case 4: // delete listing
        clearScreen();
        System.out.println(employer.displayAllListings());
        
        System.out.print("Enter the index of the listing to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        employer.deleteListing(index);
        break;
      
      case 5: // edit listing
        clearScreen();
        System.out.println(employer.displayAllListings());
        
        System.out.print("Enter the index of the listing to edit: ");
        int i = scanner.nextInt();
        scanner.nextLine();
        
        clearScreen();
        editListingMenu(i);
        break;
      }
      displayEmployerListingOptions();
      command = scanner.nextInt();
      scanner.nextLine();
    }
    clearScreen();
  }

  /**
   * Prompts the student through each field of a typical resume and creates a resume for them. 
   * The new resume is added to their ArrayList of resumes
   */
  private void createResume() {

    Resume res = new Resume();
    
    clearScreen();
    System.out.println("************** Resume Creator **************\n");
    
    String cont = "y";
    System.out.println("Enter your skills (enter \"done\" when finished): ");
    
    while (cont.equalsIgnoreCase("y")) {
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
      System.out.println("\tEducation");
      addEducation(res);
      System.out.println("Would you like to enter another education? (y)es or (n)o?");
      cont = scanner.nextLine();
    }

    clearScreen();

    System.out.println("Would you like to enter any work experience? (y)es or (n)o?");
    cont = scanner.nextLine();

    while (cont.equalsIgnoreCase("y")) {
      System.out.println("\tWork Experience");
      addWorkExperience(res);
      System.out.println("Would you like to add another work experience? (y)es or (n)o?");
      cont = scanner.nextLine();
    }

    clearScreen();

    System.out.println("Would you like to enter any extracurriculars? (y)es or (n)o?");
    cont = scanner.nextLine();

    while (cont.equalsIgnoreCase("y")) {
      System.out.println("\tExtracurricular");
      addExtracurricular(res);
      System.out.println("Would you like to add another extracurricular? (y)es or (n)o?");
      cont = scanner.nextLine();
    }
    clearScreen();

    student.addResume(res);

    System.out.println("Resume Created!");

  }

  /**
   * Prompts employers for information about their listing. Creates a listing based on 
   * given information and adds it to the employer's ArrayList of listings
   */
  private void createListing() {
    Listing listing = new Listing();

    System.out.println("************** Listing Creator **************\n");
    
    // obtain valid job title from employer account
    do {
      System.out.print("Enter job title: ");
      listing.setJobTitle(scanner.nextLine());

      if (listing.getJobTitle() == null) {
        System.out.println("Invalid job title!");
      }

    } while (listing.getJobTitle() == null);

    // obtain valid city
    do {
      System.out.print("Enter the city the position is located in: ");
      listing.setCity(scanner.nextLine());

      if (listing.getCity() == null) {
        System.out.println("Invalid city!");
      }

    } while (listing.getCity() == null);

    // obtain valid state
    do {
      System.out.print("Enter the state the position is located in: ");
      listing.setState(scanner.nextLine());

      if (listing.getState() == null) {
        System.out.println("Invalid state name! Provide state abbreviation or full state name");
      }

    } while (listing.getState() == null);

    // obtain valid start month
    do {
      System.out.print("Enter the start month for the position (January is 1, February is 2, etc.): ");
      listing.setStartMonth(scanner.nextInt());
      scanner.nextLine();

      if (listing.getStartMonth() == null) {
        System.out.println("Invalid month. Please enter a number 1 through 12");
      }

    } while (listing.getStartMonth() == null);

    // obtain valid hours per week
    do {
      System.out.print("Enter hours per week: ");
      listing.setHoursPerWeek(scanner.nextInt());
      scanner.nextLine();

      if (listing.getHoursPerWeek() == 0) {
        System.out.println("Invalid number of hours. Enter a whole number from 1 to 112");
      }

    } while (listing.getHoursPerWeek() == 0);

    // obtain pay, optional
    System.out.print("Enter pay per hour or 0 if you don't wish to specify: $ ");
    listing.setPay(scanner.nextDouble());
    scanner.nextLine();

    System.out.println("Enter \"true\" if the internship is remote or \"false\" if it is not:");
    listing.setRemote(scanner.nextBoolean());

    clearScreen();

    while (true) {
      System.out.println("Enter duties for this position (Enter \"done\" when finished): ");
      String duty = scanner.nextLine();
      if (duty.equalsIgnoreCase("done")) {
        break;
      }
      listing.addDuties(duty);
    }

    clearScreen();

    while (true) {
      System.out.println("Enter skills for this position (Enter \"done\" when finished");
      String skill = scanner.nextLine();
      if (skill.equalsIgnoreCase("done")) {
        break;
      }
      listing.addSkills(skill);
    }

    app.addListing(listing);

    clearScreen();
    System.out.println("Listing Created!");
  }

  /**
   * Functionality for the student resume edit menu; students can edit any section of the 
   * resume specified by 'i' or delete the resume entirely; the updated resume is available in 
   * the student's ArrayList of resumes
   * @param i index of resume student wants to edit chosen from a display of all their resumes
   */
  private void editResumeMenu(int i) {
    Resume resume = student.getResumes().get(i - 1);

    System.out.println("Enter the number for the section you would like to edit.");
    displayResumeEdit();
    int command = scanner.nextInt();
    scanner.nextLine();
    if (command < 6) {

      switch (command) {
      case 1:
        clearScreen();
        skillsEditor(resume);
        break;
      case 2:
        clearScreen();
        educationEditor(resume);
        break;
      case 3:
        clearScreen();
        workExperienceEditor(resume);
        break;
      case 4:
        clearScreen();
        extracurricularEditor(resume);
        break;
      }
    }
    if (command == 5) {
      clearScreen();
      student.getResumes().remove(i - 1);
      System.out.println("Resume removed");
    } else
      student.getResumes().set(i - 1, resume);
  }

  /**
   * Updates skills section of resume specified by student
   * @param resume student is editing
   */
  private void skillsEditor(Resume resume) {
    String res = "s";
    
    while (res.equalsIgnoreCase("done")) {
      clearScreen();
      System.out.println("Would you like to (a)dd or (r)emove skills? Enter \"done\" when you are done");
      res = scanner.nextLine();
      
      if (res.equalsIgnoreCase("a")) {
        System.out.print("Enter the skill you would like to add: ");
        res = scanner.nextLine();
        resume.addSkill(res);
      } else if (res.equalsIgnoreCase("r")) {
        System.out.println(resume.displaySkills());
        System.out.print("Enter the Index of the skill you would like to remove: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        resume.deleteSkill(index);
      }
    }
  }

  /**
   * Updates education section of resume specified by student
   * @param resume student is editing
   */
  private void educationEditor(Resume resume) {
    displayEducationEdit();
    int index;
    String change;
    int command = scanner.nextInt();
    scanner.nextLine();
    
    while (command != 10) {
      switch (command) {
        case 1: // add a new education
          clearScreen();
          addEducation(resume);
          break;
      
        case 2: // delete a education
          clearScreen();
          System.out.println(resume.displayEducations());
        
          System.out.print("Enter the index number of the education to delete: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
        
          resume.deleteEducation(index);
          break;
      
        case 3: // edit university of existing education
          clearScreen();
          System.out.println(resume.displayEducations());
        
          System.out.print("Enter the index number of the education to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          clearScreen();
        
          System.out.print("Enter the University: ");
          change = scanner.nextLine();
          resume.getEducations().get(index).setUniversity(change);
          break;

        case 4: // edit location of existing education (city and state)
          clearScreen();
          System.out.println(resume.displayEducations());
          System.out.print("Enter the index number of the education to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
        
          clearScreen();
        
          System.out.print("Enter the city: ");
          change = scanner.nextLine();
          resume.getEducations().get(index).setCity(change);
        
          System.out.print("Enter the state: ");
          change = scanner.nextLine();
          resume.getEducations().get(index).setState(change);
          break;
      
        case 5: // edit degree type of existing education
          clearScreen();
          System.out.println(resume.displayEducations());
          System.out.print("Enter the index number of the education to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
        
          clearScreen();
        
          System.out.print("Enter the degree type: ");
          change = scanner.nextLine();
          resume.getEducations().get(index).setDegreeType(change);
          break;

        case 6: // edit major of existing education
          clearScreen();
          System.out.println(resume.displayEducations());
          System.out.print("Enter the index number of the education to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
        
          clearScreen();
        
          System.out.print("Enter the major: ");
          change = scanner.nextLine();
          resume.getEducations().get(index).setMajor(change);
          break;

        case 7: // edit minor of an existing education
          clearScreen();
          System.out.println(resume.displayEducations());
          System.out.print("Enter the index number of the education to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
        
          clearScreen();
        
          System.out.print("Enter the minor: ");
          change = scanner.nextLine();
          resume.getEducations().get(index).addMinor(change);
          break;

        case 8: // edit grad date of existing education
          clearScreen();
          System.out.println(resume.displayEducations());
          System.out.print("Enter the index number of the education to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
        
          clearScreen();
        
          System.out.print("Enter the number of the month you graduate (January is 1, February is 2, etc.): ");
          int monthNumber = scanner.nextInt();
          scanner.nextLine();
          Month month = Month.values()[monthNumber-1];
          resume.getEducations().get(index).setGradMonth(month);

          System.out.print("Enter the year you graduate: ");
          int year = scanner.nextInt();
          scanner.nextLine();
          resume.getEducations().get(index).setGradYear(year);
          break;

        case 9:
          clearScreen();
          System.out.println(resume.displayEducations());
          System.out.print("Enter the index number of the education to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
        
          clearScreen();
        
          System.out.print("Enter the GPA: ");
          int GPA = scanner.nextInt();
          scanner.nextLine();
          resume.getEducations().get(index).addGPA(GPA);
          break;
      }
      clearScreen();
      displayEducationEdit();
      command = scanner.nextInt();
      scanner.nextLine();
    }
  }

  /**
   * Updates work experience section of resume specified by student
   * @param resume student is editing
   */
  private void workExperienceEditor(Resume resume) {
    displayWorkExperienceEdit();
    int index;
    String change;
    int command = scanner.nextInt();
    scanner.nextLine();
    
    while (command != 9) {
      switch (command) {
        case 1: // add new work experience
          clearScreen();
          addWorkExperience(resume);
          break;

        case 2: // delete a work experience
          clearScreen();
          System.out.println(resume.displayWorkExperiences());
          System.out.print("Enter the index number of the work experience to delete: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          resume.deleteWorkExperience(index);
          break;

        case 3: // edit responsibilities of existing work experience
          clearScreen();
          System.out.println(resume.displayWorkExperiences());
          System.out.print("Enter the index number of the work experience to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          responsibilitiesEditor(resume, index);
          break;

        case 4: // edit company of existing work experience
          clearScreen();
          System.out.println(resume.displayWorkExperiences());
          System.out.print("Enter the index number of the work experience to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          System.out.print("Enter the company title: ");
          change = scanner.nextLine();
          resume.getWorkExperiences().get(index).setCompany(change);
          break;

        case 5: // edit position name of existing work experience
          clearScreen();
          System.out.println(resume.displayWorkExperiences());
          System.out.print("Enter the index number of the work experience to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          System.out.print("Enter the position: ");
          change = scanner.nextLine();
          resume.getWorkExperiences().get(index).setPosition(change);
          break;

        case 6: // edit location (city and state) of existing work experience
          clearScreen();
          System.out.println(resume.displayWorkExperiences());
          System.out.print("Enter the index number of the work experience to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          System.out.print("Enter the city: ");
          change = scanner.nextLine();
          resume.getWorkExperiences().get(index).setCity(change);
          
          System.out.print("Enter the state: ");
          change = scanner.nextLine();
          resume.getWorkExperiences().get(index).setState(change);
          break;

        case 7: // edit start date of existing work experience
          clearScreen();
          System.out.println(resume.displayWorkExperiences());
          System.out.print("Enter the index number of the work experience to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          System.out.print("Enter the number of the month you started (January is 1, February is 2, etc.): ");
          int monthNumber = scanner.nextInt();
          scanner.nextLine();
          resume.getWorkExperiences().get(index).setStartMonth(monthNumber - 1);

          System.out.print("Enter the year you started: ");
          int year = scanner.nextInt();
          scanner.nextLine();
          resume.getWorkExperiences().get(index).setStartYear(year);
          break;

        case 8: // edit end date of existing work experience
          clearScreen();
          System.out.println(resume.displayWorkExperiences());
          System.out.print("Enter the index number of the work experience to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          System.out.print("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
          monthNumber = scanner.nextInt();
          scanner.nextLine();
          Month month = Month.values()[monthNumber-1];
          
          System.out.print("Enter the year you ended: ");
          year = scanner.nextInt();
          scanner.nextLine();
          resume.getWorkExperiences().get(index).addEndDate(month, year);
          break;
      }

      clearScreen();
      displayWorkExperienceEdit();
      command = scanner.nextInt();
      scanner.nextLine();
    }
  }

  /**
   * Updates responsibilities of a given work experience on a given resume
   * @param resume student is editing
   * @param index of work experience on resume being edited
   */
  private void responsibilitiesEditor(Resume resume, int index) {
    String res = "s";
    
    while (res.equalsIgnoreCase("done")) {
      clearScreen();
      
      System.out.println("Would you like to (a)dd or (r)emove responsibilities? Enter \"done\" when you are done");
      res = scanner.nextLine();
      
      if (res.equalsIgnoreCase("a")) {
        System.out.print("Enter the responsibility you would like to add: ");
        res = scanner.nextLine();
        resume.getWorkExperiences().get(index).addResponsibility(res);
      } else if (res.equalsIgnoreCase("r")) {
        System.out.println(resume.getWorkExperiences().get(index).displayResponsibilities());
        System.out.print("Enter the Index of the responsibility you would like to remove: ");
        int i = scanner.nextInt() - 1;
        scanner.nextLine();
        resume.getWorkExperiences().get(index).getResponsibilities().remove(i);
      }
    }
  }

  /**
   * Updates extracurricular section of a given resume
   * @param resume student is editing
   */
  private void extracurricularEditor(Resume resume) {
    displayExtracurricularEdit();
    int command = scanner.nextInt();
    scanner.nextLine();
    int index;
    String change;
    
    while (command != 8) {
      switch (command) {
        case 1: // add new extracurricular
          clearScreen();
          addExtracurricular(resume);
          break;

        case 2: // remove by index
          clearScreen();
          System.out.println(resume.displayExtracurriculars());
          System.out.print("Enter the index number of the extracurricular to delete: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          resume.deleteExtraCurricular(index);
          break;

        case 3: // edit activities of existing extracurricular
          clearScreen();
          System.out.println(resume.displayExtracurriculars());
          System.out.print("Enter the index number of the extracurricular to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          activitiesEditor(resume, index);
          break;

        case 4: // edit title of existing extracurricular
          clearScreen();
          System.out.println(resume.displayExtracurriculars());
          System.out.print("Enter the index number of the extracurricular to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          System.out.print("Enter the title of the extracurricular: ");
          change = scanner.nextLine();
          resume.getExtracurriculars().get(index).setTitle(change);
          break;

        case 5: // edit position of existing extracurricular
          clearScreen();
          System.out.println(resume.displayExtracurriculars());
          System.out.print("Enter the index number of the extracurricular to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          System.out.print("Enter your position in the extracurricular: ");
          change = scanner.nextLine();
          resume.getExtracurriculars().get(index).setPosition(change);
          break;

        case 6: // edit start date of existing extracurricular
          clearScreen();
          System.out.println(resume.displayExtracurriculars());
          System.out.print("Enter the index number of the extracurricular to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          System.out.print("Enter the number of the month you started (January is 1, February is 2, etc.): ");
          int monthNumber = scanner.nextInt();
          scanner.nextLine();
          resume.getExtracurriculars().get(index).setStartMonth(monthNumber-1);

          System.out.print("Enter the year you started: ");
          int year = scanner.nextInt() - 1;
          scanner.nextLine();
          resume.getExtracurriculars().get(index).setStartYear(year);
          break;

        case 7: // edit end date of existing extracurricular
          clearScreen();
          System.out.println(resume.displayExtracurriculars());
          System.out.print("Enter the index number of the extracurricular to edit: ");
          index = scanner.nextInt() - 1;
          scanner.nextLine();
          
          clearScreen();
          
          System.out.print("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
          monthNumber = scanner.nextInt();
          scanner.nextLine();
          Month month = Month.values()[monthNumber-1];
          
          System.out.print("Enter the year you ended: ");
          year = scanner.nextInt();
          scanner.nextLine();
          resume.getExtracurriculars().get(index).addEndDate(month, year);
          break;
      }

      clearScreen();
      displayExtracurricularEdit();
      command = scanner.nextInt();
      scanner.nextLine();
    }
  }

  /**
   * Updates activities of a given extracurricular on a given resume
   * @param resume student is editing
   * @param index of extracurricular to edit
   */
  private void activitiesEditor(Resume resume, int index) {
    String res = "s";
    
    while (res.equalsIgnoreCase("done")) {
      clearScreen();
      System.out.println("Would you like to (a)dd or (r)emove activities? Enter \"done\" when you are done");
      res = scanner.nextLine();
      
      if (res.equalsIgnoreCase("a")) {
        System.out.print("Enter the activity you would like to add: ");
        res = scanner.nextLine();
        resume.getExtracurriculars().get(index).addExtracurricularActivity(res);
      } else if (res.equalsIgnoreCase("r")) {
        System.out.println(resume.getExtracurriculars().get(index).displayActivities());
        System.out.print("Enter the Index of the activity you would like to remove: ");
        
        int i = scanner.nextInt() - 1;
        scanner.nextLine();
        resume.getExtracurriculars().get(index).getActivities().remove(i);
      }
    }
  }

  /**
   * Prompts a student for all information needed to create a new education; adds the new education 
   * the specified resume
   * @param resume student is adding education to
   */
  private void addEducation(Resume resume) {
    clearScreen();
    System.out.print("Enter the university: ");
    String university = scanner.nextLine();

    System.out.print("Enter the city: ");
    String city = scanner.nextLine();

    System.out.print("Enter the state: ");
    String state = scanner.nextLine();

    System.out.print("Enter the degree type: ");
    String degreeType = scanner.nextLine();

    System.out.print("Enter your major: ");
    String major = scanner.nextLine();

    System.out.print("Enter the number of your graduation month (January is 1, February is 2, etc.): ");
    int monthNumber = scanner.nextInt();
    scanner.nextLine();
    Month gradMonth = Month.values()[monthNumber-1];

    System.out.print("Enter your graduation year: ");
    int gradYear = scanner.nextInt();
    scanner.nextLine();

    Education edu = new Education(university, city, state, degreeType, major, gradMonth, gradYear);

    System.out.print("Enter your minor (enter \"none\" if you don't have one): ");
    String minor = scanner.nextLine();
    if (!minor.equalsIgnoreCase("none"))
      edu.addMinor(minor);

    System.out.print("Enter your GPA (Enter -1 if you do not wish to include a GPA): ");
    double gpa = scanner.nextDouble();
    scanner.nextLine();
    if (gpa != -1)
      edu.addGPA(gpa);

    resume.addEducation(edu);
  }

  /**
   * Prompts a student for all information needed to create a new work experience; adds the new 
   * work experience to the specified resume
   * @param resume student is adding work experience to
   */
  private void addWorkExperience(Resume resume) {
    clearScreen();
    System.out.print("Enter the position: ");
    String position = scanner.nextLine();

    System.out.print("Enter the company: ");
    String company = scanner.nextLine();

    System.out.print("Enter the city: ");
    String Wcity = scanner.nextLine();

    System.out.print("Enter the state: ");
    String Wstate = scanner.nextLine();

    System.out.print("Enter the number of the month you started (January is 1, February is 2, etc.): ");
    int monthNumber = scanner.nextInt();
    scanner.nextLine();
    Month startMonth = Month.values()[monthNumber-1];

    System.out.print("Enter the year you started: ");
    int startYear = scanner.nextInt();
    scanner.nextLine();

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
    if (response.equalsIgnoreCase("n")) {
      System.out.print("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
      monthNumber = scanner.nextInt();
      scanner.nextLine();
      Month endMonth = Month.values()[monthNumber-1];

      System.out.print("Enter the year you ended: ");
      int endYear = scanner.nextInt();
      scanner.nextLine();

      workXP.addEndDate(endMonth, endYear);
    }
    resume.addWorkExperience(workXP);
  }

  /**
   * Prompts a student for all information needed to create a new extracurricular; adds the new  
   * extracurricular to the specified resume
   * @param resume student is adding an extracurricular to
   */
  private void addExtracurricular(Resume resume) {
    clearScreen();
    System.out.print("Enter the name of the extracurricular organization: ");
    String title = scanner.nextLine();

    System.out.print("Enter your position within the organization: ");
    String position = scanner.nextLine();

    System.out.print("Enter the number of the month you joined (January is 1, February is 2, etc.): ");
    int monthNumber = scanner.nextInt();
    scanner.nextLine();
    Month startMonth = Month.values()[monthNumber-1];

    System.out.print("Enter the year you joined: ");
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

    System.out.print("Are you currently in this organization? (y)es or (n)o: ");
    if (scanner.nextLine().equalsIgnoreCase("n")) {
      System.out.print("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
      monthNumber = scanner.nextInt();
      scanner.nextLine();
      Month endMonth = Month.values()[monthNumber-1];

      System.out.print("Enter the year you ended: ");
      int endYear = scanner.nextInt();
      scanner.nextLine();
      extrac.addEndDate(endMonth, endYear);
    }

    resume.addExtracurricular(extrac);
  }

  /**
   * Functionality for employer's edit listing menu; employers can edit any field in a specified 
   * listing, 'i'
   * @param i index of employer's listing they want to edit; chosen from the listing edit menu
   */
  private void editListingMenu(int i) {
    Listing listing = employer.getListings().get(i - 1);
    clearScreen();
    displayListingEditMenu();
    int command = scanner.nextInt();
    scanner.nextLine();
    String change;
    
    while (command != 8) {
      switch (command) {
        case 1: // job title
          clearScreen();
          System.out.print("Enter the job title: ");
          change = scanner.nextLine();
          listing.setJobTitle(change);
          break;

        case 2: // location
          clearScreen();
          System.out.print("Is this listing remote? (y)es or (n)o: ");
          change = scanner.nextLine();
          if (change.equalsIgnoreCase("y")) {
            listing.setRemote(true);
          } else {
            listing.setRemote(false);
          }

          System.out.print("Enter the city of your business: ");
          change = scanner.nextLine();
          listing.setCity(change);
          
          System.out.print("Enter the state of your business: ");
          change = scanner.nextLine();
          listing.setState(change);
          break;

        case 3: // start date
          clearScreen();
          System.out.print("Enter the number of the month the internship starts (January is 1, February is 2, etc.): ");
          int monthNumber = scanner.nextInt();
          scanner.nextLine();
          
          System.out.print("Enter the year the internship starts: ");
          int year = scanner.nextInt();
          scanner.nextLine();
          
          listing.setStartMonth(monthNumber-1);
          listing.setStartYear(year);
          break;

        case 4: // hours
          clearScreen();
          System.out.print("Enter the hours per week: ");
          int hours = scanner.nextInt();
          scanner.nextLine();
          listing.setHoursPerWeek(hours);
          break;
        
        case 5: // pay
          clearScreen();
          System.out.print("Enter the pay per hour: ");
          double pay = scanner.nextDouble();
          scanner.nextLine();
          listing.setPay(pay);
          break;

        case 6: // skills
          clearScreen();
          desiredSkillsEditor(listing);
          break;
        case 7: // duties
          clearScreen();
          dutiesEditor(listing);
          break;
        }
      
      clearScreen();
      displayListingEditMenu();
      command = scanner.nextInt();
      scanner.nextLine();
    }
    employer.getListings().set(i - 1, listing);
  }

  /**
   * Updates desired skills of a given listing
   * @param listing employer is editing
   */
  private void desiredSkillsEditor(Listing listing) {
    String res = "s";
    while (res.equalsIgnoreCase("done")) {
      clearScreen();
      System.out.println("Would you like to (a)dd or (r)emove desired skills? Enter \"done\" when you are done");
      res = scanner.nextLine();
      if (res.equalsIgnoreCase("a")) {
        System.out.print("Enter the skill you would like to add: ");
        res = scanner.nextLine();
        listing.addSkills(res);
      } else if (res.equalsIgnoreCase("r")) {
          System.out.println(listing.displayDesiredSkills());
          System.out.print("Enter the Index of the activity you would like to remove: ");
          int i = scanner.nextInt() - 1;
          scanner.nextLine();
          listing.getSkills().remove(i);
      }
    }
  }

  /**
   * Updates duties listing of a given listing
   * @param listing employer is editing
   */
  private void dutiesEditor(Listing listing) {
    String res = "s";
    while (res.equalsIgnoreCase("done")) {
      clearScreen();
      System.out.println("Would you like to (a)dd or (r)emove duties? Enter \"done\" when you are done");
      res = scanner.nextLine();
      if (res.equalsIgnoreCase("a")) {
        System.out.print("Enter the duty you would like to add: ");
        res = scanner.nextLine();
        listing.addDuties(res);
      } else if (res.equalsIgnoreCase("r")) {
          System.out.println(listing.displayDuties());
          System.out.print("Enter the Index of the duty you would like to remove: ");
          int i = scanner.nextInt() - 1;
          scanner.nextLine();
          listing.getDuties().remove(i);
      }
    }
  }

 /**
   * Helper method used to clear the console UI
   */
  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void main(String[] args) {
    InternshipUI internshipUI = new InternshipUI();
    internshipUI.run();
  }
}