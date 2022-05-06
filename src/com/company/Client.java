package com.company;
import java.util.Scanner;
import java.util.List;

public class Client extends Person{
    public static Integer csv_len = 4;
    private Integer client_id;

    public Client(String name, String address, String cnp, Integer age) {
        super(name, address, cnp, age);
        this.client_id = cnp.hashCode();
    }

    public String toCSV(){
        return name + "," + address + "," + cnp + "," +
               age.toString();
    }

    public static Client fromCSV(List<String> arr, Integer offset){
        return new Client(arr.get(offset), arr.get(offset + 1), arr.get(offset + 2),
                          Integer.parseInt(arr.get(offset + 3)));
    }

    public int getClient_id() {
        return client_id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cnp='" + cnp + '\'' +
                ", age=" + age +
                '}';
    }

    public static Client read()
    {
        String name;
        String address;
        String cnp;
        Integer age;

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the name of the client:");
        name = scan.nextLine();

        System.out.println("Enter the address of the client:");
        address = scan.nextLine();

        System.out.println("Enter the CNP of the client:");
        cnp = scan.nextLine();

        System.out.println("Enter the age of the client:");
        age = scan.nextInt();

        return new Client(name, address, cnp, age);
    }
}
