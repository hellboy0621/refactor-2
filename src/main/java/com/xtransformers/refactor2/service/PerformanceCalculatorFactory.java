package com.xtransformers.refactor2.service;

import com.xtransformers.refactor2.domain.Performance;
import com.xtransformers.refactor2.domain.Play;

/**
 * 演出计算器工厂类
 *
 * @author daniel
 * @date 2021-09-05
 */
public class PerformanceCalculatorFactory {

    public static PerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        return new PerformanceCalculator(performance, play);
    }
}
