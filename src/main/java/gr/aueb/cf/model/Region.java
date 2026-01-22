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
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "region")
    private Set<Teacher> teachers = new HashSet<>();

    public Set<Teacher> getAllTeachers() {
        return Collections.unmodifiableSet(teachers);
    }

    public void addTeacher(Teacher teacher) {
        if (teachers == null) teachers = new HashSet<>();
        teachers.add(teacher);
        teacher.setRegion(this);
    }

    public void removeTeacher(Teacher teacher) {
        if (teachers == null) return;
        teachers.remove(teacher);
        teacher.setRegion(null);
    }

    @Override
    public String toString() {
        return String.format("%d, %s", id, title);
    }
}
