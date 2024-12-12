package com.diegoliveiraa.desafio_crud.controllers;

import com.diegoliveiraa.desafio_crud.dtos.StudentDTO;
import com.diegoliveiraa.desafio_crud.entitys.Student;
import com.diegoliveiraa.desafio_crud.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudent();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/new")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "new-student";
    }

    @PostMapping
    public String createStudent(@ModelAttribute StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getAllStudent().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute StudentDTO studentDTO) {
        studentDTO = new StudentDTO(id, studentDTO.name(), studentDTO.birthday(), studentDTO.cep(), studentDTO.city(), studentDTO.course());
        studentService.updateStudent(studentDTO);
        return "redirect:/students";
    }


    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(new StudentDTO(id, null, null, null, null, null));
        return "redirect:/students";
    }
}



/*public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO createData){
        Student newStudent = service.createStudent(createData);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = this.service.getAllStudent();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping
    public  ResponseEntity<Student> updateStudent(@RequestBody StudentDTO updateData){
        Student updateStudent = this.service.updateStudent(updateData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Student> deleteStudent(@RequestBody StudentDTO deleteData){
        Student deleteStudent = this.service.deleteStudent(deleteData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}*/
