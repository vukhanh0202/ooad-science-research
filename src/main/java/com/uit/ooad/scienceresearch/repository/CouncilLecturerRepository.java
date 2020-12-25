package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import com.uit.ooad.scienceresearch.entity.join.CouncilLecturerId;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopicId;
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
public interface CouncilLecturerRepository extends JpaRepository<CouncilLecturer, CouncilLecturerId> {
    List<CouncilLecturer> findAllByCouncil_CouncilIdAndLecturer_LecturerId(Long councilId, Long lecturerId);
}

