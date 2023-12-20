package se.lexicon.course_manager_assignment.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public CourseView create(CreateCourseForm form) {
        if (form == null) {
            throw new IllegalArgumentException("The form cannot be null.");
        }
        Course createdCourse = courseDao.createCourse(form.getCourseName(), form.getStartDate(), form.getWeekDuration());
        return converters.courseToCourseView(createdCourse);
    }

    @Override
    public CourseView update(UpdateCourseForm form) {
        if (form == null) {
            throw new IllegalArgumentException("The update course form cannot be null.");
        }
        Course toUpdateCourse = courseDao.findById(form.getId());
        if (toUpdateCourse == null) {
            throw new IllegalArgumentException("Course not found with ID: " + form.getId());
        }
        toUpdateCourse.setCourseName(form.getCourseName());
        toUpdateCourse.setStartDate(form.getStartDate());
        toUpdateCourse.setWeekDuration(form.getWeekDuration());

        courseDao.removeCourse(toUpdateCourse);  //Remove the course from database
        courseDao.createCourse(toUpdateCourse.getCourseName(), toUpdateCourse.getStartDate(), toUpdateCourse.getWeekDuration());  //Create a new entity

        return converters.courseToCourseView(toUpdateCourse);
    }


    @Override
    public List<CourseView> searchByCourseName(String courseName) {
        Collection<Course> courses = courseDao.findByNameContains(courseName);
        return courses.stream()
                .map(converters::courseToCourseView)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {
        Collection<Course> courses = courseDao.findByDateAfter(end);
        return courses.stream()
                .map(converters::courseToCourseView)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {
        Collection<Course> courses = courseDao.findByDateAfter(start);
        return courses.stream()
                .map(converters::courseToCourseView)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        Course course = courseDao.findById(courseId);
        Student student = studentDao.findById(studentId);

        if (course != null && student != null) {
            return course.enrollStudent(student);
        }
        return false;
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        Course course = courseDao.findById(courseId);
        Student student = studentDao.findById(studentId);

        if (course != null && student != null) {
            return course.unrollStudent(student);
        }
        return false;
    }

    @Override
    public CourseView findById(int id) {
        Course course = courseDao.findById(id);
        return converters.courseToCourseView(course);
    }

    @Override
    public List<CourseView> findAll() {
        Collection<Course> courses = courseDao.findAll();
        return converters.coursesToCourseViews(courses);
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {
        Collection<Course> courses = courseDao.findByStudentId(studentId);
        return converters.coursesToCourseViews(courses);
    }


    @Override
    public boolean deleteCourse(int id) {
        Course course = courseDao.findById(id);
        if (course != null) {
            return courseDao.removeCourse(course);
        }
        return false;
    }
}

