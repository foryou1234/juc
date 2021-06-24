package com.cn.juc.function;

import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "sb";
//            }
//        };
        Supplier<String> supplier = ()->{return "sb";};
        System.out.println(supplier.get());
    }
}
