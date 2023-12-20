package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, Converters converters) {
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        if (form == null) {
            throw new IllegalArgumentException("The form cannot be null.");
        }
        Student createdStudent = studentDao.createStudent(form.getName(), form.getEmail(), form.getAddress());
        //If we didn't have converters StudentView :return new StudentView(createdStudent.getId(), createdStudent.getName(), createdStudent.getEmail(), createdStudent.getAddress());
        return converters.studentToStudentView(createdStudent);
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        if (form == null) {
            throw new IllegalArgumentException("The update student form cannot be null.");
        }
        Student toUpdateStudent = studentDao.findById(form.getId());
        if (toUpdateStudent == null) {
            throw new IllegalArgumentException("Student not found with ID: " + form.getId());
        }

        toUpdateStudent.setName(form.getName());
        toUpdateStudent.setEmail(form.getEmail());
        toUpdateStudent.setAddress(form.getAddress());

        studentDao.removeStudent(toUpdateStudent);
        studentDao.createStudent(toUpdateStudent.getName(), toUpdateStudent.getEmail(), toUpdateStudent.getAddress()); // Create a new entry

        return converters.studentToStudentView(toUpdateStudent);
    }

    @Override
    public StudentView findById(int id) {
        Student student = studentDao.findById(id);
        return converters.studentToStudentView(student);
    }

    @Override
    public StudentView searchByEmail(String email) {
        Student student = studentDao.findByEmailIgnoreCase(email);
        return converters.studentToStudentView(student);
    }

    @Override
    public List<StudentView> searchByName(String name) {
        Collection<Student> students = studentDao.findByNameContains(name);
        List<StudentView> studentViews = new ArrayList<>();
        for (Student student : students) {
            studentViews.add(converters.studentToStudentView(student));
        }
        return studentViews;
    }

    @Override
    public List<StudentView> findAll() {
        Collection<Student> students = studentDao.findAll();
        List<StudentView> studentViews = new ArrayList<>();
        for (Student student : students) {
            studentViews.add(converters.studentToStudentView(student));
        }
        return studentViews;
    }

    @Override
    public boolean deleteStudent(int id) {
        Student student = studentDao.findById(id);
        if (student != null) {
            return studentDao.removeStudent(student);
        }
        return false;
    }
}
