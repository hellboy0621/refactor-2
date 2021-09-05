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

    public int getAmount() throws Exception {
        int result = 0;
        switch (play.getType()) {
            case "tragedy":
                throw new RuntimeException("subclass responsibility");
            case "comedy":
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new Exception("unknown type: " + play.getType());
        }
        return result;
    }

    public int getVolumeCredits() {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        if ("comedy".equals(play.getType())) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }
}
