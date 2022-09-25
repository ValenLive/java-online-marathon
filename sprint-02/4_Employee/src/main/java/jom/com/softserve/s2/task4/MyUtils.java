package jom.com.softserve.s2.task4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name;
        this.experience = experience;
        this.basePayment = basePayment;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public BigDecimal getPayment() {
        return basePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return experience == employee.experience && Objects.equals(name, employee.name) && Objects.equals(basePayment, employee.basePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience, basePayment);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", basePayment=" + basePayment +
                '}';
    }

}

class Manager extends Employee {
    private double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience, basePayment);
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public BigDecimal getPayment() {
        return super.getPayment().multiply(BigDecimal.valueOf(coefficient));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Double.compare(manager.coefficient, coefficient) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coefficient);
    }

    @Override
    public String toString() {
        return "Manager{name='" + super.getName() + '\'' +
                ", experience=" + super.getExperience() +
                ", basePayment=" + getPayment() +
                "coefficient=" + coefficient +
                '}';
    }

}

public class MyUtils {
    public List<Employee> largestEmployees(List<Employee> workers) {
        if (workers.isEmpty() || workers.get(0) == null || workers.size() == 1) return workers;

        BigDecimal employeeMax = workers.stream()
                .filter(Predicate.not(Manager.class::isInstance))
                .max(Comparator.comparing(Employee::getPayment))
                .get().getPayment();

        BigDecimal managerMax = workers.stream()
                .filter(Manager.class::isInstance)
                .max(Comparator.comparing(Employee::getPayment))
                .get().getPayment();

        int maxEmployeeExperience = workers.stream()
                .filter(Predicate.not(Manager.class::isInstance))
                .max(Comparator.comparing(Employee::getExperience))
                .get().getExperience();

        int maxManagerExperience = workers.stream()
                .filter(Manager.class::isInstance)
                .max(Comparator.comparing(Employee::getExperience))
                .get().getExperience();

        List<Employee> resultList = new ArrayList<>();

        workers.forEach(worker -> {
            if (worker instanceof Manager){
                if (worker.getExperience() == maxManagerExperience || worker.getPayment().equals(managerMax)){
                    resultList.add(worker);
                }
            } else {
                if (worker.getExperience() == maxEmployeeExperience || worker.getPayment().equals(employeeMax)){
                    resultList.add(worker);
                }
            }
        });

        return resultList;
    }

}
