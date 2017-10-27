package ru.spbstu.example4;

import javax.swing.*;

public class WorkersExample {
    public static void main(String[] args) {
        int workers = Integer.parseInt(args[0]);
        if (workers <= 0) {
            return;
        }
        new Thread(new MainWorker(workers, Integer.parseInt(args[1]), new JTextArea())).start();
    }

    public static int execute(String[] split, JTextArea result) {
        int workers = Integer.parseInt(split[0]);
        if (workers <= 0) {
            return 1;
        }
        new Thread(new MainWorker(workers, Integer.parseInt(split[1]), result)).start();

        return 0;
    }
}
