package org.stock.dao.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DaoFactory {
	private SessionFactory factory;

	public DaoFactory() {
		System.out.println("[ + ] - Initializing Dao Instance");
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void close() {
		factory.close();
	}

}
