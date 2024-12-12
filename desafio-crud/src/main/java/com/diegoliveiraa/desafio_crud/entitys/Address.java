package com.diegoliveiraa.desafio_crud.entitys;

public class Address {

    private String cep;
    private String localidade;


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public String toString() {
        return "Address{cep='" + cep + "', localidade='" + localidade + "'}";
    }
}
