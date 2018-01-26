package cn.skq.test.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Created by admin on 2017/11/3.
 * 由于JDK版本问题，guava 需要jdk1.8 才能运行
 *
 */
public class GuavaTester {
    public static void main(String args[]){
        GuavaTester gt = new GuavaTester();
        /*Integer value1 =  null;
        Integer value2 =  new Integer(10);
        Optional<Integer> a = Optional.fromNullable(value1);
        Optional<Integer> b = Optional.of(value2);
        System.out.println(gt.sum(a,b));*/

//        gt.joinerTest();
        
//        gt.optionalTest();

        gt.PreconditionsTest();

    }

    private void PreconditionsTest() {
        /*String str = null;
        String s = Preconditions.checkNotNull(str);
        System.out.println(s);*/
        boolean flag = false;
        Preconditions.checkArgument(flag);

    }

    private void optionalTest() {
//        Optional<Integer> possible = Optional.of(5);//非空时
        Optional<String> possible = Optional.of(null);//空时
        System.out.println(possible.isPresent());
        System.out.println(possible.get());
    }

    private void joinerTest() {

        String str = Joiner.on("&").join(1, null, 3);
        System.out.println(str);
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b){
        System.out.println("First parameter is present: " + a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());
        Integer value1 = a.or(new Integer(0));
        Integer value2 = b.get();

        return value1 + value2;
    }
}