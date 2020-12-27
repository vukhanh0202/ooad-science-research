package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
public interface TeamLecturerRepository extends JpaRepository<TeamLecturer, TeamLecturerId> {

    List<TeamLecturer> findAllByLecturerLecturerId(Long lecturerId);

    List<TeamLecturer> findAllByTeamTeamIdAndIsPrimary(Long teamId, Boolean primary);

    @Query(value = "SELECT * FROM team_lecturer t WHERE " +
            " t.lecturer_id IN :lecturerIds",
            nativeQuery = true)
    List<TeamLecturer> findCustomByListLecturerId(@Param("lecturerIds") Set<Long> lecturerIds);
}
