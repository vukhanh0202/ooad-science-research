package com.uit.ooad.scienceresearch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Entity
@Table(name = "field_topic")
@Data
@NoArgsConstructor
public class FieldTopic extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fieldTopic")
    private List<Topic> topics;

    @Column(name = "field_name", unique = true)
    private String fieldName;

    @Override
    public String toString() {
        return "FieldTopic{" +
                "id=" + id +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
