package com.uit.ooad.scienceresearch.service.lecturer;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface ILecturerService {

    IRegisterLecturerService<AccountLecturerDto, Lecturer> getRegisterLecturerService();
}
