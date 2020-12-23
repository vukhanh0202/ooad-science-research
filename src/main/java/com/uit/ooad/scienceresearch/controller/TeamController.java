package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.service.team.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/23/2020
 */
@RestController
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    ITeamService teamService;

    /**
     * Find Team with Id
     *
     * @return
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTopicById(@PathVariable("id") Long teamId) {
        List<TeamLecturerDto> result = teamService.getFindMemberOfTeamService().execute(teamId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(result));
    }
}
