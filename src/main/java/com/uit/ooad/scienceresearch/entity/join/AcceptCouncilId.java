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

    private Long councilId;
    private Long topicId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptCouncilId that = (AcceptCouncilId) o;
        return Objects.equals(councilId, that.councilId) &&
                Objects.equals(topicId, that.topicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(councilId, topicId);
    }


}