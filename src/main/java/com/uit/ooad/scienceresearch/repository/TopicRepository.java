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

    // search
    List<Topic> findAllByNameTopicContainingAndFacultyFacultyIdAndLevelLevelIdAndFieldTopicFieldId(String nameTopic
            ,Long facultyId, Long levelId, Long fieldTopicId, Pageable pageable);

    List<Topic> findAllByNameTopicContainingAndFacultyFacultyIdAndLevelLevelId(String nameTopic
            ,Long facultyId, Long levelId, Pageable pageable);

    List<Topic> findAllByNameTopicContainingAndFacultyFacultyIdAndFieldTopicFieldId(String nameTopic
            ,Long facultyId, Long fieldTopicId, Pageable pageable);

    List<Topic> findAllByNameTopicContainingAndFacultyFacultyId(String nameTopic
            ,Long facultyId, Pageable pageable);

    List<Topic> findAllByNameTopicContainingAndLevelLevelIdAndFieldTopicFieldId(String nameTopic
            , Long levelId, Long fieldTopicId, Pageable pageable);

    List<Topic> findAllByNameTopicContainingAndLevelLevelId(String nameTopic
            , Long levelId, Pageable pageable);

    List<Topic> findAllByNameTopicContainingAndFieldTopicFieldId(String nameTopic
            ,Long fieldTopicId, Pageable pageable);

    // Count
    Long countAllByNameTopicContainingAndFacultyFacultyIdAndLevelLevelIdAndFieldTopicFieldId(String nameTopic
            ,Long facultyId, Long levelId, Long fieldTopicId);

    Long countAllByNameTopicContainingAndFacultyFacultyIdAndLevelLevelId(String nameTopic
            ,Long facultyId, Long levelId);

    Long countAllByNameTopicContainingAndFacultyFacultyIdAndFieldTopicFieldId(String nameTopic
            ,Long facultyId, Long fieldTopicId);

    Long countAllByNameTopicContainingAndFacultyFacultyId(String nameTopic
            ,Long facultyId);

    Long countAllByNameTopicContainingAndLevelLevelIdAndFieldTopicFieldId(String nameTopic
            , Long levelId, Long fieldTopicId);

    Long countAllByNameTopicContainingAndLevelLevelId(String nameTopic
            , Long levelId);

    Long countAllByNameTopicContainingAndFieldTopicFieldId(String nameTopic
            ,Long fieldTopicId);
}
