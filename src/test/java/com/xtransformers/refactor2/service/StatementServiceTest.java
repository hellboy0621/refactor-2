package com.xtransformers.refactor2.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xtransformers.refactor2.constant.JsonConstant;
import com.xtransformers.refactor2.domain.Invoice;
import com.xtransformers.refactor2.domain.Play;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StatementServiceTest {

    @Test
    public void testPlays() {
        Map<String, Play> map = JSONObject.parseObject(JsonConstant.PLAYS, new TypeReference<Map<String, Play>>() {
        });
        assertEquals("Hamlet", map.get("hamlet").getName());
        assertEquals("tragedy", map.get("hamlet").getType());
        assertEquals("As You Like It", map.get("as-like").getName());
        assertEquals("comedy", map.get("as-like").getType());
        assertEquals("Othello", map.get("othello").getName());
        assertEquals("tragedy", map.get("othello").getType());
    }

    @Test
    public void testInvoices() {
        List<Invoice> invoices = JSONObject.parseObject(JsonConstant.INVOICES, new TypeReference<List<Invoice>>() {
        });
        assertEquals(1, invoices.size());
        assertEquals("BigCo", invoices.get(0).getCustomer());
        assertEquals(3, invoices.get(0).getPerformances().size());
    }

    @Test
    public void testNumberFormat() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        assertEquals("$120.00", numberFormat.format(120.00));
        numberFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        assertEquals("￥120.00", numberFormat.format(120.00));
        assertEquals("￥483,274.12", numberFormat.format(483274.12356));
    }

    @Test
    public void testStatement() throws Exception {
        Invoice invoice = JSONObject.parseObject(JsonConstant.INVOICES, new TypeReference<List<Invoice>>() {
        }).get(0);
        Map<String, Play> plays = JSONObject.parseObject(JsonConstant.PLAYS, new TypeReference<Map<String, Play>>() {
        });
        StatementService statementService = new StatementService();
        statementService.setPlays(plays);
        String statement = statementService.statement(invoice, plays);
        String expected = "Statement for BigCo\n" +
                "  Hamlet: $650.00 (55 seats)\n" +
                "  As You Like It: $580.00 (35 seats)\n" +
                "  Othello: $500.00 (40 seats)\n" +
                "Amount owed is $1,730.00\n" +
                "You earned 47 credits\n";
        assertEquals(expected, statement);
    }

}