package com.uit.ooad.scienceresearch.entity.join;

import com.uit.ooad.scienceresearch.constant.EProcess;
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
@Table(name = "signup_topic")
@Data
@NoArgsConstructor
public class SignUpTopic extends BaseEntity {

    @EmbeddedId
    private SignUpTopicId id = new SignUpTopicId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("topicId")
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column
    private EProcess start;

    @Column(name = "faculty_review")
    private EProcess facultyReview;

    @Column(name = "university_review")
    private EProcess universityReview;

    @Column
    private EProcess completed;

    @Column(name = "date_approved")
    private String dateApproved;

    @Column(name = "date_expired")
    private String dateExpired;

    @Column(name = "date_extend")
    private String dateExtend;

    @Column
    private Boolean result;

    @Column(columnDefinition = "boolean default false")
    private Boolean finish;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpTopic that = (SignUpTopic) o;
        return Objects.equals(team, that.team) &&
                Objects.equals(topic, that.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, topic);
    }

    @Override
    public String toString() {
        return "SignUpTopic{" +
                "id=" + id +
                '}';
    }
}
