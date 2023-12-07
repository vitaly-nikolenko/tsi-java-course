package lv.tsi.student.service;

import lv.tsi.student.model.DuplicateRecordException;
import lv.tsi.student.model.Student;
import lv.tsi.student.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private static final Logger log = LogManager.getLogger(StudentService.class);

    private final StudentRepository studentRepository = new StudentRepository();

    public List<Student> list() {
        return studentRepository.list();
    }

    public void printStudentList() {
        List<Student> students = list();
        students.forEach(s -> System.out.println(s.toString()));
    }

    public void add(Student s) {
        try {
            studentRepository.add(s);
        } catch (DuplicateRecordException e) {
            log.error("Failed to add, student with id: [{}] already exists.", s.getId());
        }
    }

    public List<Student> searchStudentByName(String name) {
        return studentRepository.findBy(name);
    }

    public void saveToFile(String fileName) {
        studentRepository.saveToFile(fileName);
    }

    public void loadFromFile(String fileName) {
        studentRepository.loadFromFile(fileName);
    }

    public void delete(String id) {
        Optional<Student> studentToDelete = studentRepository.get(id);
        if (studentToDelete.isPresent()) {
            studentRepository.delete(studentToDelete.get());
            log.info("Student with id [{}] deleted successfully.", id);
        } else {
            log.warn("Student with id [{}] not found. Deletion failed.", id);
        }
    }
}
