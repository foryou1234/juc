package com.cn.juc.stream;
/*
* 双冒号（::）运算符在Java 8中被用作方法引用（method reference），方法引用是与lambda表达式
* 相关的一个重要特性。它提供了一种不执行方法的方法。为此，方法引用需要由兼容的函数接口组成的
* 目标类型上下文
*大致意思是，使用lambda表达式会创建匿名方法， 但有时候需要使用一个lambda表达式只调用一个已
* 经存在的方法（不做其它）， 所以这才有了方法引用！
* */

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 *题目要求：一分钟内完成此题，只能用一行代码实现！
 *现在有5个用户！筛选：
 *1、ID 必须是偶数
 *2、年龄必须大于23岁
 *3、用户名转为大写字母
 *4、用户名字母倒着排序
 *5、只输出一个用户！
 */
public class Test {
    public static void main(String[] args) {
        User u1 = new User(1,"a",21);
        User u2 = new User(2,"b",22);
        User u3 = new User(3,"c",23);
        User u4 = new User(4,"d",24);
        User u5 = new User(5,"e",25);
        // 集合就是存储
        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        // 计算交给Stream流
        // lambda表达式、链式编程、函数式接口、Stream流式计算
        list.stream()
                .filter(u->{return u.getId()%2==0;})
                .filter(u->{return u.getAge()>21;})
                .map(u->{return u.getName().toUpperCase();})
                .sorted((name1,name2)->{return name2.compareTo(name1);})
                .limit(1)
                .forEach(System.out::println);
    }
}
