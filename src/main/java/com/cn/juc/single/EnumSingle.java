package com.cn.juc.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//enum是什么？本身也是一个clasa类
/*
* 枚举跟普通类一样可以用自己的变量、方法和构造函数，构造函数只能使用 private 访问修饰符，
* 所以外部无法调用。枚举既可以包含具体方法，也可以包含抽象方法。 如果枚举类具有抽象方法，
* 则枚举类的每个实例都必须实现它。
* */
public enum EnumSingle {
    instance("zws",21); //调用构造方法
    private String name;
    private int age;
    private static int sex;

    private EnumSingle(String name,int age){
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int getSex() {
        return sex;
    }
    public static void setSex(int sex) {
        EnumSingle.sex=sex;
    }

    public EnumSingle getInstance(){
        return instance;
    }
}

class Test{
    public static void main(String[] args) throws Exception {
//        EnumSingle instance1 = EnumSingle.instance;
//        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
//        declaredConstructor.setAccessible(true);
//        EnumSingle instance2 = declaredConstructor.newInstance();


        // NoSuchMethodException: com.kuang.single.EnumSingle.<init>() System.out.println(instance1);
//        System.out.println(instance2);
        EnumSingle enumSingle1 = EnumSingle.instance;
        EnumSingle enumSingle2 = EnumSingle.instance;

        System.out.println(enumSingle1);
        System.out.println(enumSingle2);

        System.out.println(enumSingle1.getAge());
        System.out.println(enumSingle1.getName());
        System.out.println(EnumSingle.getSex());
        EnumSingle.setSex(3);
        System.out.println(EnumSingle.getSex());
    }
}
