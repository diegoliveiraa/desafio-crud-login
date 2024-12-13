package com.diegoliveiraa.desafio_crud.services;

import com.diegoliveiraa.desafio_crud.dtos.StudentDTO;
import com.diegoliveiraa.desafio_crud.entitys.Address;
import com.diegoliveiraa.desafio_crud.entitys.Student;
import com.diegoliveiraa.desafio_crud.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final CepService cepService;

    public StudentService(StudentRepository repository, CepService cepService) {
        this.repository = repository;
        this.cepService = cepService;
    }

    public Student createStudent(StudentDTO createData) {
        Date birthday = formatBirthday(createData.birthday());

        Address address = this.cepService.findAdressByCep(createData.cep());
        System.out.println("Resposta do ViaCEP: " + address);

        if (address == null || address.getLocalidade() == null) {
            throw new RuntimeException("Cidade não encontrada para o CEP " + createData.cep());
        }

        Student newStudent = new Student(createData);
        newStudent.setBirthday(birthday);
        newStudent.setCep(address.getCep());
        newStudent.setCity(address.getLocalidade());

        this.repository.save(newStudent);
        return newStudent;
    }


    private Date formatBirthday(Date birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(birthday);
        return parseDate(formattedDate);
    }


    private Date parseDate(String formattedDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(formattedDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido: " + formattedDate);
        }
    }

    public List<Student> getAllStudent() {
        return this.repository.findAll();
    }

    public Student updateStudent(StudentDTO updateData) {
        Student student = this.repository.findById(updateData.id())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(updateData.name());
        student.setBirthday(updateData.birthday());
        student.setCourse(updateData.course());

        Address address = this.cepService.findAdressByCep(updateData.cep());
        System.out.println("Resposta do ViaCEP (update): " + address);

        if (address == null || address.getLocalidade() == null) {
            throw new RuntimeException("Cidade não encontrada para o CEP " + updateData.cep());
        }

        student.setCep(address.getCep());
        student.setCity(address.getLocalidade());

        this.repository.save(student);
        return student;
    }


    public Student deleteStudent(StudentDTO deleteData) {
        Student student = this.repository.findById(deleteData.id())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        this.repository.delete(student);
        return student;
    }
}
