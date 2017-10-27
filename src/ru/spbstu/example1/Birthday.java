package ru.spbstu.example1;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class Birthday {

    public static LocalDate getDateWithExampleFormat(String[] args) {

        if (args.length != 3) {
            throw new DateTimeException("Enter correct date (day month year)");
        }

        int day = Integer.parseInt(args[0]);

        Month month;

        switch (args[1]) {
            case "январь":
            case "января": {
                month = Month.JANUARY;
                break;
            }
            case "февраль":
            case "февраля": {
                month = Month.FEBRUARY;
                break;
            }
            case "март":
            case "марта": {
                month = Month.MARCH;
                break;
            }
            case "апрель":
            case "апреля": {
                month = Month.APRIL;
                break;
            }
            case "май":
            case "мая": {
                month = Month.MAY;
                break;
            }
            case "июнь":
            case "июня": {
                month = Month.JUNE;
                break;
            }
            case "июль":
            case "июля": {
                month = Month.JULY;
                break;
            }
            case "август":
            case "августа": {
                month = Month.AUGUST;
                break;
            }
            case "сентябрь":
            case "сентября": {
                month = Month.SEPTEMBER;
                break;
            }
            case "октябрь":
            case "октября": {
                month = Month.OCTOBER;
                break;
            }
            case "ноябрь":
            case "ноября": {
                month = Month.NOVEMBER;
                break;
            }
            case "декабрь":
            case "декабря": {
                month = Month.DECEMBER;
                break;
            }
            default: {
                throw new DateTimeException("Invalid value for Month (valid values январь/я - декабрь/я): " + args[1]);
            }

        }
        int year = Integer.parseInt(args[2]);

        return LocalDate.of(year, month, day);
    }

    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();

        LocalDate birthday = getDateWithExampleFormat(args);
        str.append("Input date: " + birthday.toString() + "\n");


        LocalDate now = LocalDate.now();
        str.append("System date: " + now.toString() + "\n");

        Period period = Period.between(birthday, now);
        if (period.isNegative()) {
            throw new DateTimeException("Is your birthday... in the future?? 0_0");
        }

        str.append("Your age is\n");
        str.append(period.getYears() + " years "
            + period.getMonths() + " months "
            + period.getDays() + " days ");

        System.out.println(str.toString());
    }

    public static int execute(String[] split, JTextArea result) {
        StringBuilder str = new StringBuilder();

        LocalDate birthday = getDateWithExampleFormat(split);
        str.append("Input date: " + birthday.toString() + "\n");


        LocalDate now = LocalDate.now();
        str.append("System date: " + now.toString() + "\n");

        Period period = Period.between(birthday, now);
        if (period.isNegative()) {
            throw new DateTimeException("Is your birthday... in the future?? 0_0");
        }

        str.append("Your age is\n");
        str.append(period.getYears() + " years "
                + period.getMonths() + " months "
                + period.getDays() + " days ");

        result.append(str.toString());
        return 0;
    }
}
