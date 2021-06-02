package zcw;

public class Runnable1 {
    public static void main(String[] args) throws Exception {
        CustomRunnable cRunnacle = new CustomRunnable();
        long start = System.currentTimeMillis();
        Thread thread = new Thread(cRunnacle, "子线程");
        thread.start(); //子线程执行
        System.out.println("主线程做自己的事情");
        thread.join(); //等待子线程执行完毕，这里会阻塞
        System.out.println("获取子线程返回结果：" + cRunnacle.getData());
        System.out.println("用时:"+(System.currentTimeMillis() - start));
    }

    static final class CustomRunnable implements Runnable {
        private static int sum() {
            return fibo(36);
        }

        private static int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }

        private int a;

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ":执行 start");
                Thread.sleep(2000); //子线程停留2秒
                System.out.println(Thread.currentThread().getName() + ":执行 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = sum();
        }

        private int getData() {
            return a;
        }
    }
}
