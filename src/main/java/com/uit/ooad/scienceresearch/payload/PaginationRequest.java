package com.uit.ooad.scienceresearch.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Getter
@Setter
@NoArgsConstructor
public class PaginationRequest {

    /**
     * Page number.
     */
    private Integer page;

    /**
     * Page size.
     */
    private Integer size;

    /**
     * Constructor.
     * @param page page number
     * @param size page size
     */
    public PaginationRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    /**
     * Create pageable
     * @param sortType sort type
     * @param sortBy sort by
     * @return Pageable
     */
    public Pageable createPageable(final Sort.Direction sortType, final String sortBy){
        return PageRequest.of(page - 1, size , Sort.by(sortType, sortBy));
    }
}

