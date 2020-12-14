package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.service.contract.*;
import com.uit.ooad.scienceresearch.service.faculty.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Service
@Getter
public class FacultyServiceImpl implements IFacultyService {

    @Autowired
    IFindAllFacultyService findAllFacultyService;

    @Autowired
    IFindAllNameFacultyService findAllNameFacultyService;

    @Autowired
    ICountFacultyService countFacultyService;

    @Autowired
    IFindFacultyByIdService findFacultyByIdService;

    @Autowired
    IAddFacultyService addFacultyService;

    @Autowired
    IUpdateFacultyService updateFacultyService;
}
