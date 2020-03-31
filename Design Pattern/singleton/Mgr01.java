package edu.nyu.designPattern.singleton;
/*
* 饿汉式
* 类加载到内存后就会实例化一个单例，jvm保证线程安全（因为每个class jvm只会load一次）
* 缺点：不管有没有用到这个对象，都会在类装载的时候完成实例化
* */
public class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();

/*     or initialized by static block
    private static final Mgr01 INSTANCE;
    static {
        INSTANCE = new Mgr01();
    }*/

    //private Mgr01 constructor 确保只有一个instance
    private Mgr01() {
        System.out.println("Mgr01 has been initialized");
    }
    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(m1 == m2); // same object;

//        Class.forName("Mgr01");
    }
}
