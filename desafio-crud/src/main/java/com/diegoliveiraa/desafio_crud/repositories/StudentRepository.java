package com.diegoliveiraa.desafio_crud.repositories;

import com.diegoliveiraa.desafio_crud.entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
