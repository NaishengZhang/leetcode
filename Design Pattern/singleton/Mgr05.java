package edu.nyu.designPattern.singleton;
/*
* JVM 只加载一次class, 所以只有一个instance，
* 而且可以实现lazy loading
* */
public class Mgr05 {
    private Mgr05() {

    }

    private static class Mgr05Holder {
        private static final Mgr05 INSTANCE = new Mgr05();
    }
    public static Mgr05 getInstance() {
        return Mgr05Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();
        }
    }
}
