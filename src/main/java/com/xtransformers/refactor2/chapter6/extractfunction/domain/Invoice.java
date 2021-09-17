package com.xtransformers.refactor2.chapter6.extractfunction.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author daniel
 * @date 2021-09-17
 */
@Data
public class Invoice {

    private List<Order> orders;
    private Date dueDate;
    private String customer;
    private String name;
}
