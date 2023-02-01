package io.javabrains.course;

import io.javabrains.lesson.Lesson;
import io.javabrains.lesson.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public List<Course> getAllCourses(String topicId) {
        return courseRepository.findByTopicId(topicId);
    }

    public Optional<Course> getCourse(String courseId){
        return courseRepository.findById(courseId);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(String courseId, Course course) {
        return courseRepository.save(course);
    }


    public void deleteCourse(String courseId) {
        // first we have to delete all lessons attached with this courseId
        List<Lesson> lessons = lessonRepository.findByCourseId(courseId);
        lessonRepository.deleteAll(lessons);

        // Now after delete all lessons we can delete course
        courseRepository.deleteById(courseId);
    }

}
