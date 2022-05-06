package com.company;

import java.util.Scanner;
import java.util.List;

public class DiscountCard extends Card {
    private Double discount;

    public DiscountCard(String number, String expiry_date, Double discount) {
        super(number, expiry_date);
        this.discount = discount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
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

    public String toCSV() {
        return "D" + "," + number + "," + expiry_date + "," + discount.toString();
    }

    public static DiscountCard fromCSV(List<String> arr, Integer offset) {
        return new DiscountCard(arr.get(offset), arr.get(offset + 1), Double.parseDouble(arr.get(offset +  2)));
    }

    public static DiscountCard read()
    {
        String number;
        String expiry_date;
        Double discount;

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of the card:");
        number = scan.nextLine();

        System.out.println("Enter the expiry date of the card:");
        expiry_date = scan.nextLine();

        System.out.println("Enter the discount for the purchases made with this card:");
        discount = scan.nextDouble();

        return new DiscountCard(number, expiry_date, discount);
    }
}