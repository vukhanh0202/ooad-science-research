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
@Table(name = "accept_council")
@Data
@NoArgsConstructor
public class AcceptCouncil extends BaseEntity {

    @EmbeddedId
    private AcceptCouncilId id = new AcceptCouncilId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("councilId")
    @JoinColumn(name = "council_id")
    private Council council;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("topicId")
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AcceptCouncil that = (AcceptCouncil) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "AcceptCouncil{" +
                "id=" + id +
                ", council=" + council +
                ", topic=" + topic +
                '}';
    }
}
