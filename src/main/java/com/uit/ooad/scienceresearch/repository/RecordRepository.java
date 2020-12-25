package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.Account;
import com.uit.ooad.scienceresearch.entity.join.Record;
import com.uit.ooad.scienceresearch.entity.join.RecordId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface RecordRepository extends JpaRepository<Record, RecordId> {

    List<Record> findAllByCouncil_CouncilIdAndTeamTeamIdAndTopicTopicId(Long councilId, Long teamId, Long topicId);
}
