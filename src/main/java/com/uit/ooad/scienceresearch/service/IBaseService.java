package com.uit.ooad.scienceresearch.service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface IBaseService<Input, Output> {
    Output execute(final Input input);
    Output execute();
}