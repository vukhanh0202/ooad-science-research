package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.constant.DefaultConstant;
import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.payload.PaginationResponse;
import com.uit.ooad.scienceresearch.service.contract.IContractService;
import com.uit.ooad.scienceresearch.service.contract.IFindAllContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/10/2020
 */
@RestController
@RequestMapping(value = "/contract")
public class ContractController {


    @Autowired
    IContractService contractService;

    /**
     * Find All contract
     *
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllContract(@RequestParam(value = "page", defaultValue = DefaultConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                            @RequestParam(value = "size", defaultValue = DefaultConstant.PAGE_SIZE_DEFAULT) Integer size,
                                            @RequestParam(value = "search", defaultValue = "") String search) {
        List<ContractDto> result = contractService.getFindAllContractService().execute(new IFindAllContractService.Input(search, page, size));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponse(Integer.parseInt(contractService.getCountContractService().execute().toString())
                        , size, page, result));
    }
    /**
     * Find All contract
     *
     * @return
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllContractNoPageable() {
        List<ContractDto> result = contractService.getFindAllNameContractService().execute();
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }


    /**
     * Find contract with Id
     *
     * @return
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContractById(@PathVariable("id") Long contractId) {
        ContractDto result = contractService.getFindContractByIdService().execute(contractId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(result));
    }

    /**
     * Add new contract
     *
     * @param body
     * @return
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addContract(@RequestBody ContractDto body) {
        Boolean res = contractService.getAddContractService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * Update contract
     *
     * @param body
     * @return
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateContract(@RequestBody ContractDto body) {
        Boolean res = contractService.getUpdateContractService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

}
