package com.sms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    @DisplayName("Test: Add a new student")
    void testAddStudent() {
        Student s = new Student("john doe", "john@test.com", 20, "devops");
        Student saved = studentService.addStudent(s);

        assertNotNull(saved.getId());
        assertEquals("John doe", saved.getName()); // Commons Lang capitalize
        assertEquals("DEVOPS", saved.getCourse()); // Commons Lang uppercase
        System.out.println("✅ Test Passed: Add Student → " + saved);
    }

    @Test
    @DisplayName("Test: Get all students")
    void testGetAllStudents() {
        studentService.addStudent(new Student("Alice", "alice@test.com", 21, "Java"));
        studentService.addStudent(new Student("Bob", "bob@test.com", 22, "DevOps"));

        List<Student> students = studentService.getAllStudents();
        assertEquals(2, students.size());
        System.out.println("✅ Test Passed: Total students → " + students.size());
    }

    @Test
    @DisplayName("Test: Get student by ID")
    void testGetStudentById() {
        Student saved = studentService.addStudent(
            new Student("Charlie", "charlie@test.com", 23, "AWS")
        );
        Optional<Student> found = studentService.getStudentById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Charlie", found.get().getName());
        System.out.println("✅ Test Passed: Found → " + found.get());
    }

    @Test
    @DisplayName("Test: Delete a student")
    void testDeleteStudent() {
        Student saved = studentService.addStudent(
            new Student("Dave", "dave@test.com", 24, "Linux")
        );
        studentService.deleteStudent(saved.getId());

        Optional<Student> deleted = studentService.getStudentById(saved.getId());
        assertFalse(deleted.isPresent());
        System.out.println("✅ Test Passed: Student deleted successfully");
    }

    @Test
    @DisplayName("Test: Count total students")
    void testGetTotalStudents() {
        studentService.addStudent(new Student("Eve", "eve@test.com", 25, "Docker"));
        assertEquals(1, studentService.getTotalStudents());
        System.out.println("✅ Test Passed: Student count = " + studentService.getTotalStudents());
    }
}
