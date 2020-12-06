package com.uit.ooad.scienceresearch.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public abstract class AbstractBaseService<Input, Output> implements IBaseService<Input, Output>{

    @Override
    @Transactional
    public Output execute(Input input) {
        if (input != null){
            preExecute(input);
        }
        return doing(input);
    }

    @Override
    @Transactional
    public Output execute() {
        return this.execute(null);
    }

    public void preExecute(Input input){};

    public abstract Output doing(Input input);
}
