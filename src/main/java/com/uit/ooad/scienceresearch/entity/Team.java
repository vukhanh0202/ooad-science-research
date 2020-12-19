package com.uit.ooad.scienceresearch.entity;

import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
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
@Table(name = "team")
@Data
@NoArgsConstructor
public class Team extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<TeamLecturer> groupLecturers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Team group = (Team) o;
        return Objects.equals(teamId, group.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teamId);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + teamId +
                '}';
    }
}
