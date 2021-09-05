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

    protected final Performance performance;
    private final Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public int getAmount() {
        throw new RuntimeException("subclass responsibility");
    }

    public int getVolumeCredits() {
        // 通用逻辑在超类中作为默认条件
        return Math.max(performance.getAudience() - 30, 0);
    }
}
