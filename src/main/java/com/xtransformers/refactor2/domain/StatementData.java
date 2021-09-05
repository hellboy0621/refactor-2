package com.xtransformers.refactor2.domain;

import lombok.Data;

import java.util.List;

/**
 * 中转数据结构
 *
 * @author daniel
 * @date 2021-09-05
 */
@Data
public class StatementData {

    /**
     * 客户名称
     */
    private String customer;

    /**
     * 演出列表
     */
    private List<Performance> performances;

    /**
     * 总金额
     */
    private int totalAmount;

    /**
     * 总观众量积分
     */
    private int totalVolumeCredits;

}
