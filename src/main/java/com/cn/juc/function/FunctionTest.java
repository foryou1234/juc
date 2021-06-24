package com.cn.juc.function;

import java.util.function.Function;

/**
 *Function 函数型接口, 有一个输入参数，有一个输出
 *只要是 函数型接口 可以 用 lambda表达式简化
 */
public class FunctionTest {
    public static void main(String[] args) {
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };
        Function<String,String> function = (s)->{return s;};

        System.out.println(function.apply("sb"));
    }
}
