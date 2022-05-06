package com.company;
import java.util.Scanner;
import java.util.List;

public class Account {
    private String number;
    private String currency;
    private Client owner;
    private Card associated_card;

    public Account(String number, String currency, Client owner, Card c) {
        this.number = number;
        this.currency = currency;
        this.owner = owner;
        this.associated_card = c;
    }

    public String toCSV(){
        String card_str;
        if(associated_card == null)
        {
            card_str = "null";
        }
        else
        {
            card_str = associated_card.toCSV();
        }

        return number + "," + currency + "," + owner.toCSV() +
                ","  + card_str;
    }

    public static Account fromCSV(List<String> arr, Integer offset) {
        Card c = null;
        if(!arr.get(offset + 2 + Client.csv_len).equals("null"))
        {
            if(arr.get(offset + 2 + Client.csv_len).equals("N"))
            {
                c = Card.fromCSV(arr, offset + 2 + Client.csv_len + 1);
            }
            else
            {
                c = DiscountCard.fromCSV(arr, offset + 2 + Client.csv_len + 1);
            }
        }

        return new Account(arr.get(offset), arr.get(offset + 1), Client.fromCSV(arr, offset + 2),
                           c);
    }

    public void bind_card()
    {
        System.out.println("Press N for normal card and D for discount card.");

        Scanner scan = new Scanner(System.in);
        char opt = scan.next().charAt(0);

        if(opt != 'N' && opt != 'D')
        {
            System.err.println("Invalid option. Returning to previous menu.");
            return;
        }

        if(opt == 'N')
        {
            this.associated_card = Card.read();
        }
        else
        {
            this.associated_card = DiscountCard.read();
        }
    }

    public static Account read() {
        Scanner scan = new Scanner(System.in);

        String num;
        String currency;
        Client client;

        System.out.println("Enter the account unmber:");
        num = scan.nextLine();

        System.out.println("Enter the account currency:");
        currency = scan.nextLine();

        client = Client.read();

        return new Account(num, currency, client, null);
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
