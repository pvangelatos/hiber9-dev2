package gr.aueb.cf.model;

import gr.aueb.cf.enums.GenderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teacher_more_info")
public class TeacherMoreInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Override
    public String toString() {
        return String.format("%d, %s, %s", id, dateOfBirth, gender);
    }
}
