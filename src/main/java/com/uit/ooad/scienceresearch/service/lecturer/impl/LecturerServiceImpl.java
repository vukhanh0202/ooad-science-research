package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.service.lecturer.ILecturerService;
import com.uit.ooad.scienceresearch.service.lecturer.IRegisterLecturerService;
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
public class LecturerServiceImpl implements ILecturerService {

    @Autowired
    private IRegisterLecturerService registerLecturerService;
}
