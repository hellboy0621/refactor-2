package com.xtransformers.refactor2.chapter1.service;

import com.xtransformers.refactor2.chapter1.domain.Performance;
import com.xtransformers.refactor2.chapter1.domain.Play;

/**
 * 演出计算器工厂类
 *
 * @author daniel
 * @date 2021-09-05
 */
public class PerformanceCalculatorFactory {

    public static PerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            default:
                throw new RuntimeException("unknown type : " + play.getType());
        }
    }
}
