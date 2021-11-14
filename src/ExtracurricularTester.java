import static org.junit.jupiter.api.Assertions.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.Locale;
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
}
