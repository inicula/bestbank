package com.company;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;

public class Main {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void menu()
    {
        List<Bank> banks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        while(true)
        {
            System.out.println("Main menu. Choose the number of the following available actions:");
            System.out.println("1. Print the name of all banks.");
            System.out.println("2. Enter the menu of a bank");
            System.out.println("3. Add a new bank to the list");
            System.out.println("Input any other number to exit the menu.");

            int opt = scan.nextInt();
            switch(opt)
            {
                case 1:
                    for(int i = 0; i < banks.size(); i++)
                    {
                        MessageFormat fmt = new MessageFormat("ID: {0} | Name: {1}");
                        System.out.println(fmt.format(new Object[] {i, banks.get(i).getName()}));
                    }
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Enter the index of the bank you want to access:");

                    int idx = scan.nextInt();

                    if(idx < 0 || idx >= banks.size())
                    {
                        System.err.println("Invalid index! Returning to main menu.");
                        continue;
                    }

                    System.out.println("Entering Bank menu...");
                    banks.get(idx).menu();
                    break;
                case 3:
                    banks.add(Bank.read());
                    break;
                default:
                    clearScreen();
                    System.out.println("You have exited the main menu.");
                    clearScreen();
                    return;
            }
        }
    }
    public static void main(String[] args) {
        menu();
    }
}
