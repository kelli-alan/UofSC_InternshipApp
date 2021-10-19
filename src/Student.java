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
    private Month endMonth;
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
            }

            
            boolean hasMonth = false;

            while(!hasMonth) {
                System.out.println("Enter the number of your graduation month (January is 1, February is 2, etc.): ");
                int case1 = key.nextInt();
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

        clearScreen();

        ArrayList<WorkExperience> workExperiences = new ArrayList<WorkExperience>();

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

            boolean hasMonth = false;

            while(!hasMonth) {
                System.out.println("Enter the number of the month you started (January is 1, February is 2, etc.): ");
                int case1 = key.nextInt();
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

            System.out.println("Enter the year you started: ");
            int startYear = key.nextInt();
            key.nextLine();

            ArrayList<String> responsibilities = new ArrayList<String>();
            String inner = "no";
            System.out.println("Enter responsibilities (type \"done\" when finished)");
            while(!inner.equalsIgnoreCase("done")) {
                inner = key.nextLine();
                if(!inner.equalsIgnoreCase("done"))
                    responsibilities.add(inner);
            }

            System.out.println("Are you currently working this position? (y)es or (n)o");
            if(key.nextLine().equalsIgnoreCase("n")) {
                hasMonth = false;
                while(!hasMonth) {
                    System.out.println("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
                    int case1 = key.nextInt();
                    key.nextLine();
                    hasMonth = true;
                    switch (case1) {
                        case 1: endMonth = Month.JANUARY;
                        break;
                        case 2: endMonth = Month.FEBRUARY;
                        break;
                        case 3: endMonth = Month.MARCH;
                        break;
                        case 4: endMonth = Month.APRIL;
                        break;
                        case 5: endMonth = Month.MAY;
                        break;
                        case 6: endMonth = Month.JUNE;
                        break;
                        case 7: endMonth = Month.JULY;
                        break;
                        case 8: endMonth = Month.AUGUST;
                        break;
                        case 9: endMonth = Month.SEPTEMBER;
                        break;
                        case 10: endMonth = Month.OCTOBER;
                        break;
                        case 11: endMonth = Month.NOVEMBER;
                        break;
                        case 12: endMonth = Month.DECEMBER;
                        break;
                        default: System.out.println("INVALID MONTH");
                                hasMonth = false;
                    }
                }

                System.out.println("Enter the year you ended: ");
                int endYear = key.nextInt();
                key.nextLine();

                workExperiences.add(new WorkExperience(position, month, startYear, company, Wcity, Wstate, responsibilities, endMonth, endYear));

            }

            workExperiences.add(new WorkExperience(position, month, startYear, company, Wcity, Wstate, responsibilities));

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

            boolean hasMonth = false;

            while(!hasMonth) {
                System.out.println("Enter the number of the month you joined (January is 1, February is 2, etc.): ");
                int case1 = key.nextInt();
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

            System.out.println("Enter the year you joined: ");
            int startYear = key.nextInt();
            key.nextLine();

            ArrayList<String> activities = new ArrayList<String>();

            String inner = "no";
            System.out.println("Enter responsibilities/activities (type \"done\" when finished)");
            while(!inner.equalsIgnoreCase("done")) {
                inner = key.nextLine();
                if(!inner.equalsIgnoreCase("done"))
                    activities.add(inner);
            }

            System.out.println("Are you currently in this organization? (y)es or (n)o");
            if(key.nextLine().equalsIgnoreCase("n")) {
                hasMonth = false;
                while(!hasMonth) {
                    System.out.println("Enter the number of the month you ended (January is 1, February is 2, etc.): ");
                    int case1 = key.nextInt();
                    key.nextLine();
                    hasMonth = true;
                    switch (case1) {
                        case 1: endMonth = Month.JANUARY;
                        break;
                        case 2: endMonth = Month.FEBRUARY;
                        break;
                        case 3: endMonth = Month.MARCH;
                        break;
                        case 4: endMonth = Month.APRIL;
                        break;
                        case 5: endMonth = Month.MAY;
                        break;
                        case 6: endMonth = Month.JUNE;
                        break;
                        case 7: endMonth = Month.JULY;
                        break;
                        case 8: endMonth = Month.AUGUST;
                        break;
                        case 9: endMonth = Month.SEPTEMBER;
                        break;
                        case 10: endMonth = Month.OCTOBER;
                        break;
                        case 11: endMonth = Month.NOVEMBER;
                        break;
                        case 12: endMonth = Month.DECEMBER;
                        break;
                        default: System.out.println("INVALID MONTH");
                                hasMonth = false;
                    }
                }

                System.out.println("Enter the year you ended: ");
                int endYear = key.nextInt();
                key.nextLine();

                extracurriculars.add(new Extracurricular(position, month, startYear, title, activities, endMonth, endYear));
            }
            extracurriculars.add(new Extracurricular(position, month, startYear, title, activities));
        
            System.out.println("Would you like to add another extracurricular? (y)es or (n)o?");
            cont = key.nextLine();
        }
        clearScreen();

        resumes.add(new Resume(id, firstName, lastName, eMail, phoneNum, skills, education, workExperiences, extracurriculars));

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

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
