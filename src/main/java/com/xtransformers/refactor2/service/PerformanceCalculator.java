package com.xtransformers.refactor2.service;

import com.xtransformers.refactor2.domain.Performance;
import com.xtransformers.refactor2.domain.Play;
import lombok.Data;

/**
 * 演出计算器类
 *
 * @author daniel
 * @date 2021-09-05
 */
@Data
public class PerformanceCalculator {

    private final Performance performance;
    private final Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }
}
