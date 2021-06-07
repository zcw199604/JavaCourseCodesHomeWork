package zcw;

import java.util.Random;
import java.util.concurrent.*;

public class FutureDemo1 {
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum();
            }
        });
        executor.shutdown();
        try {
            System.out.println("result:" + result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}