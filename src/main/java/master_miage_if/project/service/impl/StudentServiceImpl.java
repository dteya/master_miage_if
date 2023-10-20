package master_miage_if.project.service.impl;

import lombok.AllArgsConstructor;
import master_miage_if.project.domain.Student;
import master_miage_if.project.entity.StudentEntity;
import master_miage_if.project.mapper.StudentMapper;
import master_miage_if.project.repository.StudentEntityRepository;
import master_miage_if.project.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentEntityRepository studentEntityRepository;

    @Override
    public List<Student> findAll() {
        List<StudentEntity> entities = studentEntityRepository.findAll();
        return StudentMapper.toList(entities);
    }


    @Override
    public Student createStudent(Student studentRequest) {
        StudentEntity studentEntity = StudentMapper.toStudentEntity(studentRequest);
        studentEntity.setBooks(studentRequest.books());
        StudentEntity saved = studentEntityRepository.save(studentEntity);
        return StudentMapper.toStudent(saved);
    }

    @Override
    public Student findStudentById(UUID id) throws Exception {
        Optional<StudentEntity> studentEntityOptional = studentEntityRepository.findById(id);
        StudentEntity studentEntity = studentEntityOptional.orElseThrow(() -> new Exception("Student not found"));
        return StudentMapper.toStudent(studentEntity);
    }

    @Override
    public List<Student> findStudentByName(String name) throws Exception {
        List<StudentEntity> studentsByName = studentEntityRepository.findByLastName(name);
        if (!studentsByName.isEmpty()) {
            return StudentMapper.toList(studentsByName);
        }
        throw new Exception("No student with that last name");

    }

    @Override
    public List<Student> findAgeLessThan(int age) throws Exception {
        List<StudentEntity> studentsLessThan = studentEntityRepository.findByAgeLessThan(age);
        if (!studentsLessThan.isEmpty()) {
            return StudentMapper.toList(studentsLessThan);
        }
        throw new Exception("No students younger than" + age);
    }

    @Override
    public Student updateStudentEmail(String previousEmail, String newEmail) throws Exception {
        Optional<StudentEntity> studentOptional = Optional.ofNullable(studentEntityRepository.findByEmail(previousEmail));
        if (studentOptional.isPresent()) {
            StudentEntity student = studentOptional.get();
            student.setEmail(newEmail);
            studentEntityRepository.save(student);
            return StudentMapper.toStudent(student);
        } else {
            throw new Exception("Student with previous email not found");
        }

    }

    public void incrementAllStudentAges() {
        List<StudentEntity> students = studentEntityRepository.findAll();
        for (StudentEntity student : students) {
            int currentAge = student.getAge();
            student.setAge(currentAge + 1);
        }
        studentEntityRepository.saveAll(students);
    }
}
