package com.uit.ooad.scienceresearch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Entity
@Table(name = "faculty")
@Data
@NoArgsConstructor
public class Faculty extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_faculty", unique = true)
    private String nameFaculty;

    @Column(name = "nameUniversity")
    private String nameUniversity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    private List<Lecturer> lecturers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    private List<Topic> topics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", nameFaculty='" + nameFaculty + '\'' +
                ", nameUniversity='" + nameUniversity + '\'' +
                '}';
    }
}
