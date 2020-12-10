package com.uit.ooad.scienceresearch.service.faculty;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.service.contract.*;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface IFacultyService {

    IFindAllFacultyService<IFindAllFacultyService.Input, List<FacultyDto>> getFindAllFacultyService();

    ICountFacultyService<Void, Long> getCountFacultyService();

    IFindFacultyByIdService<Long, FacultyDto> getFindFacultyByIdService();

    IAddFacultyService<FacultyDto, Boolean> getAddFacultyService();

    IUpdateFacultyService<FacultyDto, Boolean> getUpdateFacultyService();
}
