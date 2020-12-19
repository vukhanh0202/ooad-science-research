package com.uit.ooad.scienceresearch.service.contract.impl;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.mapper.contract.ContractMapper;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.IFindAllNameContractService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllNameFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllNameContractServiceImpl extends AbstractBaseService<Void, List<ContractDto>>
        implements IFindAllNameContractService<Void, List<ContractDto>> {


    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ContractMapper contractMapper;


    @Override
    public List<ContractDto> doing(Void input) {
        try {
            return contractMapper.toContractListDto(contractRepository.
                    findAll());
        } catch (Exception e) {
            return null;
        }
    }
}
