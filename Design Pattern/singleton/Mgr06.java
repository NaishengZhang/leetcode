package edu.nyu.designPattern.singleton;
/*
* 不仅解决线程同步，还可以防止反序列化（也就是不能通过反射来实例化instance，
* 因为没有构造方法）
* */
public enum Mgr06 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(Mgr06.INSTANCE.hashCode());
            }).start();
        }
    }
}
