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
    public void save_returnsSavedStudent_whenSavingNewValidStudent_BBT() {
        final Student student = new Student("2", "student name", 933);
        final Student savedStudent = studentXMLRepository.save(student);
        assertEquals(student, savedStudent);
    }

    @Test
    public void save_returnsNull_whenSavingExistingStudent_BBT() {
        final Student student = new Student("2", "student name", 933);
        studentXMLRepository.save(student);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenStudentHasNullId_BBT() {
        final Student student = new Student(null, "student name", 933);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenStudentHasEmptyId_BBT() {
        final Student student = new Student("", "student name", 933);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenStudentHasNullName_BBT() {
        final Student student = new Student("0", null, 933);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenStudentHasEmptyName_BBT() {
        final Student student = new Student("0", "", 933);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenStudentHasGroupLessThanLowerBound_BBT() {
        final Student student = new Student("0", "student name", 110);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenStudentHasGroupGreaterThanUpperBound_BBT() {
        final Student student = new Student("0", "student name", 938);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsSavedStudent_whenStudentHasGroupEqualToLowerBound_BBT() {
        final Student student = new Student("0", "student name", 111);
        final Student savedStudent = studentXMLRepository.save(student);
        assertEquals(student, savedStudent);
    }

    @Test
    public void save_returnsSavedStudent_whenStudentHasGroupEqualToUpperBound_BBT() {
        final Student student = new Student("0", "student name", 937);
        final Student savedStudent = studentXMLRepository.save(student);
        assertEquals(student, savedStudent);
    }

    @Test
    public void save_returnsSavedStudent_whenStudentHasGroupLessThanUpperBound_BBT() {
        final Student student = new Student("0", "student name", 936);
        final Student savedStudent = studentXMLRepository.save(student);
        assertEquals(student, savedStudent);
    }

    @Test
    public void save_returnsSavedStudent_whenStudentHasGroupGreaterThanLowerBound_BBT() {
        final Student student = new Student("0", "student name", 112);
        final Student savedStudent = studentXMLRepository.save(student);
        assertEquals(student, savedStudent);
    }

}
