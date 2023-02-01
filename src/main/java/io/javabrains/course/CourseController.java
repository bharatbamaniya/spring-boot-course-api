package io.javabrains.course;

import io.javabrains.topic.Topic;
import io.javabrains.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics/{topicId}/courses")
    public List<Course> getAllCourses(@PathVariable String topicId) {
        return courseService.getAllCourses(topicId);
    }

    @GetMapping("/topics/{topicId}/courses/{courseId}")
    public Optional<Course> getCourse(@PathVariable String courseId){
        return courseService.getCourse(courseId);
    }

    @PostMapping("/topics/{topicId}/courses")
    public Course addCourse(@RequestBody Course course, @PathVariable String topicId){
        setTopicHelper(course,topicId);
        return courseService.addCourse(course);
    }

    @PutMapping("/topics/{topicId}/courses/{courseId}")
    public Course updateCourse(@PathVariable String topicId, @PathVariable String courseId, @RequestBody Course course){
        setTopicHelper(course,topicId);
        return courseService.updateCourse(courseId, course);
    }

    @DeleteMapping("/topics/{topicId}/courses/{courseId}")
    public String deleteCourse(@PathVariable String topicId, @PathVariable String courseId){
        if(getCourse(courseId).isEmpty())
            return courseId + " Not Exits!";

        courseService.deleteCourse(courseId);
        return courseId + " Deleted";
    }

    public void setTopicHelper(Course course, String topicId){
        Optional<Topic> topic = topicService.getTopic(topicId);

        if(topic.isEmpty()) {
            topicService.addTopic(new Topic(topicId, "", ""));
            topic = topicService.getTopic(topicId);
        }
        Topic topicInstance = topic.stream().collect(Collectors.toList()).get(0);
        course.setTopic(topicInstance);
    }
}