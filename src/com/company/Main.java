package com.company;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;
import java.io.*;
import java.util.*;
import java.text.*;

public class Main {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void getPreviousState(List<Bank> banks) {
        var s_parser = new StringParser();
        var reader = CSVReader.getInstance(s_parser);

        File folder = new File("banks/");
        File[] files = folder.listFiles();

        if(files == null)
        {
            return;
        }

        for(File file : files)
        {
            String path_details = file.getPath() + "/details.csv";
            String path_accounts = file.getPath() + "/accounts.csv";

            List<String> split_details;
            List<List<String>> split_accounts = new ArrayList<>();

            try {
                Scanner scn_details = new Scanner(new File(path_details));
                String line = scn_details.nextLine();
                split_details = reader.getValues(line);

                Scanner scn_accounts = new Scanner(new File(path_accounts));
                while(scn_accounts.hasNextLine()) {
                    String ac_line = scn_accounts.nextLine();

                    if(ac_line.length() > 0) {
                        split_accounts.add(reader.getValues(ac_line));
                    }
                }
            }
            catch(IOException e) { return; }

            banks.add(Bank.fromCSV(split_details, split_accounts));
        }
    }

    public static void menu()
    {
        List<Bank> banks = new ArrayList<>();
        getPreviousState(banks);

        Scanner scan = new Scanner(System.in);

        while(true)
        {
            System.out.println("Main menu. Choose the number of the following available actions:");
            System.out.println("1. Print the name of all banks.");
            System.out.println("2. Enter the menu of a bank");
            System.out.println("3. Add a new bank to the list");
            System.out.println("Input any other number to exit the menu.");

            Boolean quit = false;
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

                    Logger.getInstance().write("print bank names");

                    break;

                case 2:
                    System.out.println("Enter the index of the bank you want to access:");

                    int idx = scan.nextInt();

                    if(idx < 0 || idx >= banks.size())
                    {
                        System.err.println("Invalid index! Returning to main menu.");
                        continue;
                    }

                    Logger.getInstance().write("enter bank menu");
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
                    Logger.getInstance().write("exit main menu");
                    quit = true;
                    break;
            }

            if(quit)
            {
                break;
            }
        }

        for(var bank : banks) {
            PrintStream fout_details = null;
            PrintStream fout_accounts = null;
            try {
                String path_details = "banks/" + bank.getName() + "/details.csv";
                String path_accounts = "banks/" + bank.getName() + "/accounts.csv";

                File file_details = new File(path_details);
                File file_accounts = new File(path_accounts);

                file_details.getParentFile().mkdirs();
                file_details.createNewFile();

                file_accounts.getParentFile().mkdirs();
                file_accounts.createNewFile();

                fout_details = new PrintStream(path_details);
                fout_accounts = new PrintStream(path_accounts);
            }
            catch(IOException e)
            {
                break;
            }

            var str = bank.toCSV();
            fout_details.println(str[0]);
            fout_accounts.println(str[1]);
        }
    }
    public static void main(String[] args) {
        Logger.getInstance().write("startup");
        menu();
    }
}
