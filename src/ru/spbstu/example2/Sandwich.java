package ru.spbstu.example2;

public class Sandwich implements Food {
    private int price = 190;

    @Override
    public void eat() {
        System.out.println("Sandwich implements Food");
    }

    @Override
    public int getPrice() {
        return price;
    }
}
