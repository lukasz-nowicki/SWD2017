package model;

import java.util.ArrayList;
import java.util.List;

import model.products.Product;
import model.products.ProductA;
import model.products.ProductB;
import model.products.ProductC;
import model.products.ProductD;
import model.products.ProductE;
import model.products.ProductF;
import model.products.ProductG;
import model.products.ProductH;
import model.products.ProductI;
import model.products.ProductJ;

public class Generation {

	private final int POPULATION;	
	private final double TOTAL_AVAILABLE_CAPACITY;
	private List<Gen> genList;		
	
	public Generation(int population, double total_available_capacity) {
		super();
		POPULATION = population;		
		this.genList = new ArrayList<Gen>();
		this.TOTAL_AVAILABLE_CAPACITY = total_available_capacity;
		createGeneration();
	}

	private void createGeneration() {
		List<Product> products = new ArrayList<Product>();
		products.add(new ProductA());
		products.add(new ProductB());
		products.add(new ProductC());
		products.add(new ProductD());
		products.add(new ProductE());
		products.add(new ProductF());
		products.add(new ProductG());
		products.add(new ProductH());
		products.add(new ProductI());
		products.add(new ProductJ());

		for (int i = 0; i < POPULATION; i++) {
			Gen gen = new Gen(products, TOTAL_AVAILABLE_CAPACITY);
			genList.add(gen);
		}
		System.out.println(genList);
	}
	
	public List<Gen> getGenList() {
		return genList;
	}
	
	public double selectAndReturnGenWithBestValue(){
		double maxValue = Double.MIN_VALUE;
		System.out.println("VALUE: ");
		for(int i = 0; i < POPULATION; i++){
			Gen gen = genList.get(i);
			double genValue = gen.getValue();
			
			if(!gen.isAboveTotalCapacity() && genValue > maxValue){
				maxValue = genValue;
			}
			System.out.println("Gen = " + gen + ", GenValue: " + genValue + " , MaxValue: " + maxValue);
		}
		return maxValue;
	}
	
	public double selectAndReturnGenWithBestWeight(){
		double minWeight = Double.MAX_VALUE;
		
		for(int i = 0; i < POPULATION; i++){
			Gen gen = genList.get(i);
			double genWeight = gen.getWeight();
			
			if(!gen.isAboveTotalCapacity() && genWeight < minWeight){
				minWeight = genWeight;
			}
			System.out.println("Gen = " + gen + ", GenValue: " + genWeight + ", MinValue: " + minWeight);
		}
		return minWeight;
	}
	
	public double selectAndReturnGenWithBestCapacity(){
		double minCapacity = Double.MAX_VALUE;
		
		for(int i = 0; i < POPULATION; i++){
			Gen gen = genList.get(i);
			double genCapacity = gen.getCapacity();
			
			if(!gen.isAboveTotalCapacity() && genCapacity < minCapacity){
				minCapacity = genCapacity;
			}
			System.out.println("Gen = " + gen + ", GenValue: " + genCapacity + ", MinValue: " + minCapacity);
		}
		return minCapacity;
	}
	
//	public double selectAndReturnGenWithBestOptionForAlgorithm2(List<Gen> workList,String decisionMaxOrMin, String selectSVW){
//		String decision=decisionMaxOrMin;
//		double valVCW;
//		if(decision.equals("MAX")){
//			valVCW=Double.MAX_VALUE;
//		}else{
//			valVCW=Double.MIN_VALUE;
//		}
//		
//		
//		for(int i=0;i<workList.size();i++){
//			Gen gen = workList.get(i);
//			double genWeight = gen.getWeight();
//			
//			if(!gen.isAboveTotalCapacity() && genWeight<valVCW){
//				valVCW=genWeight;
//			}
//		//	System.out.println("Gen = "+gen+" , GenValue: "+genValue + " ,MaxValue: "+maxValue);
//		}
//		return valVCW;
//	}
//	
	
	// non preference
	public Gen firstAlgorithm(double[] bestGenCriteria){
		Gen gen = null;
		double resultValue = Double.MAX_VALUE;		
		
		for(int i = 0; i < bestGenCriteria.length; i++){
			if(bestGenCriteria[i] == Double.MAX_VALUE || bestGenCriteria[i] == Double.MIN_VALUE ){
				return gen;
			}
		}
		
		for(int i = 0; i < POPULATION; i++){
			Gen genTemp = genList.get(i);
			double resultValueTemp = Math.abs(genTemp.getValue() - bestGenCriteria[0])
									+ Math.abs(genTemp.getWeight() - bestGenCriteria[1])
									+ Math.abs(genTemp.getCapacity() - bestGenCriteria[2]);
			if(resultValueTemp < resultValue){
				resultValue = resultValueTemp;
				gen = genTemp;
			}
		}
		return gen;
	}
	
//	public List<Gen> computeValueResult(List<Gen> workingList,double epsilon,double bestGenCriteria){
//		Gen gen = null;
//		double resultValue=Double.MIN_VALUE;
//		List<Gen> result = new ArrayList<Gen>();
//		System.out.println("Obliczam best value: ");
//		double bestValueForCriteria=selectAndReturnGenWithBestValue();
//		System.out.println("Obliczone best value to: "+bestValueForCriteria);
//		for(int i=0;i<workingList.size();i++){
//			gen = genList.get(i);
//			double genValue = gen.getValue();
//		 if (genValue >= (1 - epsilon) * bestGenCriteria) {
//			resultValue = genValue;
//			result.add(genList.get(i));
//			System.out.println("SIZE: "+result.size());
//			}		
//		}
//		return result;
//	}
	
	public List<Gen> computeValueResult(List<Gen> workingList, double epsilon, double bestGenCriteria){
		Gen gen = null;
		double resultValue = Double.MIN_VALUE;
		double resultValueTemp = Double.MIN_VALUE;
		double bestValueForCriteria = 0;
		List<Gen> result = new ArrayList<Gen>();
		System.out.println("Obliczam best Value: ");
		
		if(bestGenCriteria == 0.0){
			for(int i = 0; i < workingList.size(); i++){
				Gen gen2 = workingList.get(i);
				double genValue2 = gen2.getValue();
				
				if(!gen2.isAboveTotalCapacity() && genValue2 > resultValueTemp){
					resultValueTemp = genValue2;
				}
				System.out.println("Gen = " + gen2 + ", GenValue: " + genValue2 + ", MaxValue: " + resultValueTemp);
			}
			bestValueForCriteria = resultValueTemp;
		}else{
			bestValueForCriteria = bestGenCriteria;
		}
		System.out.println("Obliczone best Value to: " + bestValueForCriteria);
		
		for(int i = 0; i < workingList.size(); i++){
			gen = workingList.get(i);
			double genValue = gen.getValue();
			System.out.println("Gen = " + gen + ", GenValue: " + genValue + ", <= " + (1 + epsilon) * bestValueForCriteria);
			if (genValue >= (1 - epsilon) * bestValueForCriteria) {
				resultValue = genValue;
				System.out.println("Zapisuje " + gen);
				result.add(workingList.get(i));
				System.out.println("SIZE: " + result.size());
			}		
		}
		return result;
	}
	
	public List<Gen> computeWeightResult(List<Gen> workingList, double epsilon, double bestGenCriteria){
		Gen gen = null;
		double resultWeight = Double.MAX_VALUE;
		double resultWeightTemp = Double.MAX_VALUE;
		double bestWeightForCriteria = 0;
		List<Gen> result = new ArrayList<Gen>();
		System.out.println("Obliczam best Weight: ");
		
		if(bestGenCriteria == 0.0){
			for(int i = 0; i < workingList.size(); i++){
				Gen gen2 = workingList.get(i);
				double genWeight2 = gen2.getWeight();
				
				if(!gen2.isAboveTotalCapacity() && genWeight2 < resultWeightTemp){
					resultWeightTemp = genWeight2;
				}
				System.out.println("Gen = " + gen2 + ", GenWeight: " + genWeight2 + ", MaxWeight: " + resultWeightTemp);
			}
			bestWeightForCriteria = resultWeightTemp;
		}else{
			bestWeightForCriteria = bestGenCriteria;
		}
		System.out.println("Obliczone best Weight to: "+bestWeightForCriteria);
		
		for(int i = 0; i < workingList.size();i++){
			gen = workingList.get(i);
			double genWeight = gen.getWeight();
			System.out.println("Gen = " + gen + ", GenValue: " + genWeight + ", <= " + (1 + epsilon) * bestWeightForCriteria);
			if (genWeight <= (1 + epsilon) * bestWeightForCriteria) {
				resultWeight = genWeight;
				System.out.println("Zapisuje " + gen);
				result.add(workingList.get(i));
				System.out.println("SIZE: " + result.size());
			}		
		}
		return result;
	}
	public List<Gen> computeCapacityResult(List<Gen> workingList, double epsilon, double bestGenCriteria){
		Gen gen = null;
		double resultCapacity = Double.MAX_VALUE;
		double resultCapacityTemp = Double.MAX_VALUE;
		double bestCapacityForCriteria = 0;
		List<Gen> result = new ArrayList<Gen>();
		System.out.println("Obliczam best Capacity: ");
		
		if(bestGenCriteria == 0.0){
			for(int i = 0; i < workingList.size(); i++){
				Gen gen2 = workingList.get(i);
				double genCapacity2 = gen2.getCapacity();
				
				if(!gen2.isAboveTotalCapacity() && genCapacity2 < resultCapacityTemp){
					resultCapacityTemp = genCapacity2;
				}
				System.out.println("Gen = " + gen2 + ", GenCapacity: " + genCapacity2 + ", MaxCapacity: " + resultCapacityTemp);
			}
			bestCapacityForCriteria = resultCapacityTemp;
		}else{
			bestCapacityForCriteria = bestGenCriteria;
		}
		System.out.println("Obliczone best Capacity to: " + bestCapacityForCriteria);
		
		for(int i = 0; i < workingList.size(); i++){
			gen = workingList.get(i);
			double genCapacity = gen.getCapacity();
			System.out.println("Gen = " + gen + ", GenCapacity: " + genCapacity + ", <= " + (1 + epsilon) * bestCapacityForCriteria);
			if (genCapacity <= (1 + epsilon) * bestCapacityForCriteria) {
				resultCapacity = genCapacity;
				System.out.println("Zapisuje "+ gen);
				result.add(workingList.get(i));
				System.out.println("SIZE: " + result.size());
			}		
		}
		return result;
	}
	
	public List<Gen> secondAlgorithm(int combination, double [] bestGenCriteria){
    	Gen gen = null;
		List<Gen> stepFirst = new ArrayList<Gen>();
		List<Gen> stepSecond = new ArrayList<Gen>();
		List<Gen> stepThird = new ArrayList<Gen>();
		double epsilon = 0.1;
		
		switch (combination){
        case 1:
        	System.out.println("Wybralem 1:");
        	stepFirst = computeValueResult(genList, epsilon, bestGenCriteria[0]);
        	stepSecond = computeWeightResult(stepFirst, epsilon, 0.0);
        	stepThird = computeCapacityResult(stepSecond, epsilon, 0.0);
        	break;
        case 2:
        	System.out.println("Wybralem 2:");
        	stepFirst = computeValueResult(genList, epsilon, bestGenCriteria[0]);
        	stepSecond = computeCapacityResult(stepFirst, epsilon, 0.0);
        	stepThird = computeWeightResult(stepSecond, epsilon, 0.0);
        	break;
        case 3:
        	System.out.println("Wybralem 3:");
        	stepFirst = computeWeightResult(genList, epsilon, bestGenCriteria[1]);
        	stepSecond = computeCapacityResult(stepFirst, epsilon, 0.0);
        	stepThird = computeValueResult(stepSecond, epsilon, 0.0);
        	break;
        case 4:
        	System.out.println("Wybralem 4:");
        	stepFirst = computeWeightResult(genList, epsilon, bestGenCriteria[1]);
        	stepSecond = computeValueResult(stepFirst, epsilon, 0.0);
        	stepThird = computeCapacityResult(stepSecond, epsilon, 0.0);
        	break;
        case 5:
        	System.out.println("Wybralem 5:");
        	stepFirst = computeCapacityResult(genList, epsilon, bestGenCriteria[2]);
        	stepSecond = computeValueResult(stepFirst, epsilon, 0.0);
        	stepThird = computeWeightResult(stepSecond, epsilon, 0.0);
        	break;
        case 6:
        	System.out.println("Wybralem 6:");
        	stepFirst = computeCapacityResult(genList, epsilon, bestGenCriteria[2]);
        	stepSecond = computeWeightResult(stepFirst, epsilon, 0.0);
        	stepThird = computeValueResult(stepSecond, epsilon, 0.0);
        	break;
        }
		
		//System.out.println("Obliczone best value to: "+bestWeightForSecondElementINCriteria);
		
//		System.out.println("Odpowied� dla etapu 2: ");
//		if(bestGenCriteria[1]==Double.MAX_VALUE || bestGenCriteria[1]==Double.MIN_VALUE ){
//			return null;
//		}
//		for(int i=0;i<stepFirst.size();i++){
//			count2++;
//			Gen genTemp = stepFirst.get(i);
//			double resultWeightTemp= Math.abs(genTemp.getWeight()-stepFirst.get(i).getWeight());
//			System.out.println("take: "+stepFirst.get(i).getWeight());
//			if(resultWeightTemp < resultValue){
//				resultValue= resultWeightTemp;
//				gen=genTemp;
//			}
//			System.out.println("Gen = "+gen+" , GenValue: "+gen.getValue() + " ,MaxValue: "+resultValue);
//		}
//		System.out.println("Count1 : "+count1+" Count2 : "+count2);
//		stepSecond.add(gen);
		return stepThird;
	}	
}
