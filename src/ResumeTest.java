import static org.junit.jupiter.api.Assertions.*;

import java.time.Month;

import org.junit.jupiter.api.*;

/**
 * Testing for the Resume Class
 * @authors Evan Grunewald
 */
public class ResumeTest {

    private Resume resume;

    @BeforeEach
    public void setup() {
        resume = new Resume();
    }

    @Test
    public void testAddNewSkill() {
        resume.addSkill("Web Design");
        assertEquals(resume.getSkills().size(), 1);
    }

    @Test
    public void testAddWorkExperience() {
        resume.addWorkExperience(new WorkExperience("position", Month.JUNE, 2021, "company", "city", "state"));
        assertEquals(resume.getWorkExperiences().size(), 1);
    }

    @Test
    public void testAddEducation() {
        resume.addEducation(new Education("university", "city", "state", "degreeType", "Major", Month.DECEMBER, 2022));
        assertEquals(resume.getEducations().size(), 1);
    }

    @Test
    public void testAddExtraCurricular() {
        resume.addExtracurricular(new Extracurricular("position", Month.JANUARY, 2020, "title"));
        assertEquals(resume.getExtracurriculars().size(), 1);
    }

    @Test
    public void testRemoveEducation() {
        resume.addEducation(new Education("university", "city", "state", "degreeType", "Major", Month.DECEMBER, 2022));
        resume.deleteEducation(0);
        assertEquals(resume.getEducations().size(), 0);
    }

    @Test
    public void testRemoveWorkExperience() {
        resume.addWorkExperience(new WorkExperience("position", Month.JUNE, 2021, "company", "city", "state"));
        resume.deleteWorkExperience(0);
        assertEquals(resume.getWorkExperiences().size(), 0);
    }

    @Test
    public void testRemoveSkill() {
        resume.addSkill("Web Design");
        resume.deleteSkill(0);
        assertEquals(resume.getSkills().size(), 0);
    }

    @Test
    public void testRemoveExtracurricular() {
        resume.addExtracurricular(new Extracurricular("position", Month.JANUARY, 2020, "title"));
        resume.deleteExtraCurricular(0);
        assertEquals(resume.getExtracurriculars().size(), 0);
    }

    @Test
    public void testDisplaySkills() {
        resume.addSkill("Skill_1");
        resume.addSkill("Skill_2");
        assertEquals(resume.displaySkills(), "1: Skill_1\n\n2: Skill_2\n\n");
    }

    @Test
    public void testAddDuplicateEducation() {
        resume.addEducation(new Education("university", "city", "state", "degreeType", "Major", Month.DECEMBER, 2022));
        resume.addEducation(new Education("university", "city", "state", "degreeType", "Major", Month.DECEMBER, 2022));
        assertEquals(resume.getEducations().size(), 1);
    }

    @Test
    public void testRemoveInvalidSkill() {
        resume.addSkill("Web Design");
        resume.deleteSkill(1);
        assertEquals(resume.getSkills().size(), 1);
    }

}
