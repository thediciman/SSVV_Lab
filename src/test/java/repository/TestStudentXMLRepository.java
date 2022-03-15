package repository;

import domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import validation.StudentValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestStudentXMLRepository {

    private static final String STUDENTS_FILE = "src/test/resources/students/students.xml";

    private StudentXMLRepository studentXMLRepository;

    @Before
    public void setUp() throws IOException {
        Files.write(
            Path.of(STUDENTS_FILE), Collections.singleton(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<Entitati>\n" +
                    "</Entitati>"
            )
        );
        studentXMLRepository = new StudentXMLRepository(new StudentValidator(), STUDENTS_FILE);
    }

    @After
    public void tearDown() {
        new File(STUDENTS_FILE).delete();
    }

    @Test
    public void save_returnsNull_whenSavingNewValidStudent() {
        final Student student = new Student("2", "student name", 933);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsSavedStudent_whenSavingExistingStudent() {
        final Student student = new Student("2", "student name", 933);
        studentXMLRepository.save(student);
        final Student savedStudent = studentXMLRepository.save(student);
        assertEquals(student, savedStudent);
    }

}
