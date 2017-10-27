package ru.spbstu.example2;

public class Cutlet implements Food {
    private int price = 100;

    @Override
    public void eat() {
        System.out.println("Cutlet implements Food");
    }

    @Override
    public int getPrice() {
        return price;
    }
}
