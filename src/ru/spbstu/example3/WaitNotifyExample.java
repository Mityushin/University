package ru.spbstu.example3;

import javax.swing.*;
import java.util.Random;

public class WaitNotifyExample {

    private static int N = 10;

    private static final Object lock = new Object();

    public static void main(String[] args) {

        try {
            N = Integer.parseInt(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Thread setter = new Thread(()->{
            Random random = new Random();
            for(int i = 0; i < N; i++) {
                synchronized (lock) {
                    while (Storage.isValueSet()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Storage.setValue(random.nextInt(101));
                    lock.notify();
                }
            }
        });
        Thread getter = new Thread(()->{
            for(int i = 0; i < N; i++) {
                synchronized (lock) {
                    while (!Storage.isValueSet()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("get " + Storage.getValue());
                    lock.notify();
                }
            }
        });

        setter.start();
        getter.start();
    }

    public static int execute(String[] split, JTextArea result) {

        try {
            N = Integer.parseInt(split[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

        Thread setter = new Thread(()->{
            Random random = new Random();
            for(int i = 0; i < N; i++) {
                synchronized (lock) {
                    while (Storage.isValueSet()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int nextInt = random.nextInt(101);
                    Storage.setValue(nextInt);
                    result.append("set " + nextInt + "\n");
                    lock.notify();
                }
            }
        });
        Thread getter = new Thread(()->{
            for(int i = 0; i < N; i++) {
                synchronized (lock) {
                    while (!Storage.isValueSet()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    result.append("get " + Storage.getValue() + "\n");
                    lock.notify();
                }
            }
        });

        setter.start();
        getter.start();
        return 0;
    }
}
