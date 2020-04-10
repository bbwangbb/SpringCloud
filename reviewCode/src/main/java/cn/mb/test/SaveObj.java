package cn.mb.test;

public class SaveObj {
    private static SaveObj saveObj = null;
    public void isAlive() {
        System.out.println("alive");
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize，当前线程：" + Thread.currentThread().getName());
        saveObj = this;
    }
    public static void main(String[] args) throws Throwable {
        //  创建对象
        saveObj = new SaveObj();
        System.out.println(saveObj);
        //  设置对象无引用，对象不可达(满足条件一) & 此时重写了finalize()且之前没调用过，可以执行finalize()
        saveObj = null;
        //  让垃圾回收器开始回收
        System.gc();
        //  睡0.5s等一下Finalizer线程，当该线程执行时，会发生自救
        Thread.sleep(500);
        if (saveObj == null) {
            System.out.println("dead");
        } else {
            //  所以此时对象还存在
            saveObj.isAlive();
        }
        /**
         * 再次设置对象无引用，对象不可达(满足条件一)，但此时该对象的finalize方法被调用过，
         * 所以不会标记为有必要执行finalize方法的对象，因此不会执行finalize方法
         */
        saveObj = null;
        //  再让垃圾回收器开始回收
        System.gc();
        Thread.sleep(500);
        if (saveObj == null) {
            System.out.println("dead");
        } else {
            saveObj.isAlive();
        }
    }
}
