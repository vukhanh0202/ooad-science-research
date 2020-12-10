package com.uit.ooad.scienceresearch.service.contract.impl;

import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.ICountContractService;
import com.uit.ooad.scienceresearch.service.lecturer.ICountLecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Service
public class CountContractServiceImpl extends AbstractBaseService<Void, Long>
        implements ICountContractService<Void, Long> {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Long doing(Void aVoid) {
        return contractRepository.count();
    }
}
