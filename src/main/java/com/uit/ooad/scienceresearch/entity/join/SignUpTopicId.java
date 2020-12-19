package com.uit.ooad.scienceresearch.entity.join;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Data
public class SignUpTopicId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long teamId;
    private Long topicId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpTopicId that = (SignUpTopicId) o;
        return Objects.equals(teamId, that.teamId) &&
                Objects.equals(topicId, that.topicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, topicId);
    }
}