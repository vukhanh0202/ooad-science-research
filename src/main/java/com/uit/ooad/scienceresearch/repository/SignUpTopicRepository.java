package com.uit.ooad.scienceresearch.repository;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
public interface SignUpTopicRepository extends JpaRepository<SignUpTopic, SignUpTopicId> {

    List<SignUpTopic> findAllByTopicTopicId(Long topicId);

    @Query(value = "SELECT * FROM signup_topic s WHERE " +
            " s.faculty_review = :facultyReview" +
            " AND s.topic_id IN :topicIds",
            nativeQuery = true)
    List<SignUpTopic> findCustomByFacultyReviewAndListTopicId(@Param("topicIds") Set<Long> topicIds, @Param("facultyReview") int facultyReview);

    @Query(value = "SELECT * FROM signup_topic s WHERE " +
            " s.university_review = :universityReview" +
            " AND s.topic_id IN :topicIds",
            nativeQuery = true)
    List<SignUpTopic> findCustomByUniversityReviewAndListTopicId(@Param("topicIds") Set<Long> topicIds, @Param("universityReview") int universityReview);

    SignUpTopic findByTopicTopicIdAndTeamTeamId(Long topicId, Long teamId);
}

