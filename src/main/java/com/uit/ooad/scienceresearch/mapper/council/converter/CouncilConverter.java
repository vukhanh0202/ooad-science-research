package com.uit.ooad.scienceresearch.mapper.council.converter;

import com.uit.ooad.scienceresearch.entity.*;
import com.uit.ooad.scienceresearch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
@Component
public class CouncilConverter {

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    CouncilRepository councilRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TopicRepository topicRepository;

    public Lecturer getLecturer(Long lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId).orElse(new Lecturer());
        if (lecturer.getLecturerId() != null) {
            return lecturer;
        }
        return null;
    }

    public Council getCouncil(Long councilId) {
        Council council = councilRepository.findById(councilId).orElse(new Council());
        if (council.getCouncilId() != null) {
            return council;
        }
        return null;
    }

    public Position getPosition(Long positionId) {
        Position position = positionRepository.findById(positionId).orElse(new Position());
        if (position.getPositionId() != null) {
            return position;
        }
        return null;
    }

    public Team getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(new Team());
        if (team.getTeamId() != null) {
            return team;
        }
        return null;
    }

    public Topic getTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElse(new Topic());
        if (topic.getTopicId() != null) {
            return topic;
        }
        return null;
    }
}
