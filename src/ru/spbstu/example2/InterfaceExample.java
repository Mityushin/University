package ru.spbstu.example2;

import javax.swing.*;

public class InterfaceExample {
    public static void main(String[] args) {

        Subject[] subjects = {new Ball(), new Doll(),
                new Cutlet(), new Sandwich(),
                new Yurt(), new Cottage()
        };

        StringBuilder str = new StringBuilder();

        for (Subject sub : subjects) {
            str.append(sub.getClass().getName() + "\n");
        }

        str.append("\n");

        for (Subject sub : subjects) {
            if (sub instanceof Food ) {
                ((Food) sub).eat();
                str.append(sub.getClass().getName() + ", the price is " + sub.getPrice() + "\n");
            }
        }

        System.out.println(str.toString());

    }

    public static int execute(String[] split, JTextArea result) {
        Subject[] subjects = {new Ball(), new Doll(),
                new Cutlet(), new Sandwich(),
                new Yurt(), new Cottage()
        };

        StringBuilder str = new StringBuilder();

        for (Subject sub : subjects) {
            str.append(sub.getClass().getName() + "\n");
        }

        str.append("\n");

        for (Subject sub : subjects) {
            if (sub instanceof Food) {
                ((Food) sub).eat();
                str.append(sub.getClass().getName() + ", the price is " + sub.getPrice() + "\n");
            }
        }

        result.append(str.toString());
        return 0;
    }
}
