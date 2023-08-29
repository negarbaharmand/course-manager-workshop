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

import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        if (form == null) {
            throw new IllegalArgumentException("The form cannot be null.");
        }
        Student createdStudent = studentDao.createStudent(form.getName(), form.getEmail(), form.getAddress());
       //If we didn't have converters StudentView result = new StudentView(createdStudent.getId(), createdStudent.getName(), createdStudent.getEmail(), createdStudent.getAddress());
        StudentView result = converters.studentToStudentView(createdStudent);
        return result;
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        return null;
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
        return null;
    }

    @Override
    public List<StudentView> findAll() {
        return null;
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
