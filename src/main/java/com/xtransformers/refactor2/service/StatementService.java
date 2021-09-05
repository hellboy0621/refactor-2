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

    public String statement(Invoice invoice, Map<String, Play> plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = "Statement for " + invoice.getCustomer() + "\n";
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        for (Performance perf : invoice.getPerformances()) {
            Play play = plays.get(perf.getPlayId());
            int thisAmount = amountFor(perf, play);

            // add volume credits
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(perf.getAudience() / 5);
            }

            // print line for this order
            result += "  " + play.getName() + ": " + numberFormat.format(thisAmount / 100)
                    + " (" + perf.getAudience() + " seats)\n";
            totalAmount += thisAmount;
        }
        result += "Amount owed is " + numberFormat.format(totalAmount / 100) + "\n";
        result += "You earned " + volumeCredits + " credits\n";
        return result;
    }

    private int amountFor(Performance aPerformance, Play play) throws Exception {
        int result = 0;
        switch (play.getType()) {
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
                throw new Exception("unknown type: " + play.getType());
        }
        return result;
    }

}
