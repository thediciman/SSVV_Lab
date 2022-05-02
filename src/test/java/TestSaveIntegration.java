import integration.IntegrationAbstractTest;
import org.junit.Assert;
import org.junit.Test;

public class TestSaveIntegration extends IntegrationAbstractTest {

    @Test
    public void saveStudent() {
        Assert.assertEquals(0, service.saveStudent("studentId", "name", 933));
    }

    @Test
    public void saveAssignment() {
        Assert.assertEquals(0, service.saveTema("assignmentId", "name", 10, 1));
    }

    @Test
    public void saveGrade() {
        Assert.assertEquals(-1, service.saveNota("studentId", "assignmentId", 10, 5, "feedback"));
    }

    @Test
    public void saveStudent_saveAssignment_saveGrade() {
        Assert.assertEquals(0, service.saveStudent("studentId", "name", 933));
        Assert.assertEquals(0, service.saveTema("assignmentId", "name", 9, 1));
        Assert.assertEquals(0, service.saveNota("studentId", "assignmentId", 10, 12, "feedback"));
    }
}
