package cn.mb.test;

public class Test {
    public static void main(String[] args) {
        new B();
    }
}

/**
 * c con        父类静态变量初始化
 * A static     父类静态代码块
 * c con        子类静态变量初始化
 * B static     子类静态代码块
 * c con        父变量初始化
 * A not static 父非静态代码块
 * A con        父类构造器
 * c con        子变量初始化
 * B not static 子非静态代码块
 * B con        子构造器
 */
class A {
    private static C c = new C();

    private C cc = new C();

    static {
        System.out.println("A static");
    }

    {
        System.out.println("A not static");
    }

    public A() {
        System.out.println("A con");
    }
}


class B extends A {
    private static C c = new C();

    private C ccc = new C();

    static {
        System.out.println("B static");
    }

    {
        System.out.println("B not static");
    }

    public B() {
        System.out.println("B con");
    }
}

class C {
    public C() {
        System.out.println("C con");
    }
}