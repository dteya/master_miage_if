package master_miage_if.project.controller;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import master_miage_if.project.domain.Email;
import master_miage_if.project.domain.Student;
import master_miage_if.project.entity.BookEntity;
import master_miage_if.project.entity.StudentEntity;
import master_miage_if.project.repository.BookRepository;
import master_miage_if.project.repository.StudentEntityRepository;
import master_miage_if.project.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping(value = "/")
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @GetMapping(value = "/name/{name}")
    public List<Student> getByName(@PathVariable String name) throws Exception {
        return studentService.findStudentByName(name);
    }

    @GetMapping(value = "/age/{age}")
    public List<Student> getByAgeLessThan(@PathVariable int age) throws Exception {
        return studentService.findAgeLessThan(age);
    }

    @PostMapping(value = "/",consumes = {"application/json"})
    public Student save(@RequestBody Student s) {
        return studentService.createStudent(s);
    }

    @PostMapping(value = "/changeEmail",consumes = {"application/json"})
    public ResponseEntity<String> changeEmail(@RequestParam String previousEmail,
                                              @RequestBody String newEmail) throws Exception {
       try {
           studentService.updateStudentEmail(previousEmail, newEmail);
           return ResponseEntity.ok("Email updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No mail found");
        }
    }

    @PostMapping("/increment-ages")
    public ResponseEntity<String> incrementAllStudentAges() {
        try {
            studentService.incrementAllStudentAges();
            return ResponseEntity.ok("Ages incremented successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    private BookRepository bookRepository;
    private StudentEntityRepository studentEntityRepository;


    // Sans passer par service, peut-etre refractored j'ai eu la flemme
    @PutMapping("/{studentId}/update-books")
    public ResponseEntity<String> updateStudentBooks(
            @PathVariable String studentId,
            @RequestBody List<Long> newBookIds
    ) {
        Optional<StudentEntity> studentOptional = studentEntityRepository.findById(UUID.fromString(studentId));
        if (studentOptional.isPresent()) {
            StudentEntity student = studentOptional.get();

            List<BookEntity> newBooks = new ArrayList<>();
            for (Long bookId : newBookIds) {
                Optional<BookEntity> bookOptional = bookRepository.findById(bookId);
                if (bookOptional.isPresent()) {
                    newBooks.add(bookOptional.get());
                } else {
                    return ResponseEntity.badRequest().body("Invalid book ID: " + bookId);
                }
            }
            student.setBooks(newBooks);
            studentEntityRepository.save(student);
            return ResponseEntity.ok("Books updated for student with ID: " + studentId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
