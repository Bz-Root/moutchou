package org.metier;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.Calcul;

public class CalculImpl implements CalculImplemente{

	private HashMap<String,String> erreurs = new HashMap<String, String>();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private final static String CHAMP_SAISI = "saisi";
	private final static String PREMIER_TERME = "premier";
	private final static String DEUSIEME_TERME = "deusieme";
	private final static String OPERATOR = "operator";
	private final static String CHAMPERROR= "error";
	
	private String Expression;
	private Calcul calcul;
	
	public CalculImpl(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		Expression = request.getParameter(CHAMP_SAISI);
		double a= 0;
		double b = 0;
		char op = '\0';
		if(Expression != null && !Expression.equals("")) {
			try {
				a = getPremierTerme();
			}catch (InvalidEntry e) {
				erreurs.put(PREMIER_TERME,"Premier terme doit être un entier");
			}
			
			try {
				b = getDeusiemeTerme();System.out.println(b);
			}catch (InvalidEntry e) {
				erreurs.put(DEUSIEME_TERME,"Deusieme terme doit être doit être un entier");
			}
			
			try {
				op = getOperator();
			} catch (InvalidEntry e) {
				erreurs.put(OPERATOR,"Operator invalid");
			}
			if(op == '\\' && b == 0) {
				erreurs.put("error", "Division par 0");
			}else {
				if(erreurs.size() == 0) {	
					calcul = new Calcul(a, b,op);
				}
			}
			
		}else {
			erreurs.put(CHAMPERROR,"Formulaire invalide");
		}
		
	}
	@Override
	public double getPremierTerme() throws InvalidEntry {
		try {
			char operator = '1';
			for (Operators op : Operators.values()) {
				if(Expression.indexOf(op.getOperator()) != -1) {
					operator = op.getOperator().charAt(0);
				}
			}
			if(operator == '1') {
				throw new InvalidEntry();
			}
			String entier = Expression.substring(0,Expression.indexOf(operator + "")).trim();
			return Double.parseDouble(entier);
		}catch (Exception e) {
			throw new InvalidEntry();
		}
	}

	@Override
	public double getDeusiemeTerme() throws InvalidEntry {
		try {
			char operator = '1';
			for (Operators op : Operators.values()) {
				if(Expression.indexOf(op.getOperator()) != -1) {
					operator = op.getOperator().charAt(0);
				}
			}
			if(operator == '1') {
				throw new InvalidEntry();
			}
			String entier = Expression.substring(Expression.indexOf(operator+"") + 1,Expression.length()).trim();
			return Double.parseDouble(entier);
		}catch (Exception e) {
			throw new InvalidEntry();
		}
	}

	@Override
	public char getOperator() throws InvalidEntry {
		char operator ='1';
		for (Operators op : Operators.values()) {
			if(Expression.indexOf(op.getOperator()) != -1) {
				operator = op.getOperator().charAt(0);
			}
		}
		if(operator == '1') {
			throw  new InvalidEntry();
		}else {
			return operator;
		}
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HashMap<String, String> getErreurs() {
		return erreurs;
	}
	public String getExpression() {
		return Expression;
	}
	public Calcul getCalcul() {
		return calcul;
	}
	public void setErreurs(HashMap<String, String> erreurs) {
		this.erreurs = erreurs;
	}
	public void setExpression(String expression) {
		Expression = expression;
	}
	public void setCalcul(Calcul calcul) {
		this.calcul = calcul;
	}
	
	
}
