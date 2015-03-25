package com.search.test.javacore.common;

import org.junit.Test;

public class TestEnum {

	@Test
	public void test1() {
		float total = 0;
//		total += FruitPrice.Apple.getUnitPrice() * 6;
		for(FruitPrice fp : FruitPrice.values()){
			float tmpPrice = fp.getUnitPrice() * 6;
			System.out.println(fp.name() + ":" + tmpPrice);
			total += tmpPrice;
		}
		System.out.println("total=" + total);
	}
	
	public enum FruitPrice{
		Apple(8),
		Peach(6),
		Plum(7);
		
		private final float price;
		FruitPrice(float price){
			this.price = price;
		}
		
		public float getUnitPrice(){
			return this.price;
		}
	}
	///////////////////////////////////////////////////
	
	@Test
	public void test2(){
		double x = 10;
		double y = 5;
		for(Operation op : Operation.values()){
			double z = op.apply(x, y);
			System.out.printf("%f %s %f = %f%n",x,op,y,z);
		}
	}
	
	public enum Operation{
		Plus("+"){
			double apply(double x, double y){ return x + y;}
		},
		Minus("-"){
			double apply(double x, double y){ return x - y;}
		},
		Multiply("*"){
			double apply(double x, double y){ return x * y;}
		},
		Divide("/"){
			double apply(double x, double y){ return x / y;}
		};
		
		private final String symble;
		Operation(String symble){
			this.symble = symble;
		}
		
		abstract double apply(double x, double y);
	}

}
