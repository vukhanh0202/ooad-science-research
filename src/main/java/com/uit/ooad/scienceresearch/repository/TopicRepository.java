package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
