import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;
import java.time.Month;
/*
 *
 * @authors Yousef Afshar, Robbie Clark
 */
public class Student extends User {
    
    private ArrayList<Resume> resumes;
    private ArrayList<Listing> listing;
    private ArrayList<Listing> savedListings;
    private FilterBehavior filterBehavior;
    private Scanner key = new Scanner(System.in);
    //private Month month;
    //private Month endMonth;
    private Major major;

    public Student(UUID id, String firstName, String lastName, String username,
        String password, Users USER_TYPE_STUDENT) {
            super(id, firstName, lastName, username, password, USER_TYPE_STUDENT);
            this.resumes = new ArrayList<Resume>();
            this.savedListings = new ArrayList<Listing>();
        }

    public void createResume() {

        System.out.println("Enter email: ");
        String eMail = key.nextLine();

        System.out.println("Enter phone number: ");
        String phoneNum = key.nextLine();

        Resume res = new Resume(UUID.randomUUID(), eMail, phoneNum);
        
        clearScreen();
        
        String cont = "y";

        while(cont.equalsIgnoreCase("y")) {
            System.out.println("Enter your skills (enter \"done\" when finished): ");
            if(!key.nextLine().equalsIgnoreCase("done"))
                res.addSkill(key.nextLine());
            else {cont = key.nextLine();}
        }

        clearScreen();

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

            System.out.println("Enter your major: ");
            String major = key.nextLine();
            
            System.out.println("Enter the number of your graduation month (January is 1, February is 2, etc.): ");
            int monthNumber = key.nextInt();
            key.nextLine();
            Month gradMonth = determineMonth(monthNumber);

            System.out.println("Enter your graduation year: ");
            int gradYear = key.nextInt();
            key.nextLine();

            Education edu = new Education(university, city, state, degreeType, major, gradMonth, gradYear);

            System.out.println("Enter your minor (enter \"none\" if you don't have one): ");
            String minor = key.nextLine();
            if(!minor.equalsIgnoreCase("none"))
              edu.addMinor(minor);
            
            System.out.println("Enter your GPA (Enter -1 if you do not wish to include a GPA): ");
            double gpa = key.nextDouble();
            key.nextLine();
            if(gpa != -1)
              edu.addGPA(gpa);

            
            res.addEducation(edu);

            System.out.println("Would you like to enter another education? (y)es or (n)o?");
            cont = key.nextLine();
        }

        clearScreen();

        // create work experience section

        System.out.println("Would you like to enter any work experience? (y)es or (n)o?");
        cont = key.nextLine();

        while(cont.equalsIgnoreCase("y")) {

            System.out.println("Enter the position: ");
            String position = key.nextLine();

            System.out.println("Enter the company: ");
            String company = key.nextLine();

            System.out.println("Enter the city: ");
            String Wcity = key.nextLine();

            System.out.println("Enter the state: ");
            String Wstate = key.nextLine();

            System.out.println("Enter the number of the month you started (January is 1, February is 2, etc.): ");
            int monthNumber = key.nextInt();
            key.nextLine();
            Month startMonth = determineMonth(monthNumber);

            System.out.println("Enter the year you started: ");
            int startYear = key.nextInt();
            key.nextLine();

            /* creates a work experience with given position, month, startYear, company, city, state; work experience constructor creates a responsibilities array for this work experience */
            WorkExperience workXP = new WorkExperience(position, startMonth, startYear, company, Wcity, Wstate);

            String inner = "no";
            System.out.println("Enter responsibilities (type \"done\" when finished)");
            while(!inner.equalsIgnoreCase("done")) {
                inner = key.nextLine();
                if(!inner.equalsIgnoreCase("done"))
                    workXP.addResponsibility(inner);            }

            System.out.println("Are you currently working this position? (y)es or (n)o");
            if(key.nextLine().equalsIgnoreCase("n")) {
                monthNumber = key.nextInt();
                key.nextLine();
                Month endMonth = determineMonth(monthNumber);

                System.out.println("Enter the year you ended: ");
                int endYear = key.nextInt();
                key.nextLine();

                workXP.addEndDate(endMonth, endYear);
            }
            res.addWorkExperience(workXP);
            System.out.println("Would you like to add another work experience? (y)es or (n)o?");
            cont = key.nextLine();
        }

        clearScreen();

        ArrayList<Extracurricular> extracurriculars = new ArrayList<Extracurricular>();
        System.out.println("Would you like to enter any extracurriculars? (y)es or (n)o?");
        cont = key.nextLine();

        while(cont.equalsIgnoreCase("y")) {
            System.out.println("Enter the name of the extracurricular organization: ");
            String title = key.nextLine();

            System.out.println("Enter your position within the organization: ");
            String position = key.nextLine();
            
            System.out.println("Enter the number of the month you joined (January is 1, February is 2, etc.): ");
            int monthNumber = key.nextInt();
            key.nextLine();
            Month startMonth = determineMonth(monthNumber);

            System.out.println("Enter the year you joined: ");
            int startYear = key.nextInt();
            key.nextLine();

            Extracurricular extrac = new Extracurricular(position, startMonth, startYear, title);

            String inner = "no";
            System.out.println("Enter responsibilities/activities (type \"done\" when finished)");
            while(!inner.equalsIgnoreCase("done")) {
                inner = key.nextLine();
                if(!inner.equalsIgnoreCase("done"))
                    extrac.addExtracurricularActivity(inner);
            }

            System.out.println("Are you currently in this organization? (y)es or (n)o");
            if(key.nextLine().equalsIgnoreCase("n")) {
              System.out.println("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
              monthNumber = key.nextInt();
              key.nextLine();
              Month endMonth = determineMonth(monthNumber);

              System.out.println("Enter the year you ended: ");
              int endYear = key.nextInt();
              key.nextLine();
              extrac.addEndDate(endMonth, endYear);
            }
            
            res.addExtracurricular(extrac);

            System.out.println("Would you like to add another extracurricular? (y)es or (n)o?");
            cont = key.nextLine();
        }
        clearScreen();

        resumes.add(res);

        System.out.println("Resume Created!");
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

    public String displayResume(int i) {
        return this.firstName + " " + this.lastName + "\n" + this.resumes.get(i).toString();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private Month determineMonth(int monthNumber) {
      Month month = Month.JANUARY;
      while(monthNumber < 1 || monthNumber > 12) {
        System.out.println("Invalid Month. Please enter a number 1 through 12");
        monthNumber = key.nextInt();
        key.nextLine();
      }

      switch (monthNumber) {
        case 1: month =  Month.JANUARY;
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
      }
      return month;
    }
  }
