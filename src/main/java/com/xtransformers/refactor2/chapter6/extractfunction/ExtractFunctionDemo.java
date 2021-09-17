package com.xtransformers.refactor2.chapter6.extractfunction;

import com.xtransformers.refactor2.chapter6.extractfunction.domain.Invoice;
import com.xtransformers.refactor2.chapter6.extractfunction.domain.Order;
import com.xtransformers.refactor2.chapter6.extractfunction.wrapper.Clock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author daniel
 * @date 2021-09-17
 */
public class ExtractFunctionDemo {

    public void printOwing(Invoice invoice) {
        printBanner();
        int outstanding = calculateOutstanding(invoice);
        recordDueDate(invoice);
        printDetail(invoice, outstanding);
    }

    private int calculateOutstanding(Invoice invoice) {
        int result = 0;
        for (Order each : invoice.getOrders()) {
            result += each.getAmount();
        }
        return result;
    }

    private void recordDueDate(Invoice invoice) {
        Date today = new Clock().today();
        invoice.setDueDate(new Date(today.getTime()));
    }

    private void printDetail(Invoice invoice, int outstanding) {
        System.out.println("name: " + invoice.getName());
        System.out.println("amount: " + outstanding);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("due: " + sdf.format(invoice.getDueDate()));
    }

    private void printBanner() {
        System.out.println("*******************************");
        System.out.println("********* Customer Owes *******");
        System.out.println("*******************************");
    }

}
