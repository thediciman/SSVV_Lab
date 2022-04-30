package integration;

import org.junit.After;
import org.junit.Before;
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

public abstract class IntegrationAbstractTest {
    private static final String STUDENTS_FILE = "src/test/resources/integration/students.xml";
    private static final String ASSIGNMENTS_FILE = "src/test/resources/integration/assignments.xml";
    private static final String GRADES_FILE = "src/test/resources/integration/grades.xml";

    protected Service service;

    @Before
    public void setup() {
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
}
