package io.javabrains.lesson;

import io.javabrains.course.Course;
import io.javabrains.course.CourseService;
import io.javabrains.topic.Topic;
import io.javabrains.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class LessonController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/topics/{topicId}/courses/{courseId}/lessons")
    public List<Lesson> getAllLessons(@PathVariable String courseId) {
        return lessonService.getAllLessons(courseId);
    }

    @GetMapping("/topics/{topicId}/courses/{courseId}/lessons/{lessonId}")
    public Optional<Lesson> getLesson(@PathVariable String lessonId) {
        return lessonService.getLesson(lessonId);
    }

    @PostMapping("/topics/{topicId}/courses/{courseId}/lessons")
    public Lesson addLesson(@PathVariable String courseId, @RequestBody Lesson lesson, @PathVariable String topicId){
        setCourseHelper(lesson,courseId, topicId);
        return lessonService.addLesson(lesson);
    }

    @PutMapping("/topics/{topicId}/courses/{courseId}/lessons/{lessonId}")
    public Lesson updateLesson(@PathVariable String topicId, @PathVariable String courseId,@PathVariable String lessonId, @RequestBody Lesson lesson){
        setCourseHelper(lesson,courseId, topicId);
        return lessonService.updateLesson(lessonId, lesson);
    }

    @DeleteMapping("/topics/{topicId}/courses/{courseId}/lessons/{lessonId}")
    public String deleteLesson(@PathVariable String courseId,@PathVariable String lessonId){
        if(getLesson(lessonId).isEmpty())
            return lessonId + " Not Exits!";

        lessonService.deleteLesson(lessonId);
        return lessonId + " Deleted";
    }

    public void setCourseHelper(Lesson lesson, String courseId, String topicId){
        Optional<Topic> topic = topicService.getTopic(topicId);
        if(topic.isEmpty()) {
            topicService.addTopic(new Topic(topicId, "", ""));
        }

        Optional<Course> course = courseService.getCourse(courseId);
        if(course.isEmpty()) {
            courseService.addCourse(new Course(courseId, "", "", topicId));
            course = courseService.getCourse(courseId);
        }

        Course courseInstance = course.stream().collect(Collectors.toList()).get(0);

        lesson.setCourse(courseInstance);
    }
}

