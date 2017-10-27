package ru.spbstu.example3;

public class Storage {

    public Storage(int value) {
        this.value = value;
    }

    public static int getValue() {
//        synchronized (lock) {
            isValueSet = false;
            return value;
//        }
    }

    public static void setValue(int value) {
//        synchronized (lock) {
            Storage.value = value;
            isValueSet = true;
            System.out.println("set " + value);
//        }
    }

    public static boolean isValueSet() {
        return isValueSet;
    }


    private static int value;

    private static boolean isValueSet = false;

//    private static final Object lock = new Object();
}
