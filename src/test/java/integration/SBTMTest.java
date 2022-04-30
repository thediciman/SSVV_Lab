package integration;

import org.junit.Assert;
import org.junit.Test;

public class SBTMTest extends IntegrationAbstractTest {

    @Test
    public void testWithoutStudent() {
        Assert.assertEquals(service.saveNota("0", "0", 7, 7, "mere"), -1);
    }

    @Test
    public void testWithValidInput() {
        service.saveStudent("0", "student", 933);
        service.saveTema("0", "tema1", 9, 5);
        Assert.assertEquals(0, service.saveNota("0", "0", 7, 7, "mere"));
    }

    @Test
    public void testWithSubmitEarly() {
        service.saveStudent("0", "student", 933);
        service.saveTema("0", "tema1", 8, 4);
        Assert.assertEquals(0, service.saveNota("0", "0", 7, 4, "mere"));
    }

    @Test
    public void testFeedbackEmpty() {
        service.saveStudent("0", "student", 933);
        service.saveTema("0", "tema1", 9, 5);
        Assert.assertEquals(0, service.saveNota("0", "0", 7, 8, ""));
    }

    @Test
    public void testAddGradeTwice() {
        service.saveStudent("0", "student", 933);
        service.saveTema("0", "tema1", 9, 5);
        Assert.assertEquals(0, service.saveNota("0", "0", 7, 8, ""));
        Assert.assertEquals(1, service.saveNota("0", "0", 7, 8, ""));
    }

    @Test
    public void testWithSubmitLate() {
        service.saveStudent("0", "student", 933);
        service.saveTema("0", "tema1", 9, 5);
        Assert.assertEquals(0, service.saveNota("0", "0", 7, 20, ""));
    }

    @Test
    public void testWithSuperLongFeedBackString() {
        service.saveStudent("0", "student", 933);
        service.saveTema("0", "tema1", 9, 5);
        Assert.assertEquals(0, service.saveNota("0", "0", 7, 8, "a".repeat(100000)));
    }

}
