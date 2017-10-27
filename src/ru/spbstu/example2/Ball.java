package ru.spbstu.example2;

public class Ball implements Toy {

    private int price = 40;

    @Override
    public void play() {
        System.out.println("Ball implements Toy");
    }

    @Override
    public int getPrice() {
        return price;
    }
}
