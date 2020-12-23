package com.uit.ooad.scienceresearch.mapper.topic;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerTopicRegisterDto;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.*;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import com.uit.ooad.scienceresearch.mapper.team.TeamLecturerMapper;
import com.uit.ooad.scienceresearch.mapper.topic.converter.SignUpTopicConverter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/20/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class SignUpTopicMapper implements BaseMapper {

    @Autowired
    SignUpTopicConverter signUpTopicConverter;

    @Autowired
    TeamLecturerMapper teamLecturerMapper;

    @Autowired
    TopicMapper topicMapper;

    @Named("toEntity")
    @BeforeMapping
    protected void toEntity(SignUpTopicDto dto, @MappingTarget SignUpTopic entity) {
        entity.setTopic(signUpTopicConverter.getTopic(dto.getTopicId()));
        entity.setTeam(signUpTopicConverter.getTeam(dto.getTeamId()));
    }

    @Named("toTopicRegister")
    @BeforeMapping
    protected void toTopicRegister(SignUpTopic entity, @MappingTarget TopicRegisterDto dto) {
        dto.setYear(entity.getTopic().getYear());
        dto.setTopicId(entity.getTopic().getTopicId());
        dto.setTeamId(entity.getTeam().getTeamId());
        dto.setDescription(entity.getTopic().getDescription());
        dto.setFacultyName(entity.getTopic().getFaculty().getNameFaculty());
        dto.setFieldTopic(entity.getTopic().getFieldTopic().getFieldName());
        dto.setLevelName(entity.getTopic().getLevel().getNameLevel());
        dto.setNameTopic(entity.getTopic().getNameTopic());
        if (entity.getCompleted().equals(EProcess.finish)) {
            dto.setStatus("Completed");
        } else if (entity.getUniversityReview().equals(EProcess.process)) {
            dto.setStatus("University Review");
        } else if (entity.getFacultyReview().equals(EProcess.process)) {
            dto.setStatus("Faculty Review");
        } else {
            dto.setStatus("Decline");
        }
    }

    @Named("toFullTopicRegister")
    @BeforeMapping
    protected void toFullTopicRegister(SignUpTopic entity, @MappingTarget SignUpTopicFullDto dto) {
        dto.setTeamId(entity.getTeam().getTeamId());
        dto.setStart(entity.getStart());
        dto.setFacultyReview(entity.getFacultyReview());
        if (!entity.getUniversityReview().equals(EProcess.none)) {
            dto.setUniversityReview(entity.getUniversityReview());
        }
        dto.setCompleted(entity.getCompleted());
        dto.setTeam(teamLecturerMapper.toListTeamLecturerDto(entity
                .getTeam()
                .getGroupLecturers()));
        for (TeamLecturerDto item : dto.getTeam()) {
            if (item.isPrimary()) {
                dto.setLeader(item.getFullName());
            }
        }
        dto.setTopic(topicMapper.toTopicFullDto(entity.getTopic()));
        // Hard code
        if (entity.getFacultyReview().equals(EProcess.error)) {
            dto.setCurrent(1L);
            dto.setStatus(EProcess.error);
        } else if (entity.getUniversityReview().equals(EProcess.error)) {
            dto.setCurrent(2L);
            dto.setStatus(EProcess.error);
        } else {
            if (entity.getFacultyReview().equals(EProcess.process)) {
                dto.setCurrent(1L);
                dto.setStatus(EProcess.process);
            } else if (entity.getUniversityReview().equals(EProcess.process)) {
                dto.setCurrent(2L);
                dto.setStatus(EProcess.process);
            } else {
                if (entity.getUniversityReview().equals(EProcess.none)) {
                    dto.setCurrent(2L);
                    dto.setStatus(EProcess.finish);
                } else {
                    dto.setCurrent(3L);
                    dto.setStatus(EProcess.finish);
                }
            }
        }
        if (entity.getFinish()) {
            dto.setFinish("COMPLETED");
        } else {
            dto.setFinish("NOT COMPLETED");
        }

        if (entity.getResult() == null) {
            dto.setResult("NOT YET RATE");
        } else if (entity.getResult()) {
            dto.setResult("PASS");
        } else {
            dto.setResult("FAIL");
        }
    }

    @Named("toSignUpTopicForFaculty")
    @BeforeMapping
    protected void toSignUpTopicForFaculty(SignUpTopic entity, @MappingTarget SignUpTopicForFaculty dto) {
        List<TeamLecturer> arr = entity.getTeam().getGroupLecturers();

        for (TeamLecturer item : arr) {
            if (item.isPrimary()) {
                dto.setLeader(item.getLecturer().getFullName());
            }
        }
        dto.setTeamId(entity.getTeam().getTeamId());
        if (entity.getFinish()) {
            dto.setFinish("COMPLETED");
        } else {
            dto.setFinish("NOT COMPLETED");
        }

        if (entity.getResult() == null) {
            dto.setResult("NOT YET RATE");
        } else if (entity.getResult()) {
            dto.setResult("PASS");
        } else {
            dto.setResult("FAIL");
        }

        // Hard code
        if (entity.getFacultyReview().equals(EProcess.error)) {
            dto.setStatus(EProcess.error);
        } else if (entity.getUniversityReview().equals(EProcess.error)) {
            dto.setStatus(EProcess.error);
        } else {
            if (entity.getFacultyReview().equals(EProcess.process)) {
                dto.setStatus(EProcess.process);
            } else if (entity.getUniversityReview().equals(EProcess.process)) {
                dto.setStatus(EProcess.process);
            } else {
                dto.setStatus(EProcess.finish);
            }
        }
    }

    @Named("toInfoTopicDto")
    @BeforeMapping
    protected void toInfoTopicDto(SignUpTopic entity, @MappingTarget InfoTopicDto dto) {
        dto.setTopic(topicMapper.toTopicFullDto(entity.getTopic()));
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        dto.setDateRegister(formatter.format(entity.getCreatedAt()));
        if (entity.getFinish()) {
            dto.setFinish("COMPLETED");
        } else {
            dto.setFinish("NOT COMPLETED");
        }
        dto.setDateApprove(entity.getDateApproved());
        if (entity.getResult() == null) {
            dto.setResult("NOT YET RATE");
        } else if (entity.getResult()) {
            dto.setResult("PASS");
        } else {
            dto.setResult("FAIL");
        }
    }

    @BeanMapping(qualifiedByName = "toEntity", ignoreByDefault = true)
    @Mapping(source = "start", target = "start")
    @Mapping(source = "facultyReview", target = "facultyReview")
    @Mapping(source = "universityReview", target = "universityReview")
    @Mapping(source = "completed", target = "completed")
    @Mapping(source = "dateApproved", target = "dateApproved")
    @Mapping(source = "dateExpired", target = "dateExpired")
    @Mapping(source = "dateExtend", target = "dateExtend")
    @Mapping(source = "result", target = "result")
    @Mapping(source = "finish", target = "finish")
    public abstract SignUpTopic toEntity(SignUpTopicDto dto);

    @BeanMapping(qualifiedByName = "toTopicRegister", ignoreByDefault = true)
    public abstract TopicRegisterDto toTopicRegisterDto(SignUpTopic entity);

    @BeanMapping(qualifiedByName = "toFullTopicRegister", ignoreByDefault = true)
    @Mapping(source = "dateApproved", target = "dateApproved")
    @Mapping(source = "dateExpired", target = "dateExpired")
    @Mapping(source = "dateExtend", target = "dateExtend")
    public abstract SignUpTopicFullDto toSignUpTopicFullDto(SignUpTopic entity);

    @BeanMapping(qualifiedByName = "toSignUpTopicForFaculty", ignoreByDefault = true)
    @Mapping(source = "dateApproved", target = "dateApproved")
    @Mapping(source = "dateExpired", target = "dateExpired")
    @Mapping(source = "dateExtend", target = "dateExtend")
    public abstract SignUpTopicForFaculty toSignUpTopicForFaculty(SignUpTopic entity);

    @BeanMapping(qualifiedByName = "toInfoTopicDto", ignoreByDefault = true)
    public abstract InfoTopicDto toInfoTopicDto(SignUpTopic entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<SignUpTopicFullDto> toListSignUpTopicFullDto(List<SignUpTopic> entities);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<TopicRegisterDto> toListTopicRegisterDto(List<SignUpTopic> entities);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<SignUpTopicForFaculty> toListSignUpTopicForFaculty(List<SignUpTopic> entities);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<InfoTopicDto> toListInfoTopicDto(List<SignUpTopic> entities);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "dateExtend", target = "dateExtend")
    public abstract void updateMyTopic(SignUpTopicDto dto, @MappingTarget SignUpTopic entity);
}
