package com.uit.ooad.scienceresearch.entity.join;

import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Position;
import com.uit.ooad.scienceresearch.entity.Topic;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Entity
@Table(name = "signup_topic")
@Data
@NoArgsConstructor
public class SignUpTopic {

    @EmbeddedId
    private SignUpTopicId id = new SignUpTopicId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("topicId")
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("positionId")
    @JoinColumn(name = "position_id")
    private Position position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpTopic that = (SignUpTopic) o;
        return Objects.equals(lecturer, that.lecturer) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecturer, topic, position);
    }

    @Override
    public String toString() {
        return "SignUpTopic{" +
                "id=" + id +
                '}';
    }
}
