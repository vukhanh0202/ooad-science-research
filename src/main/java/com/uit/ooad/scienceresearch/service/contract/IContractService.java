package com.uit.ooad.scienceresearch.service.contract;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.service.lecturer.*;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface IContractService {

    IFindAllContractService<IFindAllContractService.Input, List<ContractDto>> getFindAllContractService();

    ICountContractService<Void, Long> getCountContractService();

    IFindContractByIdService<Long, ContractDto> getFindContractByIdService();

    IAddContractService<ContractDto, Boolean> getAddContractService();

    IUpdateContractService<ContractDto, Boolean> getUpdateContractService();
}
