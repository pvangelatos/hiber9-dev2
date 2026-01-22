package gr.aueb.cf.model;

import gr.aueb.cf.enums.LessonType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String title;

    @Column(length = 1000 )
    private String comments;

    @Enumerated(EnumType.ORDINAL)   // default
    @Column(name = "lesson_type")
    private LessonType lessonType;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.NONE )
    @ManyToMany()
    @JoinTable(name = "courses_teachers")
    private Set<Teacher> teachers = new HashSet<>();

    public Set<Teacher> getAllTeachers() {
        return Collections.unmodifiableSet(teachers);
    }

    public void addTeacher(Teacher teacher) {
        if (teachers == null) teachers = new HashSet<>();
        teachers.add(teacher);
        teacher.getCourses().add(this);
    }

    public void removeTeacher(Teacher teacher) {
        if (teachers == null) return;
        teachers.remove(teacher);
        teacher.getCourses().remove(this);
    }

    @Override
    public String toString() {
        return String.format("%d, %s", id, title);
    }
}
