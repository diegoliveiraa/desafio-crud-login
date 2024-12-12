package com.diegoliveiraa.desafio_crud.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record StudentDTO(Long id, String name,@DateTimeFormat(pattern = "yyyy-MM-dd")
                        Date birthday, String cep, String city, String course) {
}
