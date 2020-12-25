package com.uit.ooad.scienceresearch.entity;

import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import com.uit.ooad.scienceresearch.entity.join.Record;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
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
@Table(name = "council")
@Data
@NoArgsConstructor
public class Council extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "council_id")
    private Long councilId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "council")
    private List<CouncilLecturer> councilLecturers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "council")
    private List<SignUpTopic> signUpTopics;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "council")
    private List<Record> records;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Council council = (Council) o;
        return Objects.equals(councilId, council.councilId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), councilId);
    }

    @Override
    public String toString() {
        return "Council{" +
                "councilId=" + councilId +
                '}';
    }
}
