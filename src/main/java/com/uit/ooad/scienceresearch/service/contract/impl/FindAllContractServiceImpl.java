package com.uit.ooad.scienceresearch.service.contract.impl;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.mapper.contract.ContractMapper;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.IFindAllContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllContractServiceImpl extends AbstractBaseService<IFindAllContractService.Input, List<ContractDto>>
        implements IFindAllContractService<IFindAllContractService.Input, List<ContractDto>> {


    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ContractMapper contractMapper;


    @Override
    public List<ContractDto> doing(Input input) {
        try {
            if (input.getSearch().equals("")) {
                return contractMapper.toContractListDto(contractRepository.
                        findAll(input.createPageable(Sort.Direction.ASC, "nameContract")).getContent());
            } else {
                return contractMapper.toContractListDto(contractRepository.
                        findAllByNameContractContaining(input.getSearch(), input.createPageable(Sort.Direction.ASC,
                                "nameContract")));
            }

        } catch (Exception e) {
            return null;
        }
    }
}
