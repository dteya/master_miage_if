package master_miage_if.project.service;


import master_miage_if.project.domain.Email;
import master_miage_if.project.domain.Student;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<Student> findAll();

    Student createStudent(Student studentRequest);

    Student findStudentById(UUID id) throws Exception;

    List<Student> findStudentByName (String name) throws Exception;

    List<Student> findAgeLessThan (int age) throws Exception;

    Student updateStudentEmail(String previousEmail, String newEmail) throws Exception;

    void incrementAllStudentAges();
}
