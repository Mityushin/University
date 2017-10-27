package ru.spbstu.example2;

public class Cottage implements House {
    private int price = 10_000;

    @Override
    public void live() {
        System.out.println("Cottage implements House");
    }

    @Override
    public int getPrice() {
        return price;
    }
}
