import static org.junit.jupiter.api.Assertions.*;
import java.time.Month;
import org.junit.jupiter.api.Test;
public class EducationTester {
    
    @Test
    public void testCreateEmptyEducation() {
        Education education = new Education("", "", "", "", "", Month.APRIL, 0);
        assertNotEquals("", education.getUniversity());
    }
    
    @Test
    public void testSetEmptyUni() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setUniversity("");
        assertNotEquals("", education.getUniversity());
    }

    @Test
    public void testSetUni() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setUniversity("new University");
        assertEquals("new University", education.getUniversity());
    }

    @Test
    public void testSetEmptyCity() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setCity("");
        assertNotEquals("", education.getCity());
    }

    @Test
    public void testSetCity() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setCity("new City");
        assertEquals("new City", education.getCity());
    }

    @Test
    public void testSetEmptyState() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setState("");
        assertNotEquals("", education.getState());
    }

    @Test
    public void testSetState() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setState("new State");
        assertEquals("new State", education.getState());
    }

    @Test
    public void testSetEmptyDegree() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setDegreeType("");
        assertNotEquals("", education.getDegreeType());
    }

    @Test
    public void testSetDegree() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setDegreeType("new Degree");
        assertEquals("new Degree", education.getDegreeType());
    }

    @Test
    public void testSetEmptyMajor() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setMajor("");
        assertNotEquals("", education.getMajor());
    }

    @Test
    public void testSetMajor() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setMajor("new Major");
        assertEquals("new Major", education.getMajor());
    }

    @Test
    public void testSetInvalidGradYear() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setGradYear(21);
        assertNotEquals(21, education.getGradYear());
    }

    @Test
    public void testSetGradYear() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.setGradYear(2025);
        assertEquals(2025, education.getGradYear());
    }

    @Test
    public void testInitInvalidGradYear() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 4000);
        assertNotEquals(4000, education.getGradYear());
    }

    @Test
    public void testSetInvalidGPA() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.addGPA(50);
        assertNotEquals(50, education.getGPA());
    }

    @Test
    public void testSetGPA() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.addGPA(3.5);
        assertEquals(3.5, education.getGPA());
    }

    @Test
    public void testSetEmptyMinor() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.addMinor("minor");
        education.addMinor("");
        assertNotEquals("", education.getMinor());
    }

    @Test
    public void testInitEmptyMinor() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.addMinor("");
        assertNotEquals("", education.getMinor());
    }

    @Test
    public void testSetMinor() {
        Education education = new Education("university", "city", "state", "degreeType", "major", Month.AUGUST, 2021);
        education.addMinor("Minor");
        assertEquals("Minor", education.getMinor());
    }
}
