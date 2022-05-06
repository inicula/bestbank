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

    public String[] toCSV() {
        String[] parts;
        parts = new String[2];

        parts[0] = name + "," + address + "," + manager.toCSV();
        parts[1] = "";
        for(var acc : accounts){
            parts[1] += acc.toCSV() + "\n";
        }

        return parts;
    }

    public static Bank fromCSV(List<String> p1, List<List<String>> p2) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < p2.size(); i++) {
            if(p2.get(i).size() > 0) {
                accounts.add(Account.fromCSV(p2.get(i), 0));
            }
        }

        return new Bank(p1.get(0), p1.get(1), accounts, Manager.fromCSV(p1, 2));
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
            System.out.println("5. Change or delegate manager.");
            System.out.println("6. Bind a card to an account.");

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
                    System.out.println();
                    Logger.getInstance().write("print all bank accounts");
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
                    Logger.getInstance().write("print bank account info");
                    break;

                case 3:
                    Logger.getInstance().write("create bank account");
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

                    Logger.getInstance().write("delete bank account");
                    accounts.remove(idx);
                    break;

                case 5:
                    manager = Manager.read();
                    System.out.println("The manager of the bank has been modified.");
                    Logger.getInstance().write("change bank's manager");
                    break;

                case 6:
                    System.out.println("Enter the index of the bank account you want to bind a card to:");

                    idx = scan.nextInt();
                    if(idx < 0 || idx >= accounts.size())
                    {
                        System.out.println("Invalid index. Returning to bank menu.");
                        continue;
                    }

                    Logger.getInstance().write("bind card to account");
                    accounts.get(idx).bind_card();
                    break;

                default:
                    System.out.println("You have exited the Bank menu. Press any key to continue.");
                    Logger.getInstance().write("exit bank menu");
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

        Manager m = Manager.read();

        return new Bank(name, address, new ArrayList<>(), m);
    }
}
