package com.company;
import java.util.Scanner;

public class Account {
    private String number;
    private String currency;
    private Client owner;
    private Card associated_card;

    public Account(String number, String currency, Client owner) {
        this.number = number;
        this.currency = currency;
        this.owner = owner;
        this.associated_card = null;
    }

    public void bind_card()
    {
        System.out.println("Press 1 for normal card and 2 for discount card.");

        Scanner scan = new Scanner(System.in);
        int opt = scan.nextInt();

        if(opt != 1 && opt != 2)
        {
            System.err.println("Invalid option. Returning to previous menu.");
            return;
        }

        if(opt == 1)
        {
            this.associated_card = Card.read();
        }
        else
        {
            this.associated_card = DiscountCard.read();
        }
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

    public Card getAssociated_card() {
        return associated_card;
    }

    public void setAssociated_card(Card associated_card) {
        this.associated_card = associated_card;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", currency='" + currency + '\'' +
                ", owner=" + owner +
                ", associated_card=" + associated_card +
                '}';
    }
}
