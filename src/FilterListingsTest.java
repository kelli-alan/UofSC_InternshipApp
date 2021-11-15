import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.util.ArrayList;

/**
 * Testing for the FilterListings
 * @authors Evan Grunewald
 */
public class FilterListingsTest {

    private ArrayList<Listing> list;

    @BeforeEach
    public void setup() {
        list = new ArrayList<Listing>();
    }

    @Test
    public void testFilterEmptyList() {
        ArrayList<Listing> listnew = FilterListings.filterByHoursPerWeek(list, 0);
        assertEquals(listnew.size(), 0);
    }

    @Test
    public void testFilterHours() {
        Listing test = new Listing();
        test.setHoursPerWeek(10);
        list.add(test);

        test = new Listing();
        test.setHoursPerWeek(20);
        list.add(test);
    
        test = new Listing();
        test.setHoursPerWeek(30);
        list.add(test);

        ArrayList<Listing> listnew = FilterListings.filterByHoursPerWeek(list, 20);
        assertEquals(listnew.size(), 2);
    }

    @Test
    public void testFilterLocation() {
        Listing test = new Listing();
        test.setState("SC");
        test.setCity("Columbia");
        list.add(test);

        test = new Listing();
        test.setState("SC");
        test.setCity("Charleston");
        list.add(test);
    
        test = new Listing();
        list.add(test);

        ArrayList<Listing> listnew = FilterListings.filterByLocation(list, "Columbia");
        assertEquals(listnew.size(), 1);
    }

    @Test
    public void testFilterSkill() {
        Listing test = new Listing();
        test.addSkills("Skill");
        list.add(test);

        test = new Listing();
        test.addSkills("Programming");
        list.add(test);
    
        test = new Listing();
        list.add(test);

        ArrayList<Listing> listnew = FilterListings.filterBySkill(list, "Skill");
        assertEquals(listnew.size(), 1);
    }

    @Test
    public void testFilterPay() {
        Listing test = new Listing();
        test.setPay(10.0);
        list.add(test);

        test = new Listing();
        test.setPay(20.0);
        list.add(test);
    
        test = new Listing();
        list.add(test);

        ArrayList<Listing> listnew = FilterListings.filterByPay(list, 10.0);
        assertEquals(listnew.size(), 2);
    }

}
