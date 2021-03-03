package org.metier;

public enum Operators {
	ADDITION("+"),SOUSTRACTION("-"),MULTIPLICATION("*"),DIVISION("/");
	
	private String operator;
	private Operators(String operator) {
		this.operator = operator;
	}
	public String getOperator() {
		return operator;
	}
}
