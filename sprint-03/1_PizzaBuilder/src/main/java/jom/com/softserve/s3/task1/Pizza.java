package jom.com.softserve.s3.task1;


class Pizza {
	private String cheese;
	private String meat;
	private String seafood;
	private String vegetable;
	private String mushroom;

	private Pizza(PizzaBuilder pizzaBuilder) {
		this.cheese = pizzaBuilder.cheese;
		this.meat = pizzaBuilder.meat;
		this.seafood = pizzaBuilder.seafood;
		this.vegetable = pizzaBuilder.vegetable;
		this.mushroom = pizzaBuilder.mushroom;
	}

	public String getCheese() {
		return cheese;
	}

	public String getMeat() {
		return meat;
	}

	public String getSeafood() {
		return seafood;
	}

	public String getVegetable() {
		return vegetable;
	}

	public String getMushroom() {
		return mushroom;
	}

	public static PizzaBuilder base() {
		return new PizzaBuilder();
	}

	public static class PizzaBuilder {
		private String cheese;
		private String meat;
		private String seafood;
		private String vegetable;
		private String mushroom;

		private PizzaBuilder() {
		}

		public PizzaBuilder addCheese(String cheese) {
			this.cheese = cheese;
			return this;
		}

		public PizzaBuilder addMeat(String meat) {
			this.meat = meat;
			return this;
		}

		public PizzaBuilder addSeafood(String seafood) {
			this.seafood = seafood;
			return this;
		}

		public PizzaBuilder addVegetable(String vegetable){
			this.vegetable = vegetable;
			return this;
		}

		public PizzaBuilder addMushroom(String mushroom){
			this.mushroom = mushroom;
			return this;
		}

		public Pizza build(){
			return new Pizza(this);
		}
	}
}

class Oven {
	public static Pizza cook() {
		// Create and return instance of Pizza class here
		return Pizza.base()
				.addCheese("Parmigiano")
				.addMeat("Steak")
				.addMushroom("Truffle")
				.build();
	}
}