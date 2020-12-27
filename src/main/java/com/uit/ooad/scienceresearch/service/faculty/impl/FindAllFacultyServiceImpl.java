package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyInfoDto;
import com.uit.ooad.scienceresearch.exception.ForbiddenException;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Role.NOT_PERMISSION;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllFacultyServiceImpl extends AbstractBaseService<IFindAllFacultyService.Input, List<FacultyInfoDto>>
        implements IFindAllFacultyService<IFindAllFacultyService.Input, List<FacultyInfoDto>> {


    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    FacultyMapper facultyMapper;

    @Override
    public void preExecute(Input input) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userPrincipal.getRoleCode()!= ERole.ADMIN){
            throw new ForbiddenException(messageHelper.getMessage(NOT_PERMISSION));
        }
    }

    @Override
    public List<FacultyInfoDto> doing(Input input) {
        try {
            return facultyMapper.toFacultyInfoListDto(facultyRepository.
                    findAllByNameFacultyContaining(input.getSearch(), input.createPageable(Sort.Direction.ASC,
                            "nameFaculty")));
        } catch (Exception e) {
            return null;
        }
    }
}
