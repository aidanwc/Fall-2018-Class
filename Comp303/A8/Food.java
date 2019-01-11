//Aidan Weber-Concannon
//260708481


public class Food {
	private String name;
	private int carbs;
	private int fat;
	private int protein;
	
	
	//Food class, Immutable 
	public Food(String name, int carbs, int fat, int protein) {

		this.name = name;
		this.carbs = carbs;
		this.fat = fat;
		this.protein = protein;
	}
	
	public String getName() {
		return name;
	}
	public int getCarbs() {
		return carbs;
	}
	public int getFat() {
		return fat;
	}
	public int getProtein() {
		return protein;
	}
	
}
