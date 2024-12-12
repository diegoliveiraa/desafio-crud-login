package com.diegoliveiraa.desafio_crud.services;

import com.diegoliveiraa.desafio_crud.entitys.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface CepService {

    @GetMapping("{cep}/json")
    Address findAdressByCep(@PathVariable("cep") String cep);
}
