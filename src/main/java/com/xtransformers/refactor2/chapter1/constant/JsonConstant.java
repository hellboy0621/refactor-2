package com.xtransformers.refactor2.chapter1.constant;

/**
 * 常量接口
 *
 * @author daniel
 * @date 2021-09-03
 */
public interface JsonConstant {

    /**
     * 剧目
     */
    String PLAYS = "{\"hamlet\":{\"name\":\"Hamlet\",\"type\":\"tragedy\"},\"as-like\":{\"name\":\"As You Like It\",\"type\":\"comedy\"},\"othello\":{\"name\":\"Othello\",\"type\":\"tragedy\"}}";

    /**
     * 账单
     */
    String INVOICES = "[{\"customer\":\"BigCo\",\"performances\":[{\"playID\":\"hamlet\",\"audience\":55},{\"playID\":\"as-like\",\"audience\":35},{\"playID\":\"othello\",\"audience\":40}]}]";

}
