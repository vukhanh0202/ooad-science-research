package com.uit.ooad.scienceresearch.mapper.council.converter;

import com.uit.ooad.scienceresearch.entity.Council;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Position;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.repository.CouncilRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.repository.PositionRepository;
import com.uit.ooad.scienceresearch.repository.TeamRepository;
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
}
