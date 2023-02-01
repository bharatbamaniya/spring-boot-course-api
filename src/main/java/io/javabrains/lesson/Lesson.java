package io.javabrains.lesson;

import io.javabrains.course.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Lesson {

    @Id
    private String id;
    private String name;
    private String description;

    @ManyToOne
    private Course course;

    public Lesson(String id, String name, String description, String courseId, String topicId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.course = new Course(courseId, "", "", topicId);
    }
}
