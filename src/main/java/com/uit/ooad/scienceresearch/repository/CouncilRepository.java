package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.Council;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface CouncilRepository extends JpaRepository<Council, Long> {
}
