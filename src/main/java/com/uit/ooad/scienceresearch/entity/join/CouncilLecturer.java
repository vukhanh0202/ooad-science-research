package com.uit.ooad.scienceresearch.entity.join;

import com.uit.ooad.scienceresearch.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
@Entity
@Table(name = "council_lecturer")
@Data
@NoArgsConstructor
public class CouncilLecturer extends BaseEntity {

    @EmbeddedId
    private CouncilLecturerId id = new CouncilLecturerId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("councilId")
    @JoinColumn(name = "council_id")
    private Council council;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("positionId")
    @JoinColumn(name = "position_id")
    private Position position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CouncilLecturer that = (CouncilLecturer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
