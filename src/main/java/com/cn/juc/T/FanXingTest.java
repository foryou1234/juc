package com.cn.juc.T;

import java.util.ArrayList;
import java.util.List;

//定义一个泛型接口
interface Generator<T> {
    public T next();
}

class Generic<T> implements Generator<T>{
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

    @Override
    public T next() {
        return null;
    }
}

abstract class Animal<T>{
    abstract public int countLegs();
}

class Dog<T> extends Animal<T>{

    @Override
    public int countLegs() {
        return 4;
    }
}

/**
 * @author Hasee
 */
public class FanXingTest {
    static int countLegs (List<? extends Animal > animals ) {
        int retVal = 0;
        for ( Animal animal : animals )
        {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    static int countLegs1 (List< Animal > animals ){
        int retVal = 0;
        for ( Animal animal : animals )
        {
            retVal += animal.countLegs();
        }
        return retVal;
    }
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        // 不会报错
        countLegs( dogs );
        // 报错
//        countLegs1(dogs);
    }
}
