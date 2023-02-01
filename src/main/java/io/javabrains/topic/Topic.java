package io.javabrains.topic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topic {

    @Id
    private String id;
    private String name;
    private String description;

    public Topic() {
    }

    public Topic(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
