package com.xtransformers.refactor2.chapter1.service;

import com.xtransformers.refactor2.chapter1.domain.Performance;
import com.xtransformers.refactor2.chapter1.domain.Play;

/**
 * @author daniel
 * @date 2021-09-05
 */
public class TragedyCalculator extends PerformanceCalculator {

    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = 40000;
        if (performance.getAudience() > 30) {
            result += 1000 * (performance.getAudience() - 30);
        }
        return result;
    }
}
