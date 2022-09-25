package jom.com.softserve.s2.task2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//import static java.util.stream.Collectors.groupingBy;


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
    private String name;
    private int rating;
    private Map<String, Integer> ingredients;

    {
        ingredients = new HashMap<>();
        addComponent("Water", 100);
        addComponent("Arabica", 20);
    }

    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return ingredients;
    }

    //TODO::Pattern Builder
    @Override
    public DrinkReceipt addComponent(String componentName, int componentCount) {
        ingredients.put(componentName, componentCount);
        return this;
    }

}

class Espresso extends Caffee {

    {
        super.setIngredients(new HashMap<>());
        super.addComponent("Water", 50);
        super.addComponent("Arabica", 20);
    }

    public Espresso(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return super.makeDrink();
    }


}

class Cappuccino extends Caffee {

    {
        super.setIngredients(new HashMap<>());
        super.addComponent("Water", 100);
        super.addComponent("Arabica", 20);
        super.addComponent("Milk", 50);
    }

    public Cappuccino(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return super.makeDrink();
    }


}

public class MyUtils {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        if (coffees.size() == 0) return new HashMap<>();

        return coffees.stream()
                .collect(Collectors.groupingBy(Caffee::getName, Collectors.averagingInt(Caffee::getRating)));

//        return Stream.of("Espresso", "Cappuccino", "Caffee")
//                .filter(str -> avg(str, coffees) != -1)
//                .collect(Collectors.toMap(key -> key, value -> avg(value, coffees)));
    }

    private Double avg(String str, List<Caffee> list) {
        return list.stream()
                .filter(i -> i.getName().equals(str))
                .mapToDouble(Caffee::getRating)
                .average()
                .orElse(-1);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        //Java 8 Distinct by property
        //persons.stream().filter(distinctByKey(Person::getName))
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
/*
if (coffees.size() == 0) return new HashMap<>();

        Map<String, Double> averageMap = new HashMap<>();

        Stream.of("Espresso", "Cappuccino", "Caffee")
                .filter(i -> avg(i, coffees) != 0)
                .forEach(i -> averageMap.put(i, avg(i, coffees)));

        return averageMap;

import java.util.HashMap;
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
    private String name;
    private int rating;
    private Map<String, Integer> ingredients = Map.of("Water", 100, "Arabica", 20);


    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return ingredients;
    }

    @Override
    public DrinkReceipt addComponent(String componentName, int componentCount) {
        return new DrinkReceipt() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public DrinkReceipt addComponent(String componentName, int componentCount) {
                return null;
            }
        };
    }

}

class Espresso extends Caffee {

    {
        super.setIngredients(Map.of("Water", 50, "Arabica", 20));
    }

    public Espresso(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return super.makeDrink();
    }

    @Override
    public DrinkReceipt addComponent(String componentName, int componentCount) {
        return super.addComponent(componentName, componentCount);
    }
}

class Cappuccino extends Caffee {

    {
        super.setIngredients(Map.of("Water", 100, "Arabica", 20, "Milk", 50));
    }

    public Cappuccino(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return super.makeDrink();
    }

    @Override
    public DrinkReceipt addComponent(String componentName, int componentCount) {
        return super.addComponent(componentName, componentCount);
    }
}

public class MyUtils {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        if (coffees.size() == 0) return new HashMap<>();

        Map<String, Double> res = new HashMap<>();

        for (Caffee coffee : coffees) {
            res.put(coffee.getName(), (double) coffee.getRating());
        }

        return res;
    }
}

 */