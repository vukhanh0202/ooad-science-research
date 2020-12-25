package com.uit.ooad.scienceresearch.entity.join;

import com.uit.ooad.scienceresearch.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
public class Record extends BaseEntity {

    @EmbeddedId
    private RecordId id = new RecordId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("councilId")
    @JoinColumn(name = "council_id")
    private Council council;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("topicId")
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @Column(name = "score")
    private Long score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Record record = (Record) o;
        return Objects.equals(id, record.id) &&
                Objects.equals(council, record.council) &&
                Objects.equals(topic, record.topic) &&
                Objects.equals(team, record.team) &&
                Objects.equals(lecturer, record.lecturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, council, topic, team, lecturer);
    }
}
