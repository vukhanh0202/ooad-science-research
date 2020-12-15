package com.uit.ooad.scienceresearch.service.contract.impl;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.contract.ContractMapper;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.IFindContractByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Contract.CONTRACT_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Service
public class FindContractByIdServiceImpl extends AbstractBaseService<Long, ContractDto>
        implements IFindContractByIdService<Long, ContractDto> {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ContractMapper contractMapper;

    @Override
    public void preExecute(Long contractId) {
        if (contractRepository.findById(contractId).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(CONTRACT_NOT_FOUND));
        }
    }

    @Override
    public ContractDto doing(Long contractId) {
        return contractMapper.toContractDto(contractRepository.findById(contractId).get());
    }
}
