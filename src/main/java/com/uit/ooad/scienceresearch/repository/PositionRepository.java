package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.Faculty;
import com.uit.ooad.scienceresearch.entity.Position;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface PositionRepository extends JpaRepository<Position, Long> {
}
