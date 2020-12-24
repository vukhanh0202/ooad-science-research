package com.uit.ooad.scienceresearch.entity.join;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
@Data
public class CouncilLecturerId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long councilId;
    private Long lecturerId;
    private Long positionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouncilLecturerId that = (CouncilLecturerId) o;
        return Objects.equals(councilId, that.councilId) &&
                Objects.equals(lecturerId, that.lecturerId) &&
                Objects.equals(positionId, that.positionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(councilId, lecturerId, positionId);
    }
}

