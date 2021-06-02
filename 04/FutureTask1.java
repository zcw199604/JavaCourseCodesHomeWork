package zcw;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTask1 {
    public static void main(String[] args) {
        // 创建一个任务
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        // 启动
        new Thread(task).start();
        try {
            System.out.println("result: " + task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void useExecutor(){
        // 通过线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        executor.submit(task);
        try {
            System.out.println("result: " + task.get());
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