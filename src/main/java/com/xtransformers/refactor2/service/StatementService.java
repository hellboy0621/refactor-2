package com.xtransformers.refactor2.service;

import com.xtransformers.refactor2.domain.Invoice;
import com.xtransformers.refactor2.domain.Performance;
import com.xtransformers.refactor2.domain.Play;
import com.xtransformers.refactor2.domain.StatementData;
import com.xtransformers.refactor2.util.FormatUtil;

import java.util.Map;

/**
 * @author daniel
 * @date 2021-09-03
 */
public class StatementService {

    private StatementDataService statementDataService = new StatementDataService();

    public String statement(Invoice invoice, Map<String, Play> plays) throws Exception {
        return renderPlainText(statementDataService.createStatementData(invoice, plays));
    }

    private String renderPlainText(StatementData statementData) {
        String result = "Statement for " + statementData.getCustomer() + "\n";
        for (Performance perf : statementData.getPerformances()) {
            result += "  " + perf.getPlay().getName() + ": " + FormatUtil.usd(perf.getAmount() / 100) + " (" + perf.getAudience() + " seats)\n";
        }
        result += "Amount owed is " + FormatUtil.usd(statementData.getTotalAmount() / 100) + "\n";
        result += "You earned " + statementData.getTotalVolumeCredits() + " credits\n";
        return result;
    }

}
