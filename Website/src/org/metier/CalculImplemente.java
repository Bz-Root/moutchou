package org.metier;

import java.util.HashMap;

import org.model.Calcul;

public interface CalculImplemente {
	public double getPremierTerme() throws InvalidEntry;
	public double getDeusiemeTerme() throws InvalidEntry;
	public char getOperator() throws InvalidEntry;
	public Calcul getCalcul();
	public HashMap<String, String> getErreurs();
}
