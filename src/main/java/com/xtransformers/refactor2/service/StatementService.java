package com.xtransformers.refactor2.service;

import com.xtransformers.refactor2.domain.Invoice;
import com.xtransformers.refactor2.domain.Performance;
import com.xtransformers.refactor2.domain.Play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-09-03
 */
public class StatementService {

    private Invoice invoice;
    private Map<String, Play> plays;

    public String statement(Invoice invoice, Map<String, Play> plays) throws Exception {
        this.invoice = invoice;
        this.plays = plays;
        return renderPlainText(invoice);
    }

    private String renderPlainText(Invoice invoice) throws Exception {
        String result = "Statement for " + invoice.getCustomer() + "\n";
        for (Performance perf : invoice.getPerformances()) {
            result += "  " + playFor(perf).getName() + ": " + usd(amountFor(perf) / 100) + " (" + perf.getAudience() + " seats)\n";
        }
        result += "Amount owed is " + usd(totalAmount() / 100) + "\n";
        result += "You earned " + totalVolumeCredits() + " credits\n";
        return result;
    }

    private int totalAmount() throws Exception {
        int result = 0;
        for (Performance perf : invoice.getPerformances()) {
            result += amountFor(perf);
        }
        return result;
    }

    private int totalVolumeCredits() {
        int result = 0;
        for (Performance perf : invoice.getPerformances()) {
            result += volumeCreditsFor(perf);
        }
        return result;
    }

    private String usd(int aNumber) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(aNumber);
    }

    private int volumeCreditsFor(Performance aPerformance) {
        int result = 0;
        result += Math.max(aPerformance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(aPerformance).getType())) {
            result += Math.floor(aPerformance.getAudience() / 5);
        }
        return result;
    }

    private Play playFor(Performance aPerformance) {
        return plays.get(aPerformance.getPlayId());
    }

    private int amountFor(Performance aPerformance) throws Exception {
        int result = 0;
        switch (playFor(aPerformance).getType()) {
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
                throw new Exception("unknown type: " + playFor(aPerformance).getType());
        }
        return result;
    }

}
