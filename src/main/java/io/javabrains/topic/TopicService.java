package io.javabrains.topic;

import io.javabrains.course.Course;
import io.javabrains.course.CourseRepository;
import io.javabrains.course.CourseService;
import io.javabrains.lesson.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Optional<Topic> getTopic(String topicId){
        return topicRepository.findById(topicId);
    }

    public Topic addTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic updateTopic(String topicId, Topic topic) {
        return topicRepository.save(topic);
    }

    public void deleteTopic(String topicId) {
        // first we have to delete all courses attached with this topicId
        List<Course> courses = courseRepository.findByTopicId(topicId);
        courses.forEach(course -> courseService.deleteCourse(course.getId()));

        // Now after delete all courses we can delete topic
        topicRepository.deleteById(topicId);
    }

}
