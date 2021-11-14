import static org.junit.jupiter.api.Assertions.*;
import java.time.Month;
import org.junit.jupiter.api.Test;
public class ExtracurricularTester {
    
   @Test
   public void testAddActivity() {
       Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
       extracurricular.addExtracurricularActivity("activity");
       boolean isListed = extracurricular.listed("activity");
       assertTrue(isListed);
   }

   @Test
   public void testAddEmptyActivity() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.addExtracurricularActivity("activity");
        extracurricular.addExtracurricularActivity("");
        assertNotEquals(2, extracurricular.getActivities().size());
   }

   @Test
   public void testListedFalse() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        boolean isListed = extracurricular.listed("activity");
        assertFalse(isListed);
   }

   @Test
   public void testAddDuplicateActivity() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.addExtracurricularActivity("activity");
        extracurricular.addExtracurricularActivity("activity");
        assertEquals(extracurricular.getActivities().size(), 1);
   }

   @Test
   public void testSetEmptyTitle() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.setTitle("");
        assertNotEquals("", extracurricular.getTitle());
   }

   @Test
   public void testInitializeEmptyTitle() {
        Extracurricular extracurricular = new Extracurricular("position", Month.APRIL, 2021, "");
        assertNotEquals("", extracurricular.getTitle());
   }

   @Test
   public void testSetEmptyPosition() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.setPosition("");
        assertNotEquals("", extracurricular.getPostion());
   }

   @Test
   public void testSetPosition() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.setPosition("new position");
        assertEquals("new position", extracurricular.getPostion());
   }

   @Test
   public void testSetInvalidStartMonth() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.setStartMonth(15);
        assertNotEquals(15, extracurricular.getStartMonth().getValue());
   }

   @Test
   public void testSetStartMonth() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.setStartMonth(12);
        assertEquals(12, extracurricular.getStartMonth().getValue());
   }

   @Test
   public void testSetInvalidStartYear() {
        Extracurricular extracurricular = new Extracurricular("position", Month.APRIL, 2021, "");
        extracurricular.setStartYear(1);
        assertNotEquals(1, extracurricular.startYear);
   }

   @Test
   public void testInitInvalidStartYear() {
        Extracurricular extracurricular = new Extracurricular("position", Month.APRIL, 1, "");
        assertNotEquals(1, extracurricular.startYear);
   }

   @Test
   public void testAddEndDate() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.addEndDate(Month.APRIL, 2025);
        assertEquals(Month.APRIL, extracurricular.endMonth);
   }
   @Test
   public void testAddEndDateP2() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.addEndDate(Month.APRIL, 2025);
        assertEquals(2025, extracurricular.endYear);
   }

   @Test
   public void testAddEndDateP3() {
        Extracurricular extracurricular = new Extracurricular("position", Month.AUGUST, 2020, "title");
        extracurricular.addEndDate(Month.APRIL, 2025);
        assertFalse(extracurricular.ongoing);
   }
}
