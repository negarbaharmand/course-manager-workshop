package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.model.Student;

import java.util.Collection;
import java.util.HashSet;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        return null;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {
        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {
        return null;
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public Collection<Student> findAll() {
        return null;
    }

    @Override
    public boolean removeStudent(Student student) {
        return false;
    }

    @Override
    public void clear() {
        this.students = new HashSet<>();
    }
}
