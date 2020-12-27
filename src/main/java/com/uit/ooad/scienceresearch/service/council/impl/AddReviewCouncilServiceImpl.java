package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.dto.council.CouncilFullDto;
import com.uit.ooad.scienceresearch.dto.council.RecordDto;
import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import com.uit.ooad.scienceresearch.entity.join.Record;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.repository.CouncilLecturerRepository;
import com.uit.ooad.scienceresearch.repository.CouncilRepository;
import com.uit.ooad.scienceresearch.repository.RecordRepository;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.council.IAddReviewCouncilService;
import com.uit.ooad.scienceresearch.service.council.IFindDetailCouncilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Default.PRESIDENT_NOT_HAVE_COMMENT;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Register.BAD_REQUEST;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class AddReviewCouncilServiceImpl extends AbstractBaseService<RecordDto, Boolean>
        implements IAddReviewCouncilService<RecordDto, Boolean> {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    CouncilLecturerRepository councilLecturerRepository;

    @Autowired
    CouncilMapper councilMapper;

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Override
    public void preExecute(RecordDto recordDto) {
        if (recordDto.getScore() == null ||
                recordDto.getCouncilId() == null ||
                recordDto.getLecturerId() == null ||
                recordDto.getTeamId() == null ||
                recordDto.getTopicId() == null) {
            throw new InvalidException(messageHelper.getMessage(BAD_REQUEST));
        }
        if (recordDto.getComment() == null || recordDto.getComment().equals("")) {
            CouncilLecturer councilLecturer = councilLecturerRepository
                    .findAllByCouncil_CouncilIdAndLecturer_LecturerIdAndPositionPositionId(recordDto.getCouncilId(), recordDto.getLecturerId(), 1L);
            if (councilLecturer != null) {
                throw new InvalidException(messageHelper.getMessage(PRESIDENT_NOT_HAVE_COMMENT));
            }
        }
        if (Long.parseLong(recordDto.getScore()) < 0 || Long.parseLong(recordDto.getScore()) > 100) {
            throw new InvalidException(messageHelper.getMessage(BAD_REQUEST));
        }
    }

    @Override
    public Boolean doing(RecordDto recordDto) {
        try {
            Record record = recordRepository.findAllByCouncil_CouncilIdAndLecturerLecturerIdAndTeamTeamIdAndTopicTopicId
                    (recordDto.getCouncilId(), recordDto.getLecturerId(), recordDto.getTeamId(), recordDto.getTopicId());
            councilMapper.updateRecord(recordDto, record);
            recordRepository.save(record);

            List<Record> records = recordRepository.findAllByCouncil_CouncilIdAndTeamTeamIdAndTopicTopicId(recordDto.getCouncilId(),
                    recordDto.getTeamId(), recordDto.getTopicId());
            int totalItem = records.size();
            records.removeIf(record1 -> record1.getScore() == null);

            if (totalItem == records.size()) {
                SignUpTopic signUpTopic = signUpTopicRepository.findByTopicTopicIdAndTeamTeamId(recordDto.getTopicId(),
                        recordDto.getTeamId());
                Long totalScore = 0L;
                for (Record item : records) {
                    totalScore += item.getScore();
                }
                Boolean result = totalScore / totalItem >= 65;
                signUpTopic.setResult(result);
                signUpTopicRepository.save(signUpTopic);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
