package com.uit.ooad.scienceresearch.service.contract.impl;

import com.uit.ooad.scienceresearch.service.contract.*;
import com.uit.ooad.scienceresearch.service.lecturer.*;
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
public class ContractServiceImpl implements IContractService {

    @Autowired
    IFindAllContractService findAllContractService;

    @Autowired
    ICountContractService countContractService;

    @Autowired
    IFindContractByIdService findContractByIdService;

    @Autowired
    IAddContractService addContractService;

    @Autowired
    IUpdateContractService updateContractService;

    @Autowired
    IFindAllNameContractService findAllNameContractService;
}
