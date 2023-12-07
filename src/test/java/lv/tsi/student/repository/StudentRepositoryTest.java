package lv.tsi.student.repository;

import lv.tsi.student.model.DuplicateRecordException;
import lv.tsi.student.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRepositoryTest {

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository();
    }

    @Test
    void testAddAndListStudents() {
        // Given
        Student student1 = new Student("John Done", "", LocalDate.of(1990, Month.JANUARY, 1));
        Student student2 = new Student("Jane Smith", "", LocalDate.of(2002, Month.FEBRUARY, 2));

        // When
        assertDoesNotThrow(() -> {
            studentRepository.add(student1);
            studentRepository.add(student2);
        });

        // Then
        List<Student> students = studentRepository.list();
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
    }

    @Test
    void testFindByStudentName() {
        // Given
        Student student1 = new Student("John Ozols", "", LocalDate.of(1990, Month.JANUARY, 1));
        Student student2 = new Student("Jane Smith", "", LocalDate.of(2002, Month.FEBRUARY, 2));
        Student student3 = new Student("Janis Ozols", "", LocalDate.of(2000, Month.MARCH, 3));

        // When
        assertDoesNotThrow(() -> {
            studentRepository.add(student1);
            studentRepository.add(student2);
            studentRepository.add(student3);
        });

        // Then
        List<Student> result = studentRepository.findBy("Ozols");
        assertEquals(2, result.size());
        assertTrue(result.contains(student1));
        assertTrue(result.contains(student3));
    }

    @Test
    void testDeleteStudent() {
        // Given
        Student student1 = new Student("John Ozols", "", LocalDate.of(1990, Month.JANUARY, 1));

        // When
        assertDoesNotThrow(() -> studentRepository.add(student1));
        studentRepository.delete(student1);

        // Then
        List<Student> students = studentRepository.list();
        assertTrue(students.isEmpty());
    }

    @Test
    void testDuplicateRecordException() {
        // Given
        Student student1 = new Student("John Ozols", "", LocalDate.of(1990, Month.JANUARY, 1));

        // When
        assertDoesNotThrow(() -> studentRepository.add(student1));

        // Then
        DuplicateRecordException exception = assertThrows(DuplicateRecordException.class, () -> studentRepository.add(student1));
        assertEquals("Record already exists: " + student1, exception.getMessage());
    }
}
