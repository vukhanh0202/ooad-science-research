package com.uit.ooad.scienceresearch.entity.join;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public class RecordId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long councilId;
    private Long topicId;
    private Long teamId;
    private Long lecturerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordId recordId = (RecordId) o;
        return Objects.equals(councilId, recordId.councilId) &&
                Objects.equals(topicId, recordId.topicId) &&
                Objects.equals(teamId, recordId.teamId) &&
                Objects.equals(lecturerId, recordId.lecturerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(councilId, topicId, teamId, lecturerId);
    }
}