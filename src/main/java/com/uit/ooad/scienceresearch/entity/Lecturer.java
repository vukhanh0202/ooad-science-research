package com.uit.ooad.scienceresearch.entity;

import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
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
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lecturer")
    private List<SignUpTopic> signUpTopics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(id, lecturer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", major='" + major + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
