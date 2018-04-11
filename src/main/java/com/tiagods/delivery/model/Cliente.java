package com.tiagods.delivery.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cliente_type")
public abstract class Cliente extends Pessoa implements AbstractEntity,Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	public Number getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return super.getNome();
	}
}
