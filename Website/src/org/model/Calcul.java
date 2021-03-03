package org.model;

import java.io.Serializable;

public class Calcul implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double a,b;
	private char op;
	
	
	
	public Calcul(double a, double b, char op) {
		super();
		this.a = a;
		this.b = b;
		this.op = op;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public Calcul(double a, double b) {
		super();
		this.a = a;
		this.b = b;
	}
	public Calcul() {
		
	}
	public double operation() {
		switch(op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		default:
			return 0;
		}
	}
	
}
