package jom.com.softserve.s2.task2;

import java.util.List;
import java.util.Map;


interface DrinkReceipt {
    String getName();

    DrinkReceipt addComponent(String componentName, int componentCount);
}

interface DrinkPreparation {
    Map<String, Integer> makeDrink();
}

interface Rating {
    int getRating();
}

class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
    String name;
    int rating;

    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public DrinkReceipt addComponent(String componentName, int componentCount) {
        return null;
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return null;
    }

    @Override
    public int getRating() {
        return 0;
    }
}

class Espresso extends Caffee {
    public Espresso(String name, int rating) {
        super(name, rating);
    }
}

class Cappuccino extends Caffee {
    public Cappuccino(String name, int rating) {
        super(name, rating);
    }
}

class MyUtils {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        return null;
    }
}
