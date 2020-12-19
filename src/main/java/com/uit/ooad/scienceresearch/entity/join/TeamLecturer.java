package com.uit.ooad.scienceresearch.entity.join;

import com.uit.ooad.scienceresearch.entity.BaseEntity;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
@Entity
@Table(name = "team_lecturer")
@Data
@NoArgsConstructor
public class TeamLecturer extends BaseEntity {

    @EmbeddedId
    private TeamLecturerId id = new TeamLecturerId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @Column
    private String position;

    @Column(columnDefinition = "boolean default false")
    private boolean isPrimary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TeamLecturer that = (TeamLecturer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
