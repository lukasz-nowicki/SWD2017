package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.products.Product;

public class Gen {

	private List<Product> products;
	private List<Product> avaliable_products;
	//final private int PRODUCTS_QUANTITY;
	final private double TOTAL_AVAILABLE_CAPACITY;
	

	public Gen(List<Product> avaliable_products, double total_available_capacity) {
		super();
		this.products = new ArrayList<Product>();
		//this.PRODUCTS_QUANTITY = genSize;
		this.avaliable_products = avaliable_products;
		this.TOTAL_AVAILABLE_CAPACITY = total_available_capacity;
		randomizeGen();
	}
	
	private void randomizeGen(){
Random rand = new Random();
		int available_products_quantity = 10;
		int counter = 0;
			
		while (getCapacity() != TOTAL_AVAILABLE_CAPACITY)//dopóki magazyn nie jest wype³niony po brzegi
		{
			int indexOfRandomProduct = rand.nextInt(available_products_quantity);
			Product randomProduct = avaliable_products.get(indexOfRandomProduct);//wybieramy losowy produkt z kolekcji produktów
			if (getCapacity() + randomProduct.getCapacity() <= TOTAL_AVAILABLE_CAPACITY) {
				products.add(randomProduct);//dodajemy go do genu
			}	
			counter++;
			
			if (counter > 100) {break;}
		}
	}
	
	public double getValue(){
		double genValue = 0.0;
		
		for(int i=0; i < products.size(); i++){
			Product product = products.get(i); 
			genValue += product.getValue();
		}
		return genValue;
	}
	
	public double getWeight(){
		double genWeight = 0.0;
		
		for(int i=0; i < products.size(); i++){
			Product product = products.get(i); 
			genWeight += product.getWeight();
		}
		return genWeight;
	}
	
	public double getCapacity(){
		double genCapacity = 0.0;
		
		for(int i=0; i < products.size(); i++){
			Product product = products.get(i); 
			genCapacity += product.getCapacity();
		}
		return genCapacity;
	}

	// Pojemnoœæ genu przekroczy³a maksymaln¹ pojemnoœæ magazynu
	public boolean isAboveTotalCapacity(){
		return TOTAL_AVAILABLE_CAPACITY < getCapacity();
	}
	
	@Override
	public String toString() {
		return "\nGEN = "+products;
			
		}
	}
	

