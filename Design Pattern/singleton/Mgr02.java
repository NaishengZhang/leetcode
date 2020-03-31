package edu.nyu.designPattern.singleton;
/*
 * Lazy loading - 懒汉模式
 * 对比01
 * 优点：可以按需初始化
 * 缺点：线程不安全
 * */
public class Mgr02 {
    private static Mgr02 INSTANCE; // 不需要final，因为final必须初始化

    private Mgr02() {
        System.out.println("Mgr02 has been initialized");
    }

    public static Mgr02 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }
            INSTANCE = new Mgr02();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 验证在单线程条件下
//        System.out.println(Mgr02.getInstance() == Mgr02.getInstance());
        for (int i = 0; i< 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr02.getInstance().hashCode());
            }).start();
//             new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            }).start();

        }
    }
}
