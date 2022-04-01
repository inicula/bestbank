package com.company;

import java.util.Scanner;

public class Card {
    protected String number;
    protected String expiry_date;

    public Card(String number, String expiry_date) {
        this.number = number;
        this.expiry_date = expiry_date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number='" + number + '\'' +
                ", expiry_date='" + expiry_date + '\'' +
                '}';
    }

    public static Card read()
    {
        String number;
        String expiry_date;

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of the card:");
        number = scan.nextLine();

        System.out.println("Enter the expiry date of the card:");
        expiry_date = scan.nextLine();

        return new Card(number, expiry_date);
    }
}