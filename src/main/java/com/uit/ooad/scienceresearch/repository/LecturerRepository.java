package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.Lecturer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    List<Lecturer> findAllByFullNameContaining(String username, Pageable pageable);
}
