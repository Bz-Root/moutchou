package org.stock.controller.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stock.controller.servlets.session.Connexion;
import org.stock.model.Personne;

@WebFilter("/*")
public class SessionFilter implements Filter {

	private ArrayList<String> listofurl = new ArrayList<String>();
	private String adminurl = "/admin";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		listofurl.add("/inc");
		listofurl.add("/connexion");
		listofurl.add("/inscrires");
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String requesturl = req.getServletPath();
		boolean test = false;
		for (String string : listofurl) {
			if (requesturl.startsWith(string))
				test = true;
		}
		if (!test) {
			Personne personne = (Personne) req.getSession().getAttribute(Connexion.ATT_SESSION);
			if (personne == null) {
				resp.sendRedirect(request.getServletContext().getContextPath() + "/connexion");
			} else {
				if (requesturl.startsWith(adminurl)) {
					if (personne.getRole().equals("admin")) {
						chain.doFilter(request, response);
					} else {
						resp.getWriter().println("Vous n'avez pas le droit");
					}
				} else {
					chain.doFilter(request, response);
				}
			}
		} else {
			Personne personne = (Personne)req.getSession().getAttribute(Connexion.ATT_SESSION);
			/*
			 * J'ai refait cette instruction pour jouer sur la contrainte temporelle
			 * */
			
			if(!(personne == null || requesturl.equals("/inc"))) {
				resp.sendRedirect(req.getServletContext().getContextPath()  + "/consultation");
			}else {
				chain.doFilter(request, response);
			}
		}

	}

}
