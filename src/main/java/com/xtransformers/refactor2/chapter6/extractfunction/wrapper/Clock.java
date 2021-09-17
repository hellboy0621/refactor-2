package com.xtransformers.refactor2.chapter6.extractfunction.wrapper;

import java.util.Date;

/**
 * @author daniel
 * @date 2021-09-17
 */
public class Clock {

    public Date today() {
        return new Date();
    }
}
