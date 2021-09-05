package com.xtransformers.refactor2.service;

import com.xtransformers.refactor2.domain.Invoice;
import com.xtransformers.refactor2.domain.Performance;
import com.xtransformers.refactor2.domain.Play;
import com.xtransformers.refactor2.domain.StatementData;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-09-05
 */
public class StatementDataService {

    public StatementData createStatementData(Invoice invoice, Map<String, Play> plays) throws Exception {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        List<Performance> collect = new ArrayList<>();
        for (Performance each : invoice.getPerformances()) {
            collect.add(enrichPerformance(each, plays));
        }
        statementData.setPerformances(collect);

        statementData.setTotalAmount(totalAmount(statementData));
        statementData.setTotalVolumeCredits(totalVolumeCredits(statementData));
        return statementData;
    }

    private Performance enrichPerformance(Performance performance, Map<String, Play> plays) throws Exception {
        PerformanceCalculator calculator = new PerformanceCalculator(performance, playFor(performance, plays));

        Performance result = new Performance();
        BeanUtils.copyProperties(performance, result);
        result.setPlay(calculator.getPlay());
        result.setAmount(amountFor(result));
        result.setVolumeCredits(volumeCreditsFor(result));
        return result;
    }

    private int totalAmount(StatementData statementData) {
        return statementData.getPerformances()
                .stream()
                .mapToInt(Performance::getAmount)
                .sum();
    }

    private int totalVolumeCredits(StatementData statementData) {
        return statementData.getPerformances()
                .stream()
                .mapToInt(Performance::getVolumeCredits)
                .sum();
    }

    private int volumeCreditsFor(Performance aPerformance) {
        int result = 0;
        result += Math.max(aPerformance.getAudience() - 30, 0);
        if ("comedy".equals(aPerformance.getPlay().getType())) {
            result += Math.floor(aPerformance.getAudience() / 5);
        }
        return result;
    }

    private Play playFor(Performance aPerformance, Map<String, Play> plays) {
        return plays.get(aPerformance.getPlayId());
    }

    private int amountFor(Performance aPerformance) throws Exception {
        int result = 0;
        switch (aPerformance.getPlay().getType()) {
            case "tragedy":
                result = 40000;
                if (aPerformance.getAudience() > 30) {
                    result += 1000 * (aPerformance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.getAudience() > 20) {
                    result += 10000 + 500 * (aPerformance.getAudience() - 20);
                }
                result += 300 * aPerformance.getAudience();
                break;
            default:
                throw new Exception("unknown type: " + aPerformance.getPlay().getType());
        }
        return result;
    }
}
