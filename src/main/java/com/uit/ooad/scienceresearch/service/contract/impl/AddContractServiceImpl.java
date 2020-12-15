package com.uit.ooad.scienceresearch.service.contract.impl;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.mapper.contract.ContractMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.IAddContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Contract.CONTRACT_EXIST;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class AddContractServiceImpl extends AbstractBaseService<ContractDto, Boolean>
        implements IAddContractService<ContractDto, Boolean> {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ContractMapper contractMapper;

    @Override
    public void preExecute(ContractDto contractDto) {
        if (contractRepository.findByNameContract(contractDto.getNameContract()).isPresent()) {
            throw new InvalidException(messageHelper.getMessage(CONTRACT_EXIST, contractDto.getNameContract()));
        }
    }

    @Override
    public Boolean doing(ContractDto contractDto) {
        try {
            contractRepository.save(contractMapper.toContract(contractDto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
