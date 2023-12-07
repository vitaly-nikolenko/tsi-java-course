package lv.tsi.student.service;

import lv.tsi.student.model.DuplicateRecordException;
import lv.tsi.student.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void testAddStudent() {
        // Given
        Student student = new Student("John Ozols", "", LocalDate.of(1990, Month.JANUARY, 1));

        // When
        assertDoesNotThrow(() -> studentService.add(student));

        // Then
        List<Student> students = studentService.list();
        assertEquals(1, students.size());
        assertEquals(student, students.get(0));
    }

    @Test
    void testAddDuplicateStudent() {
        // Given
        Student student = new Student("John Ozols", "", LocalDate.of(1990, Month.JANUARY, 1));
        studentService.add(student);

        // When
        DuplicateRecordException exception = assertThrows(DuplicateRecordException.class, () -> studentService.add(student));

        // Then
        assertEquals("Failed to add, student with id: [1] already exists.", exception.getMessage());
    }

    @Test
    void testSearchStudentByName() {
        // Given
        Student student1 = new Student("John Ozols", "", LocalDate.of(1990, Month.JANUARY, 1));
        Student student2 = new Student("Jane Smith", "", LocalDate.of(2002, Month.FEBRUARY, 2));
        studentService.add(student1);
        studentService.add(student2);

        // When
        List<Student> result = studentService.searchStudentByName("John");

        // Then
        assertEquals(1, result.size());
        assertTrue(result.contains(student1));
    }

    @Test
    void testSaveAndLoadToFile() {
        // Given
        Student student1 = new Student("John Ozols", "", LocalDate.of(1990, Month.JANUARY, 1));
        Student student2 = new Student("Jane Smith", "", LocalDate.of(2002, Month.FEBRUARY, 2));

        studentService.add(student1);
        studentService.add(student2);

        // When
        assertDoesNotThrow(() -> {
            studentService.saveToFile("test_students.txt");
            studentService.loadFromFile("test_students.txt");
        });

        // Then
        List<Student> students = studentService.list();
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
    }

}
