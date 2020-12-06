package com.uit.ooad.scienceresearch.mapper;

import com.uit.ooad.scienceresearch.entity.Contract;
import com.uit.ooad.scienceresearch.entity.Faculty;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Component
public class LecturerConverter {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    ContractRepository contractRepository;


    public Faculty getFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId).orElse(null);
    }

    public Contract getContract(Long contractId) {
        return contractRepository.findById(contractId).orElse(null);
    }
}
