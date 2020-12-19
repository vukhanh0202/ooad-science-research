package com.uit.ooad.scienceresearch.mapper.team.converter;

import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
@Component
public class TeamLecturerConverter {

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    TeamRepository teamRepository;

    public Lecturer getLecturer(Long lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId).orElse(new Lecturer());
        if (lecturer.getLecturerId() != null) {
            return lecturer;
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
}
