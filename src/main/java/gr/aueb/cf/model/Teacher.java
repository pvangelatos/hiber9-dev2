package gr.aueb.cf.model;

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
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "teachers")
    private Set<Course> courses = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "teacher_more_info_id")
    private TeacherMoreInfo teacherMoreInfo = new TeacherMoreInfo();

    public Teacher(String firstname, String lastname, Boolean active) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.active = active;
    }

    public Set<Course> getAllCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public void addCourse(Course course) {
        if (courses == null) courses = new HashSet<>();
        courses.add(course);
        course.getTeachers().add(this);
    }

    public void removeCourse (Course course) {
        if (courses == null) return;
        courses.remove(course);
        course.getTeachers().remove(this);
    }

    @Override
    public String toString() {
        return String.format("%d %s %s", id, firstname, lastname);
    }


}
