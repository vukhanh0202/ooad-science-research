package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.ICountLecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Service
public class CountLecturerServiceImpl extends AbstractBaseService<ICountLecturerService.Input, Long>
        implements ICountLecturerService<ICountLecturerService.Input, Long> {

    @Autowired
    private LecturerRepository lecturerRepository;


    @Override
    public Long doing(Input input) {
        return lecturerRepository
                .countCustomerByFullNameContainingAndContractIdAndFacultyId(input.getSearch()
                        ,input.getContractId(),input.getContractId());
    }
}
