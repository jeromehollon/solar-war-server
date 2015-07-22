package com.hollonconsulting.solarwars.server.entity;

import org.hibernate.metamodel.relational.Identifier;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    public abstract Integer getId();
}
