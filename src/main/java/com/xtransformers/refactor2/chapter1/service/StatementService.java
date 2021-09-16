package com.xtransformers.refactor2.chapter1.service;

import com.xtransformers.refactor2.chapter1.domain.Invoice;
import com.xtransformers.refactor2.chapter1.domain.Performance;
import com.xtransformers.refactor2.chapter1.domain.Play;
import com.xtransformers.refactor2.chapter1.domain.StatementData;
import com.xtransformers.refactor2.chapter1.util.FormatUtil;

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

    public String htmlStatement(Invoice invoice, Map<String, Play> plays) throws Exception {
        return renderHtml(statementDataService.createStatementData(invoice, plays));
    }

    private String renderHtml(StatementData data) {
        String result = "<h1>Statement for " + data.getCustomer() + "</h1>\n";
        result += "<table>\n";
        result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>";
        for (Performance perf : data.getPerformances()) {
            result += " <tr><td>" + perf.getPlay().getName() + "</td><td>" + perf.getAudience() + "</td>";
            result += "<td>" + FormatUtil.usd(perf.getAmount() / 100) + "</td></tr>\n";
        }
        result += "</table>\n";
        result += "<p>Amount owed is <em>" + FormatUtil.usd(data.getTotalAmount() / 100) + "</em></p>\n";
        result += "<p>You earned <em>" + data.getTotalVolumeCredits() + "</em> credits</p>\n";
        return result;
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
