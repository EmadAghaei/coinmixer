package com.crypto.coinmixer.util;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        BigDecimal bigDecimal= new BigDecimal(3);
        BigDecimal bigDecimal2= new BigDecimal(0);
        while (bigDecimal.compareTo(new BigDecimal(0.01)) > 0) {
            System.out.println(bigDecimal);
            bigDecimal = bigDecimal.subtract(new BigDecimal(0.01));
            bigDecimal2 =bigDecimal2.add(new BigDecimal(0.01));
        }
        System.out.print("-----------"+bigDecimal);
        System.out.print("-----++++++------"+bigDecimal.add(bigDecimal2));

        }

}
