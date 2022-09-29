package jom.com.softserve.s5.task4;

import java.util.ArrayList;
import java.util.List;

class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    DecisionMethod goShopping = ((product, discount) -> product.equals("product1") && discount > 10);
}

@FunctionalInterface
interface DecisionMethod {
    boolean decide(String product, int discount);
}

class Shop {
    public List<DecisionMethod> clients = new ArrayList<>();

    public int sale(String product, int percent) {
        return (int) clients.stream()
                .filter(client -> client.decide(product, percent))
                .count();
    }
}