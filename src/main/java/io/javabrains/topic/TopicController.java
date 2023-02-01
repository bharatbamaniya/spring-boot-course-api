package io.javabrains.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/topics/{topicId}")
    public Optional<Topic> getTopic(@PathVariable String topicId){
        return topicService.getTopic(topicId);
    }

    @PostMapping("/topics")
    public Topic addTopic(@RequestBody Topic topic){
        return topicService.addTopic(topic);
    }

    @PutMapping("/topics/{topidId}")
    public Topic updateTopic(@PathVariable String topidId,@RequestBody Topic topic){
        return topicService.updateTopic(topidId,topic);
    }

    @DeleteMapping("/topics/{topicId}")
    public String deleteTopic(@PathVariable String topicId){
        if(getTopic(topicId).isEmpty())
            return topicId + " Not Exits!";

        topicService.deleteTopic(topicId);
        return topicId + " Deleted";
    }
}

