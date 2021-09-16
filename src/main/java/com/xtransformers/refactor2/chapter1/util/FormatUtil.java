package com.xtransformers.refactor2.chapter1.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author daniel
 * @date 2021-09-05
 */
public class FormatUtil {

    public static String usd(int aNumber) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(aNumber);
    }
}
