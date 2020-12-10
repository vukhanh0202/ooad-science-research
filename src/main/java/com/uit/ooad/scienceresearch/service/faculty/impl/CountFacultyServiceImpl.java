package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.ICountContractService;
import com.uit.ooad.scienceresearch.service.faculty.ICountFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Service
public class CountFacultyServiceImpl extends AbstractBaseService<Void, Long>
        implements ICountFacultyService<Void, Long> {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Long doing(Void aVoid) {
        return facultyRepository.count();
    }
}
