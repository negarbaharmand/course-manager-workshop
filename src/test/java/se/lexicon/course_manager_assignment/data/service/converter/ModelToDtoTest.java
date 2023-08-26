package se.lexicon.course_manager_assignment.data.service.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {

    @Autowired
    private Converters testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
    }

    //write your tests here

    @Test
    public void testStudentToStudentView() {
        Student student = new Student("Negar", "negar@gmail.com", "dsf123");
        StudentView studentView = testObject.studentToStudentView(student);
        assertEquals(student.getId(), studentView.getId());
        assertEquals(student.getName(), studentView.getName());
        assertEquals(student.getEmail(), studentView.getEmail());
        assertEquals(student.getAddress(), studentView.getAddress());
    }

    @Test
    public void testCourseToCourseView() {
        List<Student> students = new ArrayList<>();
        Student student = new Student("Negar", "negar@gmail.com", "dsf123");
        students.add(student);
        Course course = new Course("Java", LocalDate.of(2023, 9, 1), 8);
        course.getStudents().addAll(students);
        CourseView courseView = testObject.courseToCourseView(course);

        assertEquals(course.getId(), courseView.getId());
        assertEquals(course.getCourseName(), courseView.getCourseName());
        assertEquals(course.getStartDate(), courseView.getStartDate());
        assertEquals(course.getWeekDuration(), courseView.getWeekDuration());
        assertEquals(1, courseView.getStudents().size());
    }
}
