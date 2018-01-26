package cn.skq.test.lamda;

import com.google.common.cache.Weigher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by admin on 2018/1/25.
 */
public class LambdaTest1 {

    public static void main(String[] args) {
        //testSimple();
        //testStream();
        //testStreamSort();
        //testStreamMap();
        //testStreamMatch();
        testStreamCollect();


    }

    public static void testStreamCollect() {
        List<String> list = new ArrayList<>();
        list.add("a2");
        list.add("a1");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        list.add("c1");
        list.add("a3");

        Comparator<String> c1 = (p1, p2) -> p1.compareTo(p2);

        List<String> newList = list.stream().sorted(c1).collect(Collectors.toList());
        newList.stream().forEach(System.out::println);
    }

    public static void testStreamMatch() {
        List<String> list = new ArrayList<>();
        list.add("a2");
        list.add("a1");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        list.add("c1");
        list.add("a3");

        boolean b1 = list.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(b1);
        boolean b2 = list.stream().allMatch(s -> s.startsWith("a"));
        System.out.println(b2);

    }

    public static void testStreamMap() {
        List<String> list = new ArrayList<>();
        list.add("a2");
        list.add("a1");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        list.add("c1");
        list.add("a3");

        //这里的map是映射函数，非java集合
      // list.stream().map(String::toUpperCase).sorted((p1,p2)->p2.compareTo(p1)).forEach(System.out::println);

       //自定义映射规则
        Function<String,String> pattern = (s)->{
            return s+".jpg";
        };
        list.stream().map(String::toUpperCase).map(pattern).sorted((p1,p2)->p2.compareTo(p1)).forEach(System.out::println);

    }

    public static void testStreamSort() {
        List<String> list = new ArrayList<>();
        list.add("a2");
        list.add("a1");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        list.add("c1");
        list.add("a3");
        //默认comparator字典排序
        //list.stream().sorted().filter(s->s.startsWith("a")).forEach(System.out::println);
        //自定义comparator排序
        //list.stream().sorted((p1,p2)->p1.compareTo(p2)).forEach(System.out::println);
        list.stream().sorted((p1,p2)->p2.compareTo(p1)).forEach(System.out::println);


    }

    public static void testStream(){
        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        list.add("c1");
        list.add("a3");

        //list.stream().filter(s->s.startsWith("a")).forEach(System.out::println);

        //等价于以上操作
        Predicate<String> p1 = (s) -> s.startsWith("a");
        list.stream().filter(p1).forEach(System.out::println);

       //连续过滤
        Predicate<String> p2 = (s) -> s.endsWith("1");
        list.stream().filter(p1).filter(p2).forEach(System.out::println);
    }

    public static void testSimple(){
        //()->10
        // (int x, int y) -> x + y;
        //(String name) -> System.out.println(name);

        Function<String, Integer> stringIntegerFunction = Integer::valueOf;
        Function<String, String> stringStringFunction = stringIntegerFunction.andThen(String::valueOf);
        String result = stringStringFunction.apply("1234");
        System.out.println(result);

        Function<Integer,Integer> add = (i)->{
            System.out.println("first input:" +i);
            return i+2;
        };

        Function<Integer,Integer> zero = add.andThen((i)->{
            System.out.println("second input:" +i);
            return i*0;
        });

        Integer res = zero.apply(4);
        System.out.println(res);
    }


}
