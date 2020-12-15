package com.uit.ooad.scienceresearch.service.contract.impl;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.entity.Contract;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.contract.ContractMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.IUpdateContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Contract.CONTRACT_EXIST;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Contract.CONTRACT_NOT_FOUND;

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
        if (contractRepository.findById(contractDto.getContractId()).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(CONTRACT_NOT_FOUND, contractDto.getContractId()));
        }
        if (contractRepository.findByNameContract(contractDto.getNameContract()).isPresent()) {
            throw new InvalidException(messageHelper.getMessage(CONTRACT_EXIST, contractDto.getNameContract()));
        }
    }

    @Override
    public Boolean doing(ContractDto contractDto) {
        try {
            Contract oldContract = contractRepository.findById(contractDto.getContractId()).get();
            contractMapper.updateContract(contractDto, oldContract);
            contractRepository.save(oldContract);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
