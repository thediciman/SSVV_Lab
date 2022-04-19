import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

public class TestSaveIntegration {

    private static final String STUDENTS_FILE = "src/test/resources/integration/students.xml";
    private static final String ASSIGNMENTS_FILE = "src/test/resources/integration/assignments.xml";
    private static final String GRADES_FILE = "src/test/resources/integration/grades.xml";

    private Service service;

    @Before
    public void setUp() {
        Arrays.asList(STUDENTS_FILE, ASSIGNMENTS_FILE, GRADES_FILE).forEach(file -> {
            try {
                Files.write(
                    Path.of(file), Collections.singleton(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                            "<Entitati>\n" +
                            "</Entitati>"
                    )
                );
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

        final StudentXMLRepository studentXMLRepository = new StudentXMLRepository(new StudentValidator(), STUDENTS_FILE);
        final TemaXMLRepository temaXMLRepository = new TemaXMLRepository(new TemaValidator(), ASSIGNMENTS_FILE);
        final NotaXMLRepository notaXMLRepository = new NotaXMLRepository(new NotaValidator(), GRADES_FILE);
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @After
    public void tearDown() {
        Arrays.asList(STUDENTS_FILE, ASSIGNMENTS_FILE, GRADES_FILE).forEach(file -> new File(file).delete());
    }

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
