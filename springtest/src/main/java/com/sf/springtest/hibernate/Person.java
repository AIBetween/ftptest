package com.sf.springtest.hibernate;

import org.hibernate.annotations.Entity;

import javax.persistence.*;

/**
 * 描述:
 * <p>
 * Created by 828477[JAX] on 2016/6/27 18:39.
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;
}
