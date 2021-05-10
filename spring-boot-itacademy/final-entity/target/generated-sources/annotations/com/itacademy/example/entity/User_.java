package com.itacademy.example.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.itacademy.example.entity.AEntity_ {

	public static volatile ListAttribute<User, Pet> pets;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, Integer> salary;
	public static volatile SingularAttribute<User, String> username;

	public static final String PETS = "pets";
	public static final String PASSWORD = "password";
	public static final String ROLES = "roles";
	public static final String SALARY = "salary";
	public static final String USERNAME = "username";

}

