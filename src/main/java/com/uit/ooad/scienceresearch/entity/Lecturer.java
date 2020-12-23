package com.uit.ooad.scienceresearch.entity;

import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Entity
@Table(name = "lecturer")
@Data
@NoArgsConstructor
public class Lecturer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecturer_id")
    private Long lecturerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob = new Date();

    @Column(name = "major")
    private String major;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "degree")
    private String degree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    private String position;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lecturer")
    private List<TeamLecturer> groupLecturers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(lecturerId, lecturer.lecturerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecturerId);
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + lecturerId +
                ", fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", major='" + major + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
