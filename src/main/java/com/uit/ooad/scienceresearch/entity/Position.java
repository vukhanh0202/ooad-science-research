package com.uit.ooad.scienceresearch.entity;

import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
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
@Table(name = "position")
@Data
@NoArgsConstructor
public class Position extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_position")
    private String namePosition;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private List<SignUpTopic> signUpTopics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id, position.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", namePosition='" + namePosition + '\'' +
                '}';
    }
}
