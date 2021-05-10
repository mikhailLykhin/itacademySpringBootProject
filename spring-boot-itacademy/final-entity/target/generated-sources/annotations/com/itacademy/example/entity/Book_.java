package com.itacademy.example.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ extends com.itacademy.example.entity.AEntity_ {

	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, String> isbn;
	public static volatile SingularAttribute<Book, String> name;
	public static volatile SingularAttribute<Book, String> description;
	public static volatile SingularAttribute<Book, String> picture;

	public static final String AUTHOR = "author";
	public static final String ISBN = "isbn";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PICTURE = "picture";

}

