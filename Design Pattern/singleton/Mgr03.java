package edu.nyu.designPattern.singleton;

/*
 * Lazy loading - 懒汉模式
 * 对比02
 * 优点：通过synchronized解决线程不安全问题
 * 缺点：效率下降
 * */
public class Mgr03 {
    private static Mgr03 INSTANCE;

    private Mgr03() {
        System.out.println("Mgr03 has been initialized");
    }

    public static synchronized Mgr03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr03.getInstance().hashCode());
            }).start();
        }
    }
}
