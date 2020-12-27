package com.uit.ooad.scienceresearch.mapper.council;

import com.uit.ooad.scienceresearch.dto.council.*;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.entity.Council;
import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import com.uit.ooad.scienceresearch.entity.join.Record;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import com.uit.ooad.scienceresearch.mapper.council.converter.CouncilConverter;
import com.uit.ooad.scienceresearch.mapper.team.TeamLecturerMapper;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.CouncilLecturerRepository;
import com.uit.ooad.scienceresearch.repository.RecordRepository;
import com.uit.ooad.scienceresearch.repository.TeamLecturerRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class CouncilMapper implements BaseMapper {

    @Autowired
    CouncilConverter councilConverter;

    @Autowired
    TeamLecturerMapper teamLecturerMapper;

    @Autowired
    CouncilLecturerRepository councilLecturerRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    TeamLecturerRepository teamLecturerRepository;

    @Named("toEntity")
    @BeforeMapping
    protected void toEntity(CouncilLecturerDto dto, @MappingTarget CouncilLecturer entity) {
        entity.setCouncil(councilConverter.getCouncil(dto.getCouncilId()));
        entity.setLecturer(councilConverter.getLecturer(dto.getLecturerId()));
        entity.setPosition(councilConverter.getPosition(dto.getPositionId()));
    }

    @Named("toRecordEntity")
    @BeforeMapping
    protected void toRecordEntity(RecordDto dto, @MappingTarget Record entity) {
        entity.setCouncil(councilConverter.getCouncil(dto.getCouncilId()));
        entity.setLecturer(councilConverter.getLecturer(dto.getLecturerId()));
        entity.setTeam(councilConverter.getTeam(dto.getTeamId()));
        entity.setTopic(councilConverter.getTopic(dto.getTopicId()));
    }

    @Named("toCouncilInfoDto")
    @BeforeMapping
    protected void toCouncilInfoDto(Council entity, @MappingTarget CouncilInfoDto dto) {
        dto.setCouncilId(entity.getCouncilId());
        dto.setNameTopic(entity.getSignUpTopics().get(0).getTopic().getNameTopic());
        for (CouncilLecturer item : entity.getCouncilLecturers()) {
            if (item.getPosition().getPositionId().equals(1L)) {
                dto.setPresidentOfCouncil(item.getLecturer().getFullName());
            }
        }
        dto.setTotalRegister((long) entity.getSignUpTopics().size());
    }

    @Named("toTopicReview")
    @BeforeMapping
    protected void toTopicReview(SignUpTopic entity, @MappingTarget TopicReview dto) {
        dto.setTopicId(entity.getTopic().getTopicId());
        dto.setTeamId(entity.getTeam().getTeamId());
        dto.setId(dto.getTopicId() + dto.getTeamId());
        dto.setFacultyName(entity.getTopic().getFaculty().getNameFaculty());
        dto.setFieldTopic(entity.getTopic().getFieldTopic().getFieldName());
        dto.setLevelName(entity.getTopic().getLevel().getNameLevel());
        dto.setNameTopic(entity.getTopic().getNameTopic());
        dto.setYear(entity.getTopic().getYear());
        dto.setStatus(entity.getFinish() ? "COMPLETED" : "NOT COMPLETED");
        dto.setMembers(teamLecturerMapper.toListTeamLecturerDto(entity.getTeam().getGroupLecturers()));
        dto.setCouncilId(entity.getCouncil() == null ? null : entity.getCouncil().getCouncilId());
        List<TeamLecturer> arr = entity.getTeam().getGroupLecturers();

        for (TeamLecturer item : arr) {
            if (item.isPrimary()) {
                dto.setLeader(item.getLecturer().getFullName());
            }
        }
    }

    @Named("toRecordDto")
    @BeforeMapping
    protected void toRecordDto(Record entity, @MappingTarget RecordDto dto) {
        String namePosition = councilLecturerRepository.findAllByCouncil_CouncilIdAndLecturer_LecturerId(entity.getCouncil().getCouncilId(),
                entity.getLecturer().getLecturerId()).get(0).getPosition().getNamePosition();
        dto.setPosition(namePosition);
        dto.setScore(entity.getScore() == null ? "NOT RATE" : entity.getScore().toString());
        dto.setComment(entity.getComment() == null ? "" : entity.getComment());
    }

    @Named("toTopicReviewByCouncilDto")
    @BeforeMapping
    protected void toTopicReviewByCouncilDto(SignUpTopic entity, @MappingTarget TopicReviewByCouncilDto dto) {
        dto.setTeamId(entity.getTeam().getTeamId());
        dto.setFieldTopic(entity.getTopic().getFieldTopic().getFieldName());
        dto.setLevelName(entity.getTopic().getLevel().getNameLevel());
        dto.setNameTopic(entity.getTopic().getNameTopic());
        dto.setYear(entity.getTopic().getYear());
        dto.setLeader(teamLecturerRepository.findAllByTeamTeamIdAndIsPrimary(entity.getTeam().getTeamId(), true).get(0).getLecturer().getFullName());
        dto.setRecordList(this.toListRecordDto(recordRepository
                .findAllByCouncil_CouncilIdAndTeamTeamIdAndTopicTopicId(entity.getCouncil().getCouncilId(),
                        entity.getTeam().getTeamId(), entity.getTopic().getTopicId())));
        if (entity.getResult() == null) {
            dto.setResult("NOT RATE");
        } else {
            dto.setResult(entity.getResult() ? "PASS" : "FAIL");
        }
    }

    @Named("toReviewCouncilByUserDto")
    @BeforeMapping
    protected void toReviewCouncilByUserDto(Record entity, @MappingTarget ReviewCouncilByUserDto dto) {
        dto.setId(entity.getTeam().getTeamId() + entity.getCouncil().getCouncilId());
        dto.setCouncilId(entity.getCouncil().getCouncilId());
        dto.setTeamId(entity.getTeam().getTeamId());
        dto.setTopicId(entity.getTopic().getTopicId());
        dto.setFacultyName(entity.getTopic().getFaculty().getNameFaculty());
        dto.setFieldTopic(entity.getTopic().getFieldTopic().getFieldName());
        dto.setLevelName(entity.getTopic().getLevel().getNameLevel());
        dto.setNameTopic(entity.getTopic().getNameTopic());
        dto.setScoreString(entity.getScore() == null ? "NOT RATE" : entity.getScore().toString());
        dto.setScore(entity.getScore());
        dto.setComment(entity.getComment());
    }

    @Named("toDetailReviewCouncilByUserDto")
    @BeforeMapping
    protected void toDetailReviewCouncilByUserDto(Record entity, @MappingTarget DetailReviewCouncilByUserDto dto) {
        dto.setComment(entity.getComment());
        dto.setScore(entity.getScore());
        dto.setMembers(this.toListRecordDto(recordRepository
                .findAllByCouncil_CouncilIdAndTeamTeamIdAndTopicTopicId(entity.getCouncil().getCouncilId(),
                        entity.getTeam().getTeamId(), entity.getTopic().getTopicId())));
        dto.setTopic(topicMapper.toTopicFullDto(entity.getTopic()));
    }


    // Mapping single
    @BeanMapping(qualifiedByName = "toTopicReview", ignoreByDefault = true)
    public abstract TopicReview toTopicReview(SignUpTopic entity);

    @BeanMapping(qualifiedByName = "toEntity", ignoreByDefault = true)
    public abstract CouncilLecturer toEntity(CouncilLecturerDto dto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "lecturer.lecturerId", target = "lecturerId")
    @Mapping(source = "lecturer.fullName", target = "fullName")
    @Mapping(source = "position.namePosition", target = "position")
    public abstract CouncilLecturerDto toDto(CouncilLecturer entity);

    @BeanMapping(qualifiedByName = "toRecordEntity", ignoreByDefault = true)
    @Mapping(source = "score", target = "score")
    @Mapping(source = "comment", target = "comment")
    public abstract Record toRecordEntity(RecordDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateRecord(RecordDto dto, @MappingTarget Record entity);

    @BeanMapping(qualifiedByName = "toRecordDto", ignoreByDefault = true)
    @Mapping(source = "lecturer.fullName", target = "nameLecturer")
    public abstract RecordDto toRecordDto(Record entity);

    @BeanMapping(qualifiedByName = "toCouncilInfoDto", ignoreByDefault = true)
    public abstract CouncilInfoDto toCouncilInfoDto(Council entity);

    @BeanMapping(qualifiedByName = "toTopicReviewByCouncilDto", ignoreByDefault = true)
    public abstract TopicReviewByCouncilDto toTopicReviewByCouncilDto(SignUpTopic entity);

    @BeanMapping(qualifiedByName = "toReviewCouncilByUserDto", ignoreByDefault = true)
    public abstract ReviewCouncilByUserDto toReviewCouncilByUserDto(Record entity);

    @BeanMapping(qualifiedByName = "toDetailReviewCouncilByUserDto", ignoreByDefault = true)
    public abstract DetailReviewCouncilByUserDto toDetailReviewCouncilByUserDto(Record entity);


    // List
    @BeanMapping(ignoreByDefault = true)
    public abstract List<TopicReview> toListTopicReview(List<SignUpTopic> entities);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<CouncilInfoDto> toListCouncilInfoDto(Set<Council> entities);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<RecordDto> toListRecordDto(List<Record> entities);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<TopicReviewByCouncilDto> toListTopicReviewByCouncilDto(List<SignUpTopic> entities);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<CouncilLecturerDto> toListDto(List<CouncilLecturer> entities);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<ReviewCouncilByUserDto> toListReviewCouncilByUserDto(List<Record> entities);
}
