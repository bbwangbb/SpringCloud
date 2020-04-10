package cn.mb.test;

public class ThreadTest implements Runnable {

    private int num;

    public ThreadTest(int num) {
        this.num = num;
    }

    /**
     * 直接在run加synchronized：
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            /**
             * 未加锁情况下：
             *      多个线程进入可能获取同样的初始值
             *      此时都将num赋为该初始值 + 1，所以最后不会到40
             *
             * 解决：
             *      1、synchronized run方法，获取该对象的锁才能执行该方法
             *      2、锁住循环内代码块，保证每次循环只能有一个线程在执行
             *          感觉synchronized 方法和synchronized(this)是差不多的，都是锁住当前对象，
             *          获取当前对象的锁才能执行被框住的代码
             */
            synchronized (this) {
                int n = num;
                System.out.println("当前线程：" + Thread.currentThread().getName() + "\t当前num = " + n);
                //  获取锁
                num = n + 1;
                //  释放锁
                System.out.println("当前线程：" + Thread.currentThread().getName() + "\t自增后num = " + num);
            }
        }
        System.out.println(num);
    }


    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest(0);
        Thread t1 = new Thread(threadTest);
        Thread t2 = new Thread(threadTest);
        Thread t3 = new Thread(threadTest);
        Thread t4 = new Thread(threadTest);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
