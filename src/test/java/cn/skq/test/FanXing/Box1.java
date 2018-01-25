package cn.skq.test.FanXing;

/**
 * Created by admin on 2017/12/4.
 */

/**
 * 泛型是Java中一个非常重要的知识点，在Java集合类框架中泛型被广泛应用。
 * 本文我们将从零开始来看一下Java泛型的设计，将会涉及到通配符处理，以及让人苦恼的类型擦除。
     泛型基础
     泛型类
     我们首先定义一个简单的Box类

    {@link Box2} 使用泛型改造的泛型类
 */

public class Box1 {

    private String object;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
