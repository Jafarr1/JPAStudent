package test.jpastudent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.jpastudent.model.Student;
import test.jpastudent.repositories.StudentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JpaStudentApplicationTests {

    @Autowired
    StudentRepository studentRepository;
    @Test
    void contextLoads() {
        Assertions.assertTrue(true);
    }

    @Test
    public void Test() {
        List<Student> lst = studentRepository.findAllByName("Bruce");
        assertEquals(1, lst.size());
    }
}
