package io.javabrains.course;

import io.javabrains.topic.Topic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Course {

    @Id
    private String id;
    private String name;
    private String description;

    @ManyToOne
    private Topic topic;

    public Course() {
    }

    public Course(String id, String name, String description, String topicId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = new Topic(topicId, "", "");
    }

    public String getId() {
        return id;
    }

    public void setId(String topicId) {
        this.id = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String topicName) {
        this.name = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String topicDescription) {
        this.description = topicDescription;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
