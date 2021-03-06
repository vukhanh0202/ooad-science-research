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
@Table(name = "level")
@Data
@NoArgsConstructor
public class Level extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private Long levelId;

    @Column(name = "name_level", unique = true)
    private String nameLevel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "level")
    private List<Topic> topics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return Objects.equals(levelId, level.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelId);
    }

    @Override
    public String toString() {
        return "Level{" +
                "id=" + levelId +
                ", nameLevel='" + nameLevel + '\'' +
                '}';
    }
}
