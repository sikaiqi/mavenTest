package cn.skq.test.FanXing;

/**
 * Created by admin on 2017/12/4.
 * 泛型方法
 */
public class Method {

    public static <k,v> boolean compare(Pair<k,v> p1, Pair<k,v> p2){
        return p1.getKey().equals(p2.getKey())
                && p1.getValue().equals(p2.getValue());
    }

    //实现这样一个功能，查找一个泛型数组中大于某个特定元素的个数，我们可以这样实现:
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray,T elem){
        int count = 0;
        for (T e : anArray){
//            if (e > elem){//compiler error
//                ++count;
//            }
            if (e .compareTo(elem)>0){
                ++count;
            }
        }
        return count;
    }

}

class Pair<k,v>{

    private k key;
    private v value;

    public Pair(k key, v value) {
        this.key = key;
        this.value = value;
    }

    public k getKey() {
        return key;
    }

    public void setKey(k key) {
        this.key = key;
    }

    public v getValue() {
        return value;
    }

    public void setValue(v value) {
        this.value = value;
    }
}
