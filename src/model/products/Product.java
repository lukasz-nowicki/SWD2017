package model.products;

public abstract class Product {

	protected double weight;
	protected double capacity;
	protected double value;
	
	@Override
	public String toString() {
		return "\nProduct: Weight = " + weight + ", Capacity = " + capacity
				+ ", Value = " + value;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getCapacity() {
		return capacity;
	}
	
	public double getValue() {
		return value;
	}	
}
