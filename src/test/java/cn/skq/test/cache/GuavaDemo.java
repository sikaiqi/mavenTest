package cn.skq.test.cache;

import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/10/26.
 */
public class GuavaDemo {

    public static void main(String[] args) {
//        LoadingCache<Key, Graph> graphs = CacheBuilder.newBuilder()
//                .maximumSize(1000)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(MY_LISTENER)
//                .build(
//                        new CacheLoader<Key, Graph>() {
//                            public Graph load(Key key) throws AnyException {
//                                return createExpensiveGraph(key);
//                            }
//                        });
//
//        CacheBuilder.weakKeys();
//
//        try {
//            return graphs.get(key);
//        } catch (ExecutionException e) {
//            throw new OtherException(e.getCause());
//        }

    }
}
