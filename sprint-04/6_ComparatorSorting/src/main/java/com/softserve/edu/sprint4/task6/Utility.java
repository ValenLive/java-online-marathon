package com.softserve.edu.sprint4.task6;

import java.util.Arrays;
import java.util.Comparator;

class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

class Employee extends Person {
    private double salary;

    public Employee(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return super.toString() + ", Salary: " + salary;
    }
}

class Developer extends Employee {
    private Level level;

    public Developer(String name, int age, double salary, Level level) {
        super(name, age, salary);
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return super.toString() + ", Level: " + level.name();
    }
}

enum Level {
    JUNIOR, MIDDLE, SENIOR
}

class Utility {
    public static <T extends Person> void sortPeople(T[] arr, Comparator<? super T> comparator) {
        Arrays.sort(arr, comparator);
    }
}

class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        if (person1.getName().compareTo(person2.getName()) != 0) {
            return person1.getName().compareTo(person2.getName());
        }

        return Integer.compare(person1.getAge(), person2.getAge());
    }
}

class EmployeeComparator implements Comparator<Employee> {

    public int compare(Employee emp1, Employee emp2) {
        int personComparator = new PersonComparator().compare(emp1, emp2);

        if (personComparator != 0) return personComparator;


        return Double.compare(emp1.getSalary(), emp2.getSalary());
    }
}

class DeveloperComparator implements Comparator<Developer> {

    @Override
    public int compare(Developer developer1, Developer developer2) {
        int employeeComparator = new EmployeeComparator().compare(developer1, developer2);

        if (employeeComparator != 0) return employeeComparator;

        return Integer.compare(developer1.getLevel().ordinal(), developer2.getLevel().ordinal());
    }
}
