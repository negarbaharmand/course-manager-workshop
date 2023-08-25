package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here


    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }

    @Test
    public void test_createCourse() {
        Course actualData = new Course("Java", LocalDate.now(), 4);
        Assertions.assertEquals(1, actualData.getId());
        Assertions.assertEquals("Java", actualData.getCourseName());
        Assertions.assertEquals(4, actualData.getWeekDuration());
        Assertions.assertNotEquals("Python", actualData.getCourseName());


    }
}
