package com.company;

public class Manager extends Person {
    private int employee_id;

    public Manager(String name, String address, String cnp, int age) {
        super(name, address, cnp, age);
        this.employee_id = cnp.hashCode();
    }

    public int getEmployee_id() {
        return employee_id;
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
}
