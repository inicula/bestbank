package com.company;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;

public class Bank extends Institution {
    private List<Account> accounts;
    private Manager manager;

    public Bank(String name, String address, List<Account> accounts, Manager manager) {
        super(name, address);
        this.accounts = accounts;
        this.manager = manager;
    }

    public void menu()
    {
        Scanner scan = new Scanner(System.in);
        while(true)
        {
            System.out.println("Bank menu. Select one of the available options:");
            System.out.println("1. Print ID and number of all bank accounts.");
            System.out.println("2. Print bank account information.");
            System.out.println("3. Create new bank account.");
            System.out.println("4. Delete bank account.");

            int opt = scan.nextInt();
            int idx;
            switch(opt)
            {
                case 1:
                    for(int i = 0; i < accounts.size(); i++)
                    {
                        MessageFormat fmt = new MessageFormat("ID: {0} | Number: {1}");
                        System.out.println(fmt.format(new Object[] {i, accounts.get(i).getNumber()}));
                    }
                    System.out.println("");
                    break;

                case 2:
                    System.out.println("Enter the index of the bank account you want to print");

                    idx = scan.nextInt();
                    if(idx < 0 || idx >= accounts.size())
                    {
                        System.out.println("Invalid index. Returning to bank menu.");
                        continue;
                    }

                    System.out.println("Bank account information:");
                    System.out.println(accounts.get(idx));

                    break;
                case 3:
                    accounts.add(Account.read());
                    break;
                case 4:
                    System.out.println("Enter the index of the bank account you want to delete");

                    idx = scan.nextInt();
                    if(idx < 0 || idx >= accounts.size())
                    {
                        System.out.println("Invalid index. Returning to bank menu.");
                        continue;
                    }

                    accounts.remove(idx);
                    break;
                default:
                    System.out.println("You have exited the Bank menu. Press any key to continue.");
                    scan.nextByte();
                    return;

            }
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + accounts +
                ", manager=" + manager +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static Bank read()
    {
        String name;
        String address;

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the name of the bank:");
        name = scan.nextLine();

        System.out.println("Enter the address of the bank:");
        address = scan.nextLine();

        return new Bank(name, address, new ArrayList<>(), null);
    }
}
