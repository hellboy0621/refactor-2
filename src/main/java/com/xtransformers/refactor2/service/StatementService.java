package com.xtransformers.refactor2.service;

import com.xtransformers.refactor2.domain.Invoice;
import com.xtransformers.refactor2.domain.Performance;
import com.xtransformers.refactor2.domain.Play;
import com.xtransformers.refactor2.domain.StatementData;
import org.springframework.beans.BeanUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-09-03
 */
public class StatementService {

    private Map<String, Play> plays;

    private StatementData statementData;

    public String statement(Invoice invoice, Map<String, Play> plays) throws Exception {
        this.plays = plays;

        statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        List<Performance> collect = new ArrayList<>();
        for (Performance each : invoice.getPerformances()) {
            collect.add(enrichPerformance(each));
        }
        statementData.setPerformances(collect);

        statementData.setTotalAmount(totalAmount());
        statementData.setTotalVolumeCredits(totalVolumeCredits());
        return renderPlainText(statementData);
    }

    private Performance enrichPerformance(Performance performance) throws Exception {
        Performance result = new Performance();
        BeanUtils.copyProperties(performance, result);
        result.setPlay(playFor(result));
        result.setAmount(amountFor(result));
        result.setVolumeCredits(volumeCreditsFor(result));
        return result;
    }

    private String renderPlainText(StatementData statementData) {
        String result = "Statement for " + statementData.getCustomer() + "\n";
        for (Performance perf : statementData.getPerformances()) {
            result += "  " + perf.getPlay().getName() + ": " + usd(perf.getAmount() / 100) + " (" + perf.getAudience() + " seats)\n";
        }
        result += "Amount owed is " + usd(statementData.getTotalAmount() / 100) + "\n";
        result += "You earned " + statementData.getTotalVolumeCredits() + " credits\n";
        return result;
    }

    private int totalAmount() {
        int result = 0;
        for (Performance perf : statementData.getPerformances()) {
            result += perf.getAmount();
        }
        return result;
    }

    private int totalVolumeCredits() {
        int result = 0;
        for (Performance perf : statementData.getPerformances()) {
            result += perf.getVolumeCredits();
        }
        return result;
    }

    private String usd(int aNumber) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(aNumber);
    }

    private int volumeCreditsFor(Performance aPerformance) {
        int result = 0;
        result += Math.max(aPerformance.getAudience() - 30, 0);
        if ("comedy".equals(aPerformance.getPlay().getType())) {
            result += Math.floor(aPerformance.getAudience() / 5);
        }
        return result;
    }

    private Play playFor(Performance aPerformance) {
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
