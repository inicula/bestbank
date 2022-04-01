package com.company;

import java.util.Scanner;

public class DiscountCard extends Card {
    private float discount;

    public DiscountCard(String number, String expiry_date, float discount) {
        super(number, expiry_date);
        this.discount = discount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "number='" + number + '\'' +
                ", expiry_date='" + expiry_date + '\'' +
                ", discount=" + discount +
                '}';
    }

    public static DiscountCard read()
    {
        String number;
        String expiry_date;
        float discount;

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of the card:");
        number = scan.nextLine();

        System.out.println("Enter the expiry date of the card:");
        expiry_date = scan.nextLine();

        System.out.println("Enter the discount for the purchases made with this card:");
        discount = scan.nextFloat();

        return new DiscountCard(number, expiry_date, discount);
    }
}