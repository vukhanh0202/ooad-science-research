package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findAllByNameTopicContaining(String nameTopic, Pageable pageable);
}
