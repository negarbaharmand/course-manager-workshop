package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here


    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }

    @Test
    public void test_createStudent() {
        Student actualData = new Student("test", "test@", " def");
        Assertions.assertEquals(0, actualData.getId());
        Assertions.assertEquals("test", actualData.getName());
        Assertions.assertEquals("test@", actualData.getEmail());
        Assertions.assertEquals(" def", actualData.getAddress());
        Assertions.assertNotEquals("sdef", actualData.getName());


    }
}
