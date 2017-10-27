package ru.spbstu.example4;

public class Worker implements Runnable {
    private final MainWorker main;
    private final int num;

    public Worker(MainWorker main, int num) {
        this.main = main;
        this.num = num;
//        System.out.println("Worker " + num + " created");
    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName() + " start");

        //пока не все ящики собраны
        while (!main.isWorkDone()) {

            //уснуть, пока другие работники не собрали по одному
            while (!main.mustIWork(num)) {
                synchronized (main.lock) {
                    try {
                        main.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            //попробовать собрать ящик
            main.collectBox(num);

            synchronized (main.lock) {
                main.lock.notifyAll();
            }
        }

//        System.out.println(Thread.currentThread().getName() + " done");
    }
}
