import java.time.Month;

//used to compile code to push it onto git
public class test {
    
    public static void main(String[] args) {
      WorkExperience exc = new WorkExperience("Cashier", Month.AUGUST, 2018, "Office Depot", "Lexington", "South Carolina");
     // exc.addEndDate(Month.FEBRUARY, 2019);
      exc.addResponsibility("helped customers");
      exc.addResponsibility("ink sorting");
      exc.addResponsibility("ink SortiNg");
      System.out.println(exc.toString());

    }
}
