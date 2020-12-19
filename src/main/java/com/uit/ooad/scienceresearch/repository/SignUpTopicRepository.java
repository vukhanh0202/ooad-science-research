package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopicId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
public interface SignUpTopicRepository extends JpaRepository<SignUpTopic, SignUpTopicId> {

    List<SignUpTopic> findAllByTopicTopicId(Long topicId);
}

