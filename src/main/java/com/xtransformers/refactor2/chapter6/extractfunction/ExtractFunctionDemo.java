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
        int outstanding = 0;

        System.out.println("*******************************");
        System.out.println("********* Customer Owes *******");
        System.out.println("*******************************");

        // calculate outstanding
        for (Order each : invoice.getOrders()) {
            outstanding += each.getAmount();
        }

        // record due date
        Date today = new Clock().today();
        invoice.setDueDate(new Date(today.getTime()));

        // print details
        System.out.println("name: " + invoice.getName());
        System.out.println("amount: " + outstanding);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("due: " + sdf.format(invoice.getDueDate()));
    }

}
