package com.uit.ooad.scienceresearch.entity;

import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
@Entity
@Table(name = "position")
@Data
@NoArgsConstructor
public class Position extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;

    @Column(name = "name_position")
    private String namePosition;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private List<CouncilLecturer> councilLecturers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Position position = (Position) o;
        return Objects.equals(positionId, position.positionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), positionId);
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", namePosition='" + namePosition + '\'' +
                '}';
    }
}
