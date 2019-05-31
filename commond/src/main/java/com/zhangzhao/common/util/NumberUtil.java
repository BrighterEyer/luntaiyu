package com.zhangzhao.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {

    public static double formatDouble(double d) {
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
        return bg.doubleValue();
    }
}
