import java.util.Scanner;
import java.time.Month;

/**
 * 
 * @author Robbie Clark
 */
public class InternshipUI {
  private static final String WELCOME = "\t  Welcome to the Internship App";
  private static final String[] OPEN_OPTIONS = { "Create Account", "Log In" };
  private static final String[] EMPLOYER_OPTIONS = { "Listings", "Ratings", "Logout" };
  private static final String[] STUDENT_OPTIONS = { "Resumes", "View All Internship Listings",
      "View Internship Listings by Filter", "Ratings", "Logout" };
  private static final String[] RESUME_OPTIONS = { "View Resumes", "Create New Resume", "Edit Resume", "Back" };
  private static final String[] LISTING_OPTIONS = { "View Listings", "Create New Listing", "Edit Listing", "Back" };
  private static final String[] LISTING_LIST_OPTIONS = { "Apply", "Save", "Back" };
  private static final String[] LISTING_LIST_FILTERS = { "Skills", "Hours per week", "Location", "Pay" };
  private static final String[] RESUME_EDIT_OPTIONS = {"Skills", "Education", "Work Experience", "Extracurricular", "Back"};

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

    // student's menu, displays student choices
    if (user.type == Users.STUDENT) {
      while (command != 4) {
        displayStudentOptions();
        command = scanner.nextInt();
        scanner.nextLine();
        switch (command) {
        case 1:
          clearScreen();
          ResumeMenu();
          break;
        case 2:
          clearScreen();
          app.viewAllListings();
          displayListingListOptions();
          int choice = scanner.nextInt();
          scanner.nextLine();
          if (choice == 1) {
            System.out.println("Which listing would you like to apply to?");
            choice = scanner.nextInt();
            scanner.nextLine();

          } // apply
          else if (choice == 2) {
            System.out.println("Which listing would you like to save?");
            choice = scanner.nextInt();
            scanner.nextLine();
            // student.saveListing();
          } // save
          break;
        case 3:
          System.out.println("Ratings coming soon");
          break;
        }
      }
      clearScreen();
      System.out.println("Log out Successful");
      app.logout();

    }
    // employer menu
    else if (user.type == Users.EMPLOYER) {
      while (command != 3) {
        displayEmployerOptions();
        command = scanner.nextInt();
        scanner.nextLine();
        switch (command) {
        case 1:
          clearScreen();
          ListingMenu();
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
  }

  // displays the text options for the main menu
  private void displayMainMenu() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < OPEN_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + OPEN_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  // displays the ListingList menu
  private void displayListingListOptions() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < LISTING_LIST_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + LISTING_LIST_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  // displays the employer menu
  private void displayEmployerOptions() {
    System.out.println("\n\tWelcome Employer: " + user.firstName + " " + user.lastName
        + "\n************** Choose an option **************");
    for (int i = 0; i < EMPLOYER_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + EMPLOYER_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  // displays the student menu
  private void displayStudentOptions() {
    System.out.println("\n\tWelcome Student: " + user.firstName + " " + user.lastName
        + "\n************** Choose an option **************");
    for (int i = 0; i < STUDENT_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + STUDENT_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  // displays the resume menu
  private void displayResumeOptions() {
    System.out.println("\n\tResume Menu");
    for (int i = 0; i < RESUME_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + RESUME_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  // displays the employer's listing menu
  private void displayListingOptions() {
    System.out.println("\n\tResume Menu");
    for (int i = 0; i < LISTING_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + LISTING_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  private void displayListingFilters() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < LISTING_LIST_FILTERS.length; i++) {
      System.out.println((i + 1) + ". " + LISTING_LIST_FILTERS[i]);
    }
    System.out.print("\nSelection: ");
  }
  
  private void displayResumeEdit() {
    System.out.println("************** Choose an option **************");
    for (int i = 0; i < RESUME_EDIT_OPTIONS.length; i++) {
      System.out.println((i + 1) + ". " + RESUME_EDIT_OPTIONS[i]);
    }
    System.out.print("\nSelection: ");
  }

  // log in method, asks for username and password and sets user if they are in
  // the list
  private void Login() {

    System.out.println("\tLog In\n");
    do {
      System.out.print("Enter your Username: ");
      String username = scanner.nextLine();
      System.out.print("Enter your password: ");
      String password = scanner.nextLine();
      user = app.login(username, password);
    } while (user == null);
    if (user.type == Users.STUDENT) {
      student = (Student) user;
    } else if (user.type == Users.EMPLOYER) {
      employer = (Employer) user;
    }
    clearScreen();
  }

  // creates new account of any type, logs in user after creation
  private void createAccount() {
    // TO DO Add writing functionality, check if there is already a user with the
    // same username

    System.out.println("\tCreate Account");
    System.out.println("********** Enter Account Type **********");
    System.out.println("(S)tudent\n(E)mployer\n(M)oderator");
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
      // createResume();
      user = student;
      app.addUser(student);
    } else if (sType.equalsIgnoreCase("e")) {
      Users type = Users.EMPLOYER;
      System.out.print("Enter the name of your company: ");
      String companyName = scanner.nextLine();
      System.out.println("Enter a brief description for your company: ");
      String companyDescription = scanner.nextLine();
      Employer newEmployer = new Employer(firstName, lastName, username, password, type, companyName,
          companyDescription);
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

  // functionality for resume menu
  private void ResumeMenu() {
    displayResumeOptions();
    int command = scanner.nextInt();
    scanner.nextLine();
    while (command != 4) {
      switch (command) {
      case 1:
        clearScreen();
        student.displayAllResumes();
        System.out.println("Enter \"back\" when you want to go back to menu");
        scanner.nextLine();
        clearScreen();
        break;
      case 2:
        clearScreen();
        createResume();
        break;
      case 3: // edit resume
        break;
      }

      displayResumeOptions();
      command = scanner.nextInt();
      scanner.nextLine();
    }
  }

  // functionality for listing menu
  private void ListingMenu() {
    displayListingOptions();
    int command = scanner.nextInt();
    scanner.nextLine();
    while (command != 4) {
      switch (command) {
      case 1:
        app.viewAllListings();
        break;
      case 2: // create listing
        break;
      case 3: // edit listing
        break;
      }
      displayListingOptions();
      command = scanner.nextInt();
      scanner.nextLine();
    }
  }

  private void createResume() {

    Resume res = new Resume();

    clearScreen();
    String cont = "y";

    while (cont.equalsIgnoreCase("y")) {
      System.out.println("Enter your skills (enter \"done\" when finished): "); // take out of for loop
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
      Month gradMonth = Month.values()[monthNumber];

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
      Month startMonth = Month.values()[monthNumber];

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
      if (response.equalsIgnoreCase("n")) {
        System.out.println("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
        monthNumber = scanner.nextInt();
        scanner.nextLine();
        Month endMonth = Month.values()[monthNumber];

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
      Month startMonth = Month.values()[monthNumber];

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
        Month endMonth = Month.values()[monthNumber];

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

  public void createListing() {
    Listing listing = new Listing();

    // obtain valid job title from employer account
    do {
      System.out.println("Enter job title: ");
      listing.setJobTitle(scanner.nextLine());

      if (listing.getJobTitle() == null) {
        System.out.println("Invalid job title!");
      }

    } while (listing.getJobTitle() == null);

    // obtain valid city
    do {
      System.out.println("Enter the city the position is located in: ");
      listing.setCity(scanner.nextLine());

      if (listing.getCity() == null) {
        System.out.println("Invalid city!");
      }

    } while (listing.getCity() == null);

    // obtain valid state
    do {
      System.out.println("Enter the state the position is located in: ");
      listing.setState(scanner.nextLine());

      if (listing.getState() == null) {
        System.out.println("Invalid state name! Provide state abbreviation or full state name");
      }

    } while (listing.getState() == null);

    // obtain valid start month
    do {
      System.out.println("Enter the start month for the position (January is 1, February is 2, etc.): ");
      listing.setStartMonth(scanner.nextInt());
      scanner.nextLine();

      if (listing.getStartMonth() == null) {
        System.out.println("Invalid month. Please enter a number 1 through 12");
      }

    } while (listing.getStartMonth() == null);

    // obtain valid hours per week
    do {
      System.out.println("Enter hours per week: ");
      listing.setHoursPerWeek(scanner.nextInt());
      scanner.nextLine();

      if (listing.getHoursPerWeek() == 0) {
        System.out.println("Invalid number of hours. Enter a whole number from 1 to 168");
      }

    } while (listing.getHoursPerWeek() == 0);

    // obtain pay, optional
    System.out.println("Enter pay per hour or 0 if you don't wish to specify: ");
    listing.setPay(scanner.nextDouble());
    scanner.nextLine();
  
    System.out.println("Enter \"true\" if the internship is remote or \"false\" if it is not:");
    listing.setRemote(scanner.nextBoolean());

    clearScreen();

    
    while(true) {
      System.out.println("Enter duties for this position (Enter \"done\" when finished): ");
      String duty = scanner.nextLine();
      if (duty.equalsIgnoreCase("done")) {
        break;
      }
      listing.addDuties(duty);
    }

    clearScreen();

    while(true) {
      System.out.println("Enter skills for this position (Enter \"done\" when finished");
      String skill = scanner.nextLine();
      if (skill.equalsIgnoreCase("done")) {
        break;
      }
      listing.addSkills(skill);
    }

    //this.internshipListings.add(list);

    clearScreen();
    System.out.println("Listing Created!");
  }
   
  // clears the info on the screen
  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

private void editResumeMenu(int i) {
  Resume resume = student.getResumes().get(i-1);

  System.out.println("Enter the number for the section you would like to edit.");
  displayResumeEdit();
  int command = scanner.nextInt();
  scanner.nextLine();
  if(command < 5) {

    switch(command) {
      case 1: clearScreen();
              skillsEditor(resume);
      break; 
      case 2: //edu
      break;
      case 3: //work
      break;
      case 4: //extra
      break;
    }
  }

  student.getResumes().set(i, resume);
}

private void skillsEditor(Resume resume) {
  String res = "s";
  while(res.equalsIgnoreCase("done")) {
      System.out.println("Would you like to (a)dd or (r)emove skills? Enter \"done\" when you are done");
      res = scanner.nextLine();
      if(res.equalsIgnoreCase("a")) {
        System.out.print("Enter the skill you would like to add: ");
        res = scanner.nextLine();
        resume.addSkill(res);
      }
      else if(res.equalsIgnoreCase("r")) {
        System.out.print("Enter the skill you would like to remove");
        res = scanner.nextLine();
        resume.deleteSkill(res);
      }
  } 
}

  public static void main(String[] args) {
    InternshipUI internshipUI = new InternshipUI();
    internshipUI.run();
  }
}