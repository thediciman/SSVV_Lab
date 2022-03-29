package repository;

import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import validation.TemaValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.Assert.assertNull;

public class TestTemaXMLRepository {

    private static final String ASSIGNMENTS_FILE = "src/test/resources/assignments/assignments.xml";

    private TemaXMLRepository assignmentXMLRepository;

    @Before
    public void setUp() throws IOException {
        Files.write(
            Path.of(ASSIGNMENTS_FILE), Collections.singleton(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<Entitati>\n" +
                    "</Entitati>"
            )
        );
        assignmentXMLRepository = new TemaXMLRepository(new TemaValidator(), ASSIGNMENTS_FILE);
    }

    @After
    public void tearDown() {
        new File(ASSIGNMENTS_FILE).delete();
    }

    @Test
    public void save_returnsNull_whenAssignmentHasNullId_WBT() {
        final Tema assignment = new Tema(null, "description", 1, 2);
        final Tema savedAssignment = assignmentXMLRepository.save(assignment);
        assertNull(savedAssignment);
    }

    @Test
    public void save_returnsNull_whenAssignmentHasEmptyId_WBT() {
        final Tema assignment = new Tema("", "description", 1, 2);
        final Tema savedAssignment = assignmentXMLRepository.save(assignment);
        assertNull(savedAssignment);
    }

}
