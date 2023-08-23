package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private Student student1;
    private Student student2;
    private Course course;

    @BeforeEach
    public void setUp() {
        student1 = new Student("Negar", "negar@example.com", "123 st");
        student2 = new Student("Basim", "basim@example.com", "456 St");
        course = new Course("Java1", LocalDate.now(), 3);
    }

    @Test
    public void testEnrollStudent() {
        assertTrue(course.enrollStudent(student1));
        assertTrue(course.enrollStudent(student2));
        assertFalse(course.enrollStudent(student1));
        assertFalse(course.enrollStudent(student2));
        assertFalse(course.enrollStudent(null));
        assertEquals(2, course.getStudents().size());

    }

    @Test
    public void testUnrollStudent() {
        course.enrollStudent(student1);
        course.enrollStudent(student2);
        assertTrue(course.unrollStudent(student1));
        assertFalse(course.unrollStudent(student1));
        assertFalse(course.unrollStudent(null));
        assertEquals(1, course.getStudents().size());
    }

}
