package model.products;

public abstract class Product {

	protected double weight;
	protected double capacity;
	protected double value;
	@Override
	public String toString() {
		return "\n Product = weight=" + weight + ", capacity=" + capacity
				+ ", value=" + value;
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
