package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StudentTest {
    private Student s1;
    private Student s2;
    private Student s3;

    @BeforeEach
    public void setUp() {
         s1 = new Student("Negar", "negar@gmail.com", "fdsfd");
         s2 = new Student("Basim", "basim@basim.com", "");
         s3 = new Student("Negar", "negar@gmail.com", "fdsfd");
    }

    @Test
    public void testIdGenerator() {
        assertNotEquals(s1.getId(), s2.getId());
    }

    @Test
    public void testHashcode() {
        assertNotEquals(s1.hashCode(), s3.hashCode());
    }

}
