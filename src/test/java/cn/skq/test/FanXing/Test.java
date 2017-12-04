package cn.skq.test.FanXing;

/**
 * Created by admin on 2017/12/4.
 *
 * {@link Box1} 未使用泛型改造的Box类
 * {@link Box2} 使用泛型改造的泛型类
 * {@link Method} 使用泛型改造的泛型方法
 */
public class Test {

    public static void main(String[] args) {
        /*泛型类演示*/
        Box2<Integer> b1 = new Box2<Integer>();
        Box2<String> b2 = new Box2<String>();
        /*泛型方法演示*/
        Pair<Integer, String> p1 = new Pair<Integer, String>(1,"1");
        Pair<Integer, String> p2 = new Pair<Integer, String>(2,"2");
//        boolean b = Method.<Integer,String>compare(p1, p2);
        boolean b = Method.compare(p1, p2);
        System.out.println(b);

        /*边界符演示*/


    }
}
