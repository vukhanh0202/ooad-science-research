package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query(value = "SELECT * FROM TOPIC t WHERE LOWER(t.name_topic) LIKE CONCAT(\"%\", LOWER(:nameTopic), \"%\")" +
            " AND (:facultyId is null or t.faculty_id = :facultyId)" +
            " AND (:levelId is null or t.level_id = :levelId)" +
            " AND (:fieldId is null or t.field_id = :fieldId)",
            nativeQuery = true)
    List<Topic> findCustomerByNameTopicContainingAndFacultyIdAndLevelIdAndFieldId(@Param("nameTopic") String nameTopic,
                                                                             @Param("facultyId") Long facultyId,
                                                                             @Param("levelId") Long levelId,
                                                                             @Param("fieldId") Long fieldId,
                                                                             Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM TOPIC t WHERE LOWER(t.name_topic) LIKE CONCAT(\"%\", LOWER(:nameTopic), \"%\")" +
            " AND (:facultyId is null or t.faculty_id = :facultyId)" +
            " AND (:levelId is null or t.level_id = :levelId)" +
            " AND (:fieldId is null or t.field_id = :fieldId)",
            nativeQuery = true)
    Long countCustomerByNameTopicContainingAndFacultyIdAndLevelIdAndFieldId(@Param("nameTopic") String nameTopic,
                                                                                  @Param("facultyId") Long facultyId,
                                                                                  @Param("levelId") Long levelId,
                                                                                  @Param("fieldId") Long fieldId);

    List<Topic> findAllByFacultyFacultyId(Long facultyId);
}
