package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.Lecturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {


    @Query(value = "SELECT * FROM LECTURER l WHERE LOWER(l.full_name) LIKE CONCAT(\"%\", LOWER(:fullName), \"%\")" +
            " AND (:contractId is null or l.contract_id = :contractId)" +
            " AND (:facultyId is null or l.faculty_id = :facultyId)",
            nativeQuery = true)
    List<Lecturer> findCustomerByFullNameContainingAndContractIdAndFacultyId(@Param("fullName") String fullName,
                                                                             @Param("contractId") Long contractId,
                                                                             @Param("facultyId") Long facultyId);

    @Query(value = "SELECT COUNT(*) FROM LECTURER l WHERE LOWER(l.full_name) LIKE CONCAT(\"%\", LOWER(:fullName), \"%\")" +
            " AND (:contractId is null or l.contract_id = :contractId)" +
            " AND (:facultyId is null or l.faculty_id = :facultyId)",
            nativeQuery = true)
    Long countCustomerByFullNameContainingAndContractIdAndFacultyId(@Param("fullName") String fullName,
                                                                    @Param("contractId") Long contractId,
                                                                    @Param("facultyId") Long facultyId);
}
