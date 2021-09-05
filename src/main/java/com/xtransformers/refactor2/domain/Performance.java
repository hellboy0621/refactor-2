package com.xtransformers.refactor2.domain;

import lombok.Data;

/**
 * 演出
 *
 * @author daniel
 * @date 2021-09-03
 */
@Data
public class Performance {

    /**
     * 演出剧目ID
     */
    private String playId;

    /**
     * 观众数量
     */
    private Integer audience;

    /**
     * 剧目信息
     */
    private Play play;

    /**
     * 总金额
     */
    private int amount;
}
