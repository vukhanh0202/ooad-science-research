package com.uit.ooad.scienceresearch.entity.join;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public class AcceptCouncilId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long lecturerId;
    private Long topicId;
    private Long positionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptCouncilId that = (AcceptCouncilId) o;
        return Objects.equals(lecturerId, that.lecturerId) &&
                Objects.equals(topicId, that.topicId) &&
                Objects.equals(positionId, that.positionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecturerId, topicId, positionId);
    }


}