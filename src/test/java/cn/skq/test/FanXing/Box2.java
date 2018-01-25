package cn.skq.test.FanXing;

/**
 * Created by admin on 2017/12/4.
 *
 * {@link Box1} 未使用泛型改造的Box类
 */
public class Box2<T> {

    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
