package com.xtransformers.refactor2.chapter1.service;

import com.xtransformers.refactor2.chapter1.domain.Performance;
import com.xtransformers.refactor2.chapter1.domain.Play;

/**
 * @author daniel
 * @date 2021-09-05
 */
public class ComedyCalculator extends PerformanceCalculator {

    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = 30000;
        if (performance.getAudience() > 20) {
            result += 10000 + 500 * (performance.getAudience() - 20);
        }
        result += 300 * performance.getAudience();
        return result;
    }

    @Override
    public int getVolumeCredits() {
        // 有特殊情况时按需覆盖
        return (int) (super.getVolumeCredits() + Math.floor(performance.getAudience() / 5.0));
    }
}
