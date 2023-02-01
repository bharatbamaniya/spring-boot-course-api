package io.javabrains.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getAllLessons(String lessonId) {
        return lessonRepository.findByCourseId(lessonId);
    }

    public Optional<Lesson> getLesson(String lessonId){
        return lessonRepository.findById(lessonId);
    }

    public Lesson addLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(String courseId, Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(String lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
