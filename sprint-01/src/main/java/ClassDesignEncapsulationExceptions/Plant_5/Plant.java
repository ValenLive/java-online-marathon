package ClassDesignEncapsulationExceptions.Plant_5;

class Plant {
    private String name;
    private Color color;
    private Type type;

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public static Plant tryCreatePlant(String type, String color, String name){
        Plant plant = null;
        try {
            plant = new Plant(type String, String color, String name);
        } catch (ColorException e){
            plant = new Plant(type, "RED", name);
        } catch (TypeException e){
            plant = new Plant("ORDINARY", color, name);
        }
        return plant;
    }

    private Plant(String type, String color, String name) throws ColorException, TypeException {
        try {
            this.color = Color.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ColorException("Invalid value " + color + " for field color");
        }
        try {
            this.type = Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TypeException("Invalid value " + "Unknown type" + " for field type");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        try {
            return "{type: " + this.type.toString() + ", color: " + this.color.toString() + ", name: " + this.name + '}';
        } catch (Exception e) {
            return name;
        }
    }
}

class ColorException extends Exception {
    public ColorException(String message) {
        super(message);
    }
}

class TypeException extends Exception {
    public TypeException(String message) {
        super(message);
    }
}

enum Color {
    WHITE, RED, BLUE
}

enum Type {
    RARE, ORDINARY
}