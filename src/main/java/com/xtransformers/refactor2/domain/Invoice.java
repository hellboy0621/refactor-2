package com.xtransformers.refactor2.domain;

import lombok.Data;

import java.util.List;

/**
 * 发票，清单
 *
 * @author daniel
 * @date 2021-09-03
 */
@Data
public class Invoice {

    /**
     * 客户名称
     */
    private String customer;

    /**
     * 演出列表
     */
    private List<Performance> performances;

}
