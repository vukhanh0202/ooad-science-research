package com.uit.ooad.scienceresearch.mapper.topic.converter;

import com.uit.ooad.scienceresearch.entity.Council;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.repository.CouncilRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.repository.TeamRepository;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/20/2020
 */
@Component
public class SignUpTopicConverter {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CouncilRepository councilRepository;

    public Topic getTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElse(new Topic());
        if (topic.getTopicId() != null) {
            return topic;
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

    public Council getCouncil(Long councilId) {
        if (councilId != null) {
            Council council = councilRepository.findById(councilId).orElse(new Council());
            if (council.getCouncilId() != null) {
                return council;
            }
        }
        return null;
    }
}
