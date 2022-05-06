package com.company;

import java.util.Scanner;
import java.util.List;

public class Manager extends Person {
    private int employee_id;

    public Manager(String name, String address, String cnp, Integer age) {
        super(name, address, cnp, age);
        this.employee_id = cnp.hashCode();
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String toCSV() {
        return name + "," + address + "," + cnp + "," + age.toString();
    }

    public static Manager fromCSV(List<String> arr, Integer offset){
        return new Manager(arr.get(offset), arr.get(offset + 1), arr.get(offset + 2),
                           Integer.parseInt(arr.get(offset + 3)));
    }

    @Override
    public String toString() {
        return "Manager{" +
                "employee_id=" + employee_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cnp='" + cnp + '\'' +
                ", age=" + age +
                '}';
    }

    public static Manager read()
    {
        String name;
        String address;
        String cnp;
        int age;

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the name of the manager:");
        name = scan.nextLine();

        System.out.println("Enter the address of the manager:");
        address = scan.nextLine();

        System.out.println("Enter the CNP of the manager:");
        cnp = scan.nextLine();

        System.out.println("Enter the age of the manager:");
        age = scan.nextInt();

        return new Manager(name, address, cnp, age);
    }
}
