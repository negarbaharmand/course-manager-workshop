package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StudentTest {

    @Test
    public void testIdGenerator() {
        Student s1 = new Student("Negar", "negar@gmail.com", "fdsfd");
        Student s2 = new Student("Basim", "basim@basim.com", "");
        assertNotEquals(s1.getId(), s2.getId());
    }
}
