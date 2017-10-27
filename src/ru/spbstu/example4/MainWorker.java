package ru.spbstu.example4;

import javax.swing.*;

public class MainWorker implements Runnable {

    public MainWorker(int workers, int allBoxes, JTextArea result) {
        this.workers = workers;
        workersMustWork = new boolean[workers];

        this.allBoxes = allBoxes;
        remainingBoxes = allBoxes;

        this.result = result;

        for (int i = 0; i < workers; i++) {
            workersMustWork[i] = false;
        }
    }

    //для проверки от случайного пробуждения
    public boolean mustIWork(int num) {
        synchronized (lockWorkersMustWork) {
            return workersMustWork[num];
        }
    }

    //все ли ящики собраны
    public boolean isWorkDone() {
        synchronized (lockRemainingBoxes) {
            return remainingBoxes <= 0;
        }
    }

    //сборка ящика
    public void collectBox(int num) {
        if (isWorkDone()) {
            return;
        }

        synchronized (lockRemainingBoxes) {
            synchronized (lockWorkersMustWork) {
                workersMustWork[num] = false;
            }
            remainingBoxes--;
            result.append(Thread.currentThread().getName() + " collect " +  (allBoxes - remainingBoxes) + "\n");
            System.out.println(Thread.currentThread().getName() + " collect " +  (allBoxes - remainingBoxes) + "\n");
        }
    }

    //все ли работники собрали
    private boolean isAllWorkersCollect() {
        synchronized (lockWorkersMustWork) {
            for (int i = 0; i < workers; i++) {
                if (workersMustWork[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    //для проверки от случайного пробуждения
    //отправить работников собирать
    private void doWork() {
        synchronized (lockWorkersMustWork) {
            for (int i = 0; i < workers; i++) {
                workersMustWork[i] = true;
            }
        }
    }

    public final Object lock = new Object();

    //для проверки от случайного пробуждения
    private final Object lockWorkersMustWork = new Object();
    private boolean[] workersMustWork;

    //оставшиеся ящики
    private final Object lockRemainingBoxes = new Object();
    private int remainingBoxes;

    private final int workers, allBoxes;

    private JTextArea result;

    @Override
    public void run() {
        //создание работников
        Thread[] threads = new Thread[workers];
        for (int i = 0; i < workers; i++) {
            threads[i] = new Thread(new Worker(this, i));
            threads[i].setName("Worker " + i);
        }

        //запуск работников
        for (Thread thread : threads) {
            thread.start();
        }

        //пока работа не выполнена
        while (!isWorkDone()) {

            //отправить работать
            doWork();
            synchronized (lock) {
                lock.notifyAll();
            }

            //пока все не собрали по ящику, уснуть
            while (!isAllWorkersCollect() && !isWorkDone()) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        doWork();
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
