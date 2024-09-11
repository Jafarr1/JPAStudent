package test.jpastudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.jpastudent.model.Student;
import test.jpastudent.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentRestController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> students() {
        return studentRepository.findAll();

    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@RequestBody Student student) {
        System.out.println(student);
        return studentRepository.save(student);
    }

    @PutMapping("/student{id}")
    public ResponseEntity<Student> putStudent(@RequestBody Student student) {
        Optional<Student> orgStudent = studentRepository.findById(student.getId());
        if (orgStudent.isPresent()) {
           studentRepository.save(student);
           return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Student(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/student{id}")
    public ResponseEntity<String> deleteStudent(@RequestBody int id) {
        Optional<Student> orgStudent = studentRepository.findById(id);
        if (orgStudent.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }



    @GetMapping("/addstudent")
    public List<Student> addstudent() {
        Student std = new Student();
        std.setBornDate(LocalDate.now());

        return studentRepository.findAll();

    }

    @GetMapping("students/{name}")
    public List<Student> getallstudentsbyname(@PathVariable String name) {
        return studentRepository.findAllByName(name);
    }
}
