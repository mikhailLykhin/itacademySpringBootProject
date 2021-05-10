package com.itacademy.example.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pet.class)
public abstract class Pet_ extends com.itacademy.example.entity.AEntity_ {

	public static volatile SingularAttribute<Pet, String> nickName;
	public static volatile SingularAttribute<Pet, User> user;

	public static final String NICK_NAME = "nickName";
	public static final String USER = "user";

}

