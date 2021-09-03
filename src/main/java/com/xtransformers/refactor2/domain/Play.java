package com.xtransformers.refactor2.domain;

import lombok.Data;

/**
 * 剧目
 *
 * @author daniel
 * @date 2021-09-03
 */
@Data
public class Play {

    /**
     * 剧目名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;
}
