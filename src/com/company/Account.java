package com.company;
import java.util.Scanner;

public class Account {
    private String number;
    private String currency;
    private Client owner;

    public Account(String number, String currency, Client owner) {
        this.number = number;
        this.currency = currency;
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", currency='" + currency + '\'' +
                ", owner=" + owner +
                '}';
    }

    public static Account read()
    {
        Scanner scan = new Scanner(System.in);

        String num;
        String currency;
        Client client;

        System.out.println("Enter the account unmber:");
        num = scan.nextLine();

        System.out.println("Enter the account currency:");
        currency = scan.nextLine();

        client = Client.read();

        return new Account(num, currency, client);
    }
}
