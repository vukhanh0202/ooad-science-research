package com.uit.ooad.scienceresearch.service.lecturer;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface ILecturerService {

    IRegisterLecturerService<AccountLecturerDto, Lecturer> getRegisterLecturerService();

    IFindAllLecturerService<IFindAllLecturerService.Input, List<LecturerFullDto>> getFindAllLecturerService();

    ICountLecturerService<ICountLecturerService.Input, Long> getCountLecturerService();

    IFindLecturerByIdService<Long, LecturerFullDto> getFindLecturerByIdService();

    IUpdateLecturerService<LecturerDto, Boolean> getUpdateLecturerService();

    IDeleteLecturerService<LecturerDto, Boolean> getDeleteLecturerService();
}
