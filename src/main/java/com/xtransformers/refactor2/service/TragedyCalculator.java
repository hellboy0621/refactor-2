package com.xtransformers.refactor2.service;

import com.xtransformers.refactor2.domain.Performance;
import com.xtransformers.refactor2.domain.Play;

/**
 * @author daniel
 * @date 2021-09-05
 */
public class TragedyCalculator extends PerformanceCalculator{

    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

}
