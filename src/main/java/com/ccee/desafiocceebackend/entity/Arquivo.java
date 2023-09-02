package com.ccee.desafiocceebackend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoAgente;
    private String data;
    private String regiao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoAgente() {
        return codigoAgente;
    }

    public void setCodigoAgente(String codigoAgente) {
        this.codigoAgente = codigoAgente;
    }


    public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

}
