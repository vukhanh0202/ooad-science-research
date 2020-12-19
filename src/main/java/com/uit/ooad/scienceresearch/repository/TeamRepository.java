package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}