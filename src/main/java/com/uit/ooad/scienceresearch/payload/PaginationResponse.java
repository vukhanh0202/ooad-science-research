package com.uit.ooad.scienceresearch.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.ooad.scienceresearch.constant.DefaultConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Getter
@Setter
@NoArgsConstructor
public class PaginationResponse<T> {

    /**
     * Contents of page.
     */
    private List<T> contents;

    /**
     * Total item all of page.
     */
    private Integer totalItem;

    /**
     * Total item of page.
     */
    private Integer amount;

    /**
     * Current page.
     */
    private Integer page;

    /**
     * Constructor.
     * @param total total item all of page
     * @param amount size element of page
     * @param page index of current page
     * @param contents contents of page
     */
    public PaginationResponse(Integer total, Integer amount, Integer page, List<T> contents){
        this.contents =  contents;
        this.totalItem = total;
        this.page = page;
        this.amount = amount;
    }

    /**
     * Get total pages.
     * @return
     */
    @JsonProperty
    public Integer totalPage() {
        return amount > 0 ? (totalItem - 1) / amount + 1 : 0;
    }

    /**
     * Check current page is first page or not.
     * @return
     */
    @JsonProperty("isFirst")
    public Boolean isFirst() {
        return page == Integer.parseInt(DefaultConstant.PAGE_NUMBER_DEFAULT);
    }

    /**
     * Check current page is last page or not.
     * @return
     */
    @JsonProperty("isLast")
    public Boolean isLast() {
        return page * amount >= totalItem;
    }

}