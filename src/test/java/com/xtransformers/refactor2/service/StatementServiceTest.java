package com.xtransformers.refactor2.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xtransformers.refactor2.constant.JsonConstant;
import com.xtransformers.refactor2.domain.Invoice;
import com.xtransformers.refactor2.domain.Play;
import org.junit.Test;

import java.util.List;
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

}