package lv.tsi.student.repository;

import lv.tsi.student.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository extends Repository<Student> {

    /**
     * @param name - part of whole student name
     * @return List of students containing supplied name
     */
    public List<Student> findBy(String name) {
        if (name.length() > 0)
            return list()
                    .stream()
                    .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        else return list();

    }


}
