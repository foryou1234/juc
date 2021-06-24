package com.cn.juc.function;

import java.util.function.Function;


// 泛型、枚举、反射
// lambda表达式、链式编程、函数式接口、Stream流式计算
// 超级多FunctionalInterface
// 简化编程模型，在新版本的框架底层大量应用！
// foreach(消费者类的函数式接口)

//链式编程：链式编程可以使得代码可读性高，链式编程的原理就是返回一个this对象，就是返回本身，达到链式效果

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
