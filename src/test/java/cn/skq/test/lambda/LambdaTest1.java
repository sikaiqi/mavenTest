package cn.skq.test.lambda;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by admin on 2018/1/25.
 */
public class LambdaTest1 {

    public static void main(String[] args) {
        //testSimple();
        //testSimple2();
        //testStream();
        //testStreamSort();
        //testStreamMap();
        //testStreamMatch();
        //testStreamCollect();
        //testStreamCount();
        //testStreamReduce();
        //testParallelStream();
        testLazyStream();

    }

    public static void testLazyStream() {
        Stream<Long> stream = Stream.generate(new NatureSeq());
        System.out.println("元素个数："+stream.map((param)->{
            return param;
        }).limit(1000).count());
    }

    public static void testParallelStream() {
        List<String> bigList = new ArrayList<>();
        for (int i = 0 ; i < 10000; i++){
            UUID uuid = UUID.randomUUID();
            bigList.add(uuid.toString());
        }

        //测试串行流下排序所用的时间
        /*long startTime = System.nanoTime();
        bigList.stream().sorted();
        long endTime = System.nanoTime();
        long cost = TimeUnit.NANOSECONDS.toMillis(endTime-startTime);
        System.out.println("花费毫秒数："+cost); //113毫秒*/

        //测试并行流下排序所用的时间
        long startTime = System.nanoTime();
        bigList.parallelStream().sorted();
        long endTime = System.nanoTime();
        long cost = TimeUnit.NANOSECONDS.toMillis(endTime-startTime);
        System.out.println("花费毫秒数："+cost); //10毫秒

    }

    public static void testStreamReduce() {
        List<String> list = new ArrayList<String>();
        list.add("a2");
        list.add("a1");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        list.add("c1");
        list.add("a3");

        Optional<String> optional = list.stream().sorted().reduce((s1, s2) -> {
            System.out.println(s1 + "|" + s2);
            return s1 + "|" + s2;
        });

    }

    public static void testStreamCount(){
        List<String> list = new ArrayList<String>();
        list.add("a2");
        list.add("a1");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        list.add("c1");
        list.add("a3");

        long num = list.stream().filter((s)->s.startsWith("a")).count();
        System.out.println(num);

    }

    public static void testStreamCollect() {
        List<String> list = new ArrayList<String>();
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

    public static void testSimple2() {
        Supplier<String> supplier = () -> "2222";
        String res = supplier.get();
        System.out.println(res);

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开启线程");
            }
        }).start();*/
       // new Thread(() -> System.out.println("开启线程")).start();

        /*Converter<String,Integer> converter = new Converter<String,Integer>(){
            @Override
            public Integer convert(String f) {
                return Integer.valueOf(f);
            }
        };

        Integer i = converter.convert("201");
        System.out.println(i);*/

        /*Converter<String,Integer> converter = Integer::valueOf;
        Integer i = converter.convert("3333");
        System.out.println(i);*/

    }

    public static void testSimple(){
       /* //示例1：不需要接受参数，直接返回10
        ()->10
        //示例2：接受两个int类型的参数，并返回这2个参数相加的和
        (int x, int y) -> x + y;
        //示例3：接受x,y 2个参数，参数的类型由JVM根据上下文推断出来，并返回这2个参数相加的和
        (x,y) -> x+y;
        //示例4：接受一个字符串，并将字符串打印到控制台，不返回结果
        (String name) -> System.out.println(name);
        //示例5：接受一个推断类型的参数，并将字符串打印到控制台，不返回结果
        name -> System.out.println(name);
        //示例6：接受2个String类型的参数，分别打印到控制台，不返回结果
        (String name,String sex) -> {System.out.println(name);System.out.println(sex)}
        //示例6：接受一个参数，返回该参数的2倍
        x -> 2*x;*/


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

    interface Converter<F,T>{
        T convert(String F);
    }

    static class NatureSeq implements Supplier<Long>{
        private Long value = 0L;
        @Override
        public Long get() {
            value ++;
            return value;
        }
    }

}
