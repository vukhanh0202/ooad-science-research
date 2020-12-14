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
@Table(name = "topic")
@Data
@NoArgsConstructor
public class Topic extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "name_topic")
    private String nameTopic;

    @Column(name = "year")
    private Long year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private FieldTopic fieldTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    private List<SignUpTopic> signUpTopics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(topicId, topic.topicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + topicId +
                ", nameTopic='" + nameTopic + '\'' +
                ", year=" + year +
                '}';
    }
}
