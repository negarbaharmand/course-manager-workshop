package se.lexicon.course_manager_assignment.data.service.course;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.data.service.student.StudentService;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CourseManager.class, CourseCollectionRepository.class, ModelToDto.class, StudentCollectionRepository.class})
public class CourseManagerTest {

    @Autowired
    private CourseService testObject;

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private Converters converters;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(courseDao);
    }

    //Write your tests here
    @Test
    @DisplayName("Test create course")
    void createCourse() {
        CreateCourseForm form = new CreateCourseForm();
        form.setCourseName("Java Programming");
        form.setStartDate(LocalDate.now());
        form.setWeekDuration(12);

        CourseView createdCourse = testObject.create(form);

        assertNotNull(createdCourse);
        assertEquals("Java Programming", createdCourse.getCourseName());
    }


    @Test
    @DisplayName("Test update course")
    void updateCourse() {
        CreateCourseForm form = new CreateCourseForm();
        form.setCourseName("Fullstack Java");
        form.setStartDate(LocalDate.now());
        form.setWeekDuration(12);

        CourseView createdCourse = testObject.create(form);
        UpdateCourseForm updateForm = new UpdateCourseForm();
        updateForm.setId(createdCourse.getId());
        updateForm.setCourseName("Advanced Java Programming");
        updateForm.setStartDate(LocalDate.now().plusDays(1));
        updateForm.setWeekDuration(10);

        CourseView updatedCourse = testObject.update(updateForm);

        assertNotNull(updatedCourse);
        assertEquals(("Advanced Java Programming"), updatedCourse.getCourseName());
        assertEquals(LocalDate.now().plusDays(1), updatedCourse.getStartDate());
        assertEquals(10, updatedCourse.getWeekDuration());

    }

//    @Test
//    @DisplayName("Test add and remove student from course")
//    void addAndRemoveStudent() {
//        CreateStudentForm studentForm = new CreateStudentForm();
//        studentForm.setName("Test Testsson");
//        studentForm.setEmail("test@example.com");
//        studentForm.setAddress("123 Test St");
//
//        StudentView student = studentService.create(studentForm);
//
//
//        CreateCourseForm courseForm = new CreateCourseForm();
//        courseForm.setCourseName("Java Programming");
//        courseForm.setStartDate(LocalDate.now());
//        courseForm.setWeekDuration(8);
//
//        CourseView createdCourse = testObject.create(courseForm);
//
//
//        assertTrue(testObject.addStudentToCourse(createdCourse.getId(), student.getId()));
//
//        List<CourseView> coursesByStudentId = testObject.findByStudentId(student.getId());
//        assertTrue(coursesByStudentId.contains(createdCourse));
//
//
//        assertTrue(testObject.removeStudentFromCourse(createdCourse.getId(), student.getId()));
//
//
//        coursesByStudentId = testObject.findByStudentId(student.getId());
//        assertFalse(coursesByStudentId.contains(createdCourse));
//    }

    @AfterEach
    void tearDown() {
        courseDao.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
