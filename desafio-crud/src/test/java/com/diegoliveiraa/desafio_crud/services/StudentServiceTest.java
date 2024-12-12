package com.diegoliveiraa.desafio_crud.services;

import com.diegoliveiraa.desafio_crud.dtos.StudentDTO;
import com.diegoliveiraa.desafio_crud.entitys.Address;
import com.diegoliveiraa.desafio_crud.entitys.Student;
import com.diegoliveiraa.desafio_crud.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CepService cepService;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent_Success() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1998-06-15");

        StudentDTO studentDTO = new StudentDTO(
                1L, "John Doe", birthday, "12345678", null, "Computer Science");

        Address mockAddress = new Address();
        mockAddress.setCep("12345678");
        mockAddress.setLocalidade("TestCity");

        when(cepService.findAdressByCep("12345678")).thenReturn(mockAddress);

        Student mockStudent = new Student(studentDTO);
        mockStudent.setBirthday(birthday);

        when(studentRepository.save(any(Student.class))).thenReturn(mockStudent);


        Student result = studentService.createStudent(studentDTO);


        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("TestCity", result.getCity());
        assertEquals(birthday, result.getBirthday());


        verify(cepService, times(1)).findAdressByCep("12345678");
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testCreateStudent_AddressNotFound() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1998-06-15"); // Converte a data para Date

        StudentDTO studentDTO = new StudentDTO(
                1L, "John Doe", birthday, "00000000", null, "Computer Science");

        when(cepService.findAdressByCep("00000000")).thenReturn(null);


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentService.createStudent(studentDTO);
        });

        assertEquals("Cidade não encontrada para o CEP 00000000", exception.getMessage());


        verify(studentRepository, never()).save(any(Student.class));
    }
}
