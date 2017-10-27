package ru.spbstu.example2;

public class Doll implements Toy {

    private int price = 200;

    @Override
    public void play() {
        System.out.println("Doll implements Toy");
    }

    @Override
    public int getPrice() {
        return price;
    }
}
