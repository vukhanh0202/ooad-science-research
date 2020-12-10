package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.mapper.contract.ContractMapper;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.IFindAllContractService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllFacultyService;
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
public class FindAllFacultyServiceImpl extends AbstractBaseService<IFindAllFacultyService.Input, List<FacultyDto>>
        implements IFindAllFacultyService<IFindAllFacultyService.Input, List<FacultyDto>> {


    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    FacultyMapper facultyMapper;


    @Override
    public List<FacultyDto> doing(Input input) {
        try {
            if (input.getSearch().equals("")) {
                return facultyMapper.toFacultyListDto(facultyRepository.
                        findAll(input.createPageable(Sort.Direction.ASC, "nameFaculty")).getContent());
            } else {
                return facultyMapper.toFacultyListDto(facultyRepository.
                        findAllByNameFacultyContaining(input.getSearch(), input.createPageable(Sort.Direction.ASC,
                                "nameFaculty")));
            }

        } catch (Exception e) {
            return null;
        }
    }
}
