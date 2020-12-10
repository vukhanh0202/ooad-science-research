package com.uit.ooad.scienceresearch.service.contract.impl;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.entity.Contract;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.contract.ContractMapper;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.IUpdateContractService;
import com.uit.ooad.scienceresearch.service.topic.IUpdateTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class UpdateContractServiceImpl extends AbstractBaseService<ContractDto, Boolean>
        implements IUpdateContractService<ContractDto, Boolean> {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ContractMapper contractMapper;

    @Override
    public void preExecute(ContractDto contractDto) {
        if (contractRepository.findById(contractDto.getId()).isEmpty()) {
            throw new NotFoundException("Contract is  Not Found!");
        }
        if (contractRepository.findByNameContract(contractDto.getNameContract()).isPresent() && !contractDto.getIsDeleted()) {
            throw new InvalidException("Contract is Exist!");
        }
    }

    @Override
    public Boolean doing(ContractDto contractDto) {
        try {
            Contract oldContract = contractRepository.findById(contractDto.getId()).get();
            contractMapper.updateContract(contractDto, oldContract);
            contractRepository.save(oldContract);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
