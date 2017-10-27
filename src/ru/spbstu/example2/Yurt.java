package ru.spbstu.example2;

public class Yurt implements House {
    private int price = 4_000;

    @Override
    public void live() {
        System.out.println("Yurt implements House");
    }

    @Override
    public int getPrice() {
        return price;
    }
}
